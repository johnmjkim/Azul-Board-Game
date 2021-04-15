package comp1110.ass2;

import java.util.*;

public class Azul implements Constants{

    // Maximum player numbers
    public static int MAX_PLAYER_NUMBER = 2;

    public static String[] gameState;
    public static String move;
    public static SharedState sharedState;
    public static PlayerState playerState;

    // Size, Numbers of all components
    public static final int FACTORY_MAX_NUMBER = 2 * MAX_PLAYER_NUMBER + 1;

    /**
     * Given a shared state string, determine if it is well-formed.
     * Note: you don't need to consider validity for this task.
     * A sharedState is well-formed if it satisfies the following conditions.
     * <p>
     * [turn][factories][centre][bag][discard]
     * where [turn][factories], [centre], [bag] and [discard] are replaced by the
     * corresponding small string as described below.
     * <p>
     * 0. [turn] The Turn substring is one character 'A'-'D' representing a
     * player, which indicates that it is this player's turn to make the next
     * drafting move. (In a two-player game, the turn substring can only take
     * the values 'A' or 'B').
     * <p>
     * 1. [factories] The factories substring begins with an 'F'
     * and is followed by a collection of *up to* 5 5-character factory strings
     * representing each factory.
     * Each factory string is defined in the following way:
     * 1st character is a sequential digit '0' to '4' - representing the
     * factory number.
     * 2nd - 5th characters are 'a' to 'e', alphabetically - representing
     * the tiles.
     * A factory may have between 0 and 4 tiles. If a factory has 0 tiles,
     * it does not appear in the factories string.
     * Factory strings are ordered by factory number.
     * For example: given the string "F1aabc2abbb4ddee": Factory 1 has tiles
     * 'aabc', Factory 2 has tiles 'abbb', Factory 4 has tiles 'ddee', and
     * Factories 0 and 4 are empty.
     * <p>
     * 2. [centre] The centre substring starts with a 'C'
     * This is followed by *up to* 15 characters.
     * Each character is 'a' to 'e', alphabetically - representing a tile
     * in the centre.
     * The centre string is sorted alphabetically.
     * For example: "Caaabcdde" The Centre contains three 'a' tiles, one 'b'
     * tile, one 'c' tile, two 'd' tile and one 'e' tile.
     * <p>
     * 3. [bag] The bag substring starts with a 'B'
     * and is followed by 5 2-character substrings
     * 1st substring represents the number of 'a' tiles, from 0 - 20.
     * 2nd substring represents the number of 'b' tiles, from 0 - 20.
     * 3rd substring represents the number of 'c' tiles, from 0 - 20.
     * 4th substring represents the number of 'd' tiles, from 0 - 20.
     * 5th substring represents the number of 'e' tiles, from 0 - 20.
     * <p>
     * For example: "B0005201020" The bag contains zero 'a' tiles, five 'b'
     * tiles, twenty 'c' tiles, ten 'd' tiles and twenty 'e' tiles.
     * 4. [discard] The discard substring starts with a 'D'
     * and is followed by 5 2-character substrings defined the same as the
     * bag substring.
     * For example: "D0005201020" The bag contains zero 'a' tiles, five 'b'
     * tiles, twenty 'c' tiles, ten 'd' tiles, and twenty 'e' tiles.
     *
     * @param sharedState the shared state - factories, bag and discard.
     * @return true if sharedState is well-formed, otherwise return false
     * TASK 2
     */
    // isSharedStateWellFormed() checks if the SharedState Well Formed.
    // This is “1. Game Setup”.
    public static boolean isSharedStateWellFormed(String sharedState) {
        // FIXME Task 2

        char[] sharedState_array = sharedState.toCharArray();
        ArrayList<Character> sharedState_name_arr = new ArrayList<Character>();
        ArrayList<String> sharedState_content_arr = new ArrayList<String>();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        // Filter valid capital letters
        for (char c : sharedState_array) {
            //System.out.println(c);
            if (c >= PLAYER_A && c < ALL_PLAYERS[MAX_PLAYER_NUMBER] || c == FACTORY || c == CENTER || c == BAG || c == DISCARD) {
                //System.out.println(String.valueOf(c));
                sharedState_name_arr.add(c);
                sharedState_content_arr.add(String.valueOf(SB));
                SB.delete(0, SB.length());
                len++;
            } else {
                SB.append(c);
            }
        }
        sharedState_content_arr.add(String.valueOf(SB));
        SB.delete(0, SB.length());
        sharedState_content_arr.remove(0);

        /*
        System.out.println(sharedState);
        for (int i = 0; i < len; i++) {
            System.out.println(sharedState_name_arr.get(i) + ", " + sharedState_content_arr.get(i));
        }

         */

        // Find capital letters valid
        if (sharedState_name_arr.size() != 5) {
            System.out.println(sharedState_name_arr.size() == 5);
            return false;
        }
        // Player is valid
        boolean s_player_format = check_s_player_format(sharedState_name_arr.get(0), sharedState_content_arr.get(0));
        // Factory is valid
        boolean factory_format = check_factory_format(sharedState_name_arr.get(1), sharedState_content_arr.get(1));
        // Center is valid
        boolean center_format = check_center_format(sharedState_name_arr.get(2), sharedState_content_arr.get(2));
        // Bag is valid
        boolean bag_format = check_bag_discard_format(sharedState_name_arr.get(3), sharedState_content_arr.get(3), 'B');
        // Discard is valid
        boolean discard_format = check_bag_discard_format(sharedState_name_arr.get(4), sharedState_content_arr.get(4), 'D');
        //System.out.println(s_player_format && factory_format && center_format && bag_format && discard_format);
        return s_player_format && factory_format && center_format && bag_format && discard_format;

    }

