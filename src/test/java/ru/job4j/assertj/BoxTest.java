package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void vertex0NumberOfVerticeto0() {
        Box box = new Box(0, 10);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(0);
    }

    @Test
    void vertex8NumberOfVerticeto8() {
        Box box = new Box(8, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(8);
    }

    @Test
    void isExistSpereThenTrue() {
        Box box = new Box(0, 10);
        boolean ex = box.isExist();
        assertThat(ex).isTrue();
    }

    @Test
    void isExist6ThenFalse() {
        Box box = new Box(6, 10);
        boolean ex = box.isExist();
        assertThat(ex).isFalse();
    }

    @Test
    void vertex8Edge5To150() {
        Box box = new Box(8, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(150);
    }

    @Test
    void vertex0Edge10To() {
        Box box = new Box(0, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(1256.63d, withPrecision(0.06d))
                .isCloseTo(1256.63d, withPrecision(0.01d))
                .isCloseTo(1256.63d, Percentage.withPercentage(1.0d))
                .isGreaterThan(1256.63d)
                .isLessThan(1256.64d);
    }

}