package comp1110.ass2;

import java.util.Random;

public class Experiment_PSVM6 {
    public static void main(String[] args) {
        Random rand = new Random();
        int num = 20;
        int numbers = 10;
        int low = 0;
        int high = 0;
        for(int i=0; i < numbers; i++){
            high = low + num/numbers;
            System.out.println(" low : " + low + " high : " + high + " rand : " + (rand.nextInt(high - low) + low));
            low = high;
        }
    }
}
