package comp1110.ass2;

import comp1110.ass2.Constants;

public interface TestStateCases extends Constants {

    public static final String EMPTY_PLAYER_STATE = "A0MSFB0MSF";

    /**
     * Valid States
     */
    public static final String[][] VALID_STATES = {
            // full game, automatic scoring
            // ROUND 1
            new String[]{"AF0cdde1bbbe2abde3cdee4bcceCfB1915161614D0000000000", "A0MSFB0MSF"},
            new String[]{"AFCB1915161614D0000000000", "A0MS0d11c22b33e44e1FefB0MS0a11b22d33c2F"},
            new String[]{"AFCB1915161614D0003010203", "A7Md03c13b23e32S4e1FefB3Ma00b12d20S3c2F"},
            // ROUND 2
            new String[]{"AF0abbd1abbe2adde3aabe4bddeCfB1409161110D0003010204", "A5Md03c13b23e32S4e1FB3Ma00b12d20S3c2F"},
            new String[]{"AFCB1409161110D0003010204", "A5Md03c13b23e32S2d33b44e5FfB3Ma00b12d20S0b11d23c24a5Fb"},
            new String[]{"AFCB1409161110D0406010508", "A8Md03c13d20b23e32b34e43SFfB7Ma00b01b12d14d20a44S3c2Fb"},
            // ROUND 3
            new String[]{"AF0bdde1bcde2aaac3acce4bccdCfB1006100707D0407010508", "A7Md03c13d20b23e32b34e43SFB6Ma00b01b12d14d20a44S3c2F"},
            new String[]{"AFCB1006100707D0407010508", "A7Md03c13d20b23e32b34e43S0b11d22a33a14d2FB6Ma00b01b12d14d20a44S2e33c44c4Fbbf"},
            new String[]{"AFCB1006100707D0607040610", "A14Mb01d03c13d14d20a22b23e32b34e43S3a14d2FB10Ma00b01b12d14d20e21c30a44S4c4Fbbf"},
            // ROUND 4
            new String[]{"BF0ccce1aace2aade3abde4ccdeCfB0505040402D0609040610", "A14Mb01d03c13d14d20a22b23e32b34e43S3a14d2FB6Ma00b01b12d14d20e21c30a44S4c4F"},
            new String[]{"BFCB0505040402D0609040610", "A14Mb01d03c13d14d20a22b23e32b34e43S0e11e22c33a44d2FaabB6Ma00b01b12d14d20e21c30a44S1e22c23d34c5Ff"},
            new String[]{"BFCB0505040402D0909100612", "A35Mb01d03e04e10c13d14d20a22b23c24e32a33b34e43S4d2FaabB11Ma00b01e10b12d14d20e21c30c41a44S2c23d3Ff"},
            // ROUND 5
            new String[]{"BF0bcdd1bbbc2aaad3acde4abceCfB0000000000D1110100612", "A31Mb01d03e04e10c13d14d20a22b23c24e32a33b34e43S4d2FB10Ma00b01e10b12d14d20e21c30c41a44S2c23d3F"},
            new String[]{"BFCB0000000000D1110100612", "A31Mb01d03e04e10c13d14d20a22b23c24e32a33b34e43S1a22e23c34d5FadfB10Ma00b01e10b12d14d20e21c30c41a44S1a22c33d34b5F"},
            new String[]{"BFCB0000000000D1314121012", "A40Mb01d03e04e10a11c13d14d20a22b23c24e32a33b34d42e43S2e23c3FadfB25Ma00b01e10a11b12d14d20e21c24c30b40c41a44S3d3F"},
            // ROUND 6
            new String[]{"AF0bdde1aaae2aaad3ccde4abceCfB0712090708D0000000000", "A36Mb01d03e04e10a11c13d14d20a22b23c24e32a33b34d42e43S2e23c3FB25Ma00b01e10a11b12d14d20e21c24c30b40c41a44S3d3F"},
            new String[]{"AFCB0712090708D0000000000", "A36Mb01d03e04e10a11c13d14d20a22b23c24e32a33b34d42e43S0a11b22e33c3FeeeB25Ma00b01e10a11b12d14d20e21c24c30b40c41a44S0c11c22a33d4Faaadddf"},
            new String[]{"AFCB0712090708D0201010302", "A58Ma00b01d03e04e10a11b12c13d14d20e21a22b23c24e32a33b34d42e43S3c3FeeeB48Ma00b01c02e10a11b12c13d14d20e21a22c24c30d31b40c41a44SFaaadddf"},
            // BONUS SCORING
            new String[]{"BFCfB0712090708D0501010605", "A75Ma00b01d03e04e10a11b12c13d14d20e21a22b23c24e32a33b34d42e43S3c3FB60Ma00b01c02e10a11b12c13d14d20e21a22c24c30d31b40c41a44SF"}

    };

    /**
     * Valid States Turn
     * Include 3 columns : Turn of SharedState, Turn of Player A, Turn of Player B State
     */
    public static final String[][] VALID_STATES_TURNS = {
            // full game, automatic scoring
            // ROUND 1
            new String[]{"A", "A", "B"},
            new String[]{"A", "A", "B"},
            new String[]{"A", "A", "B"},
            // ROUND 2
            new String[]{"A", "A", "B"},
            new String[]{"A", "A", "B"},
            new String[]{"A", "A", "B"},
            // ROUND 3
            new String[]{"A", "A", "B"},
            new String[]{"A", "A", "B"},
            new String[]{"A", "A", "B"},
            // ROUND 4
            new String[]{"B", "A", "B"},
            new String[]{"B", "A", "B"},
            new String[]{"B", "A", "B"},
            // ROUND 5
            new String[]{"B", "A", "B"},
            new String[]{"B", "A", "B"},
            new String[]{"B", "A", "B"},
            // ROUND 6
            new String[]{"A", "A", "B"},
            new String[]{"A", "A", "B"},
            new String[]{"A", "A", "B"},
            // BONUS SCORING
            new String[]{"B", "A", "B"}
    };

