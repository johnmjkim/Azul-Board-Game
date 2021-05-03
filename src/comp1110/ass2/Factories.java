package comp1110.ass2;

import comp1110.ass2.State;
import comp1110.ass2.Factory;

import java.util.ArrayList;

public class Factories implements Tiles {

    String factoriesState;
    int max_factories_number;

    public ArrayList<Factory> factory = new ArrayList<Factory>();

    /**
     * @author Min Jae, Kim
     * Constructor method for Factories class
     * Factories contains list of Factory class, number of factories are determined by number of players
     * @param factoriesState
     * @param max_factories_number
     */
    public Factories ( String factoriesState , int max_factories_number ){
        this.factoriesState = factoriesState;
        this.max_factories_number = max_factories_number;
        setFactories(factoriesState);
    }

    public void setFactories( String factoriesState ){
        ArrayList<String> eachfactory = new ArrayList<String>();
        // Add factories based on maximum number of players
        int max_number = this.max_factories_number;
        int len = 0;
        int div = 5;
        int fac_num = 0;
        StringBuilder SB = new StringBuilder();
        for(char c : factoriesState.toCharArray()){
            if( len % div == 0){
                if(fac_num == Character.getNumericValue(c)){
                    eachfactory.add(String.valueOf(SB));
                }
                else{
                    eachfactory.add(String.valueOf(SB));
                    // Adding empty factory
                    while(fac_num != Character.getNumericValue(c)){
                        eachfactory.add(EMPTY_STATE);
                        fac_num++;
                    }
                }
                SB.delete(0,SB.length());
                fac_num++;
            }
            else{
                SB.append(c);
            }
            len++;
        }
        // Adding remaining or blank factory
        eachfactory.add(String.valueOf(SB));
        while(fac_num < max_number){
            eachfactory.add(EMPTY_STATE);
            fac_num++;
        }
        SB.delete(0,SB.length());
        this.factory.clear();
        for(int i=0; i < max_number; i++){
            this.factory.add(new Factory(eachfactory.get(i+1),i));
        }
    }

    @Override
    public void countTilesNumber(String State) {

    }

    @Override
    public int getTilesNumber(char color) {
        int tot_tiles = 0;
        for( Factory f : this.factory){
            tot_tiles += f.getTilesNumber(color);
        }
        return tot_tiles;
    }

    @Override
    public int getTotalTilesNumber() {
        int tot_tiles = 0;
        for( Factory f : this.factory){
            tot_tiles += f.getTotalTilesNumber();
        }
        return tot_tiles;
    }

    public boolean isFactoryFull() {
        return ( FACTORY_SIZE * this.max_factories_number == getTotalTilesNumber());
    }

    public Factory getFactory(int factory_number){
        return this.factory.get(factory_number);
    }

    public boolean isFactoriesNumberValid(){
        ArrayList<Integer> factories_number = new ArrayList<Integer>();

        char[] factoriesState_char_array = factoriesState.toCharArray();
        int len = 0;
        int div = 5;
        int i = 0;
        for( char c : factoriesState_char_array ){
            if(i % div == 0){
                factories_number.add(Character.getNumericValue(c));
                len++;
            }
            i++;
        }
        if(len > max_factories_number){
            return false;
        }
        else if(len > 1){
            for(int j=1; j < len; j++){
                if(factories_number.get(j) <= factories_number.get(j-1)){
                    return false;
                }
                else if(factories_number.get(j) > max_factories_number - 1){
                    return false;
                }
            }
            return true;
        }
        else if(len == 1){
            if(factories_number.get(0) > max_factories_number - 1){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }
    }

    @Override
    public boolean isStateEmpty() {
        return this.factoriesState.isEmpty();
    }

    @Override
    public String getStateString() {
        updateState();
        return this.factoriesState;
    }

    @Override
    public String toString(){
        return getStateString();
    }

    @Override
    public void updateState() {
        StringBuilder SB = new StringBuilder();
        for(int i=0; i < max_factories_number; i++){
            if(!this.factory.get(i).isStateEmpty()){
                SB.append(this.factory.get(i).number);
                SB.append(this.factory.get(i).getStateString());
            }
        }
        this.factoriesState = String.valueOf(SB);
    }
}
