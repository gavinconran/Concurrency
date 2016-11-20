package sequential.message_passing.client_server.UDP;

import javax.swing.JFrame;

public class StartUDPServer {
    
    public static void main(String[] args) {
        ServerUDP application = new ServerUDP(); // create server
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.waitForPackets(); // run server application
    } // end main

}