    public static boolean check_s_player_format(char s_player_char, String s_player_String) {
        // Find capital letters valid
        boolean s_player_name_format = (s_player_char >= PLAYER_A && s_player_char <= ALL_PLAYERS[MAX_PLAYER_NUMBER - 1] );

        // S_Player is valid
        boolean s_player_format = s_player_name_format && s_player_String.isEmpty();
        //System.out.println(s_player_name_format + ", " + s_player_String.isEmpty());
        return s_player_format;
    }

    public static boolean check_factory_format(char factory_char, String factory_String) {
        int len = 0;
        boolean factory_name_format = (factory_char == FACTORY );
        boolean factory_content_format = true;

        // Factory numerical, alphabetical order reflected, factory tiles count needed
        boolean factory_alphabetical_order = true;
        boolean factory_numerical_order = true;
        boolean factory_tilesnumber = true;

        StringBuilder SB = new StringBuilder();

        ArrayList<Character> factoryState_name_arr = new ArrayList<Character>();
        ArrayList<String> factoryState_content_arr = new ArrayList<String>();
        for (char c : factory_String.toCharArray()) {
            //System.out.println(c);
            if (c >= ZERO && c <= FACTORY_MAX_INDICES[MAX_PLAYER_NUMBER - 2]) {
                //System.out.println(String.valueOf(c));
                factoryState_name_arr.add(c);
                factoryState_content_arr.add(String.valueOf(SB));
                SB.delete(0, SB.length());
                len++;
            } else {
                SB.append(c);
            }
        }
        factoryState_content_arr.add(String.valueOf(SB));
        SB.delete(0, SB.length());
        factoryState_content_arr.remove(0);

        /*
        System.out.println(factory_String);
        for (int i = 0; i < len; i++) {
            System.out.println(factoryState_name_arr.get(i) + ", " + factoryState_content_arr.get(i));
        }

         */

        if(len > 1){
            if(!check_alphabetical_order(factoryState_content_arr.get(0))){
                factory_alphabetical_order = false;
            }
            if(!(factoryState_content_arr.get(0).length() == FACTORY_SIZE)){
                factory_tilesnumber = false;
            }
            for(int i = 1; i < len; i++){
                if(!check_alphabetical_order(factoryState_content_arr.get(i))){
                    factory_alphabetical_order = false;
                }
                if(!(factoryState_content_arr.get(i).length() == FACTORY_SIZE)){
                    factory_tilesnumber = false;
                }
                if(Character.getNumericValue(factoryState_name_arr.get(i)) < Character.getNumericValue(factoryState_name_arr.get(i-1))){
                    factory_numerical_order = false;
                }
            }
        }
        else if(len ==1){
            factory_alphabetical_order = check_alphabetical_order(factoryState_content_arr.get(0));
        }
        factory_content_format = factory_numerical_order && factory_alphabetical_order && factory_tilesnumber;

        /*
        for (char c : factory_String.toCharArray()) {
            if (len % 5 == 0) {
                if (!(c >= ZERO && c <= FACTORY_MAX_INDICES[MAX_PLAYER_NUMBER - 2])) {
                    factory_content_format = false;
                }
            } else {
                if (!(c >= BLUE && c <= RED)) {
                    factory_content_format = false;
                }
            }
            len++;
        }
        if (!(len % 5 == 0)) {
            factory_content_format = false;
        }

         */

        boolean factory_format = factory_name_format && factory_content_format;
        //System.out.println(factory_name_format + ", " + factory_content_format);

        return factory_format;
    }

    public static boolean check_center_format(char center_char, String center_String) {
        boolean center_name_format = (center_char == CENTER);
        int len = 0;
        boolean center_content_format = true;
        center_content_format = check_alphabetical_order(center_String);
        for (char c : center_String.toCharArray()) {
            if (!(c >= BLUE && c <= FIRST_PLAYER)) {
                center_content_format = false;
            }
            len++;
        }
        if (len > 3 * FACTORY_MAX_NUMBER) {
            center_content_format = false;
        }
        boolean center_format = center_name_format && center_content_format;
        //System.out.println(center_name_format + ", " + center_content_format);
        return center_format;
    }

    public static boolean check_bag_discard_format(char bag_discard_char, String bag_discard_String, char compare) {
        boolean bag_discard_name_format = (bag_discard_char == compare);
        boolean bag_discard_content_format = true;
        StringBuilder SB = new StringBuilder();
        int len = 0;
        for (char c : bag_discard_String.toCharArray()) {
            SB.append(c);
            if (len >= 1 && len <= 10) {
                if (len % 2 == 1) {
                    if (!(Integer.valueOf(SB.toString()) >= 0 && Integer.valueOf(SB.toString()) <= 20)) {
                        bag_discard_content_format = false;
                    }
                    SB.delete(0, SB.length());
                }
            } else {
            }
            len++;
        }
        if (!(len == 10)) {
            bag_discard_content_format = false;
        }
        SB.delete(0, SB.length());
        boolean bag_discard_format = bag_discard_name_format && bag_discard_content_format && !bag_discard_String.isEmpty();
        //System.out.println(bag_discard_name_format + ", " + bag_discard_content_format + ", " + !bag_discard_String.isEmpty());
        return bag_discard_format;
    }

