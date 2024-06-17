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
        if (countOut == 0) {
            while (countIn > 0) {
                output.push(input.pop());
                countIn--;
                countOut++;
            }
        }
        countOut--;
        return output.pop();
    }

    public void push(T value) {
        input.push(value);
        countIn++;
    }
}
