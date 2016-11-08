package concurrency.synchronize;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

public class SharedArrayTestTest {

    @Test
    public void test() {
     // construct the shared object
        SimpleArray sharedSimpleArray = new SimpleArray( 6 );
        
        // create two tasks to write to the shared SimpleArray
        ArrayWriter writer1 = new ArrayWriter( 1, sharedSimpleArray );
        ArrayWriter writer2 = new ArrayWriter( 11, sharedSimpleArray );

        // execute the tasks with an ExecutorService
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute( writer1 );
        executor.execute( writer2 );
        
        executor.shutdown();
        
        try {
            // wait 1 minute for both writers to finish executing
            boolean tasksEnded = executor.awaitTermination(
                    1, TimeUnit.MINUTES);
            
            if ( tasksEnded )
                System.out.println( sharedSimpleArray ); // print contents 
            else
                System.out.println(
                        "Timed out while waiting for tasks to finish." );
        }
        catch ( InterruptedException ex ) {
            System.out.println(
                    "Interrupted while waiting for tasks to finish." );
        } // end ctach
        
        assertEquals(6, sharedSimpleArray.getArray().length);
        assertEquals(1, sharedSimpleArray.getArray()[0]);
        assertEquals(2, sharedSimpleArray.getArray()[1]);
        assertEquals(3, sharedSimpleArray.getArray()[2]);
        assertEquals(11, sharedSimpleArray.getArray()[3]);
        assertEquals(12, sharedSimpleArray.getArray()[4]);
        assertEquals(13, sharedSimpleArray.getArray()[5]);
    }
    
    

}
