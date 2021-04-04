package comp1110.ass2;

import java.util.ArrayList;

public class Experiment_PSVM {

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        Player pA = new PlayerA();
        pA.Score("07");
        System.out.println(pA.getScore());
        players.add(pA);
        Player pB = new PlayerB();
        pA.Score("07");
        System.out.println(pA.getScore());
        players.add(pA);
    }
}
