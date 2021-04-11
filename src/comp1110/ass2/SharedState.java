package comp1110.ass2;

public class SharedState extends States{

    // Shared fields Strings
    String turnState = EMPTY_STATE;
    String factoryState = EMPTY_STATE;
    String centerState = EMPTY_STATE;
    String bagState = EMPTY_STATE;
    String discardState = EMPTY_STATE;

    // Class fields
    public Factories factories;
    public Center center;
    public Bag bag;
    public Discard discard;

    // Size, Numbers of all components
    public final int FACTORY_MAX_NUMBER = 2 * MAX_PLAYER_NUMBER + 1;
    public final int FACTORY_SIZE = 4;

    public SharedState( String sharedState , int max_player_number ){
        super(sharedState, max_player_number);
        super.sharedState = sharedState;
        super.MAX_PLAYER_NUMBER = max_player_number;
        setSharedState(sharedState);
    }

    public void setSharedState( String sharedState ){
        String turnState = sharedState.substring(0, sharedState.indexOf(FACTORY));
        String rest_sharedState = sharedState.substring(sharedState.indexOf(turnState) + 1, sharedState.length());
        String factoryState = rest_sharedState.substring(rest_sharedState.indexOf(FACTORY) + 1, rest_sharedState.indexOf(CENTER));
        String centerState = rest_sharedState.substring(rest_sharedState.indexOf(CENTER) + 1, rest_sharedState.indexOf(BAG));
        String bagState = rest_sharedState.substring(rest_sharedState.indexOf(BAG) + 1, rest_sharedState.indexOf(DISCARD));
        String discardState = rest_sharedState.substring(rest_sharedState.indexOf(DISCARD) + 1, rest_sharedState.length());

        setTurnState(turnState);
        setFactoryState(factoryState);
        setCenterState(centerState);
        setBagState(bagState);
        setDiscardState(discardState);
    }

    public String getSharedState(){
        StringBuilder SB = new StringBuilder();
        // Update all strings of factory, center, bag, discard
        this.centerState = this.center.getCenterState();
        this.bagState = this.bag.getBagState();
        this.discardState = this.discard.getDiscardState();
        this.factoryState = this.factories.getFactoriesState();

        // Form sharedState
        SB.append(this.turnState);
        SB.append(FACTORY);
        SB.append(this.factoryState);
        SB.append(CENTER);
        SB.append(this.centerState);
        SB.append(BAG);
        SB.append(this.bagState);
        SB.append(DISCARD);
        SB.append(this.discardState);
        return String.valueOf(SB);
    }

    private void setTurnState( String turnState ){
        this.turnState = turnState;
    }

    private void setFactoryState( String factoryState ){
        this.factoryState = factoryState;
        Factories factories = new Factories(factoryState, FACTORY_MAX_NUMBER);
        this.factories = factories;
    }

    private void setCenterState( String centerState ){
        this.centerState = centerState;
        Center center = new Center(centerState);
        this.center = center;
    }

    private void setBagState( String bagState ){
        this.bagState = bagState;
        Bag bag = new Bag(bagState);
        this.bag = bag;
    }

    private void setDiscardState( String discardState ){
        this.discardState = discardState;
        Discard discard = new Discard(discardState);
        this.discard = discard;
    }

    /**
     * 1. Find out if each factory is empty
     * 2. If factory is empty
     * 3a. Draw four tiles from bag
     * 3b. Remove tile drawn from bag
     * 4. Update sharedState
     */
    public void refillFactory() {
        char[] factory_tiles = new char[FACTORY_SIZE];
        int factory_num = this.factories.factory.size();
        for(int i=0; i < factory_num; i++){
            if(this.factories.factory.get(i).isFactoryStateEmpty()){
                for(int j=0; j < FACTORY_SIZE; j++){
                    if(!(this.bag.getTotalTilesNumber() > 0)){
                        this.refillDiscardtoBag();
                    }
                    factory_tiles[j] = this.bag.getRandomTile();
                    this.bag.removeTile(factory_tiles[j]);
                }
                this.factories.factory.get(i).refill_eachFactory(factory_tiles);
            }
        }
        this.factories.updatefactoriesState();
    }

    /**
     * If discard is not empty :
     * 1. Refill all tiles to bag
     * 2. Remove all tiles from discard
     */
    public void refillDiscardtoBag() {
        if(this.discard.getTotalTilesNumber() > 0){
            String discard_tiles = this.discard.getDiscardState();
            this.bag.refillTilesBag(discard_tiles);
            this.discard.removeAllTiles();
        }
    }

}
