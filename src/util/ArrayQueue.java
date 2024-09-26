package util;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    private E[] queue;
    private int head;
    private int tail;
    private int size;
    private int capacity;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        queue = (E[]) new Object[capacity];
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public void enqueue(E item) {
        if (size == capacity) {
            resizeQueue();
        }
        queue[tail] = item;
        tail = (tail + 1) % capacity;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        E item = queue[head];
        queue[head] = null;
        head = (head + 1) % capacity;
        size--;
        return item;
    }

    @Override
    public E peak() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resizeQueue() {
        E[] newQueue = (E[]) new Object[capacity * 2];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(head + i) % capacity];
        }
        queue = newQueue;
        head = 0;
        tail = size;
        capacity *= 2;
    }

    public int getSize() {
        return size;
    }
    public int getCapacity() {
        return capacity;
    }
}