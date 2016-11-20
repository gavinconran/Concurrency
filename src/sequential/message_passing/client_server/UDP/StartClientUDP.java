package sequential.message_passing.client_server.UDP;

import javax.swing.JFrame;

public class StartClientUDP {
    
    public static void main( String[] args ) {
    ClientUDP application = new ClientUDP(); // create client
    application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    application.waitForPackets(); // run client application
    } // end main

}
