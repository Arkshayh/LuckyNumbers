package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Cotton Ian | 55019
 */

public class TileTest {

    private Tile tile;

    @BeforeEach     // Exécutée avant chaque test
    public void setUp() {
        tile = new Tile(5);
    }

    @Test
    public void Flip_face_up_when_false(){
        tile.flipFaceUp();
        assertTrue(tile.isFaceUp());
    }
    @Test
    public void Flip_face_up_when_true(){
        tile.flipFaceUp();
        tile.flipFaceUp();
        assertTrue(tile.isFaceUp());
    }
}
