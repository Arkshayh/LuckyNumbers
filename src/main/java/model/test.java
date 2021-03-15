package model;


public class test {
    public static void main(String[] args) {
        Model jeu = new Game();
        jeu.start(2);
        System.out.println(jeu.getState());
        jeu.pickTile();
        System.out.println(jeu.getState());
        jeu.putTile(new Position(0,0));
        System.out.println(jeu.getState());
        jeu.nextPlayer();
    }
}
