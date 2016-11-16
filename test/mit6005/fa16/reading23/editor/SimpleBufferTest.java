package mit6005.fa16.reading23.editor;

public class SimpleBufferTest extends EditBufferTest {
    /** @see EditBufferTest#make */
    @Override public EditBuffer make() {
        return new SimpleBuffer();
    }
}
