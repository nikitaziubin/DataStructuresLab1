package util;
import java.util.Arrays;


public class ArrayStack<E> implements Stack<E> {
    public static final int DEFAULT_SIZE = 10;
    private E[] stack;
    private int top;
    private int size;

    public ArrayStack() {
        stack = (E[]) new Object[DEFAULT_SIZE];
        top = -1;
        size = 0;
    }

    public void push(E item) {
        if (size == stack.length) {
            resizeStack();
        }
        stack[++top] = item;
        size++;
    }
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        E item = stack[top];
        stack[top--] = null;
        size--;
        return item;
    }
    public E peak() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack[top];
    }
    public boolean isEmpty() {
        return size == 0;
    }
    private void resizeStack() {
        stack = Arrays.copyOf(stack, stack.length * 2);
    }
}