    public static boolean check_alphabetical_order( String alphabet_string ){
        boolean string_alphabet = true;
        boolean string_alphabetical_order = true;
        char[] alphabet_string_char_arr = alphabet_string.toCharArray();
        char[] alphabet_string_char_arr_sort = alphabet_string.toCharArray();
        Arrays.sort(alphabet_string_char_arr_sort);

        int idx = 0;
        for( char c : alphabet_string_char_arr){
            if(!(c >= BLUE && c <= FIRST_PLAYER)){
                string_alphabet = false;
            }
            if(c != alphabet_string_char_arr_sort[idx]){
                string_alphabetical_order = false;
            }
            idx++;
        }

        return string_alphabet && string_alphabetical_order;
    }

    /**
     * Given a playerState, determine if it is well-formed.
     * Note: you don't have to consider validity for this task.
     * A playerState is composed of individual playerStrings.
     * A playerState is well-formed if it satisfies the following conditions.
     * <p>
     * A playerString follows this pattern: [player][score][mosaic][storage][floor]
     * where [player], [score], [mosaic], [storage] and [floor] are replaced by
     * a corresponding substring as described below.
     * Each playerString is sorted by Player i.e. Player A appears before Player B.
     * <p>
     * 1. [player] The player substring is one character 'A' to 'D' -
     * representing the Player
     * <p>
     * 2. [score] The score substring is one or more digits between '0' and '9' -
     * representing the score
     * <p>
     * 3. [mosaic] The Mosaic substring begins with a 'M'
     * Which is followed by *up to* 25 3-character strings.
     * Each 3-character string is defined as follows:
     * 1st character is 'a' to 'e' - representing the tile colour.
     * 2nd character is '0' to '4' - representing the row.
     * 3rd character is '0' to '4' - representing the column.
     * The Mosaic substring is ordered first by row, then by column.
     * That is, "a01" comes before "a10".
     * <p>
     * 4. [storage] The Storage substring begins with an 'S'
     * and is followed by *up to* 5 3-character strings.
     * Each 3-character string is defined as follows:
     * 1st character is '0' to '4' - representing the row - each row number must only appear once.
     * 2nd character is 'a' to 'e' - representing the tile colour.
     * 3rd character is '0' to '5' - representing the number of tiles stored in that row.
     * Each 3-character string is ordered by row number.
     * <p>
     * 5. [floor] The Floor substring begins with an 'F'
     * and is followed by *up to* 7 characters in alphabetical order.
     * Each character is 'a' to 'f' - where 'f' represents the first player token.
     * There is only one first player token.
     * <p>
     * An entire playerState for 2 players might look like this:
     * "A20Ma02a13b00e42S2a13e44a1FaabbeB30Mc01b11d21S0e12b2F"
     * If we split player A's string into its substrings, we get:
     * [A][20][Ma02a13b00e42][S2a13e44a1][Faabbe].
     *
     * @param playerState the player state string
     * @return True if the playerState is well-formed,
     * false if the playerState is not well-formed
     * TASK 3
     */
    // isPlayStateWellFormed() checks if the PlayerState Well Formed.
    // This is “1. Game Setup”.
    public static boolean isPlayerStateWellFormed(String playerState) {
        // FIXME Task 3
        char[] playerState_array = playerState.toCharArray();
        ArrayList<Character> playerState_name_arr = new ArrayList<Character>();
        ArrayList<String> playerState_content_arr = new ArrayList<String>();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        // Filter valid capital letters
        for (char c : playerState_array) {
            //System.out.println(c);
            if ((c >= PLAYER_A && c < ALL_PLAYERS[MAX_PLAYER_NUMBER]) || c == MOSAIC || c == STORAGE || c == FLOOR) {
                //System.out.println(String.valueOf(c));
                playerState_name_arr.add(c);
                playerState_content_arr.add(String.valueOf(SB));
                SB.delete(0, SB.length());
                len++;
            } else {
                SB.append(c);
            }
        }
        playerState_content_arr.add(String.valueOf(SB));
        SB.delete(0, SB.length());
        playerState_content_arr.remove(0);

        /*
        System.out.println(playerState);
        for (int i = 0; i < len; i++) {
            System.out.println(playerState_name_arr.get(i) + ", " + playerState_content_arr.get(i));
        }
        
         */

        // Find capital letters valid 1 : filter by size
        if (!(playerState_name_arr.size() % 4 == 0)) {
            return false;
        }
        // Find capital letters valid 2 : filter by order
        len = 0;
        int toggle_int = 0, player_int = 0;
        for (char c : playerState_name_arr) {
            toggle_int = len % 4;
            if (toggle_int == 0) {
                if (!(c == ALL_PLAYERS[player_int])) {
                    return false;
                }
                player_int++;
            } else if (toggle_int == 1) {
                if (!(c == MOSAIC)) {
                    return false;
                }
            } else if (toggle_int == 2) {
                if (!(c == STORAGE)) {
                    return false;
                }
            } else if (toggle_int == 3) {
                if (!(c == FLOOR)) {
                    return false;
                }
            }
            len++;
        }

        // Player is valid
        boolean p_player_format = true;
        boolean score_format = true;
        boolean mosaic_format = true;
        boolean storage_format = true;
        boolean floor_format = true;

        int floor_counts = 0;
        for (int i = 0; i < player_int; i++) {
            // Player is valid
            p_player_format = p_player_format && check_p_player_format(playerState_name_arr.get(4 * i));
            // Score is valid
            score_format = score_format && check_score_format(playerState_content_arr.get(4 * i));
            // Mosaic is valid
            mosaic_format = mosaic_format && check_mosaic_format(playerState_name_arr.get(4 * i + 1), playerState_content_arr.get(4 * i + 1));
            // Storage is valid
            storage_format = storage_format && check_storage_format(playerState_name_arr.get(4 * i + 2), playerState_content_arr.get(4 * i + 2));
            // Floor is valid
            floor_format = floor_format && check_floor_format(playerState_name_arr.get(4 * i + 3), playerState_content_arr.get(4 * i + 3), floor_counts);
            floor_counts = toss_floor_count(playerState_content_arr.get(4 * i + 3), floor_counts);
        }
        return p_player_format && score_format && mosaic_format && storage_format && floor_format;
    }

