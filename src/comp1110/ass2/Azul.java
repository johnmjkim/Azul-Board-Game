package comp1110.ass2;

import comp1110.ass2.backend.PlayerState;
import comp1110.ass2.backend.SharedState;

import java.util.*;

public class Azul implements Constants {

    // Maximum player numbers
    public static int MAX_PLAYER_NUMBER = 2;

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
            //System.out.println(sharedState_name_arr.size() == 5);
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
            if (c >= ZERO && c <= FACTORY_MAX_INDICES[MAX_PLAYER_NUMBER - DEFAULT_MAX_PLAYER]) {
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
                if(Character.getNumericValue(factoryState_name_arr.get(i)) <= Character.getNumericValue(factoryState_name_arr.get(i-1))){
                    factory_numerical_order = false;
                }
            }
        }
        else if(len ==1){
            factory_alphabetical_order = check_alphabetical_order(factoryState_content_arr.get(0));
        }
        factory_content_format = factory_numerical_order && factory_alphabetical_order && factory_tilesnumber;

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
        if (len > CENTER_MAX_NUMBERS[MAX_PLAYER_NUMBER - DEFAULT_MAX_PLAYER]) {
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
        if (len > MAX_MOSAIC_STRING_SIZE) {
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
        if (len > MAX_STORAGE_STRING_SIZE) {
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
        if (len > MAX_FLOOR_STRING_SIZE) {
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
        SharedState ss = new SharedState(gameState[0], MAX_PLAYER_NUMBER);
        return ss.bag.getRandomTile();

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
        SharedState ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);

        boolean isFactoryFull = ss.factories.isFactoryFull();
        boolean isCenterEmpty = ss.center.isStateEmpty();
        boolean existsOneFirstPlayerTokenCenter = ss.center.hasOnlyOneFirstPlayerToken();

        //System.out.println("Initial shared state : " + sharedState);
        if(isFactoryFull){
            //System.out.println("Factory Full");
            output_gameState[0] = ss.getStateString();
        }
        else{
            if(isCenterEmpty || existsOneFirstPlayerTokenCenter){
                //System.out.println("Factory Not Full and (Center is Empty or has Only one First Player Token)");
                ss.refillFactory();
                //System.out.println("Factory filled");
                output_gameState[0] = ss.getUpdatedSharedState();
                //System.out.println("Output shared state : " + output_gameState[0]);
                return output_gameState;
            }
            else{
                //System.out.println("Center is not Empty and does not have Only one First Player Token");
                output_gameState[0] = ss.getStateString();
            }
        }

        //System.out.println("Output shared state : " + output_gameState[0]);
        return output_gameState;
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
        int color_tiles;
        int player_turn;
        int bonus_points = 0;
        boolean row_tiles_full, col_tiles_full;
        SharedState ss = new SharedState(gameState[0], MAX_PLAYER_NUMBER);
        PlayerState ps = new PlayerState(gameState[1], MAX_PLAYER_NUMBER);

        for(int i=0; i < MAX_MOSAIC_ROW; i++){
            row_tiles_full = ps.getnPlayer(player).mosaic.getMosaicRow(i).isTilesFull();
            //System.out.print(player + " row " + i + " : " + playerState.getnPlayer(player).mosaic.getMosaicRow(i).getTotalTilesNumber() + " " + row_tiles_full);
            if(row_tiles_full){
                bonus_points += ROW_BONUS_POINT;
                //System.out.print(", bonus points : " + bonus_points);
            }
            //System.out.println();
        }

        for(int i=0; i < MAX_MOSAIC_COL; i++){
            col_tiles_full = ps.getnPlayer(player).mosaic.getMosaicCol(i).isTilesFull();
            //System.out.print(player + " col " + i + " : " + playerState.getnPlayer(player).mosaic.getMosaicCol(i).getTotalTilesNumber() + " " + col_tiles_full);
            if(col_tiles_full){
                bonus_points += COL_BONUS_POINT;
                //System.out.print(", bonus points : " + bonus_points);
            }
            //System.out.println();
        }

        char color = BLUE;
        for(int i=0; i < RED - BLUE; i++){
            color_tiles = ps.getnPlayer(player).mosaic.getTilesNumber(color);
            //System.out.print(player + " color : " + color_tiles);
            if(color_tiles == MAX_MOSAIC_COLOR){
                bonus_points += COLOR_BONUS_POINT;
                //System.out.print(", bonus points : " + bonus_points);
            }
            color++;
            //System.out.println();
        }

        return bonus_points;
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
        SharedState ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
        PlayerState ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);

        // Find if it is time to progress next round
        String current_player = ss.getTurnState();
        char current_player_char = current_player.charAt(0);

        boolean isnextround = isNextRoundStage(output_gameState);
        //boolean isNextRound = !(isdraftingstage || istilingstage);

        // Find if current player is first player
        boolean isCurrentFirstPlayer = ps.getnPlayer(current_player_char).floor.hasFirstPlayerToken();

        // Find if game is end
        boolean isendofgame = isGameEndStage(output_gameState);

        // Print shared and player state
        //System.out.println(" Before next round ");
        //System.out.println(ss);
        //System.out.println(ps);
        //System.out.println();

        if(!isnextround){
            // Return current state if it is not the time to progress next round
            //System.out.println(" It is not next round ");
            //System.out.println(ss);
            //System.out.println(ps);
            //System.out.println();

            output_gameState[0] = ss.getUpdatedSharedState();
            output_gameState[1] = ps.getUpdatedPlayerState();

            return output_gameState;
        }
        else{
            // Time to progress next round
            //System.out.println(" It is next round ");
            //System.out.println(ss);
            //System.out.println(ps);
            //System.out.println();

            output_gameState[0] = ss.getUpdatedSharedState();
            output_gameState[1] = ps.getUpdatedPlayerState();

            output_gameState = clearFloor(output_gameState);

            ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
            ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);

            // Check if it is end of the game
            if(isendofgame){
                // Game ended and add bonus point to one who ended
                for(int i=0; i < MAX_PLAYER_NUMBER; i++){
                    int bonus_points = getBonusPoints(output_gameState, ALL_PLAYERS[i]);
                    ps.getnPlayer(ALL_PLAYERS[i]).score.addScore(bonus_points);
                    //System.out.println(" It is end of game : " + ALL_PLAYERS[i] + " , bonus points are : " + bonus_points);
                    output_gameState[1] = ps.getUpdatedPlayerState();
                }
                //playerState.nplayers.get(ender_idx).score.addScore(bonus_points);
                //System.out.println(" It is end of game, ender is : " + ender + " , bonus points are : " + bonus_points);

                // Set First Player token at that center and end the game
                ss.center.addTile(FIRST_PLAYER);

                output_gameState[0] = ss.getUpdatedSharedState();
                output_gameState[1] = ps.getUpdatedPlayerState();

                ss.setSharedState(output_gameState[0],MAX_PLAYER_NUMBER);
                ps.setPlayerState(output_gameState[1],MAX_PLAYER_NUMBER);

                //System.out.println(ss);
                //System.out.println(ps);
                //System.out.println();
            }
            else {
                //System.out.println(" It is not end of game ");

                // Game is not end and refill factories
                output_gameState = refillFactories(output_gameState);

                ss.setSharedState(output_gameState[0],MAX_PLAYER_NUMBER);
                ps.setPlayerState(output_gameState[1],MAX_PLAYER_NUMBER);

                //System.out.println(ss);
                //System.out.println(ps);
                //System.out.println();

                // Start the turn to first player for the next round
                if(!isCurrentFirstPlayer){
                    ss.changeTurn();
                }

                output_gameState[0] = ss.getUpdatedSharedState();
                output_gameState[1] = ps.getUpdatedPlayerState();
            }

            //System.out.println(" Next round ready ");
            //System.out.println(output_gameState[0]);
            //System.out.println(output_gameState[1]);
            //System.out.println();

            return output_gameState;
        }
    }

    public static String[] clearFloor(String[] gameState) {
        // Store gameState information
        String[] output_gameState = new String[2];
        output_gameState[0] = gameState[0];
        output_gameState[1] = gameState[1];
        SharedState ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
        PlayerState ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);

        int tot_tiles = 0;
        String tiles_to_discard;

        // Clear the floor of each player
        for(int i=0; i < MAX_PLAYER_NUMBER; i++){
            // Get number of total tiles in floor to discard for each players
            tot_tiles = ps.getnPlayer(ALL_PLAYERS[i]).floor.getTotalTilesNumber();
            tiles_to_discard = ps.getnPlayer(ALL_PLAYERS[i]).floor.getFloorTilesString();

            // Adjust score, remove tiles from floor to discard for each player accordingly
            ps.getnPlayer(ALL_PLAYERS[i]).score.clearFloorScore(tot_tiles);
            ps.getnPlayer(ALL_PLAYERS[i]).floor.removeAllTiles();
            //sharedState.discard.refillTilesDiscard(tiles_to_discard);
            ss.discard.refillTiles(tiles_to_discard);

            //System.out.println(" Player " + ALL_PLAYERS[i] + " Floor Cleared ");
            //System.out.println(ss.getUpdatedSharedState());
            //System.out.println(ps.getUpdatedPlayerState());
            //System.out.println();
        }

        output_gameState[0] = ss.getUpdatedSharedState();
        output_gameState[1] = ps.getUpdatedPlayerState();
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

        //System.out.println(" Shared : " + gameState[0]);
        //System.out.println(" Player : " + gameState[1]);

        if(!(valid_SharedState && valid_PlayerState)){
            //System.out.println( " Not well formed ");
            return false;
        }
        else{
            //System.out.println( " Both well formed " );
            SharedState ss = new SharedState(gameState[0], MAX_PLAYER_NUMBER);
            PlayerState ps = new PlayerState(gameState[1], MAX_PLAYER_NUMBER);

            // Examine that
            // storage row do not exceed tiles
            // mosaic-storage do not have same color
            // mosaic rows, columns have valid positioning
            if(!check_mosaic_storage_valid(ss, ps)){
                return false;
            }
            // Find total number of tiles of each color
            // First player token : 1, Others : 20
            if(!check_tiles_number_valid(ss, ps)){
                return false;
            }
            return true;
        }
    }

    public static boolean check_mosaic_storage_valid(SharedState ss, PlayerState ps){
        // Examine that
        // storage row do not exceed tiles
        // mosaic-storage do not have same color
        for(int i=0; i < MAX_PLAYER_NUMBER; i++){
            for(int j=0; j < MAX_MOSAIC_ROW; j++){
                char storage_row_tile_color = ps.getnPlayer(ALL_PLAYERS[i]).storage.getStorageRow(j).getRowTilesColor();
                boolean valid_mosiac_storage_row = !ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j).existsTileColor(storage_row_tile_color);
                boolean valid_storage_row = ps.getnPlayer(ALL_PLAYERS[i]).storage.getStorageRow(j).isStorageRowTilesValid();

                //System.out.print( "     Player " + ALL_PLAYERS[i] + " MosaicRowState " + j + " : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j));
                //System.out.print(", Position Validity : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j).isMosaicRowTilePositionsValid());
                //System.out.println();
                if(!(valid_mosiac_storage_row && valid_storage_row)){
                    //System.out.println( " mosaic storage validity : " + valid_mosiac_storage_row + " valid storage row tiles not exceed : " + valid_storage_row);
                    return false;
                }

            }
        }
        // mosaic rows, columns have valid positioning
        for(int i=0; i < MAX_PLAYER_NUMBER; i++) {
            for (int j = 0; j < MAX_MOSAIC_ROW; j++) {
                boolean valid_position_row = ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j).tiles_row_position_valid;
                if(!valid_position_row){
                    //System.out.println(" mosaic row " + j + " not valid : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicRow(j).getStateString());
                    return false;
                }
            }
            for (int j = 0; j < MAX_MOSAIC_COL; j++) {
                boolean valid_position_col = ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicCol(j).tiles_col_position_valid;
                if(!valid_position_col){
                    //System.out.println(" mosaic col " + j + " not valid : " + ps.getnPlayer(ALL_PLAYERS[i]).mosaic.getMosaicCol(j).getStateString());
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean check_tiles_number_valid(SharedState ss, PlayerState ps){
        // Find total number of tiles of each color
        char color = BLUE;
        int total_tiles = 0;
        for(int i=0; i <= FIRST_PLAYER - BLUE; i++) {
            // Add tiles of shared states
            int factory_tiles = ss.factories.getTilesNumber(color);
            int center_tiles = ss.center.getTilesNumber(color);
            int bag_tiles = ss.bag.getTilesNumber(color);
            int discard_tiles = ss.discard.getTilesNumber(color);
            total_tiles = factory_tiles + center_tiles + bag_tiles + discard_tiles;
            //System.out.println(" Color (" + color + ") of factory : " + factory_tiles + ", center : " + center_tiles + ", bag : " + bag_tiles + ", discard : " + discard_tiles);
            // Add tiles of player state
            for (int j = 0; j < MAX_PLAYER_NUMBER; j++) {
                int mosaic_tiles = ps.getnPlayer(ALL_PLAYERS[j]).mosaic.getTilesNumber(color);
                int storage_tiles = ps.getnPlayer(ALL_PLAYERS[j]).storage.getTilesNumber(color);
                int floor_tiles = ps.getnPlayer(ALL_PLAYERS[j]).floor.getTilesNumber(color);
                total_tiles += mosaic_tiles + storage_tiles + floor_tiles;
                //System.out.println(" Color (" + color + ") of player " + ALL_PLAYERS[j] + " mosaic : " + mosaic_tiles + ", storage : " + storage_tiles + ", floor : " + floor_tiles);
            }
            //System.out.println(" Total tile of color (" + color + ") is : " + total_tiles);
            if (color == FIRST_PLAYER) {
                if (!(total_tiles == 1)) {
                    //System.out.println(" First player token (" + color + ") is not 1 : " + total_tiles);
                    return false;
                }
            } else {
                if (!(total_tiles == 20)) {
                    if (total_tiles > 20) {
                        //System.out.println(" Color (" + color + ") exceeds 20 : " + total_tiles);
                        return false;
                    } else if (total_tiles < 20) {
                        //System.out.println(" Color (" + color + ") less than 20 : " + total_tiles);
                        return false;
                    }
                }
            }
            color++;
        }
        return true;
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
        boolean valid_State = isStateValid(gameState);
        boolean wellFormed_move = check_move_format(move);
        boolean isDrafting_move = (move.length() == 4);
        boolean isTiling_move = (move.length() == 3);
        /*
        System.out.println(gameState[0]);
        System.out.println(gameState[1]);
        System.out.println(move);

         */

        if(!(valid_State && wellFormed_move)){
            //System.out.println(" valid state : " + valid_State + " well formed move : " + wellFormed_move);
            return false;
        }
        else{
            SharedState ss = new SharedState(gameState[0], MAX_PLAYER_NUMBER);
            PlayerState ps = new PlayerState(gameState[1], MAX_PLAYER_NUMBER);

            char player_turn = move.charAt(0);
            boolean right_player_turn = ss.getTurnState().equals(String.valueOf(player_turn));

            if(!right_player_turn){
                return false;
            }
            else{
                if(isDrafting_move){
                    return check_drafting_move_valid(ss,ps,move);
                }
                else if(isTiling_move){
                    return check_tiling_move_valid(ss,ps,move);
                }
                else{
                    return false;
                }
            }
        }
    }

    public static boolean check_move_format( String move ){
        if(move.length() == 3){
            char player_turn = move.charAt(0);
            char storage_mosaic_row = move.charAt(1);
            char mosaic_column_or_floor = move.charAt(2);
            boolean player_turn_valid = (player_turn >= ALL_PLAYERS[0]) && (player_turn < ALL_PLAYERS[MAX_PLAYER_NUMBER]);
            boolean storage_mosaic_row_valid = (storage_mosaic_row >= ZERO) && (storage_mosaic_row <= FOUR);
            boolean mosaic_column_or_floor_valid = ((mosaic_column_or_floor >= ZERO) && (mosaic_column_or_floor <= FOUR)) || mosaic_column_or_floor == FLOOR;
            return player_turn_valid && storage_mosaic_row_valid && mosaic_column_or_floor_valid;
        }
        else if(move.length() == 4){
            char player_turn = move.charAt(0);
            char factory_or_center = move.charAt(1);
            char color_of_tile = move.charAt(2);
            char storage_row_or_floor = move.charAt(3);
            boolean player_turn_valid = (player_turn >= ALL_PLAYERS[0]) && (player_turn < ALL_PLAYERS[MAX_PLAYER_NUMBER]);
            boolean factory_or_center_valid = ((factory_or_center >= ZERO) && (factory_or_center <= FACTORY_MAX_INDICES[MAX_PLAYER_NUMBER-DEFAULT_MAX_PLAYER])) || factory_or_center == CENTER;
            boolean color_of_tile_valid = (color_of_tile >= BLUE) && (color_of_tile <= RED);
            boolean storage_row_or_floor_valid = ((storage_row_or_floor >= ZERO) && (storage_row_or_floor <= FOUR)) || storage_row_or_floor == FLOOR;
            return player_turn_valid && factory_or_center_valid && color_of_tile_valid && storage_row_or_floor_valid;
        }
        else{
            return false;
        }
    }

    public static boolean check_drafting_move_valid( SharedState ss, PlayerState ps, String move){
        char player_turn = move.charAt(0);
        //System.out.println("Drafting move");
        char factory_or_center = move.charAt(1);
        char color_of_tile = move.charAt(2);
        char storage_row_or_floor = move.charAt(3);

        // Check picking tiles from factory or center
        if(!(factory_or_center == CENTER)){
            int factory_num = Character.getNumericValue(factory_or_center);
            boolean factory_has_tile = ss.factories.getFactory(factory_num).getTilesNumber(color_of_tile) > 0;
            if(!factory_has_tile){
                //System.out.println("Factory " + factory_or_center + " does not have tile : " + color_of_tile);
                return false;
            }
            else{
                //System.out.println("Factory " + factory_or_center + " has tile : " + color_of_tile);
            }
        }
        else{
            boolean center_has_tile = ss.center.getTilesNumber(color_of_tile) > 0;
            if(!center_has_tile){
                //System.out.println("Center does not have tile : " + color_of_tile);
                return false;
            }
            else{
                //System.out.println("Center has tile : " + color_of_tile);
            }
        }
        // Check placing tiles to storage row or floor
        if(storage_row_or_floor == FLOOR){
            //System.out.println("Place tiles to floor");
            return true;
        }
        else{
            int storage_row = Character.getNumericValue(storage_row_or_floor);
            boolean storage_row_full = ps.getnPlayer(player_turn).storage.getStorageRow(storage_row).isTilesFull();
            boolean storage_row_same_color = (color_of_tile == ps.getnPlayer(player_turn).storage.getStorageRow(storage_row).getRowTilesColor());
            boolean storage_row_empty = ps.getnPlayer(player_turn).storage.getStorageRow(storage_row).isStateEmpty();
            boolean storage_row_color_valid = storage_row_same_color || storage_row_empty;
            boolean mosaic_row_color_exists = ps.getnPlayer(player_turn).mosaic.getMosaicRow(storage_row).existsTileColor(color_of_tile);
            //System.out.println("Place tiles to Storage");

            if(storage_row_full || mosaic_row_color_exists || !storage_row_color_valid){
                //System.out.println(" Storage full : " + storage_row_full + ", Same color of mosaic row exist : " + mosaic_row_color_exists + ", Invalid storage row color : " + !storage_row_color_valid);
                return false;
            }
            else{
                //System.out.println("Storage row placement is valid");
                return true;
            }
        }
    }

    public static boolean check_tiling_move_valid( SharedState ss, PlayerState ps, String move){
        char player_turn = move.charAt(0);
        //System.out.println("Tiling move");
        char storage_mosaic_row = move.charAt(1);
        char mosaic_column_or_floor = move.charAt(2);
        int storage_row = Character.getNumericValue(storage_mosaic_row);
        int mosaic_row = storage_row;

        char storage_row_color = ps.getnPlayer(player_turn).storage.getStorageRow(storage_row).getRowTilesColor();
        boolean storage_row_full = ps.getnPlayer(player_turn).storage.getStorageRow(storage_row).isTilesFull();

        if(!(mosaic_column_or_floor == FLOOR)){
            int mosaic_col = Character.getNumericValue(mosaic_column_or_floor);
            // Check picking tiles from factory or center

            //boolean mosaic_row_color_exists = ps.getnPlayer(player_turn).mosaic.getMosaicRow(mosaic_row).existsTileColor(storage_row_color);
            boolean mosaic_col_color_exists = ps.getnPlayer(player_turn).mosaic.getMosaicCol(mosaic_col).existsTileColor(storage_row_color);
            boolean mosaic_row_tile_occupied = ps.getnPlayer(player_turn).mosaic.getMosaicRow(mosaic_row).existsTile(mosaic_col);

            if(!storage_row_full || mosaic_col_color_exists || mosaic_row_tile_occupied){
                //System.out.println("Storage row not full : " + !storage_row_full + " Mosaic col color exists : " + mosaic_col_color_exists + " Mosaic position occupied : " + mosaic_row_tile_occupied);
                return false;
            }
            else{
                //System.out.println("Storage row is full and mosaic position is valid");
                return true;
            }
        }
        else{
            if(!storage_row_full){
                //System.out.println("Storage is not full, cannot do tiling move");
                return false;
            }
            else{
                for(int col = 0; col < MAX_MOSAIC_COL; col++){
                    boolean mosaic_col_color_exists = ps.getnPlayer(player_turn).mosaic.getMosaicCol(col).existsTileColor(storage_row_color);
                    boolean mosaic_row_tile_occupied = ps.getnPlayer(player_turn).mosaic.getMosaicRow(storage_row).existsTile(col);
                    if(!mosaic_col_color_exists && !mosaic_row_tile_occupied){
                        //System.out.println("Storage to mosaic valid move exists, Storage row full : " + storage_row_full + " Mosaic col color not exists : " + !mosaic_col_color_exists + " Mosaic position not occupied : " + !mosaic_row_tile_occupied);
                        return false;
                    }
                }
                return true;
            }
        }
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
        String[] output_gameState = new String[2];
        SharedState ss = new SharedState(gameState[0], MAX_PLAYER_NUMBER);
        PlayerState ps = new PlayerState(gameState[1], MAX_PLAYER_NUMBER);
        /*
        System.out.println(gameState[0]);
        System.out.println(gameState[1]);
        System.out.println(move);

         */

        char player_turn = move.charAt(0);
        if(move.length() == 3){
            output_gameState = applyTilingMove(ss,ps,move);
        }
        else if(move.length() == 4){
            output_gameState = applyDraftingMove(ss,ps,move);
        }

        ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
        ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);

        boolean floor_max_exceed = ps.getnPlayer(player_turn).floor.getTotalTilesNumber() > 7;
        if(floor_max_exceed){
            output_gameState = applyMoveFloorAdjusting(ss,ps,move);
        }

        ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
        ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);

        output_gameState[0] = ss.getStateString();
        output_gameState[1] = ps.getStateString();
        /*
        System.out.println(ss);
        System.out.println(ps);
        System.out.println();

         */
        return output_gameState;
    }

    public static String[] applyTilingMove(SharedState ss, PlayerState ps, String move){
        String[] output_gameState = new String[2];
        char player_turn = move.charAt(0);
        //System.out.println("Tiling Move");
        char storage_mosaic_row = move.charAt(1);
        char mosaic_column_or_floor = move.charAt(2);
        int storage_row = Character.getNumericValue(storage_mosaic_row);
        int mosaic_row = storage_row;
        char storage_row_color = ps.getnPlayer(player_turn).storage.getStorageRow(storage_row).getRowTilesColor();
        int storage_row_tiles = ps.getnPlayer(player_turn).storage.getStorageRow(storage_row).getTotalTilesNumber();

        if(storage_row_tiles > 1){
            ss.discard.addTiles(storage_row_color, storage_row_tiles - 1);
        }
        ps.getnPlayer(player_turn).storage.getStorageRow(storage_row).removeAllTiles();
        output_gameState[0] = ss.getUpdatedSharedState();
        output_gameState[1] = ps.getUpdatedPlayerState();
        ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
        ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);

        /*
        System.out.println("Remove all storage of player " + player_turn + " at row " + storage_row);
        System.out.println(ss);
        System.out.println(ps);

         */
        if(mosaic_column_or_floor == FLOOR){
            ps.getnPlayer(player_turn).floor.addTiles(storage_row_color, storage_row_tiles);
            output_gameState[1] = ps.getUpdatedPlayerState();
            ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);
            /*
            System.out.println("Go to floor");
            System.out.println(ps);

             */
        }
        else{
            int mosaic_col = Character.getNumericValue(mosaic_column_or_floor);
            int mosaic_adjacent_score = ps.getnPlayer(player_turn).mosaic.scoreTotalMosaic();
            ps.getnPlayer(player_turn).mosaic.getMosaicRow(mosaic_row).addTile(storage_row_color, mosaic_col);
            output_gameState[1] = ps.getUpdatedPlayerState();
            ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);
            int storage_clear_score = 1;
            int mosaic_adjacent_score_increment = ps.getnPlayer(player_turn).mosaic.scoreTotalMosaic() - mosaic_adjacent_score;
            if(mosaic_adjacent_score_increment > 0){
                ps.getnPlayer(player_turn).score.addScore(ps.getnPlayer(player_turn).mosaic.scoreMosaic(mosaic_row, mosaic_col));
            }
            else{
                ps.getnPlayer(player_turn).score.addScore(storage_clear_score);
            }
            output_gameState[1] = ps.getUpdatedPlayerState();
            ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);
            //System.out.println(" Score added, one tile : " + storage_clear_score + " , adjacent : " + ps.getnPlayer(player_turn).mosaic.scoreMosaic(mosaic_row, mosaic_col));
            if(!ps.getnPlayer(player_turn).storage.existsStorageRowTilesFull()){
                ss.changeTurn();
            }
            output_gameState[0] = ss.getUpdatedSharedState();
            output_gameState[1] = ps.getUpdatedPlayerState();
            ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
            ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);
            /*
            System.out.println("Add mosaic at row : " + mosaic_row + " col : " + mosaic_col);
            System.out.println(ss);
            System.out.println(ps);

             */
        }
        output_gameState[0] = ss.getStateString();
        output_gameState[1] = ps.getStateString();
        return output_gameState;
    }

    public static String[] applyDraftingMove(SharedState ss, PlayerState ps, String move){
        String[] output_gameState = new String[2];
        char player_turn = move.charAt(0);
        //System.out.println("Drafting Move");
        char factory_or_center = move.charAt(1);
        char color_of_tile = move.charAt(2);
        char storage_row_or_floor = move.charAt(3);
        int selected_tiles = 0;
        if(factory_or_center == CENTER){
            //System.out.println("Draft from center");
            selected_tiles = ss.center.getTilesNumber(color_of_tile);
            ss.center.removeTiles(color_of_tile, selected_tiles);
            if(ss.center.hasFirstPlayerToken()){
                ss.center.removeTile(FIRST_PLAYER);
                ps.getnPlayer(player_turn).floor.addTile(FIRST_PLAYER);
            }
            output_gameState[0] = ss.getUpdatedSharedState();
            output_gameState[1] = ps.getUpdatedPlayerState();
            ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
            ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);
            /*
            System.out.println("Cleared center ");
            System.out.println(ss);
            System.out.println(ps);

             */
        }
        else{
            //System.out.println("Draft from factory " + factory_or_center);
            int factory_num = Character.getNumericValue(factory_or_center);
            selected_tiles = ss.factories.getFactory(factory_num).getTilesNumber(color_of_tile);
            char color = BLUE;
            for(int i=0; i <= RED - BLUE; i++){
                int rest_tiles = ss.factories.getFactory(factory_num).getTilesNumber(color);
                if(!(color == color_of_tile)){
                    ss.center.addTiles(color,rest_tiles);
                }
                color++;
            }
            ss.factories.getFactory(factory_num).removeAllTiles();
            output_gameState[0] = ss.getUpdatedSharedState();
            ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);

            /*
            System.out.println("Cleared factory " + factory_num);
            System.out.println(ss);
            System.out.println(ps);

             */
        }

        if(storage_row_or_floor == FLOOR){
            ps.getnPlayer(player_turn).floor.addTiles(color_of_tile, selected_tiles);
            output_gameState[0] = ss.getUpdatedSharedState();
            output_gameState[1] = ps.getUpdatedPlayerState();
            ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
            ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);
            /*
            System.out.println("Go to the floor");
            System.out.println(ss);
            System.out.println(ps);

             */
        }
        else{
            int storage_row = Character.getNumericValue(storage_row_or_floor);
            int storage_row_tiles_max = ps.getnPlayer(player_turn).storage.getStorageRow(storage_row).getMaxTilesLimit();
            int storage_row_tiles_current = ps.getnPlayer(player_turn).storage.getStorageRow(storage_row).getTotalTilesNumber();
            int tiles_to_storage = selected_tiles;
            int tiles_to_floor = 0;
            if(storage_row_tiles_max < selected_tiles + storage_row_tiles_current){
                tiles_to_storage = storage_row_tiles_max - storage_row_tiles_current;
                tiles_to_floor = selected_tiles - tiles_to_storage;
            }
            ps.getnPlayer(player_turn).storage.getStorageRow(storage_row).addTiles(color_of_tile, tiles_to_storage);
            ps.getnPlayer(player_turn).floor.addTiles(color_of_tile, tiles_to_floor);

            output_gameState[0] = ss.getUpdatedSharedState();
            output_gameState[1] = ps.getUpdatedPlayerState();
            ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
            ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);
            /*
            System.out.println("Filled storage " + storage_row);
            System.out.println(ss);
            System.out.println(ps);

             */
        }

        boolean isFactoryEmpty = ss.factories.isStateEmpty();
        boolean isCenterEmpty = ss.center.isStateEmpty();
        if(!(isFactoryEmpty && isCenterEmpty)){
            ss.changeTurn();
            output_gameState[0] = ss.getUpdatedSharedState();
            output_gameState[1] = ps.getUpdatedPlayerState();
            ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
            ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);
            /*
            System.out.println("Changed Turn ");
            System.out.println(ss);
            System.out.println(ps);

             */
        }
        output_gameState[0] = ss.getStateString();
        output_gameState[1] = ps.getStateString();
        return output_gameState;
    }

    public static String[] applyMoveFloorAdjusting ( SharedState ss, PlayerState ps, String move){
        String[] output_gameState = new String[2];
        char player_turn = move.charAt(0);
        //System.out.println("Floor max exceeded");
        int tiles_to_discard = ps.getnPlayer(player_turn).floor.getTotalTilesNumber() - 7;
        char color = RED;
        while(tiles_to_discard > 0){
            if(ps.getnPlayer(player_turn).floor.getTilesNumber(color) > 0){
                ps.getnPlayer(player_turn).floor.removeTile(color);
                ss.discard.addTile(color);
                output_gameState[0] = ss.getUpdatedSharedState();
                output_gameState[1] = ps.getUpdatedPlayerState();
                ss = new SharedState(output_gameState[0], MAX_PLAYER_NUMBER);
                ps = new PlayerState(output_gameState[1], MAX_PLAYER_NUMBER);
                /*
                System.out.println("Move tile to discard");
                System.out.println(ss);
                System.out.println(ps);

                 */
                tiles_to_discard--;
            }
            else{
                color--;
            }
        }
        output_gameState[0] = ss.getStateString();
        output_gameState[1] = ps.getStateString();
        return output_gameState;
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
        String[] output_gameState = new String[2];
        SharedState ss = new SharedState(gameState[0], MAX_PLAYER_NUMBER);
        PlayerState ps = new PlayerState(gameState[1], MAX_PLAYER_NUMBER);

        output_gameState[0] = ss.getStateString();
        output_gameState[1] = ps.getStateString();
        /*
        System.out.println(gameState[0]);
        System.out.println(gameState[1]);

         */

        boolean draftingStage = isDraftingStage(output_gameState);
        char player_turn = ss.getTurnState().charAt(0);
        ArrayList<String> valid_drafting_moves = new ArrayList<String>();
        ArrayList<String> valid_tiling_moves = new ArrayList<String>();
        String input_move;
        StringBuilder SB = new StringBuilder();

        // Generate Drafting Moves
        ArrayList<Character> second_drafting_chars = new ArrayList<Character>();
        ArrayList<Character> third_drafting_chars = new ArrayList<Character>();
        char[] fourth_drafting_chars = new char[]{ZERO, ONE, TWO, THREE, FOUR, FLOOR};

        boolean factories_has_tile = !ss.factories.isStateEmpty();
        boolean center_has_tile = !(ss.center.isStateEmpty() || ss.center.hasOnlyOneFirstPlayerToken());

        if(factories_has_tile){
            for(int i=0; i < FACTORY_MAX_NUMBER; i++){
                boolean factory_has_tile = !ss.factories.getFactory(i).getStateString().isEmpty();
                if(factory_has_tile){
                    second_drafting_chars.add(NUMBERS[i]);
                    for( char color : COLORS ){
                        boolean factory_has_color = ss.factories.getFactory(i).getTilesNumber(color) > 0;
                        if(factory_has_color){
                            third_drafting_chars.add(color);
                        }
                    }
                }
            }
        }
        if(center_has_tile){
            second_drafting_chars.add(CENTER);
            for( char color : COLORS ){
                boolean center_has_color = ss.center.getTilesNumber(color) > 0;
                if(center_has_color){
                    third_drafting_chars.add(color);
                }
            }
        }

        SB.append(player_turn);
        for( Character second_char : second_drafting_chars){
            SB.delete(1,SB.length());
            SB.append(second_char);
            for( Character third_char : third_drafting_chars){
                SB.delete(2,SB.length());
                SB.append(third_char);
                for( char fourth_char : fourth_drafting_chars){
                    SB.delete(3,SB.length());
                    SB.append(fourth_char);
                    input_move = String.valueOf(SB);
                    //System.out.println(input_move);
                    if(isMoveValid(output_gameState, input_move)){
                        valid_drafting_moves.add(input_move);
                        return input_move;
                    }
                }
            }
        }
        /*
        System.out.println(" Valid Drafting Moves : ");
        for( String str : valid_drafting_moves){
            System.out.print(str);
            System.out.print(", ");
        }
        System.out.println();

         */

        // Generate Tiling Moves
        if(valid_drafting_moves.isEmpty()){
            ArrayList<Character> second_tiling_chars = new ArrayList<Character>();
            char[] third_tiling_chars = new char[]{ZERO, ONE, TWO, THREE, FOUR, FLOOR};

            for(int i=0; i < MAX_STORAGE_ROW; i++){
                if(second_tiling_chars.isEmpty()){
                    boolean storage_row_full = ps.getnPlayer(player_turn).storage.getStorageRow(i).isTilesFull();
                    if(storage_row_full){
                        second_tiling_chars.add(NUMBERS[i]);
                    }
                }
            }

            for( Character second_char : second_tiling_chars){
                SB.delete(1,SB.length());
                SB.append(second_char);
                for( char third_char : third_tiling_chars){
                    SB.delete(2,SB.length());
                    SB.append(third_char);
                    input_move = String.valueOf(SB);
                    //System.out.println(input_move);
                    if(isMoveValid(output_gameState, input_move)){
                        valid_tiling_moves.add(input_move);
                        return input_move;
                    }
                }
            }
            /*
            System.out.println(" Valid Tiling Moves : ");
            for( String str : valid_tiling_moves){
                System.out.print(str);
                System.out.print(", ");
            }
            System.out.println();

             */

            return valid_tiling_moves.get(0);
        }
        else{
            return valid_drafting_moves.get(0);
        }

        // FIXME Task 15 Implement a "smart" generateAction()
    }

    // isStartingValid() checks if starting round movement is valid
    // This is "2. Starting the round"
    public static boolean isStartingValid(String[] gameState) {
        boolean valid_state = isStateValid(gameState);
        return valid_state;
    }

    // isDraftingStage() checks if it is drafting move stage
    // This is "3. Drafting"
    public static boolean isDraftingStage(String[] gameState) {
        if(!isStateValid(gameState)){
            return false;
        }
        else{
            SharedState ss = new SharedState(gameState[0], MAX_PLAYER_NUMBER);
            boolean factories_has_tile = !ss.factories.isStateEmpty();
            boolean center_has_tile = !(ss.center.isStateEmpty() || ss.center.hasOnlyOneFirstPlayerToken());
            return factories_has_tile || center_has_tile;
        }
    }

    // isTilingStage() checks if it is tiling move stage
    // This is “4. Mosaic-tiling/Scoring”
    public static boolean isTilingStage(String[] gameState) {
        if(!isStateValid(gameState)){
           return false;
        }
        else if(isDraftingStage(gameState)){
            return false;
        }
        else{
            SharedState ss = new SharedState(gameState[0], MAX_PLAYER_NUMBER);
            PlayerState ps = new PlayerState(gameState[1], MAX_PLAYER_NUMBER);
            char player_turn = ss.getTurnState().charAt(0);
            boolean storage_has_full_tiles = ps.getnPlayer(player_turn).storage.existsStorageRowTilesFull();
            return storage_has_full_tiles;
        }
    }

    // isNextRoundStage() checks if next round preparing stage
    // “5. Preparing for next round”
    public static boolean isNextRoundStage(String[] gameState) {
        if(!isStateValid(gameState)){
            return false;
        }
        else {
            return !(isDraftingStage(gameState) || isTilingStage(gameState));
        }
    }

    // isGameEndStage() checks if game is ended
    // This is usd in "4. Mosaic-tiling/Scoring" and “6. Other players play”
    public static boolean isGameEndStage(String[] gameState) {
        if(!isStateValid(gameState)){
            return false;
        }
        else{
            PlayerState ps = new PlayerState(gameState[1], MAX_PLAYER_NUMBER);
            return ps.isEndofGame();
        }
    }

    // isEndGameValid() checks is game end is valid
    // This is “7. End of the Game”
    public static boolean isEndGameValid(String[] gameState) {
        isStateValid(gameState);
        return false;
    }
}
