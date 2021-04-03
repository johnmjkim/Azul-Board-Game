package comp1110.ass2;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.HashMap;

public class Azul {
    public static String[] gameState;
    public static String move;
    public static int gameScore;

    /**
     * Given a shared state string, determine if it is well-formed.
     * Note: you don't need to consider validity for this task.
     * <p>
     * A sharedState is well-formed if it satisfies the following conditions.
     * <p>
     * [factories][centre][bag][discard]
     * where [factories], [centre], [bag] and [discard] are replaced by the
     * corresponding small string as described below.
     * <p>,
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
        //System.out.println(sharedState);
        char [] sharedState_array = sharedState.toCharArray();
        ArrayList<Character> sharedState_name_arr = new ArrayList<Character>();
        ArrayList<String> sharedState_content_arr = new ArrayList<String>();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        // Filter valid capital letters
        for( char c : sharedState_array ){
            //System.out.println(c);
            if(c >= 'A' && c <= 'D' || c == 'F'){
                //System.out.println(String.valueOf(c));
                sharedState_name_arr.add(c);
                sharedState_content_arr.add(String.valueOf(SB));
                SB.delete(0,SB.length());
                len++;
            }
            else{
                SB.append(c);
            }
        }
        sharedState_content_arr.add(String.valueOf(SB));
        SB.delete(0,SB.length());
        sharedState_content_arr.remove(0);

        System.out.println(sharedState);
        for(int i=0; i < len; i++){
            System.out.println(sharedState_name_arr.get(i) + ", " + sharedState_content_arr.get(i));
        }

        // Find capital letters valid
        if(sharedState_name_arr.size() != 5){
            return false;
        }
        // Player is valid
        boolean player_format = check_player_format(sharedState_name_arr.get(0), sharedState_content_arr.get(0));
        // Factory is valid
        boolean factory_format = check_factory_format(sharedState_name_arr.get(1), sharedState_content_arr.get(1));
        // Center is valid
        boolean center_format = check_center_format(sharedState_name_arr.get(2), sharedState_content_arr.get(2));
        // Bag is valid
        boolean bag_format = check_bag_discard_format(sharedState_name_arr.get(3), sharedState_content_arr.get(3), 'B');
        // Discard is valid
        boolean discard_format = check_bag_discard_format(sharedState_name_arr.get(4), sharedState_content_arr.get(4), 'D');
        return player_format && factory_format && center_format && bag_format && discard_format;

        // Sibo's code
        /*
        int character = 1;
        String[] tranferS = sharedState.split("");
        boolean isChABCD=tranferS[0].equals("A") || tranferS[0].equals("B") || tranferS[0].equals("C") || tranferS[0].equals("D");
        boolean ischF=tranferS[1].equals("F");
        boolean isF01234=tranferS[character].equals("0") || tranferS[character].equals("1") || tranferS[character].equals("2") || tranferS[character].equals("3") || tranferS[character].equals("4");
        boolean notTile = !(tranferS[character].equals("a") && tranferS[character].equals("b") && tranferS[character].equals("c") && tranferS[character].equals("d") && tranferS[character].equals("e"));
        boolean notchC=!tranferS[1].equals("C");
        boolean ischC= tranferS[1].equals("C");
        boolean notchfandB= !tranferS[character + 1].equals("f") && !tranferS[character].equals("B");
        boolean isTile =tranferS[character].equals("a") || tranferS[character].equals("b") || tranferS[character].equals("c") || tranferS[character].equals("d") || tranferS[character].equals("e");
        if (isChABCD) {
            if (ischF) {
                do { character++;
                    if (isF01234) {
                        for (int k = 0; k <= 3; k++) {
                            character++;
                            if (notTile) {
                                return true;
                            }
                        }
                    } else if (notchC) {
                        return false;
                    }
                } while (notchC);
                if (ischC) {
                    if (notchfandB) {
                        do {
                            character++;
                            if (isTile) {
                                return true;
                            } else {
                                System.out.println(false + "2");
                            }
                        } while (!tranferS[character].equals("B"));
                        if (tranferS[character].equals("B")) {
                            for (int i = 1; i <= 10; i += 2) {
                                int numofa = Integer.parseInt(tranferS[character + i]) * 10 + Integer.parseInt(tranferS[character + i + 1]);
                                if (numofa <= 20) {
                                    return true;
                                } else {
                                    System.out.println(false + "3");
                                }
                            }
                            character += 11;
                            if (tranferS[character].equals("D")) {
                                for (int j = 1; j <= 10; j += 2) {
                                    int numofb = Integer.parseInt(tranferS[character + j]) * 10 + Integer.parseInt(tranferS[character + j + 1]);
                                    if (numofb <= 20) {
                                        return true;
                                    }
                                }
                            } else {
                                System.out.println(false + "4");
                            }
                        } else {
                            System.out.println(false + "5");
                        }
                    } else if (tranferS[character + 1].equals("f")) {
                        if (tranferS[character + 2].equals("B")) {
                            for (int i = 1; i <= 10; i += 2) {
                                int numofa = Integer.parseInt(tranferS[character + i + 2]) * 10 + Integer.parseInt(tranferS[character + i + 3]);
                                if (numofa <= 20) {
                                    return true;
                                }
                            }
                            character += 13;
                            if (tranferS[character].equals("D")) {
                                for (int j = 1; j <= 10; j += 2) {
                                    int numofb = Integer.parseInt(tranferS[character + j]) * 10 + Integer.parseInt(tranferS[character + j + 1]);
                                    if (numofb <= 20) {
                                        return true;
                                    } else {
                                        System.out.println(false + "6");
                                    }
                                }
                            } else {
                                System.out.println(false + "7");
                            }
                        } else {
                            System.out.println(false + "8");
                        }
                    }
                } else {
                    System.out.println(false + "9");
                }
            } else {
                System.out.println(false + "10");
            }
        }
        return false;

         */
    }

    public static boolean check_player_format(char player_char, String player_String){
        // Find capital letters valid
        boolean player_name_format = (player_char >= 'A' && player_char <= 'D');

        // Player is valid
        boolean player_format = player_name_format && player_String.isEmpty();
        System.out.println(player_name_format + ", " + player_String.isEmpty());
        return player_format;
    }

    public static boolean check_factory_format(char factory_char, String factory_String){
        int len = 0;
        boolean factory_name_format = (factory_char == 'F');

        boolean factory_content_format = true;
        for(char c : factory_String.toCharArray()){
            if( len % 5 == 0){
                if(!(c >= '0' && c <= '4')){
                    factory_content_format = false;
                }
            }
            else{
                if(!(c >= 'a' && c <= 'e')){
                    factory_content_format = false;
                }
            }
            len++;
        }
        if(!(len % 5 ==0)){
            factory_content_format = false;
        }
        boolean factory_format = factory_name_format && factory_content_format;
        System.out.println(factory_name_format + ", " + factory_content_format);

        return factory_format;
    }

    public static boolean check_center_format(char center_char, String center_String){
        boolean center_name_format = (center_char == 'C');
        int len = 0;
        boolean center_content_format = true;
        for(char c : center_String.toCharArray()){
            if(!(c >= 'a' && c <= 'e' || c == 'f')){
                center_content_format = false;
            }
            len++;
        }
        if(len > 15){
            center_content_format = false;
        }
        boolean center_format = center_name_format && center_content_format;
        System.out.println(center_name_format + ", " + center_content_format);
        return center_format;
    }

    public static boolean check_bag_discard_format(char bag_discard_char, String bag_discard_String, char compare){
        boolean bag_discard_name_format = (bag_discard_char == compare);
        boolean bag_discard_content_format = true;
        StringBuilder SB = new StringBuilder();
        int len=0;
        for( char c : bag_discard_String.toCharArray()){
            SB.append(c);
            if(len >= 1 && len <= 10){
                if(len % 2 == 1){
                    if(!(Integer.valueOf(SB.toString()) >=0 && Integer.valueOf(SB.toString()) <= 20)){
                        bag_discard_content_format = false;
                    }
                    SB.delete(0,SB.length());
                }
            }
            else{
            }
            len++;
        }
        if(!(len==10)){
            bag_discard_content_format = false;
        }
        SB.delete(0,SB.length());
        boolean bag_discard_format = bag_discard_name_format && bag_discard_content_format && !bag_discard_String.isEmpty();
        System.out.println(bag_discard_name_format + ", " + bag_discard_content_format + ", " + !bag_discard_String.isEmpty());
        return bag_discard_format;
    }

    /**
     * Given a playerState, determine if it is well-formed.
     * Note: you don't have to consider validity for this task.
     * A playcome in agaierState is composed of individual playerStrings.
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
        //System.out.println(playerState);
        /*
        for(int i=0;i < playerState.length();i++){
            System.out.println(playerState.charAt(i));
        }

         */
        return false;
    }

    /**
     * Given the gameState, draw a *random* tile from the bag.
     * If the bag is empty, refill the the bag with the discard pile and then draw a tile.
     * If the discard pile is also empty, return 'Z'.     *
     *
     * @param gameState the current game state
     * @return the tile drawn from the bag, or 'Z' if the bag and discard pile are empty.
     * TASK 5
     */

    // drawTileFromBag() is to draw a "random" tile from the bag.
    //This is “2. Starting the round”.
    public static char drawTileFromBag(String[] gameState) {
        // FIXME Task 5
        return '0';
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
        return null;
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
        return -1;
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
        emptyFloor(gameState);
        scorePlayer(gameState);
        drawTileFromBag(gameState);
        refillFactories(gameState);
        return null;
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
        return valid_SharedState && valid_PlayerState;
    }

    /**
     * Given a valid gameState and a move, determine whether the move is valid.
     * A Drafting move is a 4-character String.
     * A Drafting move is valid ifit satisfies the following conditions:
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

    public static void emptyFloor(String[] gameState) {
        isStateValid(gameState);
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
