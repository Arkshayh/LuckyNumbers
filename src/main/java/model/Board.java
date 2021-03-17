package model;

import java.util.Arrays;

/**
 *@author g55019 / Cotton Ian
 *The board class defines a game board on which the player will place his Tiles
 *This Tile board will have a 4x4 size and any empty square will be indicated by a null value
 */

public class Board {
    private Tile[][] plateau;

    // Constructor, initialize the plate attribute -> 2-dimensional array of size 4x4
    // Adding an easily implementable size attribute
    public Board() {
        Tile[][] plateaudeTuile = new Tile[4][4];
        this.plateau = plateaudeTuile;
    }

    /*
    Allows you to know the size of the tile board and returns it.
    @return Integer the size of the array
    */
    public Integer getSize(){
        Integer taille = this.plateau.length;
        return taille;
    }

    /*
     Used to find out if a given position is in the board returns a boolean.
     @return boolean, true if in tray otherwise false
     */

    public boolean isInside(Position pos){
        Integer ligne = pos.getRow();
        Integer colonne = pos.getColumn();
        if( (ligne < getSize() && ligne >= 0) && (colonne < getSize() && colonne >= 0)){
            return true;
        }
        return false;
    }

    /*
    Returns the board tile at a given position.
    @param Position,  The position of the tile we want
    @return Tile, The tile at the given position
     */
    public Tile getTile(Position pos){

        Integer ligne  = pos.getRow();
        Integer colonne = pos.getColumn();
        return this.plateau[ligne][colonne];
    }

    /*
    Go through a row of the board and return the value of the first element on the left, if there is none: returns null
    @param Position | position of the tile that we want to put on the board
    @return Integer, null | value of the first tile met
     */
    private Integer parcourirLigneVersGauche(Position pos){
        if(pos.getColumn() == 0){
            return null;
        }
        Integer VG = 0;
        for (int i = 1; i < this.plateau.length -1; i++) {
            if(pos.getColumn()  - i >= 0){
                if(plateau[pos.getRow()][pos.getColumn()  - i] == null){

                }
                else {
                    VG = plateau[pos.getRow()][pos.getColumn() - i].getValue();
                    return VG;
                }
            }
        }
        return null;
    }
    /*
    Go through a row of the board and return the value of the first element on the right, if there is none: returns null
    @param Position | position of the tile that we want to put on the board
    @return Integer, null | value of the first tile met
     */
    private Integer parcourirLigneVersDroite(Position pos){
        if(pos.getColumn() == this.plateau[0].length-1){
            return null;
        }
        Integer VD = 0;
        for (int i = 1; i < this.plateau.length -1; i++) {
            if(pos.getColumn() + i <= this.plateau[0].length -1){
                if(plateau[pos.getRow()][pos.getColumn() + i] == null){

                }
                else{
                    VD = plateau[pos.getRow()][pos.getColumn() + i].getValue();
                    return VD;
                }
            }
        }
        return null;
    }
    /*
    Go through a column of the board and return the value of the first element on the top, if there is none: returns null
    @param Position | position of the tile that we want to put on the board
    @return Integer, null | value of the first tile met
     */
    private Integer parcourirColonneVersHaut(Position pos){
        if(pos.getRow() == 0){
            return null;
        }
        Integer VH = 0;
        for (int i = 1; i < this.plateau[0].length - 1; i++) {
            if(pos.getRow() - i >= 0){
                if(plateau[pos.getRow() - i][pos.getColumn()] == null){

                }
                else {
                    VH = plateau[pos.getRow() - i][pos.getColumn()].getValue();
                    return VH;
                }
            }
        }
        return null;
    }
    /*
    Go through a column of the board and return the value of the first element on the bottom,
    if there is none: returns null
    @param Position | position of the tile that we want to put on the board
    @return Integer, null | value of the first tile met
     */
    private Integer parcourirColonneVersBas(Position pos){
        if(pos.getRow() == this.plateau.length - 1){
            return null;
        }
        Integer VB =0 ;
        for (int i = 1; i < this.plateau[0].length - 1; i++) {
            if(pos.getRow() + i <= this.plateau.length - 1){
                if(plateau[pos.getRow() + i][pos.getColumn()] == null){

                }
                else{
                    VB = plateau[pos.getRow() + i][pos.getColumn()].getValue();
                    return VB;
                }
            }
        }
        return null;
    }

    /*
    Return a boolean if the tile at the given position is Bigger or equals to the value of the top and left tile
    the methode && bot and right < tile given return true else false

    @param Tile,Position | a Tile and a Position if the position is OOB -> exception is thrown
    @return boolean |true if the tile can be put at the given position if not -> false
     */
    public boolean canBePut(Tile tuile, Position pos){
        Integer ligne = pos.getRow();
        Integer colonne = pos.getColumn();
        Integer valTuile = tuile.getValue(); //Ne comprend pas l'erreur, tu ne peux pas donner une tuile qui est nul.
        //Case first row
        if((parcourirColonneVersHaut(pos) == null || parcourirColonneVersHaut(pos) < valTuile) &&
                (parcourirColonneVersBas(pos) == null || parcourirColonneVersBas(pos) > valTuile) &&
                (parcourirLigneVersGauche(pos) == null || parcourirLigneVersGauche(pos) < valTuile) &&
                (parcourirLigneVersDroite(pos) == null || parcourirLigneVersDroite(pos) > valTuile)){
            return true;
        }
        else{
            return false;
        }
    }

    /*
    Add a tile in the array at a given position, it is assumed that the position is on the board
    @param Tile, Position | the position is on the board
     */


    public void put(Tile tuile, Position pos){
        this.plateau[pos.getRow()][pos.getColumn()] = tuile;
    }

    /*
    Say if the board if full or not, if it's the case return true else false
    @return boolean | if there is no null return true else false
     */
    public boolean isFull(){
        for(int ligne = 0; ligne < this.plateau.length;ligne++){
            for (int colonne = 0; colonne < this.plateau[0].length; colonne++){
                if (this.plateau[ligne][colonne] == null){
                    return false;
                }
            }
        }
        return true;
    }

}
