package concurrency.mesage_passing.producer_consumer;
//UnsynchronizedBuffer maintains the shared integer that is accessed by
//a producer thread and a consumer thread via methods set and get.

public class UnsynchronizedBuffer implements Buffer {

    private int buffer = -1;

    @Override
    public void set(int value) throws InterruptedException {
        System.out.printf("Producer writes\t%2d", value);
        buffer = value;
    }

    @Override
    public int get() throws InterruptedException {
        System.out.printf("Consumer reads\t%2d", buffer);
        return buffer;
    }

} // end class
