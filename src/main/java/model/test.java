package model;


public class test {
    public static void main(String[] args) {
        Game jeu = new Game();
        jeu.start(2);
        System.out.println(jeu.getBoardSize());

        int nb = (int)(Math.random() * ((20 - 2) + 1)) + 2;
        System.out.println(nb);
        Tile tuile = new Tile(nb);
    }
}
