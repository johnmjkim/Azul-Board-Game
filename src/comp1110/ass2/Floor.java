package comp1110.ass2;

import comp1110.ass2.State;

import java.util.Arrays;

public class Floor extends OrderTypedObject{
    /**
     * @author Min Jae, Kim
     * Constructor method for Floor class
     * @param orderTypedObjectState
     */
    public Floor(String orderTypedObjectState) {
        super(orderTypedObjectState);
    }

    // Discard

    /**
     * @author Min Jae, Kim
     * Fill discard by tiles from floor
     * refillDiscardtoBag() method in SharedState
     * @return
     */
    public String getFloorTilesString(){
        StringBuilder SB = new StringBuilder();
        char color = BLUE;
        for (int i = 0; i <= FIRST_PLAYER - BLUE; i++) {
            if(this.letters[color] < 10){
                SB.append("0");
            }
            SB.append(this.letters[color]);
            color++;
        }
        return String.valueOf(SB);
    }

    boolean hasOnlyOneFirstPlayerToken() {
        return (this.letters[FIRST_PLAYER] == 1) && (getTotalTilesNumber() == 1);
    }

    boolean hasFirstPlayerToken() {return (this.letters[FIRST_PLAYER] != 0); }
}