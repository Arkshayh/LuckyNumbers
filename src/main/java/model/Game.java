package model;

public class Game implements Model{
    private State state;
    private Integer playerCount;
    private Integer currentPlayerNumber;
    private Board[] boards;
    private Tile pickedTile;

    public Game() {
        this.state = State.NOT_STARTED;
    }

    @Override
    public void start(int playerCount) {
        if(playerCount < 2 || playerCount >4){
            throw new IllegalArgumentException("Nombre de joueur incorrect : " + playerCount);
        }
        else if((this.state != State.NOT_STARTED) && (this.state != State.GAME_OVER)){
            throw new IllegalStateException("Etat invalide : " + this.state);
        }
        this.boards = new Board[playerCount]; //Create the Board[]
        for (int i = 0; i < playerCount; i++) {
            this.boards[i] = new Board(); //Create the board for each player
        }
        this.currentPlayerNumber = 0;
        this.state = State.PICK_TILE;
    }

    @Override
    public int getBoardSize() {
        return this.boards[currentPlayerNumber].getSize();
    }

    @Override
    public Tile pickTile() {
        if(this.state != State.PICK_TILE){
            throw new IllegalStateException("Etat incorrect : " + this.state);
        }
        this.state = State.PLACE_TILE;
        return new Tile((int)(Math.random() * ((20 - 2) + 1)) + 2); //valeur random entre 2 et 20 (inclu)
    }

    @Override
    public void putTile(Position pos) {

    }

    @Override
    public void nextPlayer() {

    }

    @Override
    public int getPlayerCount() {
        return 0;
    }

    @Override
    public State getState() {
        return null;
    }

    @Override
    public int getCurrentPlayerNumber() {
        return 0;
    }

    @Override
    public Tile getPickedTile() {
        return null;
    }

    @Override
    public boolean isInside(Position pos) {
        return false;
    }

    @Override
    public boolean canTileBePut(Position pos) {
        return false;
    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        return null;
    }

    @Override
    public int getWinner() {
        return 0;
    }
}
