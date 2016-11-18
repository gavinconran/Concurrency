package concurrency.message_pasing.client_server.streams;

import javax.swing.JFrame;

public class StartServer {

    public static void main(String[] args) {
        Server application = new Server();  // create server
        application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        application.runServer();  // run server application

    }

}
