package model;


import controller.Controller;
import view.MyView;
import view.View;

public class test {
    public static void main(String[] args) {
        Game game = new Game();
        Controller controller = new Controller(game, new MyView(game));
        controller.play();

    }

}
