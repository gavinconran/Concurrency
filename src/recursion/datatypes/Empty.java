package recursion.datatypes;
// SENTINEL OBJECTS PATTERN
// to signal the base case or endpoint of a data structure is an example of a design pattern called sentinel objects. 

public class Empty<E> implements ImList<E> {
    
    public Empty() {
        
    }

    @Override
    public ImList<E> cons(E e) {
        return new Cons<>(e, this);
    }

    @Override
    public E first() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ImList<E> rest() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public int size() { return 0; }
    
    @Override
    public boolean isEmpty() { return true; }
    
    @Override
    public boolean contains(E e) { return false; }
    
    @Override
    public E get(int n) { 
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ImList<E> append(ImList<E> other) { return other; }
    
    @Override
    public ImList<E> reverse() { return new Empty<E>(); }
    
}
