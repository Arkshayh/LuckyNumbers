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

        if((this.state != State.NOT_STARTED) && (this.state != State.GAME_OVER)){
            throw new IllegalStateException("Etat invalide : " + this.state);
        }
        else if(playerCount < 2 || playerCount >4){
            throw new IllegalArgumentException("Nombre de joueur incorrect : " + playerCount);
        }
        this.boards = new Board[playerCount]; //Create the Board[]
        for (int i = 0; i < playerCount; i++) {
            this.boards[i] = new Board(); //Create the board for each player
        }
        this.playerCount = playerCount;
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
        Tile tuile = new Tile((int)(Math.random() * ((20 - 2) + 1)) + 2); //valeur random entre 2 et 20 (inclu)
        this.pickedTile = tuile;
        return tuile;
    }

    @Override
    public void putTile(Position pos) {
        Tile tuile = this.pickedTile;
        if(this.state != State.PLACE_TILE){
            throw new IllegalStateException("Etat incorrect dans : " + this.state);
        }
        if(this.boards[currentPlayerNumber].canBePut(tuile, pos) == false){
            throw new IllegalArgumentException("Position incorrect / impossible de poser la tuile à cette endroit" +
                    tuile.getValue() + ", " + pos);
        }
        else{
            Board plateau = this.boards[currentPlayerNumber];
            plateau.put(tuile, pos);
            if(this.boards[currentPlayerNumber].isFull() == true){
                this.state = State.GAME_OVER;
            }
            else{
                this.state = State.TURN_END;
            }
        }
    }

    @Override
    public void nextPlayer() {
        if(this.state != State.TURN_END){
            throw new IllegalStateException("Etat incorrect : " + this.state);
        }
        if(currentPlayerNumber == playerCount - 1){
            currentPlayerNumber = 0;
        }
        else{
            currentPlayerNumber = currentPlayerNumber + 1;
            this.state = State.PICK_TILE;
        }
    }

    @Override
    public int getPlayerCount() {
        if(this.state != State.NOT_STARTED){
            throw new IllegalStateException("Etat incorrect : " + this.state);
        }
        return this.playerCount;
    }

    @Override
    public State getState() {
        return this.state;
    }

    @Override
    public int getCurrentPlayerNumber() {
        if((this.state != State.NOT_STARTED) && (this.state != State.GAME_OVER )){
            throw new IllegalStateException("Etat incorrect : " + this.state);
        }
        return this.currentPlayerNumber;
    }

    @Override
    public Tile getPickedTile() {
        if(this.state != State.PLACE_TILE){
            throw new IllegalStateException("Etat incorrect  : " + this.state);
        }
        return this.pickedTile;
    }

    @Override
    public boolean isInside(Position pos) {
        if(pos.getRow() > getBoardSize() - 1 || pos.getColumn() > getBoardSize() -1 || pos.getRow() < 0
                || pos.getColumn() < 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean canTileBePut(Position pos) {
        if(this.state != State.PLACE_TILE){
            throw new IllegalStateException("Etat incorrect  : " + this.state);
        }
        if(isInside(pos) == false){
            throw new IllegalArgumentException("Position en dehors du plateau : " + pos);
        }
        else{
            if(this.boards[getCurrentPlayerNumber()].canBePut(getPickedTile(),pos) == true){
                return true;
            }
            else{
                return false;
            }
        }
    }

    @Override
    public Tile getTile(int playerNumber, Position pos) {
        if(this.state == State.NOT_STARTED){
            throw new IllegalStateException("Etat incorrect  : " + this.state);
        }
        if(isInside(pos) == false || (playerNumber > getPlayerCount() - 1)){
            throw new IllegalArgumentException("position incorrect / numéro de joueur incorrect : " + pos +", "+
                    playerNumber);
        }
        if(this.boards[playerNumber].getTile(pos) == null){
            return null;
        }
        else{
            Tile tuile = this.boards[playerNumber].getTile(pos);
            return tuile;
        }
    }

    @Override
    public int getWinner() {
        if(this.state != State.GAME_OVER){
            throw new IllegalStateException("Etat incorrect : " + this.state);
        }
        return this.currentPlayerNumber;
    }
}
