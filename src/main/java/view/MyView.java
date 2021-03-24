package view;

/**
 *
 * @author Cotton Ian | g55019
 * contains the method from View interface
 * The method has as job to diplay information/question for players
 */

import model.Game;
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

    private void displayHeader(int taille){
        //Header
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

    private void displayBody(int taille){
        //Each line/column of the board. Display line per line
        int player = game.getCurrentPlayerNumber();
        Tile valeur;
        for (int j = 0; j < taille; j++) { //For each row display "|" at the beginning
            System.out.print((j+1) + "|");

            for (int k = 0; k < taille; k++) { //column
                valeur = game.getTile(player, new Position(j,k));

                /*
                If the value < 10 the classic display is two space and the value (__value)
                If the value >= 10 one space  (_value)
                for no value 2 space and a . (__.)

                The fist value on the  line has one less space

                The last value on the has to have a println instead of a print
                 */

                //To display the first tile of the line | There is one less space when you're the fist tile of a line
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

                //To display the last tile of a line (it must be a println)
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

                //To diplay the tile in the middle of the line classic diplay 2 space and value
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

        //transition bar
        System.out.println("-------------");

        displayBody(taille);

        //Transition
        System.out.println("-------------");

        //Show the value of the pickedtile
        System.out.println("Tuile choisie : " + game.getPickedTile());
    }

    @Override
    public void displayWinner() {
        System.out.println("Le gagnant est le joueur : " + (game.getWinner() + 1));
    }

    @Override
    public Integer askPlayerCount() {
        System.out.println("Entrer le nombre de joueur (2 minimum, 4 maximum)");
        Scanner clavier = new Scanner(System.in);
        Integer nbPlayer = clavier.nextInt();
        return nbPlayer;
    }

    @Override
    public Position askPosition() {
        System.out.println("Entrer une position.");
        Scanner clavier = new Scanner(System.in);
        System.out.println("Commencer par indique le numéro de la ligne : ");
        Integer ligne = clavier.nextInt();
        ligne = ligne - 1;
        System.out.println("Indiquer le numéro de la colonne : ");
        Integer colonne = clavier.nextInt();
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
}
