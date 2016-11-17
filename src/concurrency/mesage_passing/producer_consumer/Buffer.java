package concurrency.mesage_passing.producer_consumer;
// Buffer interface specifies methods called by Producer and Consumer

public interface Buffer {
    
    // Factory method
    public static  Buffer makeBuffer() {
//        return new UnsynchronizedBuffer();
//        return new BlockingBuffer();
//        return new SynchronizedBuffer();
        return new SynchronizedBufferLocks();
    }

    // place int value into Buffer
    public void set(int value) throws InterruptedException;

    // return int value from Buffer
    public int get() throws InterruptedException;
}
