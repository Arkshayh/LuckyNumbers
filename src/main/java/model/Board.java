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
    temporary method which displays the table for the tests
     */
    public void afficherBoard() {
        for(int ligne = 0; ligne < this.plateau.length;ligne++){
            for (int colonne = 0; colonne < this.plateau[0].length; colonne++){
                if (this.plateau[ligne][colonne] == null){
                    System.out.print("null ");
                }
                else{
                    System.out.print(this.plateau[ligne][colonne].getValue() + " ");
                }
            }
            System.out.println();
        }
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
    Return a boolean if the tile at the given position is Bigger or equals to the value of the top and left tile
    the methode && bot and right < tile given return true else false

    @param Tile,Position | a Tile and a Position if the position is OOB -> exception is thrown
    @return boolean |true if the tile can be put at the given position if not -> false
     */
    public boolean canBePut(Tile tuile, Position pos){
        Integer ligne = pos.getRow();
        Integer colonne = pos.getColumn();

        //Case first row
        if(ligne == 0){
            //case top left, check if  right is bigger and botton too
            if(colonne == 0){
                if( (plateau[ligne + 1][colonne] == null || plateau[ligne + 1][colonne].getValue() > tuile.getValue())
                        &&  (plateau[ligne][colonne+1] == null ||  plateau[ligne][colonne+1].getValue() > tuile.getValue())){
                    return true;
                }
                else{
                    return false;
                }
            }
            //case top right, check if left is smaller and bottom bigger
            else if(colonne == plateau[0].length -1){
                if( (plateau[ligne][colonne-1] == null || plateau[ligne][colonne-1].getValue() < tuile.getValue())
                        && ( plateau[ligne+1][colonne] == null || plateau[ligne+1][colonne].getValue() > tuile.getValue())){
                    return true;
                }
                else{
                    return false;
                }
            }
            //Case first row but not top right/left, check if left is smaller and botton/right bigger
            else{
                if( (plateau[ligne][colonne - 1] == null || plateau[ligne][colonne - 1].getValue() < tuile.getValue())
                && ( plateau[ligne+1][colonne] == null || plateau[ligne+1][colonne].getValue() > tuile.getValue())
                && (plateau[ligne][colonne+1] == null || plateau[ligne][colonne + 1].getValue() > tuile.getValue())){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        //Case last row
        else if(ligne == plateau.length - 1){
            //bottom left
            if(colonne == 0){
                if( (plateau[ligne-1][colonne] == null || plateau[ligne-1][colonne].getValue() < tuile.getValue())
                        && (plateau[ligne][colonne+1] == null || plateau[ligne][colonne+1].getValue() > tuile.getValue()) ){
                    return true;
                }
                else{
                    return false;
                }
            }
            //bottom right
            else if(colonne == plateau[0].length - 1){
                if( (plateau[ligne][colonne - 1] == null || plateau[ligne][colonne - 1].getValue() < tuile.getValue())
                        && (plateau[ligne-1][colonne] == null || plateau[ligne-1][colonne].getValue() < tuile.getValue())){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                if ( (plateau[ligne][colonne - 1] == null || plateau[ligne][colonne - 1].getValue() < tuile.getValue())
                        && (plateau[ligne-1][colonne] == null || plateau[ligne-1][colonne].getValue() < tuile.getValue())
                        && (plateau[ligne][colonne+1] == null || plateau[ligne][colonne+1].getValue() > tuile.getValue())){
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        //case first column
        else if(colonne == 0){
            if( (plateau[ligne-1][colonne] == null || plateau[ligne-1][colonne].getValue() < tuile.getValue())
                    && (plateau[ligne][colonne+1] == null || plateau[ligne][colonne+1].getValue() > tuile.getValue())
                    && (plateau[ligne+1][colonne] == null || plateau[ligne+1][colonne].getValue() > tuile.getValue())){
                return true;
            }
            else {
                return false;
            }
        }
        //Case last column
        else if(colonne == plateau[0].length - 1){
            if( (plateau[ligne-1][colonne] == null || plateau[ligne-1][colonne].getValue() < tuile.getValue())
                    && (plateau[ligne+1][colonne] == null || plateau[ligne+1][colonne].getValue() > tuile.getValue())
                    && (plateau[ligne][colonne-1] == null || plateau[ligne][colonne-1].getValue() < tuile.getValue())){
                return true;
            }
            else {
                return false;
            }
        }
        //autre cas
        else{
            if((plateau[ligne-1][colonne] == null || plateau[ligne-1][colonne].getValue() < tuile.getValue())
                    && (plateau[ligne + 1][colonne] == null || plateau[ligne+1][colonne].getValue() > tuile.getValue())
                    && (plateau[ligne][colonne - 1] == null || plateau[ligne][colonne-1].getValue() < tuile.getValue())
                    && (plateau[ligne][colonne+1] == null || plateau[ligne][colonne+1].getValue() > tuile.getValue())){
                return true;
            }
            else{
                return false;
            }
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
