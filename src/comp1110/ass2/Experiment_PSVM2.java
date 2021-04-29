package comp1110.ass2;

public class Experiment_PSVM2 implements Constants{
    public static void main(String[] args) {
        String bag_test_str = "1014151317";
        String discard_test_str = "1513080207";
        BagObject bag_obj = new BagObject(bag_test_str);
        DiscardObject discard_obj = new DiscardObject(discard_test_str);
        System.out.println(bag_obj);
        System.out.println(bag_obj.getTotalTilesNumber());
        for( char color : COLORS){
            System.out.println(bag_obj.getTilesNumber(color));
        }
        System.out.println(discard_obj);
        System.out.println(discard_obj.getTotalTilesNumber());
        for( char color : COLORS){
            System.out.println(discard_obj.getTilesNumber(color));
        }
    }
}
