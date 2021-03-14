package model;


public class test {
    public static void main(String[] args) {
        Board plateau = new Board();



        plateau.canBePut(new Tile(1), new Position(1,2));

        System.out.println(plateau.canBePut(new Tile(10), new Position(1, 1)));


        System.out.println("---------------------------------");
        plateau.afficherBoard();

    }

}
