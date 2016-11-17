package concurrency.shared_memory.monitor;
// Atomic Operations

// This method makes three different calls to buf — to convert it to a string in order to search for s, 
// to delete the old text, 
// and then to insert t in its place. Even though each of these calls individually is atomic, 
// the findReplace method as a whole is not threadsafe, because other threads might mutate the buffer while findReplace is working, 
// causing it to delete the wrong region or put the replacement back in the wrong place.

// To prevent this, findReplace needs to synchronize with all other clients of buf.

public class Editor {
    
    // ...
    
    /**
     * Modifies buf by replacing the first occurrence of s with t.
     * If s not found in buf, then has no effect.
     * @returns true if and only if a replacement was made
     */
    public static boolean findReplace(EditBuffer buf, String s, String t) {
        // The effect of "synchronized (buf)" is to enlarge the synchronization region 
        // that the monitor pattern already put around the individual toString, delete, and insert methods, 
        // into a single atomic region that ensures that all three methods are executed without interference from other threads.
        synchronized (buf) {
            int i = buf.toString().indexOf(s);
            if (i == -1) {
                return false;
            }
            buf.delete(i, s.length());
            buf.insert(i, t);
            return true;
        }
    }
    
    // ...
}
