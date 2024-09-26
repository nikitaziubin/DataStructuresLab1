package util;

import java.util.LinkedList;

public class LinkedListStack<E> implements Stack<E> {
    private LinkedList<E> stack;

    public LinkedListStack() {
        stack = new LinkedList<>();
    }

    @Override
    public void push(E item) {
        stack.addFirst(item);
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.removeFirst();
    }

    public E peak() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.getFirst();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }
}