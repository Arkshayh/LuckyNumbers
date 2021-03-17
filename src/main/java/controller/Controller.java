package controller;

import model.Game;
import model.Model;
import model.State;
import view.MyView;
import view.View;

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
        while(game.getState() != State.GAME_OVER){
            game.start(NbPlayer);
            game.pickTile();
            game.putTile(vue.askPosition());
            if(game.getState() != State.GAME_OVER){
                game.nextPlayer();
            }
        }
        vue.displayWinner();
    }

}
