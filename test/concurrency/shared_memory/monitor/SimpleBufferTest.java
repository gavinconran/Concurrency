package concurrency.shared_memory.monitor;

import concurrency.shared_memory.monitor.EditBuffer;
import concurrency.shared_memory.monitor.SimpleBuffer;

public class SimpleBufferTest extends EditBufferTest {
    /** @see EditBufferTest#make */
    @Override public EditBuffer make() {
        return new SimpleBuffer();
    }
}
