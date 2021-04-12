package comp1110.ass2;

public class nPlayer implements Metadata {

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

    public nPlayer( String nplayerState , char nplayerNameState ){
        this.nplayerState = nplayerState;
        this.nplayerNameState = nplayerNameState;
        setnPlayerState(nplayerState);
    }

    public void setnPlayerState( String nplayerState ){
        scoreState = nplayerState.substring(0, nplayerState.indexOf(MOSAIC));
        mosaicState = nplayerState.substring(nplayerState.indexOf(MOSAIC) + 1, nplayerState.indexOf(STORAGE));
        storageState = nplayerState.substring(nplayerState.indexOf(STORAGE) + 1, nplayerState.indexOf(FLOOR));
        floorState = nplayerState.substring(nplayerState.indexOf(FLOOR) + 1, nplayerState.length());

        setScoreState(scoreState);
        setMosaicState(mosaicState);
        setStorageState(storageState);
        setFloorState(floorState);
    }

    public String getnplayerState(){
        StringBuilder SB = new StringBuilder();
        SB.append(this.score.getScoreState());
        SB.append(MOSAIC);
        SB.append(this.mosaic.getMosaicState());
        SB.append(STORAGE);
        SB.append(this.storage.getStorageState());
        SB.append(FLOOR);
        SB.append(this.floor.getFloorState());
        this.nplayerState = String.valueOf(SB);
        return this.nplayerState;
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

    private void setFloorState( String floorState ){
        this.floorState = floorState;
        this.floor = new Floor( floorState );
    }

    public boolean isEndofGame(){
        boolean isGameEnd = false;
        for(int i=0; i < MAX_MOSAIC_ROW; i++){
            if(this.mosaic.mosaic_rows.get(i).getTotalTilesNumber() == MAX_MOSAIC_ROW){
                isGameEnd = true;
            }
        }
        return isGameEnd;
    }

    @Override
    public String printBriefMetadata() {
        return null;
    }

    @Override
    public String printDetailMetadata() {
        return null;
    }
}
