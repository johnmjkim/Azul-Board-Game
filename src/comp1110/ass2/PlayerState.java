package comp1110.ass2;

public class PlayerState extends States{

    // Player fields strings
    String[] playernameState = new String[MAX_PLAYER_NUMBER];
    String[] scoreState = new String[MAX_PLAYER_NUMBER];
    String[] mosaicState = new String[MAX_PLAYER_NUMBER];
    String[] storageState = new String[MAX_PLAYER_NUMBER];
    String[] floorState = new String[MAX_PLAYER_NUMBER];

    public PlayerState( String playerState , int max_player_number){
        super(playerState, max_player_number);
        super.playerState = playerState;
        super.MAX_PLAYER_NUMBER = max_player_number;
        setPlayerState( playerState , max_player_number);
    }

    public void setPlayerState( String playerState , int max_player_number ){
        String[] playername = new String[MAX_PLAYER_NUMBER];
        String[] scoreState = new String[MAX_PLAYER_NUMBER];
        String[] mosaicState = new String[MAX_PLAYER_NUMBER];
        String[] storageState = new String[MAX_PLAYER_NUMBER];
        String[] floorState = new String[MAX_PLAYER_NUMBER];
        String playerState_substring = EMPTY_STATE;

        for(int i=0; i < max_player_number; i++){
            if( i < max_player_number - 1){
                playerState_substring = playerState.substring(playerState.indexOf(ALL_PLAYERS[i]),playerState.indexOf(ALL_PLAYERS[i + 1]));
            }
            else{
                playerState_substring = playerState.substring(playerState.indexOf(ALL_PLAYERS[i]),playerState.length());
            }
            playername[i] = playerState_substring.substring(0, 1);
            scoreState[i] = playerState_substring.substring(1,playerState_substring.indexOf(MOSAIC));
            mosaicState[i] = playerState_substring.substring(playerState_substring.indexOf(MOSAIC) + 1,playerState_substring.indexOf(STORAGE));
            storageState[i] = playerState_substring.substring(playerState_substring.indexOf(STORAGE) + 1,playerState_substring.indexOf(FLOOR));
            floorState[i] = playerState_substring.substring(playerState_substring.indexOf(FLOOR) + 1,playerState_substring.length());
        }

        setPlayerNameState(playername);
        setScoreState(scoreState);
        setMosaicState(mosaicState);
        setStorageState(storageState);
        setFloorState(floorState);
    }

    private void setPlayerNameState( String[] playernameState ){
        this.playernameState = playernameState;
    }

    private void setScoreState( String[] scoreState ) {
        this.scoreState = scoreState;
    }

    private void setMosaicState( String[] mosaicState ) {
        this.mosaicState = mosaicState;
    }

    private void setStorageState( String[] storageState ){
        this.storageState = storageState;
    }

    private void setFloorState( String[] floorState ){
        this.floorState = floorState;
    }
}
