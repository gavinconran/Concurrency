package concurrency.shared_memory.monitor;

import concurrency.shared_memory.monitor.EditBuffer;
import concurrency.shared_memory.monitor.GapBuffer;

public class GapBufferTest extends EditBufferTest {    
    /** @see EditBufferTest#make */
    @Override public EditBuffer make() {
        return new GapBuffer();
    }
}
