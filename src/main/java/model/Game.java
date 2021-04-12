package model;

import java.util.List;

/**
 * @author g55019 / Cotton Ian
 * A class game has a State state
 * The amount of player Int
 * The current number player
 * A tab of board (one for each player)
 * the value of the tile picked
 *
 * This class contains the method from Model.java
 * This makes it possible to advance the game and its state
 */

public class Game implements Model{
    private State state;
    private int playerCount;
    private int currentPlayerNumber;
    private Board[] boards;
    private Tile pickedTile;
    private Deck deck;

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
        this.deck = new Deck(playerCount);
        this.state = State.PICK_TILE;
    }

    @Override
    public int getBoardSize() {
        return this.boards[currentPlayerNumber].getSize();
    }

    @Override
    public Tile pickFaceDownTile() {
        if(this.state != State.PICK_TILE){
            throw new IllegalStateException("Etat incorrect : " + this.state);
        }

        //random value between 2 and 20 (included)
        Tile tuile = deck.pickFaceDown();
        this.state = State.PLACE_OR_DROP_TILE;
        this.pickedTile = tuile;

        return tuile;
    }

    @Override
    public void pickFaceUpTile(Tile tuile) {
        if(this.state != State.PICK_TILE){
            throw new IllegalStateException("Etat incorrect : " + this.state);
        }
        deck.pickFaceUp(tuile);
        this.pickedTile = tuile;
        this.state = State.PLACE_TILE;
    }

    @Override
    public void dropTile(){
        if(this.state != State.PLACE_OR_DROP_TILE){
            throw new IllegalStateException("Etat incorrect : " + this.state);
        }
        deck.putBack(this.pickedTile);
        this.state = State.TURN_END;
    }

    @Override
    public int faceDownTileCount(){
        return deck.faceDownCount();
    }

    @Override
    public int faceUpTileCount(){
        return deck.faceUpCount();
    }

    @Override
    public List<Tile> getAllfaceUpTiles(){
        return deck.getAllFaceUp();
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
            this.state = State.PICK_TILE;
        }
        else{
            currentPlayerNumber = currentPlayerNumber + 1;
            this.state = State.PICK_TILE;
        }
    }

    @Override
    public int getPlayerCount() {
        if(this.state == State.NOT_STARTED){
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
        if(this.state == State.NOT_STARTED){
            throw new IllegalStateException("Etat incorrect : " + this.state);
        }
        else if(this.state == State.GAME_OVER){
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
            return this.boards[getCurrentPlayerNumber()].canBePut(getPickedTile(),pos);
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
