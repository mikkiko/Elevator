package com.mikkiko.elevator.utils;

import java.util.Random;

/**
 * Random number generator.
 */
public class RandomHelper {

    private static final Random random = new Random();

    public static int getInt(int from, int to) {
        if(from < to) {
            to = to - from + 1;
            return random.nextInt(to) + from;
        }
        throw new IllegalArgumentException();
    }

    public static int getInt(int to) {
            return random.nextInt(to) + 1;
    }
}
