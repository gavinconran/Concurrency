package concurrency.message_pasing.client_server.streams;

import javax.swing.JFrame;

public class StartClient {

    public static void main(String[] args) {
        
        Client application; // declare client application
        
     // if no command line args
        if ( args.length == 0 )
            application = new Client( "127.0.0.1" ); // connect to localhost
        else
            application = new Client( args[ 0 ] ); // use args to connect

        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        application.runClient();  // run client application
    } // end main

} // end class
