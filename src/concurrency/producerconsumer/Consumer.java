package concurrency.producerconsumer;

import java.util.Random;

// Consumer with a run method that loops, rading 10 values from buffer

public class Consumer implements Runnable {

    private final static Random generator = new Random();
    private final Buffer sharedLocation;

    // constructor
    public Consumer(Buffer shared) {
        sharedLocation = shared;
    }

    // read sharedLocation's value 10 times and sum the values
    @Override
    public void run() {

        int sum = 0;

        for (int count = 1; count <= 10; count++) {
            try {
                Thread.sleep(generator.nextInt(3000));
                sum += sharedLocation.get();
                System.out.printf("\t\t\t%2d\n", sum);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            } // end try-catch
        } // end loop

        System.out.printf("\n%s %d\n%s\n", "Consumer read values totaling", sum, "Terminating Consumer");

    } // end method run

} // end class
