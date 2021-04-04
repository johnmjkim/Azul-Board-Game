package comp1110.ass2;

import java.util.ArrayList;

public class Experiment_PSVM {

    public static void main(String[] args) {
        ArrayList<Player> players = new ArrayList<Player>();
        Player pA = new PlayerA();
        pA.Name("A");
        pA.Score("07");
        players.add(pA);
        Player pB = new PlayerB();
        pB.Name("B");
        pA.Score("10");
        players.add(pB);
        for(Player p : players){
            System.out.println(p.getName() + ", " + p.getScore());

        }
    }
}
