package util;
import java.util.List;

public interface Stack<E> {
    E pop();
    void push(E item);
    abstract E peak();
    boolean isEmpty();
}
