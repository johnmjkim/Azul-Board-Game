package comp1110.ass2;

import comp1110.ass2.State;
import comp1110.ass2.Factory;

import java.util.ArrayList;

public class Factories implements State {

    String factoriesState;
    int max_factories_number;

    public ArrayList<Factory> factory = new ArrayList<Factory>();

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
        int fac_num = 0;
        StringBuilder SB = new StringBuilder();
        for(char c : factoriesState.toCharArray()){
            if( len % 5 == 0){
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
        /*
        for(String s : factory){
            System.out.println(" -> " + s);
        }

         */
        this.factory.clear();
        for(int i=0; i < max_number; i++){
            this.factory.add(new Factory(eachfactory.get(i+1),i));
        }
    }

    public int getFactoryTilesNumber(char color) {
        int tot_tiles = 0;
        for( Factory f : this.factory){
            tot_tiles += f.getTilesNumber(color);
        }
        return tot_tiles;
    }

    public int getFactoryTotalTiles() {
        int tot_tiles = 0;
        for( Factory f : this.factory){
            tot_tiles += f.getTotalTilesNumber();
        }
        return tot_tiles;
    }

    public boolean isFactoryFull() {
        return ( FACTORY_SIZE * this.max_factories_number == getFactoryTotalTiles());
    }

    public Factory getFactory(int factory_number){
        return this.factory.get(factory_number);
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
                SB.append(this.factory.get(i).factoryState);
            }
        }
        this.factoriesState = String.valueOf(SB);
    }
}
