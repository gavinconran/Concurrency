package sequential.message_passing.client_server.TCP;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField enterField; // enters information from user
    private JTextArea displayArea; // display information to user
    private ObjectOutputStream output; // output stream to server
    private ObjectInputStream input; // input stream from server
    private String message = ""; // message from server
    private String chatServer; // host server for this application
    private Socket client; // socket to communicate with server
    
    // initialize chatServer and set up GUI
    public Client( String host ) {
        
        super( "Client" );
        
        chatServer = host;  // set server to which this client connects
        
        enterField = new JTextField(); // create enterField
        enterField.setEditable(false);
        enterField.addActionListener(
                new ActionListener() {
                    // send message to client
                    public void actionPerformed( ActionEvent event ) {
                        sendData( event.getActionCommand() );
                        enterField.setText("");
                    } // end method actionPerformed
                } // end anonymous inner class
        ); // end call to addActionListener
        
        add( enterField, BorderLayout.NORTH );
        
        displayArea = new JTextArea();  // create displayArea
        add( new JScrollPane( displayArea ), BorderLayout.CENTER );
        
        setSize( 300, 150 );  // set size of window
        setVisible( true );   // show window
    } // end Client constructor
    
    // connect to server and process messages from server
    public void runClient() {
        try {
            connectToServer();     // create a Socket to make connection
            getStreams();          // get input & output streams
            processConnection();   // process connection
        } // end try
        catch ( EOFException eofException ) {
            displayMessage( "\nClient terminated connection" );
        }
        catch (IOException ioException ) {
            ioException.printStackTrace();
        } // end catch
        finally {
            closeConnection();  // close connection
        } // end finally
    } // end method runClient
    
    
    
    // connect to server
    private void connectToServer() throws IOException {
        
        displayMessage("Attempting connection\n");
        // create Socket to make connection to server
        client = new Socket(InetAddress.getByName(chatServer), 12345);
        // display connection information
        displayMessage("Connected to: " 
                    + client.getInetAddress().getHostName());
    } // end method connectToServer
    
 // get streams to send and receive data
    private void getStreams() throws IOException {
        // set up output stream for objects
        output = new ObjectOutputStream( client.getOutputStream() );
        output.flush(); // flush output buffer to send header info
        
        // set up input stream for objects
        input = new ObjectInputStream( client.getInputStream() );
        
        displayMessage( "\nGot I/O streams\n" );
    } // end method getStreams
    
 // process connection with client
    private void processConnection() throws IOException {
        
        // enable enterField so server user can send messages
        setTextFieldEditable( true );
        
        do {// process messages sent from server
            try { // read message and display it
                message = ( String ) input.readObject();  // read new message
                displayMessage( "\n" + message );  // display message
            } // end try
            catch ( ClassNotFoundException classNotFoundException ) {
                displayMessage( "\nUnknown object type received" );
            } // end catch
        } while ( !message.equals( "SERVER>>> TERMINATE" ) );
        
    } // end method processConnection
    
 // close streams and socket
    private void closeConnection() {
        displayMessage( "\nTerminating connection\n" );
        setTextFieldEditable( false );
        
        try {
            output.close();
            input.close();
            client.close();
        } // end try
        catch ( IOException ioException ) {
            ioException.printStackTrace();
        } // end catch
    } // end method closeConnection
    
 // send message to server
    private void sendData(String message) {
        try // send object to client
        {
            output.writeObject("CLIENT>>> " + message);
            output.flush(); // flush output to client
            displayMessage("\nCLIENT>>> " + message);
        } // end try
        catch (IOException ioException) {
            displayArea.append("\nError writing object");
        } // end catch
    } // end method sendData
    
 // manipulates displayArea in the event-dispatch thread
    private void displayMessage(final String messageToDisplay) {
        // The invokeLater() drops a Runnable object at the end of the event queue, 
        // and when Swing’s event loop reaches it, it simply calls run(). 
        // Thus the body of run() ends up run by the event-dispatch thread, 
        // where it can safely call observers and mutators on the view tree. 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() // updates displayArea
            {
                displayArea.append(messageToDisplay); // append message
            } // end method run
        } // end anonymous inner class
        ); // end call to SwingUtilities.invokeLater
    } // end method displayMessage
    
 // manipulates enterField in the event-dispatch thread
    private void setTextFieldEditable(final boolean editable) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() // sets enterField's editability
            {
                enterField.setEditable(editable);
            } // end method run
        } // end inner class
        ); // end call to SwingUtilities.invokeLater
    } // end method setTextFieldEditable

} // end class
