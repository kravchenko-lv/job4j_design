package ru.job4j.iterator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int len = nodes.size();
        int index = 0;
        while (source.hasNext()) {
            Integer next = source.next();
            if (index >= len) {
                index = 0;
            }
            nodes.get(index).add(next);
            index++;
        }
    }
}