package concurrency.producerconsumer;
//Creating a synchronized buffer using an ArrayBlockingQueue.

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer {
    
    // need to add:
    // Spec
    // Abstraction Function
    // Rep Invariant

    // rep
    private final ArrayBlockingQueue<Integer> buffer; // sharedbuffer
    
    // Constructor
    public BlockingBuffer() {
        buffer = new ArrayBlockingQueue<Integer>(1);
    }

    @Override
    public void set(int value) throws InterruptedException {
        buffer.put(value);
        System.out.printf("%s%2d\t%s%d\n", "Producer writes ", value, "Buffer cells occupied: ", buffer.size());
    }

    @Override
    public int get() throws InterruptedException {
        int readValue = buffer.take();
        System.out.printf("%s %2d\t%s%d\n", "Consumer reads ", readValue, "Buffer cells occupied: ", buffer.size());
        return readValue;
    }

} // end class
