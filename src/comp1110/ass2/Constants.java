package comp1110.ass2;

/**
 * @author Min Jae, Kim
 * @author Si Bo, Hu
 * Constants interface stores every important constants that will be used over all codes
 * Reader can immediately acknowledge the meaning of character or integer
 * Code writer does not have to look up for exact character or integer when they use constants
 * It reduces mistakes of using wrong constants, and is easy to manage and fix errors
 */
public interface Constants {
    // Players substring
    public static final int DEFAULT_MAX_PLAYER = 2;
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
    public final char NO_COLOR = 'n';
    public final char[] COLORS = {BLUE, GREEN, ORANGE, PURPLE, RED};
    public final char[] COLORS_WITH_FIRST_PLAYER = {BLUE, GREEN, ORANGE, PURPLE, RED, FIRST_PLAYER};

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
    public static final char[] NUMBERS = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};

    // Size, Numbers of all components : General
    public final String EMPTY_STATE = "";

    // Size, Numbers of all components : BagTyped
    public final int MAX_DISCARD_EACH_COLOR_SIZE = 20;
    public final int MAX_BAG_EACH_COLOR_SIZE = 20;
    public final int MAX_DISCARD_SIZE = 100;
    public final int MAX_BAG_SIZE = 100;
    public final String EMPTY_TILES = "0000000000";

    // Size, Numbers of all components : OrderTyped
    public final int FACTORY_SIZE = 4;
    public final int[] CENTER_MAX_NUMBERS = {16, 22, 28};
    public final int MAX_CENTER_STRING_SIZE = 15;
    public final int MAX_CENTER_WITH_FIRST_PLAYER_TOKEN_STRING_SIZE = MAX_CENTER_STRING_SIZE + 1;
    public final int MAX_FLOOR_STRING_SIZE = 7;
    public final int MAX_FLOOR_BEFORE_ADJUST_STRING_SIZE = MAX_FLOOR_STRING_SIZE + CENTER_MAX_NUMBERS[2];
    public final int DEFAULT_FACTORY_MAX_NUMBER = 2 * DEFAULT_MAX_PLAYER + 1;
    public final int[] FACTORY_MAX_NUMBERS = {5, 7, 9};
    public final int[] FACTORY_MAX_INDICES = {FOUR, SIX, SEVEN};

    // Size, Numbers of all components : CoordinateTyped
    public final int MAX_STORAGE_STRING_SIZE = 5 * 3;
    public final int MAX_MOSAIC_STRING_SIZE = 25 * 3;
    public final int MAX_MOSAIC_ROW = 5;
    public final int MAX_MOSAIC_COL = 5;
    public final int MAX_MOSAIC_COLOR = 5;
    public final int MAX_STORAGE_ROW = 5;
    public final int MAX_STORAGE_COL = 5;

    // Size, Numbers of all components : Score
    public final int[] CLEARING_FLOOR_POINTS = new int[]{0, -1, -2, -4, -6, -8, -11, -14};
    public final int ROW_BONUS_POINT = 2;
    public final int COL_BONUS_POINT = 7;
    public final int COLOR_BONUS_POINT = 10;

    // Image files directory
    public final String BLUE_IMAGE = "file:src/comp1110/ass2/img/" + String.valueOf(BLUE) + ".png";
    public final String GREEN_IMAGE = "file:src/comp1110/ass2/img/" + String.valueOf(GREEN) + ".png";
    public final String ORANGE_IMAGE = "file:src/comp1110/ass2/img/" + String.valueOf(ORANGE) + ".png";
    public final String PURPLE_IMAGE = "file:src/comp1110/ass2/img/" + String.valueOf(PURPLE) + ".png";
    public final String RED_IMAGE = "file:src/comp1110/ass2/img/" + String.valueOf(RED) + ".png";
    public final String FIRST_PLAYER_IMAGE = "file:src/comp1110/ass2/img/" + String.valueOf(FIRST_PLAYER) + ".png";
    public final String NO_TILE_IMAGE = "file:src/comp1110/ass2/img/" + String.valueOf(NO_COLOR) + ".png";
    public final String[] COLORS_IMAGE = new String[]{BLUE_IMAGE, GREEN_IMAGE, ORANGE_IMAGE, PURPLE_IMAGE, RED_IMAGE};
    public final String[] COLORS_WITH_FIRST_PLAYER_IMAGE = new String[]{BLUE_IMAGE, GREEN_IMAGE, ORANGE_IMAGE, PURPLE_IMAGE, RED_IMAGE, FIRST_PLAYER_IMAGE};

    // Image dimensions : Tile
    public final double SMALL_TILE_IMAGE_SIZE_X = 14;
    public final double SMALL_TILE_IMAGE_SIZE_Y = 14;
    public final double SMALL_TILE_IMAGE_SIZE_X_GAP = 1;
    public final double SMALL_TILE_IMAGE_SIZE_Y_GAP = 1;
    public final double BIG_TILE_IMAGE_SIZE_X = 28;
    public final double BIG_TILE_IMAGE_SIZE_Y = 28;
    public final double BIG_TILE_IMAGE_SIZE_X_GAP = 2;
    public final double BIG_TILE_IMAGE_SIZE_Y_GAP = 2;

    // Image dimensions : Common Boards
    public final double FIRST_BOARD_POS_X = 50;
    public final double FIRST_BOARD_POS_Y = 50;
    public final double SECOND_BOARD_POS_X = 600;
    public final double SECOND_BOARD_POS_Y = 280;
    public final double THIRD_BOARD_POS_X = 1000;

    // Image dimensions : Factories, Factory
    public final int MAX_FACTORIES_TILES_COL_IMAGE = 5;
    public final int MAX_FACTORY_TILES_ROW_IMAGE = 2;
    public final int MAX_FACTORY_TILES_COL_IMAGE = 2;

    public final double INITIAL_FACTORIES_IMAGE_POS_X = SECOND_BOARD_POS_X;
    public final double INITIAL_FACTORIES_IMAGE_POS_Y = FIRST_BOARD_POS_Y;

    public final double FACTORIES_IMAGE_ROW_GAP = 5;
    public final double FACTORIES_IMAGE_COL_GAP = 5;

    public final double FACTORIES_IMAGE_SIZE_X_GAP = (BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP) * MAX_FACTORY_TILES_COL_IMAGE + FACTORIES_IMAGE_COL_GAP;
    public final double FACTORIES_IMAGE_SIZE_Y_GAP = (BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP) * MAX_FACTORY_TILES_ROW_IMAGE + FACTORIES_IMAGE_ROW_GAP;

    // Image dimensions : Center
    public final int MAX_CENTER_TILES_COL_IMAGE = 6;
    public final double INITIAL_CENTER_IMAGE_POS_X = THIRD_BOARD_POS_X;
    public final double INITIAL_CENTER_IMAGE_POS_Y = FIRST_BOARD_POS_Y-29;

    // Image dimensions : Bag
    public final int MAX_BAG_TILES_COL_IMAGE = 10;
    public final double INITIAL_BAG_IMAGE_POS_X = THIRD_BOARD_POS_X;
    public final double INITIAL_BAG_IMAGE_POS_Y = 364;

    // Image dimensions : Discard
    public final int MAX_DISCARD_TILES_COL_IMAGE = 10;
    public final double INITIAL_DISCARD_IMAGE_POS_X = THIRD_BOARD_POS_X;
    public final double INITIAL_DISCARD_IMAGE_POS_Y = 192;

    // Image dimensions : Storage
    public final double INITIAL_STORAGE_IMAGE_POS_X = SECOND_BOARD_POS_X;
    public final double INITIAL_STORAGE_IMAGE_POS_Y = SECOND_BOARD_POS_Y;

    // Image dimensions : Mosaic
    public final double STORAGE_MOSAIC_IMAGE_ROW_GAP = 15;
    public final double INITIAL_MOSAIC_IMAGE_POS_X = SECOND_BOARD_POS_X + (BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP) * MAX_STORAGE_ROW + STORAGE_MOSAIC_IMAGE_ROW_GAP;
    public final double INITIAL_MOSAIC_IMAGE_POS_Y = SECOND_BOARD_POS_Y;

    // Image dimensions : Floor
    public final int MAX_FLOOR_TILES_COL_IMAGE = 7;
    public final double INITIAL_FLOOR_IMAGE_POS_X = SECOND_BOARD_POS_X;
    public final double INITIAL_FLOOR_IMAGE_POS_Y = 465;

    // Image dimensions : Status (Rank, Score, Current Player)
    public final double INITIAL_SCORE_IMAGE_POS_X = 500;
    public final double INITIAL_SCORE_IMAGE_POS_Y = 550;
    public final double SCORE_IMAGE_GAP = 10;

}
