package model;

/**
 * @author g55019 / Cotton Ian
 * La classe position définit un position via 2 attributs : une ligne (row) Integer
 * et une colonne (column) Integer
 */
public class Position {
    private Integer row;
    private Integer column;

    /*Constructeur d'une position Les valeurs de la ligne et de la colonnes de celle-ci sont nécéssaires.
    @param Integer, Integer valeur de la ligne et de la colonne
     */
    public Position(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    /*
    Getter row
    @return Integer Renvoie la valeur de la ligne d'une position
     */
    public Integer getRow() {
        return row;
    }

    /*
    Getter column
    @return Integer Renvoie la valeur de la colonne d'une position
     */
    public Integer getColumn() {
        return column;
    }
}
