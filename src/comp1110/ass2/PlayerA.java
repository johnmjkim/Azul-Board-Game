package comp1110.ass2;

public class PlayerA implements Player{
    int turn;
    int score;
    String playerState;
    String nameState;
    String scoreState;
    String mosaicState;
    String storageState;
    String floorState;

    @Override
    public void Player(String playerState) {
        this.playerState = playerState;
    }

    @Override
    public void Name(String nameState) {
        this.nameState = nameState;
    }

    @Override
    public void Score(String scoreState) {
        this.scoreState = scoreState;
        this.score = Integer.valueOf(scoreState);
    }

    @Override
    public void Mosaic(String mosaicState) {
        this.mosaicState = mosaicState;
    }

    @Override
    public void Storage(String storageState) {
        this.storageState = storageState;
    }

    @Override
    public void Floor(String floorState) {
        this.floorState = floorState;
    }

    @Override
    public void Move(String applyMove) {

    }

    @Override
    public void Turn(int turn) {
        this.turn = turn;
    }

    @Override
    public int getTurn() {
        return this.turn;
    }

    @Override
    public String getName() {
        return this.nameState;
    }

    @Override
    public int getScore() {
        return this.score;
    }
}
