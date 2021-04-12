package controller;

import model.Game;
import model.State;
import view.View;

import java.util.Scanner;

public class Controller {
    private Game game;
    private View vue;

    /**
     * Constructor
     * @param game
     * @param vue
     */
    public Controller(Game game, View vue) {
        this.game = game;
        this.vue = vue;
    }

    /**
     * manages the game from start to finish. It is driven by the state of the game
     */
    public void play(){
        this.vue.displayWelcome();
        int NbPlayer = vue.askPlayerCount();
        int choix;
        int choix2;
        boolean replay = true;
        while (replay == true){
            game.start(NbPlayer);
            while(game.getState() != State.GAME_OVER){
                vue.displayFaceUp();
                vue.displayFaceDown(game.faceDownTileCount());
                choix = vue.askFaceUpOrDown();
                choix2 = vue.chooseTile(choix);
                vue.displayGame();
                if(choix2 == 1){
                    game.putTile(vue.askPosition());
                }
                else{
                    game.dropTile();
                }
                if(game.getState() != State.GAME_OVER){
                    game.nextPlayer();
                }
            }
            vue.displayWinner();
            Scanner clavier = new Scanner(System.in);
            System.out.println("Vous voulez rejouez 'Y' | 'N'");
            String rep = clavier.nextLine();
            if(rep.toLowerCase() == "N"){
                replay = false;
            }
        }
    }

}
