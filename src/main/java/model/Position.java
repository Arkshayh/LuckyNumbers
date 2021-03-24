package model;

/**
 * @author g55019 / Cotton Ian
 * The position class defines a position via 2 attributes: an Integer row
 * and an Integer column
 */
public class Position {
    private Integer row;
    private Integer column;

    /* Constructor of a position The values of the row and the column of the position are required.
    @param Integer, Integer value of row and column
    */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /*
    Get row
    @return Integer Returns the value of the row of a position
     */
    public int getRow() {
        return row;
    }

    /*
     Get column
     @return Integer Returns the value of the column of a position
     */
    public int getColumn() {
        return column;
    }

    /*
    toString method
     */
    @Override
    public String toString() {
        return "Position : " +
                row +
                ", " + column;
    }
}