    /**
     * Valid States Factories and Factory
     * Include 6 columns : Factories, Factory 0, Factory 1, ... ,Factory 4
     */
    public static final String[][] VALID_STATES_FACTORIES = {
            // full game, automatic scoring
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

    };

    /**
     * Valid States Center
     * Include : Center
     */
    public static final String[] VALID_STATES_CENTER = {
            // ROUND 1
            "f",
            "",
            "",
            // ROUND 2
            "f",
            "",
            "",
            // ROUND 3
            "f",
            "",
            "",
            // ROUND 4
            "f",
            "",
            "",
            // ROUND 5
            "f",
            "",
            "",
            // ROUND 6
            "f",
            "",
            "",
            // BONUS SCORING
            "f"

    };

    /**
     * Valid States Bag
     * Include : Bag
     */
    public static final String[] VALID_STATES_BAG = {
            // ROUND 1
            "1915161614",
            "1915161614",
            "1915161614",
            // ROUND 2
            "1409161110",
            "1409161110",
            "1409161110",
            // ROUND 3
            "1006100707",
            "1006100707",
            "1006100707",
            // ROUND 4
            "0505040402",
            "0505040402",
            "0505040402",
            // ROUND 5
            "0000000000",
            "0000000000",
            "0000000000",
            // ROUND 6
            "0712090708",
            "0712090708",
            "0712090708",
            // BONUS SCORING
            "0712090708"
    };

    /**
     * Valid States Discard
     * Include : Discard
     */
    public static final String[] VALID_STATES_DISCARD = {
            // ROUND 1
            "0000000000",
            "0000000000",
            "0003010203",
            // ROUND 2
            "0003010204",
            "0003010204",
            "0406010508",
            // ROUND 3
            "0407010508",
            "0407010508",
            "0607040610",
            // ROUND 4
            "0609040610",
            "0609040610",
            "0909100612",
            // ROUND 5
            "1110100612",
            "1110100612",
            "1314121012",
            // ROUND 6
            "0000000000",
            "0000000000",
            "0201010302",
            // BONUS SCORING
            "0501010605"
    };

    /**
     * Valid Player States
     * Include 2 columns : Player A's state, Player B's state
     */
    public static final String[][] VALID_PLAYER_STATES = {
            // full game, automatic scoring
            // ROUND 1
            new String[]{"0MSF", "0MSF"},
            new String[]{"0MS0d11c22b33e44e1Fef", "0MS0a11b22d33c2F"},
            new String[]{"7Md03c13b23e32S4e1Fef", "3Ma00b12d20S3c2F"},
            // ROUND 2
            new String[]{"5Md03c13b23e32S4e1F", "3Ma00b12d20S3c2F"},
            new String[]{"5Md03c13b23e32S2d33b44e5Ff", "3Ma00b12d20S0b11d23c24a5Fb"},
            new String[]{"8Md03c13d20b23e32b34e43SFf", "7Ma00b01b12d14d20a44S3c2Fb"},
            // ROUND 3
            new String[]{"7Md03c13d20b23e32b34e43SF", "6Ma00b01b12d14d20a44S3c2F"},
            new String[]{"7Md03c13d20b23e32b34e43S0b11d22a33a14d2F", "6Ma00b01b12d14d20a44S2e33c44c4Fbbf"},
            new String[]{"14Mb01d03c13d14d20a22b23e32b34e43S3a14d2F", "10Ma00b01b12d14d20e21c30a44S4c4Fbbf"},
            // ROUND 4
            new String[]{"14Mb01d03c13d14d20a22b23e32b34e43S3a14d2F", "6Ma00b01b12d14d20e21c30a44S4c4F"},
            new String[]{"14Mb01d03c13d14d20a22b23e32b34e43S0e11e22c33a44d2Faab", "6Ma00b01b12d14d20e21c30a44S1e22c23d34c5Ff"},
            new String[]{"35Mb01d03e04e10c13d14d20a22b23c24e32a33b34e43S4d2Faab", "11Ma00b01e10b12d14d20e21c30c41a44S2c23d3Ff"},
            // ROUND 5
            new String[]{"31Mb01d03e04e10c13d14d20a22b23c24e32a33b34e43S4d2F", "10Ma00b01e10b12d14d20e21c30c41a44S2c23d3F"},
            new String[]{"31Mb01d03e04e10c13d14d20a22b23c24e32a33b34e43S1a22e23c34d5Fadf", "10Ma00b01e10b12d14d20e21c30c41a44S1a22c33d34b5F"},
            new String[]{"40Mb01d03e04e10a11c13d14d20a22b23c24e32a33b34d42e43S2e23c3Fadf", "25Ma00b01e10a11b12d14d20e21c24c30b40c41a44S3d3F"},
            // ROUND 6
            new String[]{"36Mb01d03e04e10a11c13d14d20a22b23c24e32a33b34d42e43S2e23c3F", "25Ma00b01e10a11b12d14d20e21c24c30b40c41a44S3d3F"},
            new String[]{"36Mb01d03e04e10a11c13d14d20a22b23c24e32a33b34d42e43S0a11b22e33c3Feee", "25Ma00b01e10a11b12d14d20e21c24c30b40c41a44S0c11c22a33d4Faaadddf"},
            new String[]{"58Ma00b01d03e04e10a11b12c13d14d20e21a22b23c24e32a33b34d42e43S3c3Feee", "48Ma00b01c02e10a11b12c13d14d20e21a22c24c30d31b40c41a44SFaaadddf"},
            // BONUS SCORING
            new String[]{"75Ma00b01d03e04e10a11b12c13d14d20e21a22b23c24e32a33b34d42e43S3c3F", "60Ma00b01c02e10a11b12c13d14d20e21a22c24c30d31b40c41a44SF"}

    };

