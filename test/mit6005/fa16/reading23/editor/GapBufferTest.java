package mit6005.fa16.reading23.editor;

public class GapBufferTest extends EditBufferTest {    
    /** @see EditBufferTest#make */
    @Override public EditBuffer make() {
        return new GapBuffer();
    }
}
