package com.mikkiko.elevator.utils;

import java.util.Random;

public class RandomHelper {

    private static final Random random = new Random();

    public static int getInt(int from, int to) {
        if(from < to) {
            return random.nextInt(to - from) + from;
        }
        throw new IllegalArgumentException();
    }

    public static int getInt(int to) {
            return random.nextInt(to);
    }
}