    /**
     * Valid Player Mosaic States
     * Include 2 columns: PLayer A Mosaic, Player B Mosaic
     */
    public static final String[][] VALID_STATES_MOSAIC = {
            // ROUND 1
            new String[]{"", ""},
            new String[]{"", ""},
            new String[]{"d03c13b23e32", "a00b12d20"},
            // ROUND 2
            new String[]{"d03c13b23e32", "a00b12d20"},
            new String[]{"d03c13b23e32", "a00b12d20"},
            new String[]{"d03c13d20b23e32b34e43", "a00b01b12d14d20a44"},
            // ROUND 3
            new String[]{"d03c13d20b23e32b34e43", "a00b01b12d14d20a44"},
            new String[]{"d03c13d20b23e32b34e43", "a00b01b12d14d20a44"},
            new String[]{"b01d03c13d14d20a22b23e32b34e43", "a00b01b12d14d20e21c30a44"},
            // ROUND 4
            new String[]{"b01d03c13d14d20a22b23e32b34e43", "a00b01b12d14d20e21c30a44"},
            new String[]{"b01d03c13d14d20a22b23e32b34e43", "a00b01b12d14d20e21c30a44"},
            new String[]{"b01d03e04e10c13d14d20a22b23c24e32a33b34e43", "a00b01e10b12d14d20e21c30c41a44"},
            // ROUND 5
            new String[]{"b01d03e04e10c13d14d20a22b23c24e32a33b34e43", "a00b01e10b12d14d20e21c30c41a44"},
            new String[]{"b01d03e04e10c13d14d20a22b23c24e32a33b34e43", "a00b01e10b12d14d20e21c30c41a44"},
            new String[]{"b01d03e04e10a11c13d14d20a22b23c24e32a33b34d42e43", "a00b01e10a11b12d14d20e21c24c30b40c41a44"},
            // ROUND 6
            new String[]{"b01d03e04e10a11c13d14d20a22b23c24e32a33b34d42e43", "a00b01e10a11b12d14d20e21c24c30b40c41a44"},
            new String[]{"b01d03e04e10a11c13d14d20a22b23c24e32a33b34d42e43", "a00b01e10a11b12d14d20e21c24c30b40c41a44"},
            new String[]{"a00b01d03e04e10a11b12c13d14d20e21a22b23c24e32a33b34d42e43", "a00b01c02e10a11b12c13d14d20e21a22c24c30d31b40c41a44"},
            // BONUS SCORING
            new String[]{"a00b01d03e04e10a11b12c13d14d20e21a22b23c24e32a33b34d42e43", "a00b01c02e10a11b12c13d14d20e21a22c24c30d31b40c41a44"}
    };

    /**
     * Valid Player Mosaic Row States
     * Include 10 columns: Player A Mosaic Row 0, 1, 2, 3, 4 and Player B Mosaic Row 0, 1, 2, 3, 4
     */
    public static final String[][] VALID_STATES_MOSAIC_ROW = {
            // ROUND 1
            new String[]{"","","","","","","","","",""},
            new String[]{"","","","","","","","","",""},
            new String[]{"d03","c13","b23","e32","", "a00","b12","d20","",""},
            // ROUND 2
            new String[]{"d03","c13","b23","e32","","a00","b12","d20","",""},
            new String[]{"d03","c13","b23","e32","","a00","b12","d20","",""},
            new String[]{"d03","c13","d20b23","e32b34","e43","a00b01","b12d14","d20","","a44"},
            // ROUND 3
            new String[]{"d03","c13","d20b23","e32b34","e43","a00b01","b12d14","d20","","a44"},
            new String[]{"d03","c13","d20b23","e32b34","e43","a00b01","b12d14","d20","","a44"},
            new String[]{"b01d03","c13d14","d20a22b23","e32b34","e43","a00b01","b12d14","d20e21","c30","a44"},
            // ROUND 4
            new String[]{"b01d03","c13d14","d20a22b23","e32b34","e43","a00b01","b12d14","d20e21","c30","a44"},
            new String[]{"b01d03","c13d14","d20a22b23","e32b34","e43","a00b01","b12d14","d20e21","c30","a44"},
            new String[]{"b01d03e04","e10c13d14","d20a22b23c24","e32a33b34","e43","a00b01","e10b12d14","d20e21","c30","c41a44"},
            // ROUND 5
            new String[]{"b01d03e04","e10c13d14","d20a22b23c24","e32a33b34","e43","a00b01","e10b12d14","d20e21","c30","c41a44"},
            new String[]{"b01d03e04","e10c13d14","d20a22b23c24","e32a33b34","e43","a00b01","e10b12d14","d20e21","c30","c41a44"},
            new String[]{"b01d03e04","e10a11c13d14","d20a22b23c24","e32a33b34","d42e43","a00b01","e10a11b12d14","d20e21c24","c30","b40c41a44"},
            // ROUND 6
            new String[]{"b01d03e04","e10a11c13d14","d20a22b23c24","e32a33b34","d42e43","a00b01","e10a11b12d14","d20e21c24","c30","b40c41a44"},
            new String[]{"b01d03e04","e10a11c13d14","d20a22b23c24","e32a33b34","d42e43","a00b01","e10a11b12d14","d20e21c24","c30","b40c41a44"},
            new String[]{"a00b01d03e04","e10a11b12c13d14","d20e21a22b23c24","e32a33b34","d42e43","a00b01c02","e10a11b12c13d14","d20e21a22c24","c30d31","b40c41a44"},
            // BONUS SCORING
            new String[]{"a00b01d03e04","e10a11b12c13d14","d20e21a22b23c24","e32a33b34","d42e43","a00b01c02","e10a11b12c13d14","d20e21a22c24","c30d31","b40c41a44"}

    };

