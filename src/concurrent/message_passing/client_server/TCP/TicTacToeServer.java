package concurrent.message_passing.client_server.TCP;

//Server side of client/server Tic-Tac-Toe program.
import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class TicTacToeServer extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String[] board = new String[ 9 ]; // tic-tac-toe board
    private JTextArea outputArea; // for outputting moves
    private Player[] players; // array of Players
    private ServerSocket server; // server socket to connect with clients
    private int currentPlayer; // keeps track of player with current move
    private final static int PLAYER_X = 0; // constant for first player
    private final static int PLAYER_O = 1; // constant for second player
    private final static String[] MARKS = { "X", "O" }; // array of marks
    private ExecutorService runGame; // will run players
    private Lock gameLock; // to lock game for synchronization
    private Condition otherPlayerConnected; // to wait for other player
    private Condition otherPlayerTurn; // to wait for other player's turn
    
    // set up tic-tac-toe server and GUI that displays messages
    public TicTacToeServer() {
        
        super( "Tic-Tac-Toe Server" ); // set title of window
        // create ExecutorService with a thread for each player
        runGame = Executors.newFixedThreadPool( 2 );
        gameLock = new ReentrantLock(); // create lock for game
        // condition variable for both players being connected
        otherPlayerConnected = gameLock.newCondition();
        // condition variable for the other player's turn
        otherPlayerTurn = gameLock.newCondition();
        
        
        for ( int i = 0; i < 9; i++ )
            board[ i ] = new String( "" ); // create tic-tac-toe board
        players = new Player[2];
        currentPlayer = PLAYER_X;
        
        try {
            server = new ServerSocket( 12345, 2 );
        } catch (IOException ioException ) {
            ioException.printStackTrace();
            System.exit(1);
        } // end try-catch
        
        outputArea = new JTextArea(); // create JTextArea for output
        add( outputArea, BorderLayout.CENTER );
        outputArea.setText( "Server awaiting connections\n" );
        
        setSize( 300, 300 ); // set size of window
        setVisible( true ); // show window
        
    } // end constructor  
    
    // wait for two connections so game can be played
    public void execute() {
        // wait for each client to connect
        for ( int i = 0; i < players.length; i++ ) {
            try { // wait for connection, create Player, start runnable
                players[ i ] = new Player( server.accept(), i );
                runGame.execute( players[ i ] ); // execute player runnable
            } catch ( IOException ioException ) {
                ioException.printStackTrace();
                System.exit( 1 );
            } // end catch
        } // end for
    
        gameLock.lock(); // lock game to signal player X's thread
        try {
            players[ PLAYER_X ].setSuspended( false ); // resume player X
            otherPlayerConnected.signal(); // wake up player X's thread
        } finally {
            gameLock.unlock(); // unlock game after signalling player X
        } // end finally
    } // end method execute
    
    // determine if move is valid
    public boolean validateAndMove( int location, int player ) {
        // while not current player, must wait for turn
        while ( player != currentPlayer ) {
            gameLock.lock(); // lock game to wait for other player to go
            try {
                otherPlayerTurn.await(); // wait for player's turn
            } catch ( InterruptedException exception ) {
                exception.printStackTrace();
            } finally {
                gameLock.unlock(); // unlock game after waiting
            } // end finally
        } // end while
        
        // if location not occupied, make move
        if ( !isOccupied( location ) ) {
            board[ location ] = MARKS[ currentPlayer ]; // set move on board
            currentPlayer = ( currentPlayer + 1 ) % 2; // change player
            // let new current player know that move occurred
            players[ currentPlayer ].otherPlayerMoved( location );
            gameLock.lock(); // lock game to signal other player to go
            try {
                otherPlayerTurn.signal(); // signal other player to continue
            } finally {
                gameLock.unlock(); // unlock game after signaling
            } // end finally
            
            return true; // notify player that move was true
        } else {
            return false; // notify player that move is not valid
        } 
    } // end method validateAndMove
    
    // determine whether location is occupied
    public boolean isOccupied( int location ) {
        if ( board[ location ].equals( MARKS[ PLAYER_X ] ) ||
                board [ location ].equals( MARKS[ PLAYER_O ] ) )
            return true; // location is occupied
        else
            return false; // location is not occupied
    } // end method isOccupied
    
    // place code in this method to determine whether game over
    public boolean isGameOver() {
        return false; // this is left as an exercise
    } // end method isGameOver
    
    // private inner class Player manages each Player as a runnable
    private class Player implements Runnable {
        
        private Socket connection; // connection to client
        private Scanner input; // input from client
        private Formatter output; // output to client
        private int playerNumber; // tracks which player this is
        private String mark; // mark for this player
        private boolean suspended = true; // whether thread is suspended
        
        // set up Player thread
        public Player( Socket socket, int number ) {
            playerNumber = number; // store this player's number
            mark = MARKS[ playerNumber ]; // specify player's mark
            connection = socket; // store socket for client
            
            try { // obtain streams from Socket
                input = new Scanner( connection.getInputStream() );
                output = new Formatter( connection.getOutputStream() );
            } catch ( IOException ioException ){ 
                ioException.printStackTrace();
                System.exit( 1 );
            } // end catch
        } // end Player constructor
        
        // send message that other player moved
        public void otherPlayerMoved ( int location ) {
            output.format( "Opponent moved\n" );
            output.format( "%d\n", location ); // send location of move
            output.flush(); // flush output
        } // end otherPlayerMoved
        
        @Override
        public void run() {
         // send client its mark (X or O), process messages from client
            try {
                displayMessage( "Player " + mark + " connected\n" );
                output.format( "%s\n", mark ); // send player's mark
                output.flush(); // flush output
                
             // if player X, wait for another player to arrive
                if ( playerNumber == PLAYER_X ) {
                    output.format( "%s\n%s", "Player X connected",
                            "Waiting for another player\n" );
                    output.flush(); // flush output
                    gameLock.lock(); // lock game to wait for second player
                    try {
                        while( suspended ) {
                            otherPlayerConnected.await(); // wait for player O
                        } // end while
                    } catch ( InterruptedException exception ) {
                        exception.printStackTrace();
                    } finally {
                        gameLock.unlock(); // unlock game after second player
                    } // end finally
                    // send message that other player connected
                    output.format( "Other player connected. Your move.\n" );
                    output.flush(); // flush output
                } else {
                    output.format( "Player O connected, please wait\n" );
                    output.flush(); // flush output
                } // end if else
                
                // while game not over
                while ( !isGameOver() ) {
                    
                    int location = 0; // initialise move location
                    
                    if ( input.hasNext() )
                        location = input.nextInt(); // get move location
                    
                    // check for valid move
                    if ( validateAndMove( location, playerNumber ) ) {
                        displayMessage( "\nlocation: " + location );
                        output.format( "Valid move.\n" ); // notify client
                        output.flush(); // flush output
                    } else {  // invalid move
                        output.format( "Invalid move, try again\n" );
                        output.flush(); // flush output
                    } // end if-else    
                } // end while
            } finally {
                try {
                    connection.close(); // close connection
                } catch ( IOException ioException ) {
                    ioException.printStackTrace();
                    System.exit( 1 );
                } // end try-catch
            } // end finally
            
        } // end method run
            
        // set whether or not thread is suspended
        public void setSuspended( boolean status ) {
   
            suspended = status; // set value of suspended
        } // end method setSuspended    
        
    } // end class Player
    
     // manipulates displayArea in the event-dispatch thread
        private void displayMessage( final String messageToDisplay ) {
            SwingUtilities.invokeLater(
                    new Runnable() {
                        public void run() { // updates displayArea
                            outputArea.append( messageToDisplay ); // display message
                        } // end method run
                    } // end anonymous inner class
                ); // end call to SwingUtilities.invokeLater
        } // end method displayMessage
}