package concurrency.mesage_passing.producer_consumer;
//Synchronizing access to a shared integer using the Lock and Condition
//interfaces in place of using synchronize keyword

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class SynchronizedBufferLocks implements Buffer {
    
    // Lock to control synchronisation with the buffer
    private final Lock accessLock = new ReentrantLock();
    
    // conditions to control reading and writing
    private final Condition canWrite = accessLock.newCondition();
    private final Condition canRead = accessLock.newCondition();
    
    private int buffer = -1; // shared by producer and consumer threads
    private boolean occupied = false; // whether buffer is occupied

    @Override
    public void set(int value) throws InterruptedException {
        accessLock.lock();  // lock this object
        
        // output buffer information and buffer information, then wait
        try {
            // while buffer is not empty, place thread in waiting state
            while ( occupied ) {
                System.out.println( "Producer tries to write" );
                displayState( "Buffer full. Producer waits." );
                canWrite.await(); // wait until buffer is empty
            }
            
            buffer = value;  // set new buffer value
            
            // indicate producer cannot store another value
            // until consumer retrieves current buffer value
            occupied = true;
            
            displayState( "Producer writes " + buffer );
            
            // signal thread waiting to read from buffer
            canRead.signal();
        } // end try
        finally {
            accessLock.unlock();  // unlock this object
        } // end finally

    } // end method set

    @Override
    public int get() throws InterruptedException {
        
        int readValue = 0;  // initialise value read from buffer
        accessLock.lock();  // lock this object
        // output thread information and buffer information, then wait
        try {
            // if there is no data read, wait
            while ( !occupied ) {
                System.out.println( "Consumer tries to read." );
                displayState( "Buffer empty. Consumer waits" );
                canRead.await();  // wait until buffer is full
            } // end while
            
            // indicate that producer can store another value
            // because consumer just retrieved buufer value
            occupied = false;
            
            readValue = buffer; // retrieve value from buffer
            displayState( "Consumer reads " + readValue );
            
            // signal thread waiting for buffer to be empty
            canWrite.signal();        
        } 
        finally {
            accessLock.unlock();
        }
        
        return readValue;
    }

 // display current operation and buffer state
    public void displayState( String operation ) {
        System.out.printf( "%-40s%d\t\t%b\n\n", operation, buffer,
                occupied );
    } // end method displayState
}
