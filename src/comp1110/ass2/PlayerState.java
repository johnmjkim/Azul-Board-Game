package comp1110.ass2;

import java.util.ArrayList;

public class PlayerState extends States {

    // Player fields strings
    String[] playerNameState = new String[MAX_PLAYER_NUMBER];
    String[] playerStates = new String[MAX_PLAYER_NUMBER];

    public ArrayList<nPlayer> nplayers = new ArrayList<nPlayer>();

    public PlayerState(){
        super(EMPTY_STATE, DEFAULT_MAX_PLAYER);
        super.playerState = EMPTY_STATE;
        super.MAX_PLAYER_NUMBER = DEFAULT_MAX_PLAYER;
        setPlayerState( playerState , MAX_PLAYER_NUMBER );
    }

    public PlayerState( String playerState , int max_player_number){
        super(playerState, max_player_number);
        super.playerState = playerState;
        super.MAX_PLAYER_NUMBER = max_player_number;
        setPlayerState( playerState , max_player_number);
    }

    public void setPlayerState( String playerState , int max_player_number ){
        String[] playerNameState = new String[max_player_number];
        String[] playerStates = new String[max_player_number];

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

    public nPlayer getnPlayer( char player ){
        int nplayer_index = 0;
        for( int i=0; i < MAX_PLAYER_NUMBER; i++){
            if(ALL_PLAYERS[i] == player){
                nplayer_index = i;
            }
        }
        return this.nplayers.get(nplayer_index);
    }

    public String getPlayerState(){
        updatePlayerState();
        return super.playerState;
    }

    public String getPlayerState( char player ){
        updatePlayerState();
        int index = getPlayer_index( player );
        return this.playerStates[index];
    }

    public int getPlayer_index( char player ){
        int player_index = 0;
        int i = 0;
        for( char c : ALL_PLAYERS ){
            if(c == player){
                player_index = i;
            }
            i++;
        }
        return player_index;
    }

    public void updatePlayerState(){
        StringBuilder SB = new StringBuilder();
        for(int i=0; i < MAX_PLAYER_NUMBER; i++){
            this.playerNameState[i] = String.valueOf(this.nplayers.get(i).nplayerNameState);
            this.playerStates[i] = this.nplayers.get(i).nplayerState;

            SB.append(this.nplayers.get(i).nplayerNameState);
            SB.append(this.nplayers.get(i).getStateString());
        }
        super.playerState = String.valueOf(SB);
    }

    public boolean isEndofGame(){
        boolean isGameEnd = false;
        for( nPlayer p : this.nplayers ){
            if(p.isEnder() == true){
                isGameEnd = true;
            }
        }
        return isGameEnd;
    }

    public char getEnder(){
        int ender_idx = 0;
        for( nPlayer p : this.nplayers ){
            if(p.isEnder() == true){
                return ALL_PLAYERS[ender_idx];
            }
            ender_idx++;
        }
        return ' ';
    }

    public boolean existsPlayerFullStorageRow(){
        boolean existsRowTilesFull = false;
        for( nPlayer p : this.nplayers ){
            if(p.existsStorageRowTilesFull()){
                existsRowTilesFull = true;
            }
        }
        return existsRowTilesFull;
    }
}
