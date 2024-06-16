package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> input = new SimpleStack<>();
    private final SimpleStack<T> output = new SimpleStack<>();
    private int countIn = 0;
    private int countOut = 0;

    public T poll() {
        if (countIn == 0 && countOut == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        T value = null;
        if ( countOut > 0 ) {
            value = output.pop();
            countOut--;
        }
        if (value == null) {
            while (countIn > 1) {
                value = input.pop();
                output.push(value);
                countIn--;
                countOut++;
            }
            value = input.pop();
            countIn--;
        }
        return value;
    }

    public void push(T value) {
        input.push(value);
        countIn++;
    }
}
