package concurrency.producerconsumer;
//Synchronizing access to shared data using Object
//methods wait and notifyAll.

// Using an ArrayBlockingQueue will result in more-maintainable
// and better-performing code.

public class SynchronizedBuffer implements Buffer {
    
    private int buffer = -1; // shared by producer and consumer threads
    private boolean occupied = false; // whether the buffer is occupied

    // synchronised method
    @Override
    public synchronized void set(int value) throws InterruptedException {
        
        // while there are no empty locations, place thread in waiting state
        while ( occupied ) {
            // output thread information and buffer information, then wait
            System.out.println( "Producer tries to write." );
            displayState( "Buffer full. Producer waits." );
            wait();
        }

        buffer = value; // set new buffer value
        // indicate producer cannot store another value
        // until consumer retrieves current buffer value
        occupied = true;
        displayState( "Producer writes " + buffer );
        notifyAll(); // tell waiting thread(s) to enter runnable state
    }

    // synchronised method
    @Override
    public synchronized int get() throws InterruptedException {
     // while no data to read, place thread in waiting state
        while ( !occupied ) {
            // output thread information and buffer information, then wait
            System.out.println( "Consumer tries to read." );
            displayState( "Buffer empty. Consumer waits." );
            wait();
        } // end while
        // indicate that producer can store another value
        // because consumer just retrieved buffer value
        occupied = false;
        displayState( "Consumer reads " + buffer );
        notifyAll(); // tell waiting thread(s) to enter runnable state
        return buffer;
    }
    
    // display current operation and buffer state
    public void displayState( String operation ) {
    System.out.printf( "%-40s%d\t\t%b\n\n", operation, buffer,
    occupied );
    } // end method displayState

}