    /**
     * Valid Player Mosaic Col States
     * Include 10 columns: Player A Mosaic Col 0, 1, 2, 3, 4, 5 and Player B Mosaic Row 0, 1, 2, 3, 4
     */
    public static final String[][] VALID_STATES_MOSAIC_COL = {
            // ROUND 1
            new String[]{"","","","","","","","","",""},
            new String[]{"","","","","","","","","",""},
            new String[]{"","","e32","d03c13b23","","a00d20","","b12","",""},
            // ROUND 2
            new String[]{"","","e32","d03c13b23","","a00d20","","b12","",""},
            new String[]{"","","e32","d03c13b23","","a00d20","","b12","",""},
            new String[]{"d20","","e32","d03c13b23e43","b34","a00d20","b01","b12","","d14a44"},
            // ROUND 3
            new String[]{"d20","","e32","d03c13b23e43","b34","a00d20","b01","b12","","d14a44"},
            new String[]{"d20","","e32","d03c13b23e43","b34","a00d20","b01","b12","","d14a44"},
            new String[]{"d20","b01","a22e32","d03c13b23e43","d14b34","a00d20c30","b01e21","b12","","d14a44"},
            // ROUND 4
            new String[]{"d20","b01","a22e32","d03c13b23e43","d14b34","a00d20c30","b01e21","b12","","d14a44"},
            new String[]{"d20","b01","a22e32","d03c13b23e43","d14b34","a00d20c30","b01e21","b12","","d14a44"},
            new String[]{"e10d20","b01","a22e32","d03c13b23a33e43","e04d14c24b34","a00e10d20c30","b01e21c41","b12","","d14a44"},
            // ROUND 5
            new String[]{"e10d20","b01","a22e32","d03c13b23a33e43","e04d14c24b34","a00e10d20c30","b01e21c41","b12","","d14a44"},
            new String[]{"e10d20","b01","a22e32","d03c13b23a33e43","e04d14c24b34","a00e10d20c30","b01e21c41","b12","","d14a44"},
            new String[]{"e10d20","b01a11","a22e32d42","d03c13b23a33e43","e04d14c24b34","a00e10d20c30b40","b01a11e21c41","b12","","d14c24a44"},
            // ROUND 6
            new String[]{"e10d20","b01a11","a22e32d42","d03c13b23a33e43","e04d14c24b34","a00e10d20c30b40","b01a11e21c41","b12","","d14c24a44"},
            new String[]{"e10d20","b01a11","a22e32d42","d03c13b23a33e43","e04d14c24b34","a00e10d20c30b40","b01a11e21c41","b12","","d14c24a44"},
            new String[]{"a00e10d20","b01a11e21","b12a22e32d42","d03c13b23a33e43","e04d14c24b34","a00e10d20c30b40","b01a11e21d31c41","c02b12a22","c13","d14c24a44"},
            // BONUS SCORING
            new String[]{"a00e10d20","b01a11e21","b12a22e32d42","d03c13b23a33e43","e04d14c24b34","a00e10d20c30b40","b01a11e21d31c41","c02b12a22","c13","d14c24a44"}
    };

    /**
     * Valid Player Storage States
     * Include 2 columns: PLayer A Storage, Player B Storage
     */
    public static final String[][] VALID_STATES_STORAGE = {
            // ROUND 1
            new String[]{"",""},
            new String[]{"0d11c22b33e44e1","0a11b22d33c2"},
            new String[]{"4e1","3c2"},
            // ROUND 2
            new String[]{"4e1","3c2"},
            new String[]{"2d33b44e5","0b11d23c24a5"},
            new String[]{"","3c2"},
            // ROUND 3
            new String[]{"","3c2"},
            new String[]{"0b11d22a33a14d2","2e33c44c4"},
            new String[]{"3a14d2","4c4"},
            // ROUND 4
            new String[]{"3a14d2","4c4"},
            new String[]{"0e11e22c33a44d2","1e22c23d34c5"},
            new String[]{"4d2","2c23d3"},
            // ROUND 5
            new String[]{"4d2","2c23d3"},
            new String[]{"1a22e23c34d5","1a22c33d34b5"},
            new String[]{"2e23c3","3d3"},
            // ROUND 6
            new String[]{"2e23c3","3d3"},
            new String[]{"0a11b22e33c3","0c11c22a33d4"},
            new String[]{"3c3",""},
            // BONUS SCORING
            new String[]{"3c3",""}

    };

    /**
     * Valid Player Storage Row States
     * Include 10 columns: Player A Storage Row 0, 1, 2, 3, 4 and Player B Storage Row 0, 1, 2, 3, 4
     */
    public static final String[][] VALID_STATES_STORAGE_ROW = {
            // ROUND 1
            new String[]{"","","","","","","","","",""},
            new String[]{"d1","c2","b3","e4","e1","a1","b2","d3","c2",""},
            new String[]{"","","","","e1","","","","c2",""},
            // ROUND 2
            new String[]{"","","","","e1","","","","c2",""},
            new String[]{"","","d3","b4","e5","b1","d2","","c2","a5"},
            new String[]{"","","","","","","","","c2",""},
            // ROUND 3
            new String[]{"","","","","","","","","c2",""},
            new String[]{"b1","d2","a3","a1","d2","","","e3","c4","c4"},
            new String[]{"","","","a1","d2","","","","","c4"},
            // ROUND 4
            new String[]{"","","","a1","d2","","","","","c4"},
            new String[]{"e1","e2","c3","a4","d2","","e2","c2","d3","c5"},
            new String[]{"","","","","d2","","","c2","d3",""},
            // ROUND 5
            new String[]{"","","","","d2","","","c2","d3",""},
            new String[]{"","a2","e2","c3","d5","","a2","c3","d3","b5"},
            new String[]{"","","e2","c3","","","","","d3",""},
            // ROUND 6
            new String[]{"","","e2","c3","","","","","d3",""},
            new String[]{"a1","b2","e3","c3","","c1","c2","a3","d4",""},
            new String[]{"","","","c3","","","","","",""},
            // BONUS SCORING
            new String[]{"","","","c3","","","","","",""},
    };

