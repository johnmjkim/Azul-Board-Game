package comp1110.ass2.gui;

import comp1110.ass2.Constants;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

/**
 * @author Min Jae, Kim
 * In this class, we put different kinds of information of players in different ArrayList,
 * We can use it in other classes
 */
public class PlayersInformation implements Constants {
    int MAX_PLAYER_NUMBER;
    HashMap<Character, PlayerInfo> playermap;
    ArrayList<Integer> playerOrders;
    ArrayList<String> playerNames;
    ArrayList<Character> playerTypes;
    ArrayList<PlayerInfo> playerInfos = new ArrayList<>();

    public PlayersInformation(ArrayList<Integer> player_orders, ArrayList<String> player_names, ArrayList<Character> player_types) {
        this.MAX_PLAYER_NUMBER = player_orders.size();
        this.playerOrders = player_orders;
        this.playerNames = player_names;
        this.playerTypes = player_types;
        setPlayerInfos();
    }

    private void setPlayerInfos(){
        for(int i=0; i < MAX_PLAYER_NUMBER; i++){
            int order = playerOrders.get(i);
            this.playerInfos.add(new PlayerInfo(order, ALL_PLAYERS[order], playerNames.get(i), playerTypes.get(i)));
        }
        this.playermap = reorder();
    }

    public ArrayList<PlayerInfo> getPlayerInfos(){
        return this.playerInfos;
    }

    public HashMap<Character, PlayerInfo> getPlayerMap(){
        return this.playermap;
    }

    public PlayerInfo getPlayerInfo(char player_turn){
        return this.playermap.get(player_turn);
    }

    HashMap reorder(){
        HashMap<Character, PlayerInfo> reorder = new HashMap();
        for( char player_turn : ALL_PLAYERS ){
            for(PlayerInfo pi : this.playerInfos ){
                if(pi.getTurn() == player_turn){
                    reorder.put(player_turn, pi);
                }
            }
        }
        return reorder;
    }

    @Override
    public String toString(){
        StringBuilder SB = new StringBuilder();
        for(int i=0; i < MAX_PLAYER_NUMBER; i++){
            SB.append(this.playerInfos.get(i));
        }
        return String.valueOf(SB);
    }

    public class PlayerInfo implements Comparable<PlayerInfo>{
        char player_turn;
        String name;
        char type;
        int order;
        int score = INITIAL_SCORE;
        int rank;
        String move;

        PlayerInfo(int order, char player_turn, String name, char type) {
            this.order = order;
            this.player_turn = player_turn;
            this.name = name;
            this.type = type;
        }

        public void setScore(int score){
            this.score = score;
        }

        public void setRank(int rank){
            this.rank = rank;
        }

        public void setMove(String move){
            this.move = move;
        }

        public int getOrder(){
            return this.order;
        }

        public char getTurn(){
            return this.player_turn;
        }

        public String getName(){
            return this.name;
        }

        public char getType(){
            return this.type;
        }

        public int getScore(){
            return this.score;
        }

        public int getRank(){
            return this.rank;
        }

        public String getMove(){
            return this.move;
        }

        @Override
        public String toString() {
            return " Name : " + getName() + " Order : " + getOrder() + " Turn : " + getTurn() + " Type : " + getType() + " Score : " + getScore() + " Rank : " + getRank() + " Move : " + getMove();
        }

        @Override
        public int compareTo(PlayerInfo playerInfo) {
            if(order == playerInfo.order){
                return 0;
            }
            else if(order > playerInfo.order){
                return 1;
            }
            else{
                return -1;
            }
        }
    }
}