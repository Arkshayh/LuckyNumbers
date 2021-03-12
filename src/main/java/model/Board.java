package model;

/**
 * @author g55019 / Cotton Ian
 * La classe board définit un plateau de jeu sur lequel le joueur va poser ses Tile
 * Ce plateau de Tile aura un taille 4x4 et toute case vide sera indiquer par une valeur null
 */

public class Board {
    private Tile[][] plateau;

    //Constructeur, initialise l'attribut plateau -> Tableau à 2 dimension de taille 4x4
    //L'ajout d'un attribut taille facilement implémentable
    public Board() {
        Tile[][] plateaudeTuile = new Tile[4][4];
        this.plateau = plateaudeTuile;
    }

    /*
    Permet de connaitre la taille du plateau de tuile et renvoie celle-ci.
    @return Integer la taille du tableau
     */
    public Integer getSize(){
        Integer taille = this.plateau.length;
        return taille;
    }

    /*
    Permet de savoir si une position donnée se trouve dans le plateau renvoie un boolean.
    @return boolean, true si dans plateau sinon false
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
