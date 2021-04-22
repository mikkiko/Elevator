package com.mikkiko.elevator.utils;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomHelperTest {

    @Test
    public void shouldReturnRandomNumTo10() {
        Set<Integer> resultSet = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            resultSet.add(RandomHelper.getInt(10));
        }
        assertEquals(resultSet.size(), 10);
    }

    @Test
    public void shouldReturnRandomNumFrom5AndTo20() {
        Set<Integer> resultSet = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            resultSet.add(RandomHelper.getInt(5, 20));
        }
        Set<Integer> expectedSet = IntStream.rangeClosed(5,20).boxed().collect(Collectors.toSet());
        assertEquals(resultSet, expectedSet);
    }
}