package model;

/**
 *
 * @author g55019 / Cotton Ian
 * La classe tuile définit une tuile.
 * Celle-ci possède un nombre entre 1 et 20 (compris)
 * Méthode de cette classe : getValue (le getter) et un constructeur
 * Pas d'accesseur car la valeur d'une tuile ne change jamais.
 */

public class Tile {
    private Integer value;

    /*
    Constructeur, construit une tuile qui possèdent comme attribut une valeur entre 1 et 20 (compris)
    @param Integer, nombre compris entre 1 et 20
     */
    public Tile(Integer value) {
        if(value < 1 || value > 20){
            throw new IllegalArgumentException("Valeur de la tuile incorrecte");
        }
        this.value = value;
    }

    /*
    Getter
    @return Integer, valeur de la tuile entre 1 & 20 (compris)
     */
    public Integer getValue() {
        return value;
    }
}