    /**
     * Valid Player Floor States
     * Include 2 columns: PLayer A Floor, Player B Floor
     */
    public static final String[][] VALID_STATES_FLOOR = {
            // ROUND 1
            new String[]{"",""},
            new String[]{"ef",""},
            new String[]{"ef",""},
            // ROUND 2
            new String[]{"",""},
            new String[]{"f","b"},
            new String[]{"f","b"},
            // ROUND 3
            new String[]{"",""},
            new String[]{"","bbf"},
            new String[]{"","bbf"},
            // ROUND 4
            new String[]{"",""},
            new String[]{"aab","f"},
            new String[]{"aab","f"},
            // ROUND 5
            new String[]{"",""},
            new String[]{"adf",""},
            new String[]{"adf",""},
            // ROUND 6
            new String[]{"",""},
            new String[]{"eee","aaadddf"},
            new String[]{"eee","aaadddf"},
            // BONUS SCORING
            new String[]{"",""}

    };

    /**
     * Full Game Moves
     */
    public static final String[][] FULL_GAME_WITH_MOVES = {
            new String[]{"AF0cdee1bdde2abbe3bcde4aaaeCfB1616181614D0000000000", "A0MSFB0MSF", "A2a4"},
            new String[]{"BF0cdee1bdde3bcde4aaaeCbbefB1616181614D0000000000", "A0MS4a1FB0MSF", "BCe2"},
            new String[]{"AF0cdee1bdde3bcde4aaaeCbbB1616181614D0000000000", "A0MS4a1FB0MS2e1Ff", "A3c2"},
            new String[]{"BF0cdee1bdde4aaaeCbbbdeB1616181614D0000000000", "A0MS2c14a1FB0MS2e1Ff", "B0c0"},
            new String[]{"AF1bdde4aaaeCbbbddeeeB1616181614D0000000000", "A0MS2c14a1FB0MS0c12e1Ff", "A4a3"},
            new String[]{"BF1bddeCbbbddeeeeB1616181614D0000000000", "A0MS2c13a34a1FB0MS0c12e1Ff", "B1b1"},
            new String[]{"AFCbbbddddeeeeeB1616181614D0000000000", "A0MS2c13a34a1FB0MS0c11b12e1Ff", "ACb1"},
            new String[]{"BFCddddeeeeeB1616181614D0000000000", "A0MS1b22c13a34a1FbB0MS0c11b12e1Ff", "BCd3"},
            new String[]{"AFCeeeeeB1616181614D0000000000", "A0MS1b22c13a34a1FbB0MS0c11b12e13d4Ff", "ACe0"},
            new String[]{"AFCB1616181614D0000000000", "A0MS0e11b22c13a34a1FbeeeeB0MS0c11b12e13d4Ff", "A04"},
            new String[]{"AFCB1616181614D0000000000", "A1Me04S1b22c13a34a1FbeeeeB0MS0c11b12e13d4Ff", "A11"},
            new String[]{"BFCB1616181614D0001000000", "A2Me04b11S2c13a34a1FbeeeeB0MS0c11b12e13d4Ff", "B02"},
            new String[]{"BFCB1616181614D0001000000", "A2Me04b11S2c13a34a1FbeeeeB1Mc02S1b12e13d4Ff", "B33"},
            new String[]{"AFCB1616181614D0001000300", "A2Me04b11S2c13a34a1FbeeeeB2Mc02d33S1b12e1Ff"},
            new String[]{"BF0acde1bcce2cdde3acde4aadeCfB1215131109D0002000304", "A0Me04b11S2c13a34a1FB1Mc02d33S1b12e1F", "B1b3"},
            new String[]{"AF0acde2cdde3acde4aadeCccefB1215131109D0002000304", "A0Me04b11S2c13a34a1FB1Mc02d33S1b12e13b1F", "A2c2"},
            new String[]{"BF0acde3acde4aadeCccddeefB1215131109D0002000304", "A0Me04b11S2c23a34a1FB1Mc02d33S1b12e13b1F", "B3d0"},
            new String[]{"AF0acde4aadeCacccddeeefB1215131109D0002000304", "A0Me04b11S2c23a34a1FB1Mc02d33S0d11b12e13b1F", "A4d1"},
            new String[]{"BF0acdeCaaacccddeeeefB1215131109D0002000304", "A0Me04b11S1d12c23a34a1FB1Mc02d33S0d11b12e13b1F", "B0a4"},
            new String[]{"AFCaaaccccdddeeeeefB1215131109D0002000304", "A0Me04b11S1d12c23a34a1FB1Mc02d33S0d11b12e13b14a1F", "ACc2"},
            new String[]{"BFCaaadddeeeeeB1215131109D0002000304", "A0Me04b11S1d12c33a34a1FcccfB1Mc02d33S0d11b12e13b14a1F", "BCe2"},
            new String[]{"AFCaaadddB1215131109D0002000304", "A0Me04b11S1d12c33a34a1FcccfB1Mc02d33S0d11b12e33b14a1Feee", "ACa4"},
            new String[]{"BFCdddB1215131109D0002000304", "A0Me04b11S1d12c33a34a4FcccfB1Mc02d33S0d11b12e33b14a1Feee", "BCdF"},
            new String[]{"BFCB1215131109D0002000304", "A0Me04b11S1d12c33a34a4FcccfB1Mc02d33S0d11b12e33b14a1Fdddeee", "B04"},
            new String[]{"BFCB1215131109D0002000304", "A0Me04b11S1d12c33a34a4FcccfB2Mc02d04d33S1b12e33b14a1Fdddeee", "B24"},
            new String[]{"AFCB1215131109D0002000306", "A0Me04b11S1d12c33a34a4FcccfB3Mc02d04e24d33S1b13b14a1Fdddeee", "A21"},
            new String[]{"BFCB1215131109D0002020306", "A2Me04b11c21S1d13a34a4FcccfB3Mc02d04e24d33S1b13b14a1Fdddeee"},
            new String[]{"AF0bbbb1bbce2acde3aabe4abeeCfB0807111004D0002050609", "A0Me04b11c21S1d13a34a4FB0Mc02d04e24d33S1b13b14a1F", "A4b0"},
            new String[]{"BF0bbbb1bbce2acde3aabeCaeefB0807111004D0002050609", "A0Me04b11c21S0b11d13a34a4FB0Mc02d04e24d33S1b13b14a1F", "B2c2"},
            new String[]{"AF0bbbb1bbce3aabeCaadeeefB0807111004D0002050609", "A0Me04b11c21S0b11d13a34a4FB0Mc02d04e24d33S1b12c13b14a1F", "ACd1"},
            new String[]{"BF0bbbb1bbce3aabeCaaeeeB0807111004D0002050609", "A0Me04b11c21S0b11d23a34a4FfB0Mc02d04e24d33S1b12c13b14a1F", "B0b3"},
            new String[]{"AF1bbce3aabeCaaeeeB0807111004D0002050609", "A0Me04b11c21S0b11d23a34a4FfB0Mc02d04e24d33S1b12c13b44a1Fb", "A3a3"},
            new String[]{"BF1bbceCaabeeeeB0807111004D0002050609", "A0Me04b11c21S0b11d23a44a4FafB0Mc02d04e24d33S1b12c13b44a1Fb", "B1e0"},
            new String[]{"AFCaabbbceeeeB0807111004D0002050609", "A0Me04b11c21S0b11d23a44a4FafB0Mc02d04e24d33S0e11b12c13b44a1Fb", "ACa2"},
            new String[]{"BFCbbbceeeeB0807111004D0002050609", "A0Me04b11c21S0b11d22a23a44a4FafB0Mc02d04e24d33S0e11b12c13b44a1Fb", "BCc2"},
            new String[]{"AFCbbbeeeeB0807111004D0002050609", "A0Me04b11c21S0b11d22a23a44a4FafB0Mc02d04e24d33S0e11b12c23b44a1Fb", "ACeF"},
            new String[]{"BFCbbbB0807111004D0002050609", "A0Me04b11c21S0b11d22a23a44a4FaeeeefB0Mc02d04e24d33S0e11b12c23b44a1Fb", "BCb1"},
            new String[]{"BFCB0807111004D0002050609", "A0Me04b11c21S0b11d22a23a44a4FaeeeefB0Mc02d04e24d33S0e11b22c23b44a1Fbbb", "B03"},
            new String[]{"BFCB0807111004D0002050609", "A0Me04b11c21S0b11d22a23a44a4FaeeeefB3Mc02e03d04e24d33S1b22c23b44a1Fbbb", "B14"},
            new String[]{"BFCB0807111004D0003050609", "A0Me04b11c21S0b11d22a23a44a4FaeeeefB6Mc02e03d04b14e24d33S2c23b44a1Fbbb", "B31"},
            new String[]{"AFCB0807111004D0006050609", "A0Me04b11c21S0b11d22a23a44a4FaeeeefB7Mc02e03d04b14e24b31d33S2c24a1Fbbb", "A00"},
            new String[]{"AFCB0807111004D0006050609", "A1Mb00e04b11c21S1d22a23a44a4FaeeeefB7Mc02e03d04b14e24b31d33S2c24a1Fbbb", "A10"},
            new String[]{"AFCB0807111004D0006050709", "A5Mb00e04d10b11c21S2a23a44a4FaeeeefB7Mc02e03d04b14e24b31d33S2c24a1Fbbb", "A32"},
            new String[]{"BFCB0807111004D0306050709", "A6Mb00e04d10b11c21a32S2a24a4FaeeeefB7Mc02e03d04b14e24b31d33S2c24a1Fbbb"},
            new String[]{"AF0bddd1abce2acdd3bcde4acceCfB0504060401D0409050713", "A0Mb00e04d10b11c21a32S2a24a4FB3Mc02e03d04b14e24b31d33S2c24a1F", "A4a4"},
            new String[]{"BF0bddd1abce2acdd3bcdeCccefB0504060401D0409050713", "A0Mb00e04d10b11c21a32S2a24a5FB3Mc02e03d04b14e24b31d33S2c24a1F", "B3c3"},
            new String[]{"AF0bddd1abce2acddCbccdeefB0504060401D0409050713", "A0Mb00e04d10b11c21a32S2a24a5FB3Mc02e03d04b14e24b31d33S2c23c14a1F", "A1b3"},
            new String[]{"BF0bddd2acddCabcccdeeefB0504060401D0409050713", "A0Mb00e04d10b11c21a32S2a23b14a5FB3Mc02e03d04b14e24b31d33S2c23c14a1F", "B2d1"},
            new String[]{"AF0bdddCaabccccdeeefB0504060401D0409050713", "A0Mb00e04d10b11c21a32S2a23b14a5FB3Mc02e03d04b14e24b31d33S1d22c23c14a1F", "ACa1"},
            new String[]{"BF0bdddCbccccdeeeB0504060401D0409050713", "A0Mb00e04d10b11c21a32S1a22a23b14a5FfB3Mc02e03d04b14e24b31d33S1d22c23c14a1F", "BCc2"},
            new String[]{"AF0bdddCbdeeeB0504060401D0409050713", "A0Mb00e04d10b11c21a32S1a22a23b14a5FfB3Mc02e03d04b14e24b31d33S1d22c33c14a1Fccc", "ACb3"},
            new String[]{"BF0bdddCdeeeB0504060401D0409050713", "A0Mb00e04d10b11c21a32S1a22a23b24a5FfB3Mc02e03d04b14e24b31d33S1d22c33c14a1Fccc", "B0b0"},
            new String[]{"AFCddddeeeB0504060401D0409050713", "A0Mb00e04d10b11c21a32S1a22a23b24a5FfB3Mc02e03d04b14e24b31d33S0b11d22c33c14a1Fccc", "ACd0"},
            new String[]{"BFCeeeB0504060401D0409050713", "A0Mb00e04d10b11c21a32S0d11a22a23b24a5FdddfB3Mc02e03d04b14e24b31d33S0b11d22c33c14a1Fccc", "BCeF"},
            new String[]{"BFCB0504060401D0409050713", "A0Mb00e04d10b11c21a32S0d11a22a23b24a5FdddfB3Mc02e03d04b14e24b31d33S0b11d22c33c14a1Fccceee", "B00"},
            new String[]{"BFCB0504060401D0409050713", "A0Mb00e04d10b11c21a32S0d11a22a23b24a5FdddfB4Mb00c02e03d04b14e24b31d33S1d22c33c14a1Fccceee", "B12"},
            new String[]{"BFCB0504060401D0409050813", "A0Mb00e04d10b11c21a32S0d11a22a23b24a5FdddfB6Mb00c02e03d04d12b14e24b31d33S2c33c14a1Fccceee", "B21"},
            new String[]{"AFCB0504060401D0409070813", "A0Mb00e04d10b11c21a32S0d11a22a23b24a5FdddfB8Mb00c02e03d04d12b14c21e24b31d33S3c14a1Fccceee", "A03"},
            new String[]{"AFCB0504060401D0409070813", "A2Mb00d03e04d10b11c21a32S1a22a23b24a5FdddfB8Mb00c02e03d04d12b14c21e24b31d33S3c14a1Fccceee", "A14"},
            new String[]{"AFCB0504060401D0509070813", "A4Mb00d03e04d10b11a14c21a32S2a23b24a5FdddfB8Mb00c02e03d04d12b14c21e24b31d33S3c14a1Fccceee", "A43"},
            new String[]{"BFCB0504060401D0909070813", "A5Mb00d03e04d10b11a14c21a32a43S2a23b2FdddfB8Mb00c02e03d04d12b14c21e24b31d33S3c14a1Fccceee"},
            new String[]{"AF0bccc1aabc2abce3acdd4abddCfB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S2a23b2FB0Mb00c02e03d04d12b14c21e24b31d33S3c14a1F", "A2a2"},
            new String[]{"BF0bccc1aabc3acdd4abddCbcefB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S2a33b2FB0Mb00c02e03d04d12b14c21e24b31d33S3c14a1F", "BCb2"},
            new String[]{"AF0bccc1aabc3acdd4abddCceB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S2a33b2FB0Mb00c02e03d04d12b14c21e24b31d33S2b13c14a1Ff", "A1c0"},
            new String[]{"BF0bccc3acdd4abddCaabceB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S0c12a33b2FB0Mb00c02e03d04d12b14c21e24b31d33S2b13c14a1Ff", "B4b2"},
            new String[]{"AF0bccc3acddCaaabcddeB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S0c12a33b2FB0Mb00c02e03d04d12b14c21e24b31d33S2b23c14a1Ff", "ACd4"},
            new String[]{"BF0bccc3acddCaaabceB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S0c12a33b24d2FB0Mb00c02e03d04d12b14c21e24b31d33S2b23c14a1Ff", "B3c3"},
            new String[]{"AF0bcccCaaaabcddeB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S0c12a33b24d2FB0Mb00c02e03d04d12b14c21e24b31d33S2b23c24a1Ff", "A0b3"},
            new String[]{"BFCaaaabccccddeB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S0c12a33b34d2FB0Mb00c02e03d04d12b14c21e24b31d33S2b23c24a1Ff", "BCa1"},
            new String[]{"AFCbccccddeB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S0c12a33b34d2FB0Mb00c02e03d04d12b14c21e24b31d33S1a22b23c24a1Faaf", "ACc1"},
            new String[]{"BFCbddeB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S0c11c22a33b34d2FccB0Mb00c02e03d04d12b14c21e24b31d33S1a22b23c24a1Faaf", "BCb2"},
            new String[]{"AFCddeB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S0c11c22a33b34d2FccB0Mb00c02e03d04d12b14c21e24b31d33S1a22b33c24a1Faaf", "ACd4"},
            new String[]{"BFCeB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S0c11c22a33b34d4FccB0Mb00c02e03d04d12b14c21e24b31d33S1a22b33c24a1Faaf", "BCeF"},
            new String[]{"BFCB0000000000D0909101116", "A0Mb00d03e04d10b11a14c21a32a43S0c11c22a33b34d4FccB0Mb00c02e03d04d12b14c21e24b31d33S1a22b33c24a1Faaef", "B13"},
            new String[]{"BFCB0000000000D1009101116", "A0Mb00d03e04d10b11a14c21a32a43S0c11c22a33b34d4FccB5Mb00c02e03d04d12a13b14c21e24b31d33S2b33c24a1Faaef", "B23"},
            new String[]{"AFCB0000000000D1011101116", "A0Mb00d03e04d10b11a14c21a32a43S0c11c22a33b34d4FccB11Mb00c02e03d04d12a13b14c21b23e24b31d33S3c24a1Faaef", "A02"},
            new String[]{"AFCB0000000000D1011101116", "A3Mb00c02d03e04d10b11a14c21a32a43S1c22a33b34d4FccB11Mb00c02e03d04d12a13b14c21b23e24b31d33S3c24a1Faaef", "A13"},
            new String[]{"AFCB0000000000D1011111116", "A7Mb00c02d03e04d10b11c13a14c21a32a43S2a33b34d4FccB11Mb00c02e03d04d12a13b14c21b23e24b31d33S3c24a1Faaef", "A20"},
            new String[]{"BFCB0000000000D1211111116", "A12Mb00c02d03e04d10b11c13a14a20c21a32a43S3b34d4FccB11Mb00c02e03d04d12a13b14c21b23e24b31d33S3c24a1Faaef"},
            new String[]{"BF0aacc1aacd2abde3aace4abeeCfB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S3b34d4FB5Mb00c02e03d04d12a13b14c21b23e24b31d33S3c24a1F", "B1c1"},
            new String[]{"AF0aacc2abde3aace4abeeCaadfB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S3b34d4FB5Mb00c02e03d04d12a13b14c21b23e24b31d33S1c13c24a1F", "A4e2"},
            new String[]{"BF0aacc2abde3aaceCaaabdfB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S2e23b34d4FB5Mb00c02e03d04d12a13b14c21b23e24b31d33S1c13c24a1F", "B2a2"},
            new String[]{"AF0aacc3aaceCaaabbddefB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S2e23b34d4FB5Mb00c02e03d04d12a13b14c21b23e24b31d33S1c12a13c24a1F", "ACe1"},
            new String[]{"BF0aacc3aaceCaaabbddB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S1e12e23b34d4FfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S1c12a13c24a1F", "BCa4"},
            new String[]{"AF0aacc3aaceCbbddB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S1e12e23b34d4FfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S1c12a13c24a4F", "ACb3"},
            new String[]{"BF0aacc3aaceCddB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S1e12e23b44d4FbfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S1c12a13c24a4F", "B0c3"},
            new String[]{"AF3aaceCaaddB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S1e12e23b44d4FbfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S1c12a13c44a4F", "A3a0"},
            new String[]{"BFCaacddeB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S0a11e12e23b44d4FabfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S1c12a13c44a4F", "BCa0"},
            new String[]{"AFCcddeB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S0a11e12e23b44d4FabfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S0a11c12a13c44a4Fa", "ACe2"},
            new String[]{"BFCcddB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S0a11e12e33b44d4FabfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S0a11c12a13c44a4Fa", "BCc1"},
            new String[]{"AFCddB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S0a11e12e33b44d4FabfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S0a11c22a13c44a4Fa", "ACd4"},
            new String[]{"AFCB0609090913D0000000000", "A10Mb00c02d03e04d10b11c13a14a20c21a32a43S0a11e12e33b44d5FabdfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S0a11c22a13c44a4Fa", "A01"},
            new String[]{"AFCB0609090913D0000000000", "A18Mb00a01c02d03e04d10b11c13a14a20c21a32a43S1e12e33b44d5FabdfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S0a11c22a13c44a4Fa", "A22"},
            new String[]{"AFCB0609090913D0000000002", "A23Mb00a01c02d03e04d10b11c13a14a20c21e22a32a43S1e13b44d5FabdfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S0a11c22a13c44a4Fa", "A33"},
            new String[]{"AFCB0609090913D0003000002", "A27Mb00a01c02d03e04d10b11c13a14a20c21e22a32b33a43S1e14d5FabdfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S0a11c22a13c44a4Fa", "A44"},
            new String[]{"BFCB0609090913D0003000402", "A29Mb00a01c02d03e04d10b11c13a14a20c21e22a32b33a43d44S1e1FabdfB5Mb00c02e03d04d12a13b14c21b23e24b31d33S0a11c22a13c44a4Fa", "B01"},
            new String[]{"BFCB0609090913D0003000402", "A29Mb00a01c02d03e04d10b11c13a14a20c21e22a32b33a43d44S1e1FabdfB10Mb00a01c02e03d04d12a13b14c21b23e24b31d33S1c22a13c44a4Fa", "B10"},
            new String[]{"BFCB0609090913D0003010402", "A29Mb00a01c02d03e04d10b11c13a14a20c21e22a32b33a43d44S1e1FabdfB12Mb00a01c02e03d04c10d12a13b14c21b23e24b31d33S2a13c44a4Fa", "B34"},
            new String[]{"AFCB0609090913D0003040402", "A29Mb00a01c02d03e04d10b11c13a14a20c21e22a32b33a43d44S1e1FabdfB18Mb00a01c02e03d04c10d12a13b14c21b23e24b31d33c34S2a14a4Fa"},
            new String[]{"AFCfB0609090913D0204040502", "A35Mb00a01c02d03e04d10b11c13a14a20c21e22a32b33a43d44S1e1FB19Mb00a01c02e03d04c10d12a13b14c21b23e24b31d33c34S2a14a4F"}
    };

}
