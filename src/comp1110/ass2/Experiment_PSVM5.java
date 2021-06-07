package comp1110.ass2;

import comp1110.ass2.backend.MultiAzul;
import comp1110.ass2.backend.PlayerState;
import comp1110.ass2.backend.SharedState;

import java.util.ArrayList;

public class Experiment_PSVM5 implements Constants{
    public static void main(String[] args) {
        int PLAYER_NUMBER = 2;
        String[] gameState = new String[NUMBER_OF_STATES];
        String move;
        ArrayList<String> all_moves;
        int min_floor_tiles = MAX_FLOOR_STRING_SIZE;
        boolean storage_full_exist = false;
        int high_storage_row_full = 0;
        int min_storage_empty_tiles = MAX_STORAGE_COL;
        MultiAzul multiazul = new MultiAzul(PLAYER_NUMBER);

        gameState = multiazul.setInitalStates(PLAYER_NUMBER);
        gameState = multiazul.setStartingState(gameState);

        /*
        gameState[0] = "BFCceB1713191615D0000000000";
        gameState[1] = "A0MS0b11d22b33d24e1FbbbB0MS0a11e22a13e1Faf";
         */
        System.out.println(gameState[0]);
        System.out.println(gameState[1]);
        SharedState ss = new SharedState(gameState[0], PLAYER_NUMBER);
        PlayerState ps = new PlayerState(gameState[1], PLAYER_NUMBER);
        char current_turn = ss.getTurnState().charAt(0);
        all_moves = multiazul.generateAllActions(gameState);
        for(int i=0; i < all_moves.size(); i++) {
            move = all_moves.get(i);
            String[] new_gameState = multiazul.applyMove(gameState, move);
            PlayerState new_ps = new PlayerState(new_gameState[1], PLAYER_NUMBER);
            if(new_ps.getnPlayer(current_turn).storage.existsStorageRowTilesFull()){
                storage_full_exist = true;
            }
            if(storage_full_exist){
                min_storage_empty_tiles = 0;
                int new_min_floor_tiles = new_ps.getnPlayer(current_turn).floor.getTotalTilesNumber();
                int new_high_storage_row_full = new_ps.getnPlayer(current_turn).storage.findHighestStorageRowFull();
                if(new_min_floor_tiles < min_floor_tiles){
                    min_floor_tiles = new_min_floor_tiles;
                    high_storage_row_full = 0;
                }
                if(min_floor_tiles == new_min_floor_tiles){
                    if(new_high_storage_row_full > high_storage_row_full){
                        high_storage_row_full = new_high_storage_row_full;
                    }
                }
            }
            else{
                int new_min_storage_empty_tiles = new_ps.getnPlayer(current_turn).storage.findMinimumEmptyStorageTiles();
                if(new_min_storage_empty_tiles < min_storage_empty_tiles){
                    min_storage_empty_tiles = new_min_storage_empty_tiles;
                }
            }
            System.out.print(move);
            System.out.print(" -> ");
            System.out.print(new_gameState[1]);
            System.out.print(", Floor : ");
            System.out.print(new_ps.getnPlayer(current_turn).floor.getTotalTilesNumber());
            System.out.print(", Storage : ");
            System.out.print(new_ps.getnPlayer(current_turn).storage.existsStorageRowTilesFull());
            System.out.print(", Empty Tiles :");
            System.out.print(new_ps.getnPlayer(current_turn).storage.findMinimumEmptyStorageTiles());
            System.out.println(" || max floor tiles : " + min_floor_tiles + " ,storage full exists : " + storage_full_exist + " ,min storage empty tiles : " + min_storage_empty_tiles);

            for(int row=0; row < MAX_STORAGE_ROW; row++){
                System.out.print("( row " + row + " full = ");
                System.out.print(new_ps.getnPlayer(current_turn).storage.getStorageRow(row).isTilesFull());
                System.out.print(" ) ");
            }
            System.out.println();
        }
        int idx =0;
        ArrayList<String> some_moves = all_moves;
        ArrayList<Integer> delete_indice = new ArrayList<>();
        for(String move_candidate : all_moves) {
            String[] new_gameState = multiazul.applyMove(gameState, move_candidate);
            PlayerState new_ps = new PlayerState(new_gameState[1], PLAYER_NUMBER);
            if(storage_full_exist){
                if(!new_ps.getnPlayer(current_turn).storage.existsStorageRowTilesFull()){
                    delete_indice.add(idx);
                }
                else{
                    if(new_ps.getnPlayer(current_turn).floor.getTotalTilesNumber() > min_floor_tiles){
                        delete_indice.add(idx);
                    }
                    else{
                        if(new_ps.getnPlayer(current_turn).storage.findHighestStorageRowFull() < high_storage_row_full){
                            delete_indice.add(idx);
                        }
                    }
                }
            }
            else{
                if(new_ps.getnPlayer(current_turn).storage.findMinimumEmptyStorageTiles() > min_storage_empty_tiles){
                    delete_indice.add(idx);
                }
            }
            idx++;
        }
        for(int i=0; i < delete_indice.size(); i++){
            some_moves.remove(delete_indice.get(i) - i);
        }
        System.out.println(" pruned ");
        for(int i=0; i < some_moves.size(); i++) {
            move = some_moves.get(i);
            String[] new_gameState = multiazul.applyMove(gameState, move);
            PlayerState new_ps = new PlayerState(new_gameState[1], PLAYER_NUMBER);
            if(new_ps.getnPlayer(current_turn).storage.existsStorageRowTilesFull()){
                storage_full_exist = true;
            }
            if(storage_full_exist){
                min_storage_empty_tiles = 0;
                if(new_ps.getnPlayer(current_turn).floor.getTotalTilesNumber() < min_floor_tiles){
                    min_floor_tiles = new_ps.getnPlayer(current_turn).floor.getTotalTilesNumber();
                }
            }
            else{
                if(new_ps.getnPlayer(current_turn).storage.findMinimumEmptyStorageTiles() < min_storage_empty_tiles){
                    min_storage_empty_tiles = new_ps.getnPlayer(current_turn).storage.findMinimumEmptyStorageTiles();
                }
            }
            System.out.print(move);
            System.out.print(" -> ");
            System.out.print(new_gameState[1]);
            System.out.print(", Floor : ");
            System.out.print(new_ps.getnPlayer(current_turn).floor.getTotalTilesNumber());
            System.out.print(", Storage : ");
            System.out.print(new_ps.getnPlayer(current_turn).storage.existsStorageRowTilesFull());
            System.out.print(", Empty Tiles :");
            System.out.print(new_ps.getnPlayer(current_turn).storage.findMinimumEmptyStorageTiles());
            System.out.println(" || max floor tiles : " + min_floor_tiles + " ,storage full exists : " + storage_full_exist + " ,min storage empty tiles : " + min_storage_empty_tiles);
            /*
            for(int row=0; row < MAX_STORAGE_ROW; row++){
                System.out.print("( row " + row + " full = ");
                System.out.print(new_ps.getnPlayer(current_turn).storage.getStorageRow(row).isTilesFull());
                System.out.print(" ) ");
            }
             */
        }
        System.out.println();

        //move = multiazul.generateSmartAction(gameState);
    }
}
