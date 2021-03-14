package model;
/**
 * @author g55019 / Cotton Ian
 * Represents the state of the current game and the actions that can be performed at each state of the game
 */
public enum State {
    /*
    You can only use start() if the game is not started -> go to picktile state
     */
    NOT_STARTED,

    /*
        You can only use picktile() in this state -> go to placeTile state
     */
    PICK_TILE,

    /*
    You can only putTile() in this state after that action, there will be a checking to see if the game is over
    or if the turn is over (turn end / game over state)
     */
    PLACE_TILE,

    /*
    It's the turn of another player to play and you go back to the picktile state
     */
    TURN_END,

    /*
    the game is over, you can only restart one (-> bring back to pick tile state)
     */
    GAME_OVER;
    
}
