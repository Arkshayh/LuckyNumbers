package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Ian Cotton | g55019
 */
public class DeckTest {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck(2);
    }

    @Test
    public void pick_face_down_when_enough_tile(){
        deck.pickFaceDown();
    }

    @Test
    public void pick_face_down_when_not_enough_tile(){
        for (int i = 0; i <40; i++) {
            deck.pickFaceDown();
        }
        assertThrows(IllegalArgumentException.class,
                () -> deck.pickFaceDown());
    }

   @Test
    public void face_down_count_ok(){
        assertEquals(40, deck.faceDownCount());
   }

   @Test
    public void has_face_up_false(){
        assertFalse(deck.hasFaceUp(new Tile(5)));
    }

    @Test
    public void has_face_up_true(){
        deck.putBack(new Tile(5));
        assertTrue(deck.hasFaceUp(new Tile(5)));
    }

    @Test
    public void get_all_face_up(){
        deck.getAllFaceUp();
    }

    @Test
    public void pick_Face_Up_ok(){
        deck.putBack(new Tile(5));
        deck.pickFaceUp(new Tile(5));
    }

    @Test
    public void pick_Face_Up_not_ok(){
        deck.putBack(new Tile(10));
        deck.pickFaceUp(new Tile(5));
    }

    @Test
    public void putBack_ok(){
        deck.putBack(new Tile(5));
    }

}
