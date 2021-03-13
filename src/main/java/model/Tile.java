package model;

/**
 *
 * @author g55019 / Cotton Ian
 * The tile class defines a tile.
 * This has a number between 1 and 20 (included)
 * Method of this class: getValue (the getter) and a constructor
 */

public class Tile {
    private Integer value;

    /*
    Constructor, builds a tile with an attribute value between 1 and 20 (included)
    @param Integer, number between 1 and 20
     */
    public Tile(Integer value) {
        if(value < 1 || value > 20){
            throw new IllegalArgumentException("Valeur de la tuile incorrecte");
        }
        this.value = value;
    }

    /*
    Getter
    @return Integer, tile value between 1 & 20 (inclusive)
    */
    public Integer getValue() {
        return value;
    }
}
