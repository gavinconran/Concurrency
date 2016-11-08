package concurrency.runnable;

import java.lang.Thread;

public class ThreadCreator {

    public static void main(String[] args) {
        // main executes in the main thread
        
        System.out.println( "Creating threads" );
        
        // create each thread with a new targeted runnable
        Thread thread1 = new Thread( new PrintTask( "task1" ) );
        Thread thread2 = new Thread( new PrintTask( "task2" ) );
        Thread thread3 = new Thread( new PrintTask( "task3" ) );
        
        System.out.println( "Threads created, starting tasks" );
        
        // start threads and place in runnable state
        thread1.start();
        thread2.start();
        thread3.start();
        
        System.out.println( "Threads started, main ends" );
        
        

    }
 // Program will not terminate until all threads have terminated
}
