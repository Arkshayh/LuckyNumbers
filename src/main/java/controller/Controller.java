package controller;

import model.Game;
import model.Model;
import model.State;
import view.MyView;
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
        boolean replay = true;
        while (replay == true){
            game.start(NbPlayer);
            while(game.getState() != State.GAME_OVER){
                game.pickTile();
                vue.displayGame();
                game.putTile(vue.askPosition());
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
