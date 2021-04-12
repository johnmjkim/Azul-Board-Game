package comp1110.ass2;

import java.util.ArrayList;

public class PlayerState extends States{

    // Player fields strings
    String[] playerNameState = new String[MAX_PLAYER_NUMBER];
    String[] playerStates = new String[MAX_PLAYER_NUMBER];

    public ArrayList<nPlayer> nplayers = new ArrayList<nPlayer>();

    public PlayerState( String playerState , int max_player_number){
        super(playerState, max_player_number);
        super.playerState = playerState;
        super.MAX_PLAYER_NUMBER = max_player_number;
        setPlayerState( playerState , max_player_number);
    }

    public void setPlayerState( String playerState , int max_player_number ){
        String[] playerNameState = new String[MAX_PLAYER_NUMBER];
        String[] playerStates = new String[MAX_PLAYER_NUMBER];

        String playerState_substring = EMPTY_STATE;

        for(int i=0; i < max_player_number; i++){
            if( i < max_player_number - 1){
                playerState_substring = playerState.substring(playerState.indexOf(ALL_PLAYERS[i]),playerState.indexOf(ALL_PLAYERS[i + 1]));
            }
            else{
                playerState_substring = playerState.substring(playerState.indexOf(ALL_PLAYERS[i]),playerState.length());
            }
            playerNameState[i] = playerState_substring.substring(0, 1);
            playerStates[i] = playerState_substring.substring(1,playerState_substring.length());
        }

        setPlayerStates(playerStates, playerNameState);
    }

    private void setPlayerStates( String[] playerStates, String[] playerNameState ){
        this.nplayers.clear();
        for(int i=0; i < MAX_PLAYER_NUMBER; i++){
            this.nplayers.add(new nPlayer( playerStates[i] , playerNameState[i].charAt(0) ));
        }

    }

    public String getPlayerState(){
        updatePlayerState();
        return super.playerState;
    }

    public void updatePlayerState(){
        StringBuilder SB = new StringBuilder();
        for(int i=0; i < MAX_PLAYER_NUMBER; i++){
            this.playerNameState[i] = String.valueOf(this.nplayers.get(i).nplayerNameState);
            this.playerStates[i] = this.nplayers.get(i).nplayerState;

            SB.append(this.nplayers.get(i).nplayerNameState);
            SB.append(this.nplayers.get(i).getnplayerState());
        }
        super.playerState = String.valueOf(SB);
    }
}
