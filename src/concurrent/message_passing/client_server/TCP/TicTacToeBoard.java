package concurrent.message_passing.client_server.TCP;

public class TicTacToeBoard implements Board {
    
    private final static int PLAYER_X = 0; // constant for first player
    private final static int PLAYER_O = 1; // constant for second player
    private final static String[] MARKS = { "X", "O" }; // array of marks
    
    // Spec:
    // Rep
    private String[] board = new String[ 9 ]; // tic-tac-toe board
    // Abstract Function
    // Rep Invariant
    // CheckRep()
    
    // Constructor
    public TicTacToeBoard() {
        for ( int i = 0; i < 9; i++ )
            board[ i ] = new String( "" ); // create tic-tac-toe board
    }

    @Override
    // determine whether location is occupied
    public boolean isOccupied( int location ) {
        if ( board[ location ].equals( MARKS[ PLAYER_X ] ) ||
                board [ location ].equals( MARKS[ PLAYER_O ] ) )
            return true; // location is occupied
        else
            return false; // location is not occupied
    } // end method isOccupied
    
    @Override
    // set a move on the board
    public void setMove( int location, String mark ) {
        board[ location ] = mark;        
    }

    @Override
    // Determine whether game over
    public boolean isGameOver() {
        return false; // this is left as an exercise
    } // end method isGameOver

}
