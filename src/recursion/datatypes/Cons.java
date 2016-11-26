package recursion.datatypes;

public class Cons<E> implements ImList<E> {
    
    private final E e;
    private final ImList<E> rest;
    private int size = 0;
    // rep invariant:
    //   e != null, rest != null, size >= 0
    //   size > 0 implies size == 1+rest.size()

    public Cons(E e, ImList<E> rest) {
        this.e = e;
        this.rest = rest;
        
    }
    
    @Override
    public ImList<E> cons(E e) {
        return new Cons<>(e, this);
    }
    
    @Override
    public E first() {
        return e;
    }
    
    @Override
    public ImList<E> rest() {
        return rest;
    }
    
    @Override
    public int size() { 
        if (size == 0) size = 1 + rest.size();
        return size;
    }
    
    @Override
    public boolean isEmpty() { return false; }
    
    @Override
    public boolean contains(E e) { return this.e.equals(e) || rest.contains(e); }
    
    @Override
    public E get(int n) { 
        if (n == 0)
            return first();
        else
            return rest.get(n - 1);
    }
    
    @Override
    public ImList<E> append(ImList<E> other) { return new Cons<>(e, rest.append(other)); }
    
    @Override
    public ImList<E> reverse() { return rest.reverse().append(new Cons<>(e, new Empty<E>())); }
    
}