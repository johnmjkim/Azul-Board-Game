package comp1110.ass2;

public class Score {

    // Size, Numbers of all components
    public final String EMPTY_STATE = "";
    int score;

    // Player fields strings
    String scoreState = EMPTY_STATE;

    public Score ( String scoreState ){
        this.scoreState = scoreState;
        this.score = Integer.valueOf(scoreState);
    }

    public String getScoreState(){
        return this.scoreState;
    }

    public void addScore( int score_added ){
        int current_score = this.score;
        int adjusted_score = current_score + score_added;
        if(adjusted_score < 0){
            this.score = 0;
        }
        else{
            this.score = adjusted_score;
        }
        updateScoreState();
    }

    public int scoreLose_clearFloor( int floor_length ) {
        if (floor_length == 0) {
            return 0;
        }
        else if (floor_length == 1) {
            return -1;
        }
        else if (floor_length == 2) {
            return -2;
        }
        else if (floor_length == 3) {
            return -4;
        }
        else if (floor_length == 4) {
            return -6;
        }
        else if (floor_length == 5) {
            return -8;
        }
        else if (floor_length == 6) {
            return -11;
        }
        else {
            return -14;
        }
    }

    public void clearFloorScore( int floor_length ){
        int score_lost = scoreLose_clearFloor(floor_length);
        addScore(score_lost);
        updateScoreState();
    }

    public void updateScoreState(){
        StringBuilder SB = new StringBuilder();
        SB.append(this.score);
        this.scoreState = String.valueOf(SB);
    }

    boolean isScoreStateEmpty(){
        return this.scoreState.isEmpty();
    }

}
