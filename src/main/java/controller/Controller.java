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

    public Controller(Game game, View vue) {
        this.game = game;
        this.vue = vue;
    }

    public void play(){
        this.vue.displayWelcome();
        Integer NbPlayer = vue.askPlayerCount();
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
