package model;

public class test {
    public static void main(String[] args) {
        Board plateau = new Board();
        Position pos = new Position(3,4);

        System.out.println(plateau.isFull());
        plateau.afficherBoard();
        plateau.put(new Tile(5),new Position(0,0));
        plateau.put(new Tile(5),new Position(0,1));
        plateau.put(new Tile(5),new Position(0,2));
        plateau.put(new Tile(5),new Position(0,3));
        plateau.put(new Tile(5),new Position(1,0));
        plateau.put(new Tile(5),new Position(1,1));
        plateau.put(new Tile(5),new Position(1,2));
        plateau.put(new Tile(5),new Position(1,3));
        plateau.put(new Tile(5),new Position(2,0));
        plateau.put(new Tile(5),new Position(2,1));
        plateau.put(new Tile(5),new Position(2,2));
        plateau.put(new Tile(5),new Position(2,3));
        plateau.put(new Tile(5),new Position(3,0));
        plateau.put(new Tile(5),new Position(3,1));
        plateau.put(new Tile(5),new Position(3,2));
        plateau.put(new Tile(5),new Position(3,3));

        System.out.println("---------------------------------");
        plateau.afficherBoard();

        System.out.println(plateau.isFull());

    }

}
