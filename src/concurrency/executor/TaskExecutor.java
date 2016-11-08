package concurrency.executor;

// using an ExecutorService to execute Runnables
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import concurrency.runnable.*;

public class TaskExecutor {
    
    public static void main( String[] args ) {
        
        // create and name each runnable
        PrintTask task1 = new PrintTask( "task1" );
        PrintTask task2 = new PrintTask( "task2" );
        PrintTask task3 = new PrintTask( "task3" );
        
        System.out.println( "Starting Executor" );
        
        // create ExecutorService to manage threads
        ExecutorService threadExecutor = Executors.newCachedThreadPool();
        
        // start threads and place in runnable state
        // Method execute returns immediately after each invocation
        // i.e. the program does not wait for each PrintTask to complete
        threadExecutor.execute(task1);
        threadExecutor.execute(task2);
        threadExecutor.execute(task3);
        
        // shut down worker threads when their tasks complete
        // i..e stop accepting new tasks
        // but continues executing tasks already submitted 
        threadExecutor.shutdown();
        
        System.out.println( "Tasks started, main ends.\n" );

    }

}
