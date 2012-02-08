OPENREST_COLORSCHEME_THEME_LIGHT = "light";
OPENREST_COLORSCHEME_THEME_DARK = "dark";

function ColorSchemeFromObj(data)
{
    return new ColorScheme(data.theme, data.background, data.font, data.border, data.imageBackground, 
            data.imageBorder, data.buttonFont, data.buttonUp, data.buttonDown, data.buttonOver, data.categoryFont, 
            data.categoryUp, data.categoryDown, data.categoryOver);
}

function ColorScheme(theme, background, font, border, imageBackground, imageBorder,
        buttonFont, buttonUp, buttonDown, buttonOver, categoryFont, categoryUp, categoryDown, categoryOver) 
{
    this.theme = theme;
    this.background = background;
    this.font = font;
    this.border = border;
    this.imageBackground = imageBackground;
    this.imageBorder = imageBorder;
    this.buttonFont = buttonFont;
    this.buttonUp = buttonUp;
    this.buttonDown = buttonDown;
    this.buttonOver = buttonOver;
    this.categoryFont = categoryFont;
    this.categoryUp = categoryUp;
    this.categoryDown = categoryDown;
    this.categoryOver = categoryOver;
}

ColorScheme.prototype.toOpenRestObj = function()
{
    var ret = {};

    if (this.theme) ret['theme'] = this.theme;
    if (this.background) ret['background'] = this.background;
    if (this.font) ret['font'] = this.font;
    if (this.border) ret['border'] = this.border;
    if (this.imageBackground) ret['imageBackground'] = this.imageBackground;
    if (this.imageBorder) ret['imageBorder'] = this.imageBorder;
    if (this.buttonFont) ret['buttonFont'] = this.buttonFont;
    if (this.buttonUp) ret['buttonUp'] = this.buttonUp;
    if (this.buttonDown) ret['buttonDown'] = this.buttonDown;
    if (this.buttonOver) ret['buttonOver'] = this.buttonOver;
    if (this.categoryFont) ret['categoryFont'] = this.categoryFont;
    if (this.categoryUp) ret['categoryUp'] = this.categoryUp;
    if (this.categoryDown) ret['categoryDown'] = this.categoryDown;
    if (this.categoryOver) ret['categoryOver'] = this.categoryOver;

    return ret;
}
