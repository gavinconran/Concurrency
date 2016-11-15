package concurrency.producerconsumer;
// Buffer interface specifies methods called by Producer and Consumer

public interface Buffer {
    
    // Factory method
    public static  Buffer makeBuffer() {
//        return new UnsynchronizedBuffer();
        return new BlockingBuffer();
//        return new SynchronizedBuffer();
    }

    // place int value into Buffer
    public void set(int value) throws InterruptedException;

    // return int value from Buffer
    public int get() throws InterruptedException;
}
