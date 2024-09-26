package util;

public interface Stack<E> {
    E pop();
    void push(E item);
    abstract E peak();
    boolean isEmpty();
}
