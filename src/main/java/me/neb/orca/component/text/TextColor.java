package me.neb.orca.component.text;

public record TextColor(String hex) {
    public static final TextColor EMPTY = new TextColor("");

    public static final TextColor BLACK = new TextColor("#000000");
    public static final TextColor DARK_BLUE = new TextColor("#0000AA");
    public static final TextColor DARK_GREEN = new TextColor("#00AA00");
    public static final TextColor DARK_AQUA = new TextColor("#00AAAA");
    public static final TextColor DARK_RED = new TextColor("#AA0000");
    public static final TextColor DARK_PURPLE = new TextColor("#AA00AA");
    public static final TextColor GOLD = new TextColor("#FFAA00");
    public static final TextColor GRAY = new TextColor("#AAAAAA");
    public static final TextColor DARK_GRAY = new TextColor("#555555");
    public static final TextColor BLUE = new TextColor("#5555FF");
    public static final TextColor GREEN = new TextColor("#55FF55");
    public static final TextColor AQUA = new TextColor("#55FFFF");
    public static final TextColor RED = new TextColor("#FF5555");
    public static final TextColor LIGHT_PURPLE = new TextColor("#FF55FF");
    public static final TextColor YELLOW = new TextColor("#FFFF55");
    public static final TextColor WHITE = new TextColor("#FFFFFF");

