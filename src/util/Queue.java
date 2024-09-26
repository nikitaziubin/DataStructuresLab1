package util;

public interface Queue <E>{

    public void enqueue(E item);
    public E dequeue();
    public boolean isEmpty();
    public E peak();
}