    public static boolean check_p_player_format(char p_player_char) {
        // Find capital letters valid
        boolean p_player_name_format = (p_player_char >= PLAYER_A && p_player_char <= ALL_PLAYERS[MAX_PLAYER_NUMBER]);

        // P_Player is valid
        boolean p_player_format = p_player_name_format;
        //System.out.println(p_player_name_format);
        return p_player_format;
    }

    public static boolean check_score_format(String score_String) {
        // Find capital letters valid
        boolean score_name_format = true;
        for (char c : score_String.toCharArray()) {
            if (!(c >= ZERO && c <= NINE)) {
                score_name_format = false;
            }
        }
        // P_Player is valid
        boolean score_format = score_name_format && !score_String.isEmpty();
        //System.out.println(score_format + ", " + !score_String.isEmpty());
        return score_format;
    }

    public static boolean check_mosaic_format(char mosaic_char, String mosaic_String) {
        boolean mosaic_name_format = (mosaic_char == MOSAIC);
        int len = 0;
        boolean mosaic_content_format = true;
        for (char c : mosaic_String.toCharArray()) {
            if (len % 3 == 0) {
                if (!(c >= BLUE && c <= RED)) {
                    mosaic_content_format = false;
                }
            } else {
                if (!(c >= ZERO && c <= FOUR)) {
                    mosaic_content_format = false;
                }
            }
            len++;
        }
        if (len > 75) {
            mosaic_content_format = false;
        } else if (mosaic_String.isEmpty()) {
            mosaic_content_format = true;
        }
        boolean center_format = mosaic_name_format && mosaic_content_format;
        //System.out.println(mosaic_name_format + ", " + mosaic_content_format);
        return center_format;
    }

    public static boolean check_storage_format(char storage_char, String storage_String) {
        boolean storage_name_format = (storage_char == STORAGE);
        int len = 0;
        boolean storage_content_format = true;
        for (char c : storage_String.toCharArray()) {
            if (len % 3 == 0) {
                if (!(c >= ZERO && c <= FOUR)) {
                    storage_content_format = false;
                }
            } else if (len % 3 == 1) {
                if (!(c >= BLUE && c <= RED)) {
                    storage_content_format = false;
                }
            } else if (len % 3 == 2) {
                if (!(c >= ZERO && c <= FIVE)) {
                    storage_content_format = false;
                }
            }
            len++;
        }
        if (len > 15) {
            storage_content_format = false;
        } else if (storage_String.isEmpty()) {
            storage_content_format = true;
        }
        boolean storage_format = storage_name_format && storage_content_format;
        //System.out.println(storage_name_format + ", " + storage_content_format);
        return storage_format;
    }

    public static boolean check_floor_format(char floor_char, String floor_String, int floor_counts) {
        boolean floor_name_format = (floor_char == FLOOR);
        int len = 0;
        boolean floor_content_format = true;
        for (char c : floor_String.toCharArray()) {
            if (!(c >= BLUE && c <= FIRST_PLAYER)) {
                floor_content_format = false;
            }
            if (c == FIRST_PLAYER) {
                floor_counts++;
                if (floor_counts > 1) {
                    floor_content_format = false;
                }
            }
            len++;
        }
        if (len > 7) {
            floor_content_format = false;
        } else if (floor_String.isEmpty()) {
            floor_content_format = true;
        }
        boolean storage_format = floor_name_format && floor_content_format;
        //System.out.println(floor_name_format + ", " + floor_content_format);
        return storage_format;
    }

    public static int toss_floor_count(String floor_String, int floor_counts) {
        for (char c : floor_String.toCharArray()) {
            if (c == FIRST_PLAYER) {
                floor_counts++;
            }
        }
        return floor_counts;
    }

    /**
     * Given the gameState, draw a *random* tile from the bag.
     * If the bag is empty, refill the the bag with the discard pile and then draw a tile.
     * If the discard pile is also empty, return 'Z'.
     *
     * @param gameState the current game state
     * @return the tile drawn from the bag, or 'Z' if the bag and discard pile are empty.
     * TASK 5
     */

    // drawTileFromBag() is to draw a "random" tile from the bag.
    //This is “2. Starting the round”.
    public static char drawTileFromBag(String[] gameState) {
        // FIXME Task 5
        sharedState = new SharedState(gameState[0], MAX_PLAYER_NUMBER);
        return sharedState.bag.getRandomTile();
        //SharedState ss = new SharedState(gameState[0], 2);
        //return ss.bag.getRandomTile();

    }

    /**
     * Given a state, refill the factories with tiles.
     * If the factories are not all empty, return the given state.
     *
     * @param gameState the state of the game.
     * @return the updated state after the factories have been filled or
     * the given state if not all factories are empty.
     * TASK 6
     */

    // refillFactories() is to refill the Factories with tiles.
    //This is “2. Starting the round”
    public static String[] refillFactories(String[] gameState) {
        // FIXME Task 6
        String[] output_gameState = new String[2];
        output_gameState[0] = gameState[0];
        output_gameState[1] = gameState[1];
        sharedState = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);

