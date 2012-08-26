using System;

namespace com.openrest.v1_1
{
    public class ColorScheme
    {
        public const string THEME_LIGHT = "light";
        public const string THEME_DARK = "dark";

        public ColorScheme(String theme, String background, String font, String border,
            String imageBackground, String imageBorder,
            String buttonFont, String buttonUp, String buttonDown, String buttonOver,
            String categoryFont, String categoryUp, String categoryDown, String categoryOver)
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

        /** Empty constructor required for initialization from JSON-encoded string. */
        public ColorScheme() { }

        public string theme;
        public string background;
        public string font;
        public string border;
        public string imageBackground;
        public string imageBorder;
        public string buttonFont;
        public string buttonUp;
        public string buttonDown;
        public string buttonOver;
        public string categoryFont;
        public string categoryUp;
        public string categoryDown;
        public string categoryOver;
    }
}
