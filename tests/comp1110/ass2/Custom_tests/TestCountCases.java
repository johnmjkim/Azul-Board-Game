package comp1110.ass2.Custom_tests;

public interface TestCountCases extends TestStateCases{

    /**
     * Tiles Counting of Factories
     * Include 6 columns : Total Tiles, BLUE, GREEN, ORANGE, PURPLE, RED
     */
    public static final int[][] TILES_FACTORIES = {
            // full game, automatic scoring
            // ROUND 1
            new int[]{20, 1, 5, 4, 4, 6},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // ROUND 2
            new int[]{20, 5, 6, 0, 5, 4},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // ROUND 3
            new int[]{20, 4, 3, 6, 4, 3},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // ROUND 4
            new int[]{20, 5, 1, 6, 3, 5},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // ROUND 5
            new int[]{20, 5, 5, 4, 4, 2},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // ROUND 6
            new int[]{20, 7, 2, 3, 4, 4},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // BONUS SCORING
            new int[]{0, 0, 0, 0, 0, 0}
            /*
            // ROUND 1
            new String[]{"0cdde1bbbe2abde3cdee4bcce", "cdde", "bbbe", "abde", "cdee", "bcce"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // ROUND 2
            new String[]{"0abbd1abbe2adde3aabe4bdde", "abbd", "abbe", "adde", "aabe", "bdde"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // ROUND 3
            new String[]{"0bdde1bcde2aaac3acce4bccd", "bdde", "bcde", "aaac", "acce", "bccd"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // ROUND 4
            new String[]{"0ccce1aace2aade3abde4ccde", "ccce", "aace", "aade", "abde", "ccde"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // ROUND 5
            new String[]{"0bcdd1bbbc2aaad3acde4abce", "bcdd", "bbbc", "aaad", "acde", "abce"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // ROUND 6
            new String[]{"0bdde1aaae2aaad3ccde4abce", "bdde", "aaae", "aaad", "ccde", "abce"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // BONUS SCORING
            new String[]{"", "", "", "", "", ""}
             */
    };

    /**
     * Tiles Counting of Center
     * Include 6 columns : Total Tiles, BLUE, GREEN, ORANGE, PURPLE, RED
     */
    public static final int[][] TILES_CENTER = {
            // full game, automatic scoring
            // ROUND 1
            new int[]{20, 1, 5, 4, 4, 6},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // ROUND 2
            new int[]{20, 5, 6, 0, 5, 4},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // ROUND 3
            new int[]{20, 4, 3, 6, 4, 3},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // ROUND 4
            new int[]{20, 5, 1, 6, 3, 5},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // ROUND 5
            new int[]{20, 5, 5, 4, 4, 2},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // ROUND 6
            new int[]{20, 7, 2, 3, 4, 4},
            new int[]{0, 0, 0, 0, 0, 0},
            new int[]{0, 0, 0, 0, 0, 0},
            // BONUS SCORING
            new int[]{0, 0, 0, 0, 0, 0}
            /*
            // ROUND 1
            new String[]{"0cdde1bbbe2abde3cdee4bcce", "cdde", "bbbe", "abde", "cdee", "bcce"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // ROUND 2
            new String[]{"0abbd1abbe2adde3aabe4bdde", "abbd", "abbe", "adde", "aabe", "bdde"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // ROUND 3
            new String[]{"0bdde1bcde2aaac3acce4bccd", "bdde", "bcde", "aaac", "acce", "bccd"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // ROUND 4
            new String[]{"0ccce1aace2aade3abde4ccde", "ccce", "aace", "aade", "abde", "ccde"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // ROUND 5
            new String[]{"0bcdd1bbbc2aaad3acde4abce", "bcdd", "bbbc", "aaad", "acde", "abce"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // ROUND 6
            new String[]{"0bdde1aaae2aaad3ccde4abce", "bdde", "aaae", "aaad", "ccde", "abce"},
            new String[]{"", "", "", "", "", ""},
            new String[]{"", "", "", "", "", ""},
            // BONUS SCORING
            new String[]{"", "", "", "", "", ""}
             */
    };
}
