package comp1110.ass2;

import java.util.Random;

public class BagObject extends BagTypedObject{

    /**
     * @author Min Jae, Kim
     * Constructor method for BagObject class
     * @param bagTypedObjectState
     */
    public BagObject(String bagTypedObjectState) {
        super(bagTypedObjectState);
    }

    /**
     * @author Min Jae, Kim
     * Get random tiles based on number of tiles in bag
     * r_output range is 1 (inclusive) ~ getTotalTilesNumber() (inclusive)
     * @return
     */
    public char getRandomTile(){
        char picked_tile = 'Z';
        int prob_blue = getTilesNumber(BLUE);
        int prob_green = prob_blue + getTilesNumber(GREEN);
        int prob_orange = prob_green + getTilesNumber(ORANGE);
        int prob_purple = prob_orange + getTilesNumber(PURPLE);
        int prob_red = prob_purple + getTilesNumber(RED);

        Random r = new Random();
        int r_range = getTotalTilesNumber();
        int r_output = 0;
        if(r_range > 0){
            r_output = r.nextInt(getTotalTilesNumber()) + 1;
        }
        else{
            prob_blue = 1;
            prob_green = 2;
            prob_orange = 3;
            prob_purple = 4;
            prob_red = 5;
            r_output = r.nextInt(5) + 1;
        }
        //System.out.println(r_range + " : " + prob_blue + ", " + prob_green + ", " + prob_orange + ", " + prob_purple + ", " + prob_red + " : " + r_output);

        if (r_output > 0 && r_output <= prob_blue) {
            picked_tile = BLUE;
        }
        else if (r_output > prob_blue && r_output <= prob_green) {
            picked_tile = GREEN;
        }
        else if (r_output > prob_green && r_output <= prob_orange) {
            picked_tile = ORANGE;
        }
        else if (r_output > prob_orange && r_output <= prob_purple) {
            picked_tile = PURPLE;
        }
        else if (r_output > prob_purple && r_output <= prob_red) {
            picked_tile = RED;
        }
        return picked_tile;
    }
}
