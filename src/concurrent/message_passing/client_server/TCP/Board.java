package concurrent.message_passing.client_server.TCP;

public interface Board {
    
 // Factory method
    public static  Board makeBoard() {
        return new TicTacToeBoard();
    }
    
    // determine whether location is occupied
    public boolean isOccupied( int location );
    
    // set a move on the board
    public void setMove( int location, String mark );
    
    // Determine whether game over
    public boolean isGameOver();

}
