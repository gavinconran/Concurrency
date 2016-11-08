package concurrency.runnable;

import java.util.Random;

public class PrintTask implements Runnable {
    
    private final int sleepTime;    // random sleep time for thread
    private final String taskName;  // name of task
    private final static Random generator = new Random();
    
    // Constructor
    public PrintTask( String name ) {
        taskName = name; // set task name
        
        // // pick random sleep time between 0 and 5 seconds
        sleepTime = generator.nextInt( 5000 );  // milliseconds
        
    }
    
    // method run contains the code that a thread will execute
    @Override
    public void run() {
        try { // put thread to sleep for sleepTime amount of time
            System.out.printf("%s going to sleep for %d millisecoonds.\n", 
                    taskName, sleepTime);
            Thread.sleep(sleepTime); // put thread to sleep
        } catch ( InterruptedException exception ) {
            System.out.printf( "%s %s\n", taskName,
                    "terminated prematurely due to interruption" );
        }
        
        // print task name
        System.out.printf("%s done sleeping\n", taskName );
    }

}
