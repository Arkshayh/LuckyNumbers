package view;

/**
 *
 * @author Cotton Ian | g55019
 * contains the method from View interface
 * The method has as job to diplay information/question for players
 */


import model.Position;
import model.Model;
import model.Tile;

import java.util.Scanner;

public class MyView implements View{
    private Model game;

    public MyView(Model game) {
        this.game = game;
    }

    @Override
    public void displayWelcome() {
        System.out.println("*Bienvenue sur le jeu luckynumbers* par Cotton Ian (version 0.1)");
    }

    /**
     * Display the number of the current player and the line with the number of each column
     * @param taille int | taille = the size of the baord
     */
    private void displayHeader(int taille){
        System.out.println("Joueur "+ (game.getCurrentPlayerNumber()+1));
        for (int i = 0; i <taille; i++) {
            if(i == 0){
                System.out.print("   ");
            }
            else{
                System.out.print("  ");
            }
            System.out.print(i+1);
        }
        System.out.println();

    }

    /**
     *Display the each line of the board (line per line) and each tile putted
     * @param taille int
     */
    private void displayBody(int taille){

        int player = game.getCurrentPlayerNumber();
        Tile valeur;
        for (int j = 0; j < taille; j++) {
            System.out.print((j+1) + "|");

            for (int k = 0; k < taille; k++) { //column
                valeur = game.getTile(player, new Position(j,k));
                if(k == 0){
                    if(valeur == null){
                        System.out.print(" .");
                    }
                    else if(valeur.getValue() < 10){
                        System.out.print(" " + valeur);
                    }
                    else{
                        System.out.print(valeur);
                    }
                }
                else if(k == game.getBoardSize() - 1){
                    if(valeur == null){
                        System.out.println("  .");
                    }
                    else if(valeur.getValue() < 10){
                        System.out.println("  "+valeur);
                    }
                    else{
                        System.out.println(" " + valeur);
                    }
                }
                else{
                    if(valeur == null){
                        System.out.print("  .");
                    }
                    else if(valeur.getValue() < 10){
                        System.out.print("  "+valeur);
                    }
                    else{
                        System.out.print(" " + valeur);
                    }
                }
            }
        }
    }



    @Override
    public void displayGame() {
        int taille = 4;
        displayHeader(taille);
        System.out.println("-------------");

        displayBody(taille);
        System.out.println("-------------");

        System.out.println("Tuile choisie : " + game.getPickedTile());
    }

    @Override
    public void displayWinner() {
        if(game.getWinners().size() == 1){
            System.out.println("Le gagnant est le joueur : " + ((game.getWinners().get(0)) + 1));
        }
        else{
            System.out.println("Les gagnants sont : ");
            for (int i = 0; i < game.getWinners().size(); i++) {
                System.out.println("Le joueur : " + ((game.getWinners().get(i)) + 1));
            }
        }
    }

    @Override
    public int askPlayerCount() {
        System.out.println("Entrer le nombre de joueur (2 minimum, 4 maximum)");
        Scanner clavier = new Scanner(System.in);
        int nbPlayer = clavier.nextInt();
        return nbPlayer;
    }

    @Override
    public Position askPosition() {
        System.out.println("Entrer une position.");
        Scanner clavier = new Scanner(System.in);
        System.out.println("Commencer par indique le numéro de la ligne : ");
        int ligne = clavier.nextInt();
        ligne = ligne - 1;
        System.out.println("Indiquer le numéro de la colonne : ");
        int colonne = clavier.nextInt();
        colonne = colonne - 1;
        Position pos = new Position(ligne, colonne);
        boolean test = false;
        while(test == false){
            try {
                if(game.canTileBePut(pos) == true){
                    test = true;
                }
                else{
                    while (game.canTileBePut(pos) == false) {
                        System.out.println("position incorrect !");
                        System.out.println("Indiquer une ligne : ");
                        ligne = clavier.nextInt();
                        ligne = ligne - 1;
                        System.out.println("Indiquer une colonne : ");
                        colonne = clavier.nextInt();
                        colonne = colonne - 1;
                        pos = new Position(ligne, colonne);
                    }
                }
            }
            catch(Exception e){
                System.out.println("position incorrect !");
                System.out.println("Indiquer une ligne : ");
                ligne = clavier.nextInt();
                ligne = ligne - 1;
                System.out.println("Indiquer une colonne : ");
                colonne = clavier.nextInt();
                colonne = colonne - 1;
                pos = new Position(ligne, colonne);
            }
        }
        return pos;
    }

    @Override
    public void displayError(String message) {
        System.out.println("Erreur : " + message);
    }

    @Override
    public void displayFaceDown(int nb){
        System.out.println("Il reste " + nb + " tuiles cachées.");
    }

    @Override
    public void displayFaceUp(){
        System.out.println("Les tuiles non cachées : " + game.getAllfaceUpTiles());
    }

    @Override
    public int askFaceUpOrDown(){
        Scanner clavier = new Scanner(System.in);
        System.out.println("Entrer '1' pour choisir une tuile cachée aléatoire et '2' pour une tuile visible : ");
        int choix = clavier.nextInt();
        while(choix != 1 && choix != 2){
            System.out.println("Erreur !");
            System.out.println("Entrer '1' pour choisir une tuile cachée aléatoire et '2' pour une tuile visible : ");
            choix = clavier.nextInt();
        }
        return choix;
    }

    @Override
    public int chooseTile(int choix){
        Scanner clavier = new Scanner(System.in);
        Tile tuile;
        int rep;

        if(choix == 1){
            tuile = game.pickFaceDownTile();
            System.out.println("Votre tuile : " + tuile);
            System.out.println("Taper 1 si vous voulez placer votre tuile, taper 2 si vous voulez la drop :");
            rep = clavier.nextInt();
            while(rep != 1 && rep != 2) {
                System.out.println("Erreur");
                System.out.println("Taper 1 pour placer votre tuile , taper 2 pour la drop");
                rep = clavier.nextInt();
            }
        }
        else{
            displayFaceUp();
            System.out.println("Entrer la valeur de la tuile que vous voulez choisir : ");
            int val = clavier.nextInt();
            tuile = new Tile(val);
            while(game.getAllfaceUpTiles().contains(tuile) == false){
                System.out.println("Erreur !");
                System.out.println("Entrer la valeur de la tuile que vous voulez choisir : ");
                val = clavier.nextInt();
                tuile = new Tile(val);
            }
            game.pickFaceUpTile(tuile);
            rep = 1;
        }
        return rep;
    }


}
