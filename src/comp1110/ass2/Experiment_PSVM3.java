package comp1110.ass2;

import java.util.ArrayList;
import java.util.Random;

public class Experiment_PSVM3 implements Constants{
    public static void main(String[] args) {
        ArrayList<Integer> orders = new ArrayList<Integer>();
        Random r = new Random();
        int bound = 10;
        for(int i=0; i < bound; i++){
            int rand_num = r.nextInt(bound);
            if(i == 0){
                orders.add(rand_num);
            }
            else{
                boolean repeated = false;
                for(int j=0; j < orders.size(); j++){
                    if(orders.get(j) == rand_num){
                        repeated = true;
                    }
                }
                if(repeated){
                    i--;
                }
                else{
                    orders.add(rand_num);
                }
            }
        }
        for(int i=0; i < bound; i++){
            System.out.println(orders.get(i));
        }

    }
}
