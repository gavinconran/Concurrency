package concurrency.synchronize;
// Class that manages an integer array to be shared by multiple threads

import java.util.Arrays;
import java.util.Random;

public class SimpleArray {
    
    private final int[] array; // the shared integer array
    private int writeIndex = 0;
    private final static Random generator = new Random();
    
    // Construct a SimpleArray of a given size
    public SimpleArray( int size ) {
        array = new int[ size ];
    }
    
    // add a value to the shared array
    // add synchronized keyword
    public synchronized void add( int value ) {
        int position = writeIndex;
        
        try {
            // put thread to sleep for 0-499 milliseconds
            Thread.sleep(generator.nextInt(500));
        } 
        catch ( InterruptedException ex ) {
            ex.printStackTrace();
        }
        
        // put value in the appropriate element
        getArray()[ position ] = value;
        System.out.printf("%s wrote %2d to element %d.\n",
                Thread.currentThread().getName(), value, position );
        
        ++writeIndex;
        System.out.printf( "Next write index: %d\n", writeIndex );        
    } // end method add
    
    public String toString() {
        return "\nContents of SimpleArray:\n" + Arrays.toString( getArray() );
    } // end method toString

    public int[] getArray() {
        return array;
    }
} // end class SimpleArray
