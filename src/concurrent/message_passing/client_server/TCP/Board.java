package concurrent.message_passing.client_server.TCP;

public interface Board {
    
 // Spec:
    // Abstract Function
        // Represents all the valid moves made by both Players in the form of "marks"
    // Rep Invariant
        // Length of board must always be equal to the variable squares
        // countMoves must always be equal to or less than the varaible squares
    
 // Factory method
    public static  Board makeBoard( int squares ) {
        return new TicTacToeBoard( squares );
    }
    
    // determine whether location is occupied
    public boolean isOccupied( int location );
    
    // set a move on the board
    public void setMove( int location, String mark );
    
    // Determine whether game over
    public boolean isGameOver();

}
