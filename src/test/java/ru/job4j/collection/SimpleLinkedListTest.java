package ru.job4j.collection;

import ru.job4j.collection.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

class SimpleLinkedListTest {

    private SimpleLinked<Integer> list;

    @BeforeEach
    public void initData() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
    }

    @Test
    void checkIteratorSimple() {
        assertThat(list).hasSize(2);
        list.add(3);
        list.add(4);
        assertThat(list).hasSize(4);
    }

    @Test
    void whenAddAndGet() {
        list.add(3);
        list.add(4);
        assertThat(list.get(0)).isEqualTo(1);
        assertThat(list.get(1)).isEqualTo(2);
        assertThat(list.get(2)).isEqualTo(3);
        assertThat(list.get(3)).isEqualTo(4);
    }

    @Test
    void whenGetFromOutOfBoundThenExceptionThrown() {
        assertThatThrownBy(() -> list.get(2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddIterHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOne() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
    }

    @Test
    void whenEmptyIterHashNextFalse() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    void whenAddIterMultiHasNextTrue() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenAddIterNextOneNextTwo() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
    }

    @Test
    void whenGetIteratorTwiceThenEveryFromBegin() {
        Iterator<Integer> first = list.iterator();
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(1);
        assertThat(first.hasNext()).isTrue();
        assertThat(first.next()).isEqualTo(2);
        assertThat(first.hasNext()).isFalse();
        Iterator<Integer> second = list.iterator();
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(1);
        assertThat(second.hasNext()).isTrue();
        assertThat(second.next()).isEqualTo(2);
        assertThat(second.hasNext()).isFalse();
    }
    @Test
    void whenAddAfterGetIteratorHasNextThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        list.add(3);
        assertThatThrownBy(iterator::hasNext)
                .isInstanceOf(ConcurrentModificationException.class);
    }

    @Test
    void whenRemoveAfterGetIteratorHasNextThenMustBeException() {
        Iterator<Integer> iterator = list.iterator();
        assertThat(iterator.hasNext()).isTrue();
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(iterator.hasNext()).isTrue();
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.get(1);
        assertThat(iterator.hasNext()).isTrue();
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(iterator.hasNext()).isTrue();
    }

    @Test
    void whenSetAfterGetIteratorThenMustBeOk() {
        Iterator<Integer> iterator = list.iterator();
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(iterator.next()).isEqualTo(1);
        assertThat(iterator.next()).isEqualTo(2);
        assertThat(iterator.next()).isEqualTo(3);
    }
    @Test
    void whenGetByIncorrectIndexThenGetException() {
        assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAndGetByCorrectIndex() {
        list.add(3);
        assertThat(list.get(2)).isEqualTo(3);
    }

    @Test
    void whenAndAndGetByIncorrectIndexThenGetException() {
        SimpleLinkedList<Integer> list = new SimpleLinkedList<>();
        list.add(5);
        assertThatThrownBy(() -> list.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }
    @Test
    void whenGetByNegateIndexThenGetException() {
        assertThatThrownBy(() -> list.get(-2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }
    @Test
    void whenAddNullThenMustBeSameBehavior() {
        list = new SimpleLinkedList<>();
        list.add(null);
        list.add(null);
        assertThat(list).hasSize(2);
        assertThat(list.get(0)).isNull();
        assertThat(list.get(1)).isNull();
    }
}