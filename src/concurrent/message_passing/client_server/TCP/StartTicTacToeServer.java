package concurrent.message_passing.client_server.TCP;

import javax.swing.JFrame;

public class StartTicTacToeServer {
    
    public static void main( String[] args ) {
    
        TicTacToeServer application = new TicTacToeServer();
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.execute();
    }
}
