package Constants;

public class Const {
    public static final int SCREEN_WIDTH = 1010;
    public static final int SCREEN_HEIGHT = 635;
    public static String[] colors = {"#947dfb", "#ffb28f", "#75eab2", "#fa92ba", "#de93ff", "#ffe18a"};
    public static String[] clientMenu = {"Cabinet", "Services", "History", "Bonuses"};
    public static String[] adminMenu = {"Workers", "Services", "Calendar", "Statistics"};
    public static String[] workersMenu = {"Cabinet", "Services", "History", "Bonuses"};
    public static String selectIconStyle = "-fx-effect: dropshadow( three-pass-box , #bb9def, 15 , 0.0 , 0 , 0 );";
    public static String selectButtonStyle = "-fx-background-color: #28386f;\n" +
            "    -fx-text-fill: #c3a6e8;\n" +
            "    -fx-border-width: 0 0 0 4;\n" +
            "    -fx-border-color: #c3a6e8;";
    public static String unselectButton = "-fx-background-color: transparent;\n" +
            "    -fx-alignment: LEFT;\n" +
            "    -fx-padding: 0 0 0 80;\n" +
            "    -fx-text-fill: #34427f;\n" +
            "    -fx-font-size: 12pt;\n" +
            "    -fx-font-family: \"Euclid Flex\";\n" +
            "    -fx-font-weight: 700;";
    public static String CALENDAR_ITEM_STYLE = "-fx-background-color: rgba(24, 38, 81, 0.39);" +
            "-fx-background-radius: 10;";
    public static String CALENDAR_ITEM_SELECTED = "-fx-border-color:  #c3a6e8;\n" +
            "-fx-border-width: 2;\n" +
            "    -fx-border-radius: 10;";
}
