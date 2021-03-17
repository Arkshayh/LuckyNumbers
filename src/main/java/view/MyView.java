package view;

import model.Game;
import model.Position;
import model.Model;
import model.Tile;

import java.awt.font.GlyphMetrics;
import java.sql.SQLOutput;
import java.util.Scanner;

public class MyView implements View{
    private Model game;

    public MyView(Model game) {
        this.game = game;
    }

    @Override
    public void displayWelcome() {
        System.out.println("*Bienvenue sur le jeu luckynumbers* par Cotton Ian (version 0.1");
    }

    @Override
    public void displayGame() {
        //Header
        System.out.println("Joueur "+ game.getCurrentPlayerNumber()+1);
        Integer taille = 4;
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
        System.out.println("-------------");

        //Body
        Integer player = game.getCurrentPlayerNumber();
        Tile valeur;
        for (int j = 0; j < taille; j++) { //row
            System.out.print((j+1) + "|  ");
            for (int k = 0; k < taille; k++) { //column
                valeur = game.getTile(player, new Position(j,k));
                if(valeur == null){
                    System.out.print(".");
                }
                else{
                    if(j == taille -1){
                        if(valeur.getValue() >= 10){
                            System.out.println(valeur.getValue() + " ");

                        }
                        else{
                            System.out.println(valeur.getValue() + "  ");
                        }
                    }
                    else{
                        if(valeur.getValue() >= 10){
                            System.out.print(valeur.getValue() + " ");
                        }
                        else {
                            System.out.print(valeur.getValue() + "  ");
                        }
                    }
                }
            }
        }
        System.out.println("-------------");
        System.out.println("Tuile choisie : " + game.getPickedTile());
    }

    @Override
    public void displayWinner() {
        System.out.println("Le gagnant est le joueur : " + game.getWinner());
    }

    @Override
    public Integer askPlayerCount() {
        return game.getPlayerCount();
    }

    @Override
    public Position askPosition() {
        System.out.println("Entrer une position.");
        Scanner clavier = new Scanner(System.in);
        System.out.println("Commencer par indique le numéro de la ligne la ligne : ");
        Integer ligne = clavier.nextInt() - 1;
        System.out.println("Indiquer le numéro de la colonne : ");
        Integer colonne = clavier.nextInt() - 1;
        Position pos = new Position(ligne, colonne);

        while (game.canTileBePut(pos) == false){
            System.out.println("position incorrect !");
            System.out.println("Indiquer une ligne : ");
            ligne = clavier.nextInt();
            System.out.println("Indiquer une colonne : ");
            colonne = clavier.nextInt();
            pos = new Position(ligne, colonne);
        }
        return pos;
    }

    @Override
    public void displayError(String message) {
        System.out.println("Erreur : " + message);
    }
}
