package comp1110.ass2.gui;

import comp1110.ass2.Constants;

import java.util.ArrayList;

public class FactoriesCoordinates implements Constants {
    int max_factories_number;
    ArrayList<FactoryCoordinates> factoriescoordinates = new ArrayList<FactoryCoordinates>();

    public FactoriesCoordinates(int max_factories_number){
        // Factories
        this.max_factories_number = max_factories_number;
        int[] max_row_col = new int[]{MAX_FACTORY_TILES_ROW_IMAGE, MAX_FACTORY_TILES_COL_IMAGE};
        double[] initial_xy = new double[]{INITIAL_FACTORIES_IMAGE_POS_X,INITIAL_FACTORIES_IMAGE_POS_Y};
        double[] gap_xy = new double[]{BIG_TILE_IMAGE_SIZE_X + BIG_TILE_IMAGE_SIZE_X_GAP,BIG_TILE_IMAGE_SIZE_Y + BIG_TILE_IMAGE_SIZE_Y_GAP};

        for(int factory_number=0; factory_number < max_factories_number; factory_number++){
            int factories_row = (int) factory_number/MAX_FACTORIES_TILES_COL_IMAGE;
            int factories_col = factory_number % MAX_FACTORIES_TILES_COL_IMAGE + factories_row;
            double[] factory_initial_xy = new double[]{initial_xy[0] + FACTORIES_IMAGE_SIZE_X_GAP * factories_col, initial_xy[1] + FACTORIES_IMAGE_SIZE_Y_GAP * factories_row};
            FactoryCoordinates FACTORY_COORDINATES = new FactoryCoordinates(max_row_col, factory_initial_xy, gap_xy, factory_number);
            factoriescoordinates.add(FACTORY_COORDINATES);
        }
    }

    public FactoryCoordinates getFactoryCoordinates(int factory_number){
        return this.factoriescoordinates.get(factory_number);
    }

    public String toString(){
        StringBuilder SB = new StringBuilder();
        SB.append("Factories\n");
        for(int factory=0; factory < max_factories_number; factory++){
            SB.append(String.valueOf(this.getFactoryCoordinates(factory)));
            if(factory < max_factories_number - 1){
                SB.append("\n");
            }
        }
        return String.valueOf(SB);
    }
}
