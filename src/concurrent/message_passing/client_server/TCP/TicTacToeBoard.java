package concurrent.message_passing.client_server.TCP;

public class TicTacToeBoard implements Board {
    
    private final static int PLAYER_X = 0; // constant for first player
    private final static int PLAYER_O = 1; // constant for second player
    private final static String[] MARKS = { "X", "O" }; // array of marks
        
    // Spec:
    // Rep
    private String[] board;; // tic-tac-toe board
    private int countMoves;
    // Abstract Function
        // Represents all the valid moves made by both Players in the form of "marks"
    // Rep Invariant
        // Length of board must always be equal to squares
        // countMoves must always be equal to or less than squares
    
    // Constructor
    public TicTacToeBoard( int squares ) {
        board = new String[ squares ];
        for ( int i = 0; i < squares; i++ )
            board[ i ] = new String( "" ); // create tic-tac-toe board
        countMoves = 0;
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
    // mutator: set a move on the board
    public void setMove( int location, String mark ) {
        board[ location ] = mark;
        countMoves++;
        RepCheck();
    }

    @Override
    // Determine whether game over
    public boolean isGameOver() {
        return countMoves == board.length; 
    } // end method isGameOver
    
    // RepCheck Function
    private void RepCheck() {
        assert board.length == board.length; 
        assert countMoves <= board.length;
    }

}