    // https://developer.mozilla.org/en-US/docs/Web/CSS/named-color
    public static final TextColor CSS_ALICEBLUE = new TextColor("#F0F8FF");
    public static final TextColor CSS_ANTIQUEWHITE = new TextColor("#FAEBD7");
    public static final TextColor CSS_AQUAMARINE = new TextColor("#7FFFD4");
    public static final TextColor CSS_AZURE = new TextColor("#F0FFFF");
    public static final TextColor CSS_BEIGE = new TextColor("#F5F5DC");
    public static final TextColor CSS_BISQUE = new TextColor("#FFE4C4");
    public static final TextColor CSS_BLANCHEDALMOND = new TextColor("#FFEBCD");
    public static final TextColor CSS_BLUEVIOLET = new TextColor("#8A2BE2");
    public static final TextColor CSS_BROWN = new TextColor("#A52A2A");
    public static final TextColor CSS_BURLYWOOD = new TextColor("#DEB887");
    public static final TextColor CSS_CADETBLUE = new TextColor("#5F9EA0");
    public static final TextColor CSS_CHARTREUSE = new TextColor("#7FFF00");
    public static final TextColor CSS_CHOCOLATE = new TextColor("#D2691E");
    public static final TextColor CSS_CORAL = new TextColor("#FF7F50");
    public static final TextColor CSS_CORNFLOWERBLUE = new TextColor("#6495ED");
    public static final TextColor CSS_CORNSILK = new TextColor("#FFF8DC");
    public static final TextColor CSS_CRIMSON = new TextColor("#DC143C");
    public static final TextColor CSS_DARKCYAN = new TextColor("#008B8B");
    public static final TextColor CSS_DARKGOLDENROD = new TextColor("#B8860B");
    public static final TextColor CSS_DARKKHAKI = new TextColor("#BDB76B");
    public static final TextColor CSS_DARKMAGENTA = new TextColor("#8B008B");
    public static final TextColor CSS_DARKOLIVEGREEN = new TextColor("#556B2F");
    public static final TextColor CSS_DARKORANGE = new TextColor("#FF8C00");
    public static final TextColor CSS_DARKORCHID = new TextColor("#9932CC");
    public static final TextColor CSS_DARKSALMON = new TextColor("#E9967A");
    public static final TextColor CSS_DARKSEAGREEN = new TextColor("#8FBC8F");
    public static final TextColor CSS_DARKSLATEBLUE = new TextColor("#483D8B");
    public static final TextColor CSS_DARKSLATEGRAY = new TextColor("#2F4F4F");
    public static final TextColor CSS_DARKTURQUOISE = new TextColor("#00CED1");
    public static final TextColor CSS_DARKVIOLET = new TextColor("#9400D3");
    public static final TextColor CSS_DEEPPINK = new TextColor("#FF1493");
    public static final TextColor CSS_DEEPSKYBLUE = new TextColor("#00BFFF");
    public static final TextColor CSS_DIMGRAY = new TextColor("#696969");
    public static final TextColor CSS_DODGERBLUE = new TextColor("#1E90FF");
    public static final TextColor CSS_FIREBRICK = new TextColor("#B22222");
    public static final TextColor CSS_FLORALWHITE = new TextColor("#FFFAF0");
    public static final TextColor CSS_FORESTGREEN = new TextColor("#228B22");
    public static final TextColor CSS_FUCHSIA = new TextColor("#FF00FF");
    public static final TextColor CSS_GAINSBORO = new TextColor("#DCDCDC");
    public static final TextColor CSS_GHOSTWHITE = new TextColor("#F8F8FF");
    public static final TextColor CSS_GOLDENROD = new TextColor("#DAA520");
    public static final TextColor CSS_GREENYELLOW = new TextColor("#ADFF2F");
    public static final TextColor CSS_HONEYDEW = new TextColor("#F0FFF0");
    public static final TextColor CSS_HOTPINK = new TextColor("#FF69B4");
    public static final TextColor CSS_INDIANRED = new TextColor("#CD5C5C");
    public static final TextColor CSS_INDIGO = new TextColor("#4B0082");
    public static final TextColor CSS_IVORY = new TextColor("#FFFFF0");
    public static final TextColor CSS_KHAKI = new TextColor("#F0E68C");
    public static final TextColor CSS_LAVENDER = new TextColor("#E6E6FA");
    public static final TextColor CSS_LAVENDERBLUSH = new TextColor("#FFF0F5");
    public static final TextColor CSS_LAWNGREEN = new TextColor("#7CFC00");
    public static final TextColor CSS_LEMONCHIFFON = new TextColor("#FFFACD");
    public static final TextColor CSS_LIGHTBLUE = new TextColor("#ADD8E6");
    public static final TextColor CSS_LIGHTCORAL = new TextColor("#F08080");
    public static final TextColor CSS_LIGHTCYAN = new TextColor("#E0FFFF");
    public static final TextColor CSS_LIGHTGOLDENRODYELLOW = new TextColor("#FAFAD2");
    public static final TextColor CSS_LIGHTGREEN = new TextColor("#90EE90");
    public static final TextColor CSS_LIGHTPINK = new TextColor("#FFB6C1");
    public static final TextColor CSS_LIGHTSALMON = new TextColor("#FFA07A");
    public static final TextColor CSS_LIGHTSEAGREEN = new TextColor("#20B2AA");
    public static final TextColor CSS_LIGHTSKYBLUE = new TextColor("#87CEFA");
    public static final TextColor CSS_LIGHTSLATEGRAY = new TextColor("#778899");
    public static final TextColor CSS_LIGHTSTEELBLUE = new TextColor("#B0C4DE");
    public static final TextColor CSS_LIGHTYELLOW = new TextColor("#FFFFE0");
    public static final TextColor CSS_LIME = new TextColor("#00FF00");
    public static final TextColor CSS_LIMEGREEN = new TextColor("#32CD32");
    public static final TextColor CSS_LINEN = new TextColor("#FAF0E6");
    public static final TextColor CSS_MAGENTA = new TextColor("#FF00FF");
    public static final TextColor CSS_MAROON = new TextColor("#800000");
    public static final TextColor CSS_MEDIUMAQUAMARINE = new TextColor("#66CDAA");
    public static final TextColor CSS_MEDIUMBLUE = new TextColor("#0000CD");
    public static final TextColor CSS_MEDIUMORCHID = new TextColor("#BA55D3");
    public static final TextColor CSS_MEDIUMPURPLE = new TextColor("#9370DB");
    public static final TextColor CSS_MEDIUMSEAGREEN = new TextColor("#3CB371");
    public static final TextColor CSS_MEDIUMSLATEBLUE = new TextColor("#7B68EE");
    public static final TextColor CSS_MEDIUMSPRINGGREEN = new TextColor("#00FA9A");
    public static final TextColor CSS_MEDIUMTURQUOISE = new TextColor("#48D1CC");
    public static final TextColor CSS_MEDIUMVIOLETRED = new TextColor("#C71585");
    public static final TextColor CSS_MIDNIGHTBLUE = new TextColor("#191970");
    public static final TextColor CSS_MINTCREAM = new TextColor("#F5FFFA");
    public static final TextColor CSS_MISTYROSE = new TextColor("#FFE4E1");
    public static final TextColor CSS_MOCCASIN = new TextColor("#FFE4B5");
    public static final TextColor CSS_NAVAJOWHITE = new TextColor("#FFDEAD");
    public static final TextColor CSS_NAVY = new TextColor("#000080");
    public static final TextColor CSS_OLDLACE = new TextColor("#FDF5E6");
    public static final TextColor CSS_OLIVE = new TextColor("#808000");
    public static final TextColor CSS_OLIVEDRAB = new TextColor("#6B8E23");
    public static final TextColor CSS_ORANGE = new TextColor("#FFA500");
    public static final TextColor CSS_ORANGERED = new TextColor("#FF4500");
    public static final TextColor CSS_ORCHID = new TextColor("#DA70D6");
    public static final TextColor CSS_PALEGOLDENROD = new TextColor("#EEE8AA");
    public static final TextColor CSS_PALEGREEN = new TextColor("#98FB98");
    public static final TextColor CSS_PALETURQUOISE = new TextColor("#AFEEEE");
    public static final TextColor CSS_PALEVIOLETRED = new TextColor("#DB7093");
    public static final TextColor CSS_PAPAYAWHIP = new TextColor("#FFEFD5");
    public static final TextColor CSS_PEACHPUFF = new TextColor("#FFDAB9");
    public static final TextColor CSS_PERU = new TextColor("#CD853F");
    public static final TextColor CSS_PINK = new TextColor("#FFC0CB");
    public static final TextColor CSS_PLUM = new TextColor("#DDA0DD");
    public static final TextColor CSS_POWDERBLUE = new TextColor("#B0E0E6");
    public static final TextColor CSS_PURPLE = new TextColor("#800080");
    public static final TextColor CSS_REBECCAPURPLE = new TextColor("#663399");
    public static final TextColor CSS_ROSYBROWN = new TextColor("#BC8F8F");
    public static final TextColor CSS_ROYALBLUE = new TextColor("#4169E1");
    public static final TextColor CSS_SADDLEBROWN = new TextColor("#8B4513");
    public static final TextColor CSS_SALMON = new TextColor("#FA8072");
    public static final TextColor CSS_SANDYBROWN = new TextColor("#F4A460");
    public static final TextColor CSS_SEAGREEN = new TextColor("#2E8B57");
    public static final TextColor CSS_SEASHELL = new TextColor("#FFF5EE");
    public static final TextColor CSS_SIENNA = new TextColor("#A0522D");
    public static final TextColor CSS_SILVER = new TextColor("#C0C0C0");
    public static final TextColor CSS_SKYBLUE = new TextColor("#87CEEB");
    public static final TextColor CSS_SLATEBLUE = new TextColor("#6A5ACD");
    public static final TextColor CSS_SLATEGRAY = new TextColor("#708090");
    public static final TextColor CSS_SNOW = new TextColor("#FFFAFA");
    public static final TextColor CSS_SPRINGGREEN = new TextColor("#00FF7F");
    public static final TextColor CSS_STEELBLUE = new TextColor("#4682B4");
    public static final TextColor CSS_TAN = new TextColor("#D2B48C");
    public static final TextColor CSS_TEAL = new TextColor("#008080");
    public static final TextColor CSS_THISTLE = new TextColor("#D8BFD8");
    public static final TextColor CSS_TOMATO = new TextColor("#FF6347");
    public static final TextColor CSS_TURQUOISE = new TextColor("#40E0D0");
    public static final TextColor CSS_VIOLET = new TextColor("#EE82EE");
    public static final TextColor CSS_WHEAT = new TextColor("#F5DEB3");
    public static final TextColor CSS_WHITESMOKE = new TextColor("#F5F5F5");
    public static final TextColor CSS_YELLOWGREEN = new TextColor("#9ACD32");

    public static String[] hexLinearGrad(String hexFrom, String hexTo, int size) {
        int rf = Integer.parseInt(hexFrom.substring(1, 3), 16);
        int gf = Integer.parseInt(hexFrom.substring(3, 5), 16);
        int bf = Integer.parseInt(hexFrom.substring(5, 7), 16);

        int rt = Integer.parseInt(hexTo.substring(1, 3), 16);
        int gt = Integer.parseInt(hexTo.substring(3, 5), 16);
        int bt = Integer.parseInt(hexTo.substring(5, 7), 16);

        String[] colors = new String[size];

        for (int i = 0; i < size; i++) {
            double t = (size == 1) ? 0.0 : (double) i / (size - 1);
            int r = (int) Math.round(rf + t * (rt - rf));
            int g = (int) Math.round(gf + t * (gt - gf));
            int b = (int) Math.round(bf + t * (bt - bf));

            colors[i] = "#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
        }

        return colors;
    }

    public boolean isEmpty() {
        return this == EMPTY || hex.trim().isEmpty();
    }
}
