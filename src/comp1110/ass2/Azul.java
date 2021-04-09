package comp1110.ass2;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Azul {
    public static String[] gameState;
    public static String move;
    public static int gameScore;

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
        boolean s_player_format = check_s_player_format(sharedState_name_arr.get(0), sharedState_content_arr.get(0));
        // Factory is valid
        boolean factory_format = check_factory_format(sharedState_name_arr.get(1), sharedState_content_arr.get(1));
        // Center is valid
        boolean center_format = check_center_format(sharedState_name_arr.get(2), sharedState_content_arr.get(2));
        // Bag is valid
        boolean bag_format = check_bag_discard_format(sharedState_name_arr.get(3), sharedState_content_arr.get(3), 'B');
        // Discard is valid
        boolean discard_format = check_bag_discard_format(sharedState_name_arr.get(4), sharedState_content_arr.get(4), 'D');
        return s_player_format && factory_format && center_format && bag_format && discard_format;

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

    public static boolean check_s_player_format(char s_player_char, String s_player_String){
        // Find capital letters valid
        boolean s_player_name_format = (s_player_char >= 'A' && s_player_char <= 'D');

        // S_Player is valid
        boolean s_player_format = s_player_name_format && s_player_String.isEmpty();
        System.out.println(s_player_name_format + ", " + s_player_String.isEmpty());
        return s_player_format;
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
            if(!(c >= 'a' && c <= 'f')){
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
        char [] playerState_array = playerState.toCharArray();
        ArrayList<Character> playerState_name_arr = new ArrayList<Character>();
        ArrayList<String> playerState_content_arr = new ArrayList<String>();
        StringBuilder SB = new StringBuilder();
        int len = 0;
        // Filter valid capital letters
        for( char c : playerState_array ){
            //System.out.println(c);
            if( (c >= 'A' && c <= 'D') || c =='M' || c =='S' || c == 'F'){
                //System.out.println(String.valueOf(c));
                playerState_name_arr.add(c);
                playerState_content_arr.add(String.valueOf(SB));
                SB.delete(0,SB.length());
                len++;
            }
            else{
                SB.append(c);
            }
        }
        playerState_content_arr.add(String.valueOf(SB));
        SB.delete(0,SB.length());
        playerState_content_arr.remove(0);

        System.out.println(playerState);
        for(int i=0; i < len; i++){
            System.out.println(playerState_name_arr.get(i) + ", " + playerState_content_arr.get(i));
        }
        // Find capital letters valid 1 : filter by size
        if(!(playerState_name_arr.size() % 4 == 0)){
            return false;
        }
        // Find capital letters valid 2 : filter by order
        len = 0;
        int toggle_int = 0, player_int = 0;
        for( char c : playerState_name_arr){
            toggle_int = len % 4;
            if(toggle_int == 0){
                if(!(c == 'A' + player_int)){
                    return false;
                }
                player_int++;
            }
            else if(toggle_int == 1){
                if(!(c == 'M')){
                    return false;
                }
            }
            else if(toggle_int == 2){
                if(!(c == 'S')){
                    return false;
                }
            }
            else if(toggle_int == 3){
                if(!(c == 'F')){
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
        for(int i=0; i < player_int; i++){
            // Player is valid
            p_player_format = p_player_format && check_p_player_format(playerState_name_arr.get(4*i));
            // Score is valid
            score_format = score_format && check_score_format(playerState_content_arr.get(4*i));
            // Mosaic is valid
            mosaic_format = mosaic_format && check_mosaic_format(playerState_name_arr.get(4*i + 1), playerState_content_arr.get(4*i + 1));
            // Storage is valid
            storage_format = storage_format && check_storage_format(playerState_name_arr.get(4*i + 2), playerState_content_arr.get(4*i + 2));
            // Floor is valid
            floor_format = floor_format && check_floor_format(playerState_name_arr.get(4*i + 3), playerState_content_arr.get(4*i + 3), floor_counts);
            floor_counts = toss_floor_count(playerState_content_arr.get(4*i + 3), floor_counts);
        }
        return p_player_format && score_format && mosaic_format && storage_format && floor_format;
    }

    public static boolean check_p_player_format(char p_player_char){
        // Find capital letters valid
        boolean p_player_name_format = (p_player_char >= 'A' && p_player_char <= 'D');

        // P_Player is valid
        boolean p_player_format = p_player_name_format;
        System.out.println(p_player_name_format);
        return p_player_format;
    }

    public static boolean check_score_format(String score_String){
        // Find capital letters valid
        boolean score_name_format = true;
        for( char c : score_String.toCharArray() ){
            if(!(c >= '0' && c <= '9')){
                score_name_format = false;
            }
        }
        // P_Player is valid
        boolean score_format = score_name_format && !score_String.isEmpty();
        System.out.println(score_format + ", " + !score_String.isEmpty());
        return score_format;
    }

    public static boolean check_mosaic_format(char mosaic_char, String mosaic_String){
        boolean mosaic_name_format = (mosaic_char == 'M');
        int len = 0;
        boolean mosaic_content_format = true;
        for(char c : mosaic_String.toCharArray()){
            if(len % 3 == 0){
                if(!(c >= 'a' && c <= 'e')){
                    mosaic_content_format = false;
                }
            }
            else{
                if(!(c >= '0' && c <= '4')){
                    mosaic_content_format = false;
                }
            }
            len++;
        }
        if(len > 75){
            mosaic_content_format = false;
        }
        else if(mosaic_String.isEmpty()){
            mosaic_content_format = true;
        }
        boolean center_format = mosaic_name_format && mosaic_content_format;
        System.out.println(mosaic_name_format + ", " + mosaic_content_format);
        return center_format;
    }

    public static boolean check_storage_format(char storage_char, String storage_String){
        boolean storage_name_format = (storage_char == 'S');
        int len = 0;
        boolean storage_content_format = true;
        for(char c : storage_String.toCharArray()){
            if(len % 3 == 0){
                if(!(c >= '0' && c <= '4')){
                    storage_content_format = false;
                }
            }
            else if(len % 3 == 1){
                if(!(c >= 'a' && c <= 'e')){
                    storage_content_format = false;
                }
            }
            else if(len % 3 == 2){
                if(!(c >= '0' && c <= '5')){
                    storage_content_format = false;
                }
            }
            len++;
        }
        if(len > 15){
            storage_content_format = false;
        }
        else if(storage_String.isEmpty()){
            storage_content_format = true;
        }
        boolean storage_format = storage_name_format && storage_content_format;
        System.out.println(storage_name_format + ", " + storage_content_format);
        return storage_format;
    }

    public static boolean check_floor_format(char floor_char, String floor_String, int floor_counts){
        boolean floor_name_format = (floor_char == 'F');
        int len = 0;
        boolean floor_content_format = true;
        for(char c : floor_String.toCharArray()){
            if(!(c >= 'a' && c <= 'f')){
                floor_content_format = false;
            }
            if(c == 'f'){
                floor_counts++;
                if(floor_counts > 1){
                    floor_content_format = false;
                }
            }
            len++;
        }
        if(len > 7){
            floor_content_format = false;
        }
        else if(floor_String.isEmpty()){
            floor_content_format = true;
        }
        boolean storage_format = floor_name_format && floor_content_format;
        System.out.println(floor_name_format + ", " + floor_content_format);
        return storage_format;
    }

    public static int toss_floor_count(String floor_String, int floor_counts){
        for(char c : floor_String.toCharArray()){
            if(c == 'f'){
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
        //put the string of gamestate such as B0005101520 in a new array {"00","05","10","15","20"}
        String[] transferGameState = gameState[0].split("");
        int B=gameState[0].indexOf("B");//find the number of the place where B start in gameState
        int[] tiles={0,0,0,0,0};
        for (int i=0; i<=4; i++){
           tiles[i]= Integer.parseInt(transferGameState[B+1] +transferGameState[B+2]);
           B+=2;
        }

        ArrayList<Character> exp_char_array = new ArrayList<Character>();
        ArrayList<Integer> exp_int_array = new ArrayList<Integer>();

        exp_char_array.add('a');
        exp_char_array.add('b');
        exp_char_array.add('c');
        exp_char_array.add('d');
        exp_char_array.add('e');

        for(int i=0; i < tiles.length; i++){
            exp_int_array.add(tiles[i]);
        }
        /*
        System.out.println(gameState[0]);
        for(int i=0; i < exp_int_array.size(); i++){
            System.out.println(exp_char_array.get(i) + ", " + exp_int_array.get(i));
        }

         */

        char rand_tile = randomTiles(exp_int_array, exp_char_array);
        return rand_tile;

        // sibo's work
        /*
        //find out if the Bag is empty
        int allTiles=0;
        for (int i:tiles){
            allTiles++;//==5
        }
        if (allTiles==0){
            return '0';
        }

        //generate random and give it to alltiles
        Random r=new Random();
        int output=r.nextInt(allTiles);

        //put the random into a corresponding tileType colour
        int R1= tiles[0]+tiles[1];int R2= R1+tiles[2];int R3= R2+tiles[3];int R4= R3+tiles[4];
        if (output<tiles[0]){
            return 'a';
        }else if (tiles[0]<=output && output<R1){
            return 'b';
        }else if (R1<=output && output<R2){
            return 'c';
        }else if (R2<=output && output<R3){
            return 'd';
        }else if (R3<=output && output<R4){
            return 'e';
        }
        return 'B';

         */
    }

    public static char randomTiles(ArrayList<Integer> int_array, ArrayList<Character> char_array){
        char char_out = 'Z';
        ArrayList<Integer> sums= new ArrayList<Integer>();

        if(int_array.size() != char_array.size()){
            return 'Z';
        }
        else{
            int sum = 0;
            sums.add(sum);
            for(Integer i : int_array){
                sum += i;
                sums.add(sum);
            }
            Random r = new Random();
            int r_output = r.nextInt(sum - 1) + 1;
            //System.out.println(r_output);
            for(int i=0; i < int_array.size(); i++){
                if(i == 0){
                    if(r_output >= 0 && r_output <= sums.get(i+1)){
                        char_out = char_array.get(i);
                    }
                }
                else{
                    if(r_output > sums.get(i) && r_output <= sums.get(i+1)){
                        char_out = char_array.get(i);
                    }
                }
            }
            return char_out;
        }
    }

   /*
   As the task 5 give the corresponding random number to the tile type 'a' to 'e',
   method updateBag modified the tile number of one type.
   'afterDraw' moves 1 tile from a type and calculate the tile number-1, then update gameState.
   */
    public static String[] updateBag(String[] gameState, char tileType, int drawnum){
        int B = gameState[0].indexOf('B');
        String[] transferGameState = gameState[0].split("");
        drawnum=1;
        if (tileType=='a'){
            int t1=B+1;
            int t2=B+2;
            int afterDraw=Integer.parseInt(transferGameState[t1]+transferGameState[t2])-1;
            gameState[0]=gameState[0].substring(0,t1)+ afterDraw +gameState[0].substring(t2+1);
        }else if (tileType=='b'){
            int t1=B+3;
            int t2=B+4;
            int afterDraw=Integer.parseInt(transferGameState[t1]+transferGameState[t2])-1;
            gameState[0]=gameState[0].substring(0,t1)+ afterDraw +gameState[0].substring(t2+1);
        }else if (tileType=='c'){
            int t1=B+5;
            int t2=B+6;
            int afterDraw=Integer.parseInt(transferGameState[t1]+transferGameState[t2])-1;
            gameState[0]=gameState[0].substring(0,t1)+ afterDraw +gameState[0].substring(t2+1);
        }else if (tileType=='d'){
            int t1=B+7;
            int t2=B+8;
            int afterDraw=Integer.parseInt(transferGameState[t1]+transferGameState[t2])-1;
            gameState[0]=gameState[0].substring(0,t1)+ afterDraw +gameState[0].substring(t2+1);
        }else if (tileType=='e'){
            int t1=B+9;
            int t2=B+10;
            int afterDraw=Integer.parseInt(transferGameState[t1]+transferGameState[t2])-1;
            gameState[0]=gameState[0].substring(0,t1)+ afterDraw +gameState[0].substring(t2+1);
        }else {
            System.out.println("unknown tile");
            return gameState;
        }
        return gameState;
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
        // If the factories are not all empty, return the given state.
        if (gameState[0].charAt(gameState[0].indexOf('F')+1)!='C'){
            return gameState;
        }

        //one factory has 4 tiles,there are 5 factories each has 4 tiles
        String[] factory = new String[4];
        String[][] factories = new  String[5][4];

        //step 1. Draw tiles 4 times to one factory, the type of the tiles is decided by the method 'drawTileFromBag'.
        //step 2. give one factory[4] to the factories[5][4], do step 1. 5 times
        for (int i = 0; i < 5; i++){
            for (int j = 0; j< 4; j++) {
                char makeDraw=drawTileFromBag(gameState);
                gameState=updateBag(gameState, makeDraw, 1);
                factory[j] = Character.toString(makeDraw);
            }factories[i] = factory;
        }

        //print output like a part of gameState, and replace the old one
        String output = "";
        String[] emptyFactory = {"0","0","0","0"};
        for (int i = 0; i<factories.length; i++){
            if (factories[i] != emptyFactory){
                output += i;
                for (int j = 0; j<factory.length; j++){
                    if (!factory[j].equals("0")){
                        output += factories[i][j];
                    }
                }
            }
        }
        gameState[0]=gameState[0].substring(0,gameState[0].indexOf("F")+1)+output+gameState[0].substring(gameState[0].indexOf("C"));
        return gameState;
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
