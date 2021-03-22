package LuckyNumbers;

import controller.Controller;
import model.Game;
import view.MyView;

/**
 * Launch the game Luckynumbers
 */
public class Luckynumbers {
    public static void main(String[] args) {
        Game game = new Game();
        Controller controller = new Controller(game, new MyView(game));
        controller.play();
    }
}
