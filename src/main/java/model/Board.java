package model;

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

}
