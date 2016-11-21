package concurrent.message_passing.client_server.TCP;

public interface Board {
    
 // Factory method
    public static  Board makeBoard() {
        return new TicTacToeBoard();
    }
    
    public boolean isOccupied( int location );
    
    public boolean isGameOver();
    
    public void setMove( int location, String mark );

}
