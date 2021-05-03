package comp1110.ass2;

import comp1110.ass2.State;

import java.util.Arrays;

public class Center extends OrderTypedObject{
    /**
     * @author Min Jae, Kim
     * Constructor method for Center class
     * @param orderTypedObjectState
     */
    public Center(String orderTypedObjectState) {
        super(orderTypedObjectState);
    }

    /**
     * @author Min Jae, Kim
     * Check if there is only one first player token if center has first player token
     * @return
     */
    boolean hasOnlyOneFirstPlayerToken() {
        return (this.letters[FIRST_PLAYER] == 1) && (getTotalTilesNumber() == 1);
    }

    /**
     * @author Min Jae, Kim
     * Check if the center has first player token
     * @return
     */
    boolean hasFirstPlayerToken() {return (this.letters[FIRST_PLAYER] != 0); }
}
