package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author MCD <mcodutti@he2b.be>
 */
public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
    }

    /* =====================
         Tests for start()
       ===================== */

    /* --- test related to the state --- */
    @Test
    public void start_when_game_not_started_ok() {
        game.start(4);
    }

    @Test
    public void start_when_game_over_ok() {
        fullPlay();
        game.start(2);
    }

    /* Play a game till the end */
    private void fullPlay() {
        game.start(2);
        int value = 1;
        int line = 0;
        int col = 0;
        for (int turn = 1; turn < game.getBoardSize() * game.getBoardSize(); turn++) {
            for (int player = 0; player < game.getPlayerCount(); player++) {
                game.pickTile(value);
                game.putTile(new Position(line, col));
                game.nextPlayer();
            }
            value++;
            col++;
            if (col == game.getBoardSize()) {
                col = 0;
                line++;
            }
        }
        game.pickTile(20);
        game.putTile(new Position(line,col));
    }

    @Test
    public void start_when_game_in_progress_ISE() {
        game.start(4);
        assertThrows(IllegalStateException.class,
                () -> game.start(1));
    }

    @Test
    public void start_state_changed_to_PICK_TILE() {
        game.start(3);
        assertEquals(State.PICK_TILE, game.getState());
    }

    /* --- tests related to the parameter --- */
    @Test
    public void start_playerCount_too_small_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(1));
    }

    @Test
    public void start_playerCount_minimum_accepted() {
        game.start(2);
    }

    @Test
    public void start_playerCount_maximum_accepted() {
        game.start(4);
    }

    @Test
    public void start_playerCount_too_big_Exception() {
        assertThrows(IllegalArgumentException.class,
                () -> game.start(5));
    }

    /* -- tests related to fields initialization --- */
    @Test
    public void start_playerCount_initialized() {
        game.start(4);
        assertEquals(4, game.getPlayerCount());
    }

    @Test
    public void start_current_player_is_player_0() {
        game.start(4);
        assertEquals(0, game.getCurrentPlayerNumber());
    }

    @Test 
    public void get_board_size_4(){
        game.start(2);
        assertEquals(4, game.getBoardSize());
    }
    
    @Test
    public void pick_tile_when_state_incorrect_Exception(){
        game.start(2);
        game.pickTile(5);
        assertThrows(IllegalStateException.class,
                () -> game.pickTile(10)); 
    }
    
    @Test
    public void pick_tile_when_state_ok(){
        game.start(2);
        game.pickTile(5);
    }
    
    @Test
    public void pick_tile_when_value_ok(){
        game.start(2);
        game.pickTile(5);
    }
    
    @Test
    public void pick_tile_when_value_max(){
        game.start(2);
        game.pickTile(20);
    }
    
    @Test
    public void pick_tile_when_value_min(){
        game.start(2);
        game.pickTile(20);
    }
    
    @Test
    public void pick_tile_when_value_too_big_Exeption(){
        game.start(2);
        assertThrows(IllegalArgumentException.class,
                () -> game.pickTile(21)); 
    }
    
    @Test
    public void pick_tile_when_value_too_small_Exeption(){
        game.start(2);
        assertThrows(IllegalArgumentException.class,
                () -> game.pickTile(-4)); 
    }

    @Test
    public void put_Tile_StateExeption(){
        game.start(2);
        assertThrows(IllegalStateException.class,
                () -> game.putTile(new Position(0,0)));
    }

    @Test
    public void put_Tile_ok(){
        game.start(2);
        game.pickTile(5);
        game.putTile(new Position(2,2));
    }

    @Test
    public void put_Tile_wrong_position(){
        game.start(2);
        game.pickTile(5);
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> game.putTile(new Position(-1,0)));
    }

    @Test
    public void next_player_ok(){
        game.start(2);
        game.pickTile(5);
        game.putTile(new Position(2,2));
        game.nextPlayer();
    }

    @Test
    public void next_player_State_Exception(){
        game.start(2);
        game.pickTile(5);
        assertThrows(IllegalStateException.class,
                () -> game.nextPlayer());
    }
}
