/**
 * @param minuteOfDay    Integer
 * @param durationMins   Integer
 */
function DailyPeriod(minuteOfDay, durationMins, i18n) {
  this.minuteOfDay = minuteOfDay;
  this.durationMins = durationMins;
  this.i18n = i18n;
}

DailyPeriod.prototype.toString = function() {
    if (!this.i18n.isRtlDir())
    {
        return this.minuteOfDayToString(this.minuteOfDay) + '-' +
            this.minuteOfDayToString(this.minuteOfDay + this.durationMins) +
            ((this.minuteOfDay + this.durationMins < AvailabilityPrinter.DAY) ? ("") : (this.i18n.get("AM")));
    }
    else
    {
        return this.minuteOfDayToString(this.minuteOfDay + this.durationMins) +
            '-' + this.minuteOfDayToString(this.minuteOfDay) +
            ((this.minuteOfDay + this.durationMins < AvailabilityPrinter.DAY) ? ("") : (this.i18n.get("AM")));
    }
}


DailyPeriod.prototype.equals = function(other) {
  if (this === other) {
    return true;
  } else if (typeof(this) != typeof(other)) {
    return false;
  } else if (this.minuteOfDay != other.minuteOfDay) {
    return false;
  } else if (this.durationMins != other.durationMins) {
    return false;
  }
  return true;
}

/**
 * @param array1   List<DailyPeriod>
 * @param array2   List<DailyPeriod>
 * @return Boolean
 */
DailyPeriod.arrayEquals = function(array1, array2) {
  if (array1.length != array2.length) {
    return false;
  }
  
  for (var i = 0; i < array1.length; ++i) {
    if (!array1[i].equals(array2[i])) {
      return false;
    }
  }
  return true;
}

/**
 * @param minuteOfDay   Integer
 * @return String representation. 
 */
DailyPeriod.prototype.minuteOfDayToString = function(minuteOfDay) {
  var hour = Math.floor(minuteOfDay / AvailabilityPrinter.HOUR);
  var minute = minuteOfDay - hour * AvailabilityPrinter.HOUR;
  if (hour >= 24) {
    hour -= 24;
  }
  
  var date = new Date(2000, 0, 1, hour, minute, 0, 0);
  return this.i18n.dateFormat({date:date, selector: "time"});
}

///////////////////////////////////////////////////////////////////////////////

function AvailabilityPrinter(i18n) {this.i18n = i18n;}

/**
 * @param alwaysStr      String representing "always".
 * @param availability   Availability object.
 * @return String representation of the Availability object.
 */
AvailabilityPrinter.prototype.prettyPrint = function(alwaysStr, availability) {
  if (typeof(availability) == "undefined") {
    availability = { weekly: [], exceptions: [] };
  }
  
  var weeklyStr = this.prettyPrintWeekly(availability.weekly);
  var exceptionsStr = this.prettyPrintExceptions(availability.exceptions);
  
  var result = "";
  if (weeklyStr.length != 0) {
    result += weeklyStr;
  } else {
    result += alwaysStr;
  }
  if (exceptionsStr.length != 0) {
    result += ", " + this.i18n.get("Except") + " " + exceptionsStr;
  }
  return result;
}

/**
 * @param List<WeeklyTimeWindow>
 * @return the String representation of the weekly availability.
 */
AvailabilityPrinter.prototype.prettyPrintWeekly = function(weekly) {
  if ((typeof(weekly) == "undefined") || (weekly.length == 0)) {
    return "";
  }
  
  // Handle overlapping last period
  weekly = this.normalize(weekly);

  var dailyPeriods = [];
  for (var day = 0; day < 7; ++day) {
	dailyPeriods.push([]);
  }

  for (var i = 0; i < weekly.length; ++i) {
    var timeWindow = weekly[i];
    if (timeWindow.durationMins >= AvailabilityPrinter.DAY) {
      // Not sure what's the right thing to say here
      return this.i18n.get("AvailabilityUnresolved");
	}
	
	var day = Math.floor(timeWindow.minuteOfWeek / AvailabilityPrinter.DAY);
	var minuteOfDay = timeWindow.minuteOfWeek - day * AvailabilityPrinter.DAY;
	var periods = dailyPeriods[day];
	periods.push(new DailyPeriod(minuteOfDay, timeWindow.durationMins, this.i18n));
  }

  var result = "";

  var seenDays = [false, false, false, false, false, false, false];
  var firstPeriod = true;

  for (var day = 0; day < 7; ++day) {
    if (!seenDays[day]) {
      seenDays[day] = true;
      var periods = dailyPeriods[day];
      if (periods.length != 0) {
        var days = [];
        days.push(day);
        
        for (var otherDay = day + 1; otherDay < 7; ++otherDay) {
          if (!seenDays[otherDay]) {
            var otherPeriods = dailyPeriods[otherDay];
            if (DailyPeriod.arrayEquals(periods, otherPeriods)) {
              days.push(otherDay);
              seenDays[otherDay] = true;
            }
          }
        }
        
        if (!firstPeriod) {
          result += ", ";
        }
        firstPeriod = false;
        
        result += this.daysToString(days);
        result += " " + periods[0];
        for (var i = 1; i < periods.length; ++i) {
          result += ", " + periods[i];
        }
      }
    }
  }
  return result;
}

