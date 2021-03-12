package model;

public class test {
    public static void main(String[] args) {
        Board plateau = new Board();
        Position pos = new Position(3,4);
        System.out.println(plateau.isInside(pos));
    }
}
