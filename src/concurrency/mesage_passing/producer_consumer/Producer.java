package concurrency.mesage_passing.producer_consumer;

import java.util.Random;

// producer with a run method that inserts the values 1 to 10 in buffer.

public class Producer implements Runnable {

    private final static Random generator = new Random();
    private final Buffer sharedLocation; // reference to shared object

    // constructor
    public Producer(Buffer shared) {
        sharedLocation = shared;
    }

    // store values from 1 to 10 in sharedLocation
    @Override
    public void run() {

        int sum = 0;

        for (int count = 1; count <= 10; count++) {
            try {
                Thread.sleep(generator.nextInt(3000)); // random sleep
                sharedLocation.set(count); // set value in buffer
                sum += count; // increment sum of values
                System.out.printf("\t%2d\n", sum);
            } // end try
            catch (InterruptedException exception) {
                exception.printStackTrace();
            } // end catch
        } // end for

        System.out.println("Producer done producing\nTerminating Producer");
    } // end method run

} // end class
