package comp1110.ass2;

public class States {

    // Players substring
    public final char PLAYER_A = 'A';
    public final char PLAYER_B = 'B';
    public final char PLAYER_C = 'C';
    public final char PLAYER_D = 'D';

    // Shared state substring
    public final char FACTORY = 'F';
    public final char CENTER = 'C';
    public final char BAG = 'B';
    public final char DISCARD = 'D';

    // Colors and characters
    public final char BLUE = 'a';
    public final char GREEN = 'b';
    public final char ORANGE = 'c';
    public final char PURPLE = 'd';
    public final char RED = 'e';
    public final char FIRST_PLAYER = 'f';

    String name;
    public States ( String State ){
        this.name = State;
    }

    public String getName(){
        return this.name;
    }

    public void printName(){
        System.out.println(this.name);
    }
}
