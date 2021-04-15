package comp1110.ass2;

public interface Constants {
    // Players substring
    public final char PLAYER_A = 'A';
    public final char PLAYER_B = 'B';
    public final char PLAYER_C = 'C';
    public final char PLAYER_D = 'D';
    public final char[] ALL_PLAYERS = {PLAYER_A, PLAYER_B, PLAYER_C, PLAYER_D};

    // Shared state substring
    public final char FACTORY = 'F';
    public final char CENTER = 'C';
    public final char BAG = 'B';
    public final char DISCARD = 'D';

    // Players state substring
    public final char MOSAIC = 'M';
    public final char STORAGE = 'S';
    public final char FLOOR = 'F';

    // Colors and characters
    public final char BLUE = 'a';
    public final char GREEN = 'b';
    public final char ORANGE = 'c';
    public final char PURPLE = 'd';
    public final char RED = 'e';
    public final char FIRST_PLAYER = 'f';
    public final char[] TILES_MASK = {BLUE, GREEN, ORANGE, PURPLE, RED};

    // Number in characters
    public static final char ZERO = '0';
    public static final char ONE = '1';
    public static final char TWO = '2';
    public static final char THREE = '3';
    public static final char FOUR = '4';
    public static final char FIVE = '5';
    public static final char SIX = '6';
    public static final char SEVEN = '7';
    public static final char EIGHT = '8';
    public static final char NINE = '9';

    // Size, Numbers of all components
    public static final int DEFAULT_MAX_PLAYER = 2;
    public static final int DEFAULT_FACTORY_MAX_NUMBER = 2 * DEFAULT_MAX_PLAYER + 1;
    public static final int[] FACTORY_MAX_INDICES = {FOUR, SIX, SEVEN};
    public final int FACTORY_SIZE = 4;
    public final int MAX_MOSAIC_ROW = 5;
    public final int MAX_MOSAIC_COL = 5;
    public final int MAX_MOSAIC_COLOR = 5;
    public final int MAX_STORAGE_ROW = 5;
    public final String EMPTY_TILES = "0000000000";
    public final String EMPTY_STATE = "";
    public final int ROW_BONUS_POINT = 2;
    public final int COL_BONUS_POINT = 7;
    public final int COLOR_BONUS_POINT = 10;
}
