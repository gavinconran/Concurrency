package concurrency.synchronize;

import static org.junit.Assert.*;
import org.junit.Test;

public class SimpleArrayTest {
    
    @Test
    public void testConstructor() {
        SimpleArray s = new SimpleArray(5);
        assertEquals(5, s.getArray().length);
        
    }
    
    @Test
    public void testAdd() {
        SimpleArray s = new SimpleArray(5);
        s.add(1);
        assertEquals(1, s.getArray()[0]);
        s.add(2);
        assertEquals(2, s.getArray()[1]);
        
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testQuestion1NullPointerException() {
        SimpleArray s = new SimpleArray(5);
        s.add(1);
        s.add(1);
        s.add(1);
        s.add(1);
        s.add(1);
        s.add(1);
    }   

}