        if(sharedState.factories.isFactoryFull()){
            //System.out.println("Factory Full");
            //System.out.println();
            return gameState;
        }
        else{
            //System.out.println("Factory Not Full");
            sharedState.refillFactory();
            //System.out.println("Factory filled");
            //SharedState.printState();
            //System.out.println();
            output_gameState[0] = sharedState.getSharedState();
            return output_gameState;
        }

        /*
        SharedState ss = new SharedState(gameState[0], 2);
        ss.printState();
        //azulShareds.printFactory();
        if(ss.factories.isFactoryFull()){
            System.out.println("Factory Full");
            System.out.println();
            return gameState;
        }
        else{
            System.out.println("Factory Not Full");
            ss.refillFactory();
            System.out.println("Factory filled");
            ss.printState();
            System.out.println();
            gameState[0] = ss.getSharedState();
            return gameState;
        }

         */

    }

    /**
     * Given a gameState for a completed game,
     * return bonus points for rows, columns, and sets.
     *
     * @param gameState a completed game state
     * @param player    the player for whom the score is to be returned
     * @return the number of bonus points awarded to this player for rows,
     * columns, and sets
     * TASK 7
     */
    // getBonusPoints() is to calculate the Score when Game completed.
    // This is “7. End of the Game”
    public static int getBonusPoints(String[] gameState, char player) {
        // FIXME Task 7
        int row_tiles, col_tiles, color_tiles;
        int player_turn;
        int bonus_points = 0;
        sharedState = new SharedState(gameState[0], MAX_PLAYER_NUMBER);
        playerState = new PlayerState(gameState[1], MAX_PLAYER_NUMBER);

        if(player == PLAYER_A){
            player_turn = 0;
        }
        else if(player == PLAYER_B){
            player_turn = 1;
        }
        else if(player == PLAYER_C){
            player_turn = 2;
        }
        else{
            player_turn = 3;
        }

        for(int i=0; i < MAX_MOSAIC_ROW; i++){
            row_tiles = playerState.nplayers.get(player_turn).mosaic.mosaic_rows.get(i).getTotalTilesNumber();
            if(row_tiles == MAX_MOSAIC_ROW){
                bonus_points += ROW_BONUS_POINT;
            }
        }

        for(int i=0; i < MAX_MOSAIC_COL; i++){
            col_tiles = playerState.nplayers.get(player_turn).mosaic.mosaic_cols.get(i).getTotalTilesNumber();
            if(col_tiles == MAX_MOSAIC_COL){
                bonus_points += COL_BONUS_POINT;
            }
        }

        char color = BLUE;
        for(int i=0; i < RED - BLUE; i++){
            color_tiles = playerState.nplayers.get(player_turn).mosaic.getTilesNumber(color);
            if(color_tiles == MAX_MOSAIC_COLOR){
                bonus_points += COLOR_BONUS_POINT;
            }
            color++;
        }

        return bonus_points;
        // Sibo Hu's work
        /*
        //find where M starts and then print out the information,give the points,print out results
        //row 0, column 0 (0,0); 5 rows same value +2, 5 columns same value +7,5 same type of tile +10,
        //"b00a02a13e42" means there is one b tile located at row 0, column 0 (0,0) two a tiles located at (0,2) and (1,3)
        if (player == 'A') {
            //get the 2 player's MosaicState use M to divide
            //String palyer_A_State = gameState[1].substring(gameState[1].indexOf("M"), gameState[1].indexOf("S"));
            String palyer_A_State = playerState.nplayers.get(0).mosaic.getMosaicState();
            String[] palyer_A = palyer_A_State.split("");

            String row_A = "";
            String column_A = "";
            String alphabet_A = "";
            int BonusPointsofA = 0;

            //get player A's kind of tile
            for (int i = 1; i < palyer_A.length; i += 3) {
                alphabet_A += palyer_A[i];
            }
            //get player A's point of row
            for (int i = 2; i < palyer_A.length; i += 3) {
                row_A += palyer_A[i];
            }
            //get player A's point of column
            for (int i = 3; i < palyer_A.length; i += 3) {
                column_A += palyer_A[i];
            }

            //output the compressString
            alphabet_A = compressString(sortChar(alphabet_A));
            row_A = compressString(row_A);
            column_A = compressString(sortChar(column_A));

            String[] alphabetofA = alphabet_A.split("");
            String[] rowofA = row_A.split("");
            String[] columnofA = column_A.split("");

            for (int i = 1; i < alphabet_A.length(); i += 2) {
                if (alphabetofA[i].equals("5")) {
                    BonusPointsofA += 10;
                }
            }
            for (int i = 1; i < row_A.length(); i += 2) {
                if (rowofA[i].equals("5")) {
                    BonusPointsofA += 2;
                }
            }
            for (int i = 1; i < column_A.length(); i += 2) {
                if (columnofA[i].equals("5")) {
                    BonusPointsofA += 7;
                }
            }

            return BonusPointsofA;
        }

        if (player == 'B') {
            //String palyer_B_State = gameState[1].substring(gameState[1].indexOf("B") + 3, gameState[1].indexOf("SF"));
            String palyer_B_State = playerState.nplayers.get(1).mosaic.getMosaicState();
            String[] palyer_B = palyer_B_State.split("");

            String row_B = "";
            String column_B = "";
            String alphabet_B = "";
            int BonusPointsofB = 0;

            //get player B's kind of tile
            for (int i = 1; i < palyer_B.length; i += 3) {
                alphabet_B += palyer_B[i];
            }
            //get player B's point of row
            for (int i = 2; i < palyer_B.length; i += 3) {
                row_B += palyer_B[i];
            }
            //get player B's point of column
            for (int i = 3; i < palyer_B.length; i += 3) {
                column_B += palyer_B[i];
            }

            //output the compressString
            alphabet_B = compressString(sortChar(alphabet_B));
            row_B = compressString(row_B);
            column_B = compressString(sortChar(column_B));

            String[] alphabetofB = alphabet_B.split("");
            String[] rowofB = row_B.split("");
            String[] columnofB = column_B.split("");


            for (int i = 1; i < row_B.length(); i += 2) {
                if (rowofB[i].equals("5")) {
                    BonusPointsofB += 2;
                }
            }

            for (int i = 1; i < alphabet_B.length(); i += 2) {
                if (alphabetofB[i].equals("5")) {
                    BonusPointsofB += 10;
                }
            }

            for (int i = 1; i < column_B.length(); i += 2) {
                if (columnofB[i].equals("5")) {
                    BonusPointsofB += 7;
                }
            }

            return BonusPointsofB;
        }

        return 0;

         */

    }

    public static String compressString(String str) {
        int length = str.length();
        String str0 = "";
        String str1 = "";
        int n = 1;
        if (!str.equals(" ")) {
            for (int i = 0; i < length; i++) {
                if (i != length - 1) {
                    if (str.charAt(i) == str.charAt(i + 1)) {
                        n++;
                    } else {
                        str0 += "" + str.charAt(i) + n;
                        n = 1;
                    }
                } else {
                    str0 += "" + str.charAt(i) + n;
                }
            }
        } else {
            str0 = "";
        }
        for (int j = 0; j < str0.length(); j += 2) {
            if (str0.charAt(j + 1) == '1') {
                str1 += "" + str0.charAt(j);
            } else {
                str1 += "" + str0.charAt(j) + str0.charAt(j + 1);
            }
        }
        return str1;
    }

    private static String sortChar(String str) {
        char[] chs = stringToArray(str);
        sort(chs);
        return toString(chs);
    }

    private static String toString(char[] chs) {
        return new String(chs);
    }

    private static void sort(char[] chs) {
        Arrays.sort(chs);
    }

    private static char[] stringToArray(String string) {
        return string.toCharArray();
    }

    /**
     * Given a valid gameState prepare for the next round.
     * 1. Empty the floor area for each player and adjust their score accordingly (see the README).
     * 2. Refill the factories from the bag.
     * * If the bag is empty, refill the bag from the discard pile and then
     * (continue to) refill the factories.
     * * If the bag and discard pile do not contain enough tiles to fill all
     * the factories, fill as many as possible.
     * * If the factories and centre contain tiles other than the first player
     * token, return the current state.
     *
     * @param gameState the game state
     * @return the state for the next round.
     * TASK 8
     */

    // emptyFloor() is to empty the floor area.
    // drawTileFromBag() is to draw the Tiles from Bag.
    // refillFactories() is to refill the Factories from the Bag.
    // This is “5. Preparing for next round”
    public static String[] nextRound(String[] gameState) {
        // FIXME TASK 8
        // Store gameState information
        String[] output_gameState = new String[2];
        output_gameState[0] = gameState[0];
        output_gameState[1] = gameState[1];
        sharedState = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
        playerState = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);

        // Find if it is time to progress next round
        String current_player = sharedState.turnState;
        int current_player_idx = 0;
        for(int i=0; i < MAX_PLAYER_NUMBER; i++){
            if(ALL_PLAYERS[i] == current_player.charAt(0)){
                current_player_idx = i;
            }
        }

        boolean isFactoryEmpty = sharedState.factories.isStateEmpty();
        boolean isCenterEmpty = sharedState.center.isStateEmpty();
        boolean existsFullStorageRow = playerState.existsPlayerFullStorageRow();
        boolean isNextRound = isFactoryEmpty && isCenterEmpty && !existsFullStorageRow;

        // Find if current player is first player
        boolean isCurrentFirstPlayer = playerState.nplayers.get(current_player_idx).floor.hasFirstPlayerToken();

        // Find if game is end
        boolean isEndofGame = playerState.isEndofGame();

        // Print shared and player state
        System.out.println(" Before next round ");
        System.out.println(sharedState.getSharedState());
        System.out.println(playerState.getPlayerState());
        System.out.println();

        if(!isNextRound){
            // Return current state if it is not the time to progress next round
            System.out.println(" It is not next round ");
            System.out.println(sharedState.getSharedState());
            System.out.println(playerState.getPlayerState());
            System.out.println();

            output_gameState[0] = sharedState.getSharedState();
            output_gameState[1] = playerState.getPlayerState();

            return output_gameState;
        }
        else{
            // Time to progress next round
            System.out.println(" It is next round ");
            System.out.println(sharedState.getSharedState());
            System.out.println(playerState.getPlayerState());
            System.out.println();

            output_gameState[0] = sharedState.getSharedState();
            output_gameState[1] = playerState.getPlayerState();

            output_gameState = clearFloor(output_gameState);

            // Check if it is end of the game
            if(isEndofGame){
                char ender = playerState.getEnder();
                int ender_idx = 0;
                for(int i=0; i < MAX_PLAYER_NUMBER; i++){
                    if(ALL_PLAYERS[i] == current_player.charAt(0)){
                        ender_idx = i;
                    }
                }
                // Game ended and add bonus point to one who ended
                int bonus_points = getBonusPoints(output_gameState, ALL_PLAYERS[ender_idx]);
                //playerState.nplayers.get(ender_idx).score.addScore(bonus_points);
                System.out.println(" It is end of game, ender is : " + ender + " , bonus points are : " + bonus_points);

                // Set First Player token at that center and end the game
                sharedState.center.addTile(FIRST_PLAYER);
                output_gameState[0] = sharedState.getSharedState();
                output_gameState[1] = playerState.getPlayerState();

                sharedState.setSharedState(output_gameState[0],MAX_PLAYER_NUMBER);
                playerState.setPlayerState(output_gameState[1],MAX_PLAYER_NUMBER);

                System.out.println(sharedState.getSharedState());
                System.out.println(playerState.getPlayerState());
                System.out.println();
            }
            else {
                System.out.println(" It is not end of game ");

                // Game is not end and refill factories
                output_gameState = refillFactories(output_gameState);

                sharedState.setSharedState(output_gameState[0],MAX_PLAYER_NUMBER);
                playerState.setPlayerState(output_gameState[1],MAX_PLAYER_NUMBER);

                System.out.println(sharedState.getSharedState());
                System.out.println(playerState.getPlayerState());
                System.out.println();

                // Start the turn to first player for the next round
                if(!isCurrentFirstPlayer){
                    sharedState.changeTurn();
                }

                System.out.println(sharedState.getSharedState());
                System.out.println(playerState.getPlayerState());
                System.out.println();

                output_gameState[0] = sharedState.getSharedState();
                output_gameState[1] = playerState.getPlayerState();
            }

            System.out.println(" Next round ready ");
            System.out.println(output_gameState[0]);
            System.out.println(output_gameState[1]);
            System.out.println();

            return output_gameState;
        }

        /*
        int max_player_number = 2;
        SharedState ss = new SharedState(gameState[0], max_player_number);
        PlayerState ps = new PlayerState(gameState[1], max_player_number);

        int tot_tiles = 0;
        String tiles_to_discard;

        // Print shared and player state
        System.out.println(" Before next round ");
        System.out.println(ss.getSharedState());
        System.out.println(ps.getPlayerState());
        System.out.println();

        for(int i=0; i < max_player_number; i++){
            // Get number of total tiles in floor to discard for each players
            tot_tiles = ps.nplayers.get(i).floor.getTotalTilesNumber();
            tiles_to_discard = ps.nplayers.get(i).floor.getFloorTilesString();

            // Adjust score, remove tiles from floor to discard for each player accordingly
            ps.nplayers.get(i).score.clearFloorScore(tot_tiles);
            ps.nplayers.get(i).floor.removeAllTiles();
            ss.discard.refillTilesDiscard(tiles_to_discard);

            System.out.println(" Player " + i + " Floor Cleared ");
            System.out.println(ss.getSharedState());
            System.out.println(ps.getPlayerState());
            System.out.println();
        }

        gameState[0] = ss.getSharedState();
        gameState[1] = ps.getPlayerState();

        System.out.println("Refill all factories");

        gameState = refillFactories(gameState);

        System.out.println(gameState[0]);
        System.out.println(gameState[1]);
        System.out.println();

        return gameState;

         */
    }

    public static String[] clearFloor(String[] gameState) {
        // Store gameState information
        String[] output_gameState = new String[2];
        output_gameState[0] = gameState[0];
        output_gameState[1] = gameState[1];
        sharedState = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
        playerState = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);

        int tot_tiles = 0;
        String tiles_to_discard;

        // Clear the floor of each player
        for(int i=0; i < MAX_PLAYER_NUMBER; i++){
            // Get number of total tiles in floor to discard for each players
            tot_tiles = playerState.nplayers.get(i).floor.getTotalTilesNumber();
            tiles_to_discard = playerState.nplayers.get(i).floor.getFloorTilesString();

            // Adjust score, remove tiles from floor to discard for each player accordingly
            playerState.nplayers.get(i).score.clearFloorScore(tot_tiles);
            playerState.nplayers.get(i).floor.removeAllTiles();
            sharedState.discard.refillTilesDiscard(tiles_to_discard);

            System.out.println(" Player " + ALL_PLAYERS[i] + " Floor Cleared ");
            System.out.println(sharedState.getSharedState());
            System.out.println(playerState.getPlayerState());
            System.out.println();
        }

        output_gameState[0] = sharedState.getSharedState();
        output_gameState[1] = playerState.getPlayerState();
        return output_gameState;
    }

    /**
     * Given an entire game State, determine whether the state is valid.
     * A game state is valid if it satisfies the following conditions.
     * <p>
     * [General]
     * 1. The game state is well-formed.
     * 2. There are no more than 20 of each colour of tile across all player
     * areas, factories, bag and discard
     * 3. Exactly one first player token 'f' must be present across all player
     * boards and the centre.
     * <p>
     * [Mosaic]
     * 1. No two tiles occupy the same location on a single player's mosaic.
     * 2. Each row contains only 1 of each colour of tile.
     * 3. Each column contains only 1 of each colour of tile.
     * [Storage]
     * 1. The maximum number of tiles stored in a row must not exceed (row_number + 1).
     * 2. The colour of tile stored in a row must not be the same as a colour
     * already found in the corresponding row of the mosaic.
     * <p>
     * [Floor]
     * 1. There are no more than 7 tiles on a single player's floor.
     * [Centre]
     * 1. The number of tiles in the centre is no greater than 3 * the number of empty factories.
     * [Factories]
     * 1. At most one factory has less than 4, but greater than 0 tiles.
     * Any factories with factory number greater than this factory must contain 0 tiles.
     *
     * @param gameState array of strings representing the game state.
     *                  state[0] = sharedState
     *                  state[1] = playerStates
     * @return true if the state is valid, false if it is invalid.
     * TASK 9
     */
    // isStateValid() checks two methods shared, player state if the entire game State is Valid.
    // This is "1. Game Setup"
    public static boolean isStateValid(String[] gameState) {
        // FIXME Task 9
        boolean valid_SharedState = isSharedStateWellFormed(gameState[0]);
        boolean valid_PlayerState = isPlayerStateWellFormed(gameState[1]);

        if(valid_SharedState && valid_PlayerState){
            System.out.println(" Shared : " + gameState[0]);
            System.out.println(" Player : " + gameState[1]);
            //sharedState = new SharedState(gameState[0], MAX_PLAYER_NUMBER);
            //playerState = new PlayerState(gameState[1], MAX_PLAYER_NUMBER);
            return valid_SharedState && valid_PlayerState;
        }
        else{
            return false;
        }
    }

    /**
     * Given a valid gameState and a move, determine whether the move is valid.
     * A Drafting move is a 4-character String.
     * A Drafting move is valid if it satisfies the following conditions:
     * <p>
     * 1. The specified factory/centre contains at least one tile of the specified colour.
     * 2. The storage row the tile is being placed in does not already contain a different colour.
     * 3. The corresponding mosaic row does not already contain a tile of that colour.
     * Note that the tile may be placed on the floor.
     * </p>
     * <p>
     * A Tiling move is a 3-character String.
     * A Tiling move is valid if it satisfies the following conditions:
     * 1. The specified row in the Storage area is full.
     * 2. The specified column does not already contain a tile of the same colour.
     * 3. The specified location in the mosaic is empty.
     * 4. If the specified column is 'F', no valid move exists from the
     * specified row into the mosaic.
     * </p>
     *
     * @param gameState the game state.
     * @param move      A string representing a move.
     * @return true if the move is valid, false if it is invalid.
     * TASK 10
     */
    // isMoveValid() checks if Drafting, Tiling move is Valid.
    // This is “3. Drafting” and “4. Mosaic-tiling/Scoring”.
    public static boolean isMoveValid(String[] gameState, String move) {
        // FIXME Task 10
        return false;
    }

    /**
     * Given a gameState and a move, apply the move to the gameState.
     * If the move is a Tiling move, you must also update the player's score.
     * If the move is a Tiling move, you must also empty the remaining tiles
     * into the discard.
     * If the move is a Drafting move, you must also move any remaining tiles
     * from the specified factory into the centre.
     * If the move is a Drafting move and you must put tiles onto the floor,
     * any tiles that cannot fit on the floor are placed in the discard with
     * the following exception:
     * If the first player tile would be placed into the discard, it is instead
     * swapped with the last tile in the floor, when the floor is sorted
     * alphabetically.
     *
     * @param gameState the game state.
     * @param move      A string representing a move.
     * @return the updated gameState after the move has been applied.
     * TASK 11
     */

    // applyMove() is to apply the Drafting and Tiling Move.
    // This is “3. Drafting” and “4. Mosaic-tiling/Scoring”.
    public static String[] applyMove(String[] gameState, String move) {
        // FIXME Task 11
        return null;
    }

    /**
     * Given a valid game state, return a valid move.
     *
     * @param gameState the game state
     * @return a move for the current game state.
     * TASK 13
     */
    // generationAction() is to generation a "smart" Action.
    // This is “6. Other players play”.
    public static String generateAction(String[] gameState) {
        // FIXME Task 13
        //return null;
        // FIXME Task 15 Implement a "smart" generateAction()
        isDraftingValid(gameState, move);
        isTilingValid(gameState, move);
        return null;
    }

    // isStartingValid() checks if starting round movement is valid
    // This is "2. Starting the round"
    public static boolean isStartingValid(String[] gameState, String move) {
        drawTileFromBag(gameState);
        refillFactories(gameState);
        boolean valid_state = isStateValid(gameState);
        boolean valid_move = isMoveValid(gameState, move);
        return valid_state && valid_move;
    }

    // isDraftingValid() checks if drafting movement is valid
    // This is "3. Drafting"
    public static boolean isDraftingValid(String[] gameState, String move) {
        boolean valid_state = isStateValid(gameState);
        boolean valid_move = isMoveValid(gameState, move);
        return valid_state && valid_move;
    }

    // isTilingValid() checks if tiling movement is valid
    // This is “4. Mosaic-tiling/Scoring”
    public static boolean isTilingValid(String[] gameState, String move) {
        boolean valid_state = isStateValid(gameState);
        boolean valid_move = isMoveValid(gameState, move);
        return valid_state && valid_move;
    }

    public static void tileScore(String[] gameState) {

    }

    public static boolean tilingEnd(String[] gameState) {
        return false;
    }

    // isTPreparingNextValid() checks if next round preparing movement is valid
    // “5. Preparing for next round”
    public static boolean isPreparingNextValid(String[] gameState) {
        nextRound(gameState);
        isStateValid(gameState);
        return false;
    }

    public static void scorePlayer(String[] gameState) {

    }

    public static boolean isHumanPlayer(String[] gameState) {
        return false;
    }

    // isGameEnd() checks if game is ended
    // This is usd in "4. Mosaic-tiling/Scoring" and “6. Other players play”
    public static void isGameEnd(String[] gameState) {
    }

    // isEndGameValid() checks is game end is valid
    // This is “7. End of the Game”
    public static boolean isEndGameValid(String[] gameState) {
        isStateValid(gameState);
        return false;
    }
}
