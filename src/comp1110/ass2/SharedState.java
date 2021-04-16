package comp1110.ass2;

public class SharedState extends States {

    // Shared fields Strings
    String turnState = EMPTY_STATE;
    String factoriesState = EMPTY_STATE;
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

    public SharedState(){
        super( EMPTY_STATE, DEFAULT_MAX_PLAYER);
        super.sharedState = EMPTY_STATE;
        super.MAX_PLAYER_NUMBER = DEFAULT_MAX_PLAYER;
        setSharedState( sharedState, MAX_PLAYER_NUMBER );
    }

    public SharedState( String sharedState , int max_player_number ){
        super(sharedState, max_player_number);
        super.sharedState = sharedState;
        super.MAX_PLAYER_NUMBER = max_player_number;
        setSharedState(sharedState, max_player_number);
    }

    public void setSharedState( String sharedState , int max_player_number ){
        super.MAX_PLAYER_NUMBER = max_player_number;
        String turnState = sharedState.substring(0, sharedState.indexOf(FACTORY));
        String rest_sharedState = sharedState.substring(sharedState.indexOf(turnState) + 1, sharedState.length());
        String factoryState = rest_sharedState.substring(rest_sharedState.indexOf(FACTORY) + 1, rest_sharedState.indexOf(CENTER));
        String centerState = rest_sharedState.substring(rest_sharedState.indexOf(CENTER) + 1, rest_sharedState.indexOf(BAG));
        String bagState = rest_sharedState.substring(rest_sharedState.indexOf(BAG) + 1, rest_sharedState.indexOf(DISCARD));
        String discardState = rest_sharedState.substring(rest_sharedState.indexOf(DISCARD) + 1, rest_sharedState.length());

        setTurnState(turnState);
        setFactoriesState(factoryState);
        setCenterState(centerState);
        setBagState(bagState);
        setDiscardState(discardState);
        updateSharedState();
    }

    public String getUpdatedSharedState(){
        updateSharedState();
        return super.sharedState;
    }

    public void updateSharedState(){
        StringBuilder SB = new StringBuilder();
        // Update all strings of factory, center, bag, discard
        this.centerState = this.center.getStateString();
        this.bagState = this.bag.getStateString();
        this.discardState = this.discard.getStateString();
        this.factoriesState = this.factories.getStateString();

        // Form sharedState
        SB.append(this.turnState);
        SB.append(FACTORY);
        SB.append(this.factoriesState);
        SB.append(CENTER);
        SB.append(this.centerState);
        SB.append(BAG);
        SB.append(this.bagState);
        SB.append(DISCARD);
        SB.append(this.discardState);

        super.sharedState = String.valueOf(SB);
    }

    public void changeTurn(){
        char now_turn = this.turnState.charAt(0);
        int player_idx = 0;
        for(int i=0; i < MAX_PLAYER_NUMBER; i++){
            if(now_turn == ALL_PLAYERS[i]){
                player_idx = i;
            }
        }
        setTurnState(String.valueOf(ALL_PLAYERS[(player_idx+1) % MAX_PLAYER_NUMBER]));
    }

    private void setTurnState( String turnState ){
        this.turnState = turnState;
    }

    private void setFactoriesState( String factoryState ){
        this.factoriesState = factoryState;
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
            if(this.factories.factory.get(i).isStateEmpty()){
                for(int j=0; j < FACTORY_SIZE; j++){
                    if(!(this.bag.getTotalTilesNumber() > 0)){
                        this.refillDiscardtoBag();
                    }
                    factory_tiles[j] = this.bag.getRandomTile();
                    this.bag.removeTile(factory_tiles[j]);
                }
                //this.factories.factory.get(i).refill_eachFactory(factory_tiles);
                this.factories.factory.get(i).refillTiles(factory_tiles);
            }
        }
        this.factories.updateState();
    }

    /**
     * If discard is not empty :
     * 1. Refill all tiles to bag
     * 2. Remove all tiles from discard
     */
    public void refillDiscardtoBag() {
        if(this.discard.getTotalTilesNumber() > 0){
            String discard_tiles = this.discard.getStateString();
            this.bag.refillTiles(discard_tiles);
            //this.bag.refillTilesBag(discard_tiles);
            this.discard.removeAllTiles();
        }
    }

}
