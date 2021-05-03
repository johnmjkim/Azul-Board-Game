package comp1110.ass2;

import comp1110.ass2.State;

import java.util.Arrays;

public class Factory extends OrderTypedObject{
    // number of Factory 0~4 for two players
    int number;

    /**
     * Constructor method for Factory class with factory number
     * @param orderTypedObjectState
     * @param number
     */
    public Factory(String orderTypedObjectState, int number ) {
        super(orderTypedObjectState);
        this.number = number;
    }
}