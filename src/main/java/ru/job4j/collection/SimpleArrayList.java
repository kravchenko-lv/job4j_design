package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size = 0;
    private int modCount;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
        size = 0;
        modCount = 0;
    }

    @Override
    public void add(T value) {
        ensureCapacity();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedValue = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        container[--size] = null;
        modCount++;
        return removedValue;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (container.length == 0) {
            container = Arrays.copyOf(container, 1);
        } else if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[cursor++];
            }
        };
    }
}
