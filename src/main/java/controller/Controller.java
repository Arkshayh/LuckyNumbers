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
     * Step 1 display the welcome message
     * Step 2 ask the number of player (between 2 and 4)
     * Step 3 the game start and will display the face up tiles and will display
     *        the number of face down tile
     * Step 4 ask if the current player want to pick up a face up tile
     *        or a face down tile
     * Step 5 display the board of the current player
     * Step 6 put tile on the board (you can also drop it if you took a face down
     *        tile
     * Step 7 if the state of the game is not game over -> it's the next player
     *        turn
     * Step 8 if the state is game over ask the player if he want to replay
     *        if yes -> Step 3 if no -> end of the game
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
