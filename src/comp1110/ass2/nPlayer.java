package comp1110.ass2;

public class nPlayer implements State {

    // Player fields strings
    String nplayerState = EMPTY_STATE;
    char nplayerNameState;
    String scoreState = EMPTY_STATE;
    String mosaicState = EMPTY_STATE;
    String storageState = EMPTY_STATE;
    String floorState = EMPTY_STATE;

    // Class fields
    public Score score;
    public Mosaic mosaic;
    public Storage storage;
    public Floor floor;

    /**
     * @author Min Jae, Kim
     * Constructor method for nPLayer class
     * This class represents playerstate of each player : PLayer A, B, C, D
     * @param nplayerState
     * @param nplayerNameState
     */
    public nPlayer( String nplayerState , char nplayerNameState , int max_player_number){
        this.nplayerState = nplayerState;
        this.nplayerNameState = nplayerNameState;
        setnPlayerState(nplayerState, max_player_number);
    }

    public void setnPlayerState( String nplayerState , int max_player_number){
        String scoreState = nplayerState.substring(0, nplayerState.indexOf(MOSAIC));
        String mosaicState = nplayerState.substring(nplayerState.indexOf(MOSAIC) + 1, nplayerState.indexOf(STORAGE));
        String storageState = nplayerState.substring(nplayerState.indexOf(STORAGE) + 1, nplayerState.indexOf(FLOOR));
        String floorState = nplayerState.substring(nplayerState.indexOf(FLOOR) + 1, nplayerState.length());

        setScoreState(scoreState);
        setMosaicState(mosaicState);
        setStorageState(storageState);
        setFloorState(floorState, max_player_number);
    }

    private void setScoreState( String scoreState ) {
        this.scoreState = scoreState;
        this.score = new Score( scoreState );
    }

    private void setMosaicState( String mosaicState ) {
        this.mosaicState = mosaicState;
        this.mosaic = new Mosaic( mosaicState );
    }

    private void setStorageState( String storageState ){
        this.storageState = storageState;
        this.storage = new Storage( storageState );
    }

    private void setFloorState( String floorState , int max_player_number ){
        this.floorState = floorState;
        this.floor = new Floor( floorState , max_player_number);
    }

    public boolean isEnder(){
        boolean isEnder = false;
        for(int i=0; i < MAX_MOSAIC_ROW; i++){
            if(this.mosaic.mosaic_rows.get(i).isTilesFull()){
                isEnder = true;
            }
        }
        return isEnder;
    }

    public char getnPlayerChar(){
        return this.nplayerNameState;
    }

    boolean existsStorageRowTilesFull() {
        return this.storage.existsStorageRowTilesFull();
    }

    @Override
    public boolean isStateEmpty() {
        return this.nplayerState.isEmpty();
    }

    @Override
    public String getStateString() {
        updateState();
        return this.nplayerState;
    }

    @Override
    public void updateState() {
        StringBuilder SB = new StringBuilder();

        // Update all strings of score, mosaic, storage, floor
        this.scoreState = this.score.getStateString();
        this.mosaicState = this.mosaic.getStateString();
        this.storageState = this.storage.getStateString();
        this.floorState = this.floor.getStateString();

        SB.append(this.score.getStateString());
        SB.append(MOSAIC);
        SB.append(this.mosaic.getStateString());
        SB.append(STORAGE);
        SB.append(this.storage.getStateString());
        SB.append(FLOOR);
        SB.append(this.floor.getStateString());
        this.nplayerState = String.valueOf(SB);
    }
}