/**
 * @param List<WeeklyTimeWindow>
 * @return List<WeeklyTimeWindow> where the first time and last time windows are joined if they wrap-around.
 */
AvailabilityPrinter.prototype.normalize = function(weekly) {
  if (weekly.length < 2) {
    return weekly;
  }
 
  var firstWindow = weekly[0];
  if (firstWindow.minuteOfWeek != AvailabilityPrinter.SUNDAY) {
	return weekly;
  }

  var lastWindow = weekly[weekly.length - 1];
  if (lastWindow.minuteOfWeek + lastWindow.durationMins != AvailabilityPrinter.MINUTES_IN_WEEK) {
    return weekly;
  }

  var normalizedWeekly = [];
  for (var i = 1; i < weekly.length - 1; ++i) {
    normalizedWeekly.push(weekly[i]);
  }
  normalizedWeekly.push({ minuteOfWeek: lastWindow.minuteOfWeek, durationMins: lastWindow.durationMins + firstWindow.durationMins });
  return normalizedWeekly;
}

/**
 * @param exceptions   A List<DateTimeWindow>.
 * @return the String representation of the exceptions.
 */
AvailabilityPrinter.prototype.prettyPrintExceptions = function(exceptions) {
  // TODO: implement this
  return "";
}

/**
 * @param day   List<Integer> representing days-of-week. Sunday = 0.
 * @return the days' short String representation.
 */
AvailabilityPrinter.prototype.daysToString = function(days) {
  if (days.length == 7) {
    return this.i18n.get("EveryDayOfWeek");
  }
	
  var result = "";
	
  var sessionStartIndex = 0;
  while (sessionStartIndex < days.length) {
    var sessionStartDay = days[sessionStartIndex];
    var sessionLength = 1;
    while ((sessionStartIndex + sessionLength) < days.length) {
      if (days[sessionStartIndex + sessionLength] != (sessionStartDay + sessionLength)) {
        break;
      }
      ++sessionLength;
    }

    if (sessionStartIndex > 0) {
      result += ",";
    }
    
    switch(sessionLength) {
      case 1:
        result += this.dayToString(days[sessionStartIndex]);
        break;
      case 2:
        result += this.dayToString(days[sessionStartIndex]) + "," + this.dayToString(days[sessionStartIndex+sessionLength-1]);
        break;
      default:
        result += this.dayToString(days[sessionStartIndex]) + "-" + this.dayToString(days[sessionStartIndex+sessionLength-1]);
        break;
    }
    
    sessionStartIndex += sessionLength;
  }
  
  return result;
}

/**
 * @param day   Integer (0-6) representing the day-of-week. Sunday = 0.
 * @return the day's short String representation.
 */
AvailabilityPrinter.prototype.dayToString = function(day) {
  switch(day) {
    case 0: return this.i18n.get("Sunday");
    case 1: return this.i18n.get("Monday");
    case 2: return this.i18n.get("Tuesday");
    case 3: return this.i18n.get("Wednesday");
    case 4: return this.i18n.get("Thursday");
    case 5: return this.i18n.get("Friday");
    case 6: return this.i18n.get("Saturday");
    default: throw new Error("illegal day: " + day);
  }
}

AvailabilityPrinter.HOUR = 60;
AvailabilityPrinter.DAY = AvailabilityPrinter.HOUR * 24;
AvailabilityPrinter.SUNDAY = 0;
AvailabilityPrinter.MONDAY = AvailabilityPrinter.SUNDAY + AvailabilityPrinter.DAY;
AvailabilityPrinter.TUESDAY = AvailabilityPrinter.MONDAY + AvailabilityPrinter.DAY;
AvailabilityPrinter.WEDNESDAY = AvailabilityPrinter.TUESDAY + AvailabilityPrinter.DAY;
AvailabilityPrinter.THURSDAY = AvailabilityPrinter.WEDNESDAY + AvailabilityPrinter.DAY;
AvailabilityPrinter.FRIDAY = AvailabilityPrinter.THURSDAY + AvailabilityPrinter.DAY;
AvailabilityPrinter.SATURDAY = AvailabilityPrinter.FRIDAY + AvailabilityPrinter.DAY;
AvailabilityPrinter.MINUTES_IN_WEEK = AvailabilityPrinter.SATURDAY + AvailabilityPrinter.DAY; 
