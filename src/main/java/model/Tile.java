package model;

import java.util.Objects;

/**
 *
 * @author g55019 / Cotton Ian
 * The tile class defines a tile.
 * This has a number between 1 and 20 (included)
 * Method of this class: getValue (the getter) and a constructor
 */

public class Tile {
    private Integer value;
    private boolean faceUp;

    /**
     *Constructor, builds a tile with an attribute value between 1 and 20 (included)
     *@param value Integer, number between 1 and 20
     */
    public Tile(Integer value) {
        if(value < 1 || value > 20){
            throw new IllegalArgumentException("Valeur de la tuile incorrecte" +
                    value);
        }
        this.value = value;
        this.faceUp = false;
    }

    /**
     *Getter
     *@return Integer, tile value between 1 & 20 (inclusive)
     */
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * getter for faceUp attribute
     * @return boolean
     */

    public boolean isFaceUp() {
        return faceUp;
    }

    /**
     * Make a tile visible. Nothing happens if it is already visible.
     */
    public void flipFaceUp(){
        if(this.faceUp == false){
            this.faceUp = true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Objects.equals(getValue(), tile.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), isFaceUp());
    }
}
