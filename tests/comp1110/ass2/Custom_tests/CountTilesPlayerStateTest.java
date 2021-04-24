package comp1110.ass2.Custom_tests;

import comp1110.ass2.PlayerState;
import comp1110.ass2.TestCountCases;
import org.junit.jupiter.api.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@org.junit.jupiter.api.Timeout(value = 1000, unit = MILLISECONDS)

public class CountTilesPlayerStateTest implements TestCountCases {

    private void CountMosaictest(String in, int[] expected, int player) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        int out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getTotalTilesNumber();
        assertEquals(expected[6 * player], out, "Input state in CountMosaicTest " + ALL_PLAYERS[player] + " : \"" + in + "\"");
    }

    private void CountMosaicByTilestest(String in, int[] expected, int player) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        char color = BLUE;
        for(int j=0; j <= RED - BLUE; j++){
            int out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getTilesNumber(color);
            assertEquals(expected[6 * player + j+1], out, "Input state in CountMosaicByTilestest Player " + ALL_PLAYERS[player] + " color " + color + " : \"" + in + "\"");
            color++;
        }
    }

    private void CountMosaicRowtest(String in, int[] expected, int player) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        for(int row=0; row < MAX_MOSAIC_ROW; row++){
            int out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getMosaicRow(row).getTotalTilesNumber();
            assertEquals(expected[5 * player + row], out, "Input state in CountMosaicRowTest Player " + ALL_PLAYERS[player] + " Row " + row + " : \"" + in + "\"");
        }
    }

    private void CountMosaicColtest(String in, int[] expected, int player) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        for(int col=0; col < MAX_MOSAIC_COL; col++){
            int out = ps.getnPlayer(ALL_PLAYERS[player]).mosaic.getMosaicCol(col).getTotalTilesNumber();
            assertEquals(expected[5 * player + col], out, "Input state in CountMosaicColTest Player " + ALL_PLAYERS[player] + " Col " + col + " : \"" + in + "\"");
        }
    }

    private void CountStoragetest(String in, int[] expected, int player) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        int out = ps.getnPlayer(ALL_PLAYERS[player]).storage.getTotalTilesNumber();
        assertEquals(expected[6 * player], out, "Input state in CountStorageTest " + ALL_PLAYERS[player] + " : \"" + in + "\"");
    }

    private void CountStorageByTilestest(String in, int[] expected, int player) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        char color = BLUE;
        for(int j=0; j <= RED - BLUE; j++){
            int out = ps.getnPlayer(ALL_PLAYERS[player]).storage.getTilesNumber(color);
            assertEquals(expected[6 * player + j+1], out, "Input state in CountStorageByTilestest Player " + ALL_PLAYERS[player] + " color " + color + " : \"" + in + "\"");
            color++;
        }
    }

    private void CountStorageRowtest(String in, int[] expected, int player) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        for(int row=0; row < MAX_STORAGE_ROW; row++){
            int out = ps.getnPlayer(ALL_PLAYERS[player]).storage.getStorageRow(row).getTotalTilesNumber();
            assertEquals(expected[5 * player + row], out, "Input state in CountStorageRowTest Player " + ALL_PLAYERS[player] + " Row " + row + " : \"" + in + "\"");
        }
    }

    private void CountFloortest(String in, int[] expected, int player) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        int out = ps.getnPlayer(ALL_PLAYERS[player]).floor.getTotalTilesNumber();
        assertEquals(expected[7 * player], out, "Input state in CountFloorTest " + ALL_PLAYERS[player] + " : \"" + in + "\"");
    }

    private void CountFloorByTilestest(String in, int[] expected, int player) {
        PlayerState ps = new PlayerState(in, DEFAULT_MAX_PLAYER);
        char color = BLUE;
        for(int j=0; j <= FIRST_PLAYER - BLUE; j++){
            int out = ps.getnPlayer(ALL_PLAYERS[player]).floor.getTilesNumber(color);
            assertEquals(expected[7 * player + j+1], out, "Input state in CountFloorByTilestest Player " + ALL_PLAYERS[player] + " color " + color + " : \"" + in + "\"");
            color++;
        }
    }

    @Test
    public void testMosaicCount(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountMosaictest(VALID_STATES[i][1], VALID_TILES_MOSAIC[i], j);
            }
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountMosaictest(FULL_GAME_WITH_MOVES[i][1], GAME_WITH_MOVE_TILES_MOSAIC[i], j);
            }
        }
    }

    @Test
    public void testMosaicCountByTiles(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountMosaicByTilestest(VALID_STATES[i][1], VALID_TILES_MOSAIC[i], j);
            }
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountMosaicByTilestest(FULL_GAME_WITH_MOVES[i][1], GAME_WITH_MOVE_TILES_MOSAIC[i], j);
            }
        }
    }

    @Test
    public void testMosaicRowCount(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountMosaicRowtest(VALID_STATES[i][1], VALID_TILES_MOSAIC_ROW[i], j);
            }
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountMosaicRowtest(FULL_GAME_WITH_MOVES[i][1], GAME_WITH_MOVE_TILES_MOSAIC_ROW[i], j);
            }
        }
    }

    @Test
    public void testMosaicColCount(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountMosaicColtest(VALID_STATES[i][1], VALID_TILES_MOSAIC_COL[i], j);
            }
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountMosaicColtest(FULL_GAME_WITH_MOVES[i][1], GAME_WITH_MOVE_TILES_MOSAIC_COL[i], j);
            }
        }
    }

    @Test
    public void testStorageCount(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountStoragetest(VALID_STATES[i][1], VALID_TILES_STORAGE[i], j);
            }
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountStoragetest(FULL_GAME_WITH_MOVES[i][1], GAME_WITH_MOVE_TILES_STORAGE[i], j);
            }
        }
    }

    @Test
    public void testStorageCountByTiles(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountStorageByTilestest(VALID_STATES[i][1], VALID_TILES_STORAGE[i], j);
            }
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountStorageByTilestest(FULL_GAME_WITH_MOVES[i][1], GAME_WITH_MOVE_TILES_STORAGE[i], j);
            }
        }
    }

    @Test
    public void testStorageRowCount(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountStorageRowtest(VALID_STATES[i][1], VALID_TILES_STORAGE_ROW[i], j);
            }
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountStorageRowtest(FULL_GAME_WITH_MOVES[i][1], GAME_WITH_MOVE_TILES_STORAGE_ROW[i], j);
            }
        }
    }

    @Test
    public void testFloorCount(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountFloortest(VALID_STATES[i][1], VALID_TILES_FLOOR[i], j);
            }
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountFloortest(FULL_GAME_WITH_MOVES[i][1], GAME_WITH_MOVE_TILES_FLOOR[i], j);
            }
        }
    }

    @Test
    public void testFloorCountByTiles(){
        for (int i = 0; i < VALID_STATES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountFloorByTilestest(VALID_STATES[i][1], VALID_TILES_FLOOR[i], j);
            }
        }
        for (int i = 0; i < FULL_GAME_WITH_MOVES.length; i++) {
            for(int j = 0; j < DEFAULT_MAX_PLAYER; j++){
                CountFloorByTilestest(FULL_GAME_WITH_MOVES[i][1], GAME_WITH_MOVE_TILES_FLOOR[i], j);
            }
        }
    }

}
