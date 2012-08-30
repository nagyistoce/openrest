using System;
using System.Collections.Generic;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Runtime.Serialization.Formatters;

namespace com.openrest.v1_1
{
    public class RequestJsonConvertor : JsonConverter
    {
        private const string TYPE_PROPERTY = "type";

        public override bool CanConvert(Type objectType)
        {
            return typeof(Request).IsAssignableFrom(objectType);
        }

        public override object ReadJson(JsonReader reader, Type objectType,
                object existingValue, JsonSerializer serializer)
        {
            JObject jObject = JObject.Load(reader);

            Type targetType = GetType(jObject);
            object target = Activator.CreateInstance(targetType);

            serializer.Populate(jObject.CreateReader(), target);

            return target;
        }

        public override void WriteJson(JsonWriter writer, Object value, JsonSerializer serializer)
        {
            JsonSerializer.Create(new JsonSerializerSettings()).Serialize(writer, value);
        }

        private static Type GetType(JObject jObject)
        {
            string typeStr = (string) jObject.Property(TYPE_PROPERTY);
            if (typeStr == null)
            {
                throw new ApplicationException("Missing type property");
            }

            switch (typeStr)
            {
                case GetOrganizationRequest.TYPE:
                    return typeof(GetOrganizationRequest);
                case GetOrganizationFullRequest.TYPE:
                    return typeof(GetOrganizationFullRequest);
                case SubmitOrderRequest.TYPE:
                    return typeof(SubmitOrderRequest);
                case QueryEmailRequest.TYPE:
                    return typeof(QueryEmailRequest);
            }

            throw new ApplicationException(String.Format("Unknown object type: {0}", typeStr));
        }
    }
}
