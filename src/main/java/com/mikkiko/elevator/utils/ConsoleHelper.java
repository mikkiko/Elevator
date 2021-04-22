package com.mikkiko.elevator.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Console helper.
 */
public class ConsoleHelper {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static int readInt(String message, int min, int max) {
        print(message);
        try {
            int num = Integer.parseInt(reader.readLine());
            if (num < min || max < num)
                throw new NumberFormatException();
            return num;
        } catch (IOException | NumberFormatException e) {
            System.out.print("Incorrect input. Try again.");
            return readInt("", min, max);
        }
    }

    public static void print(String str) {
        System.out.println(str);
    }
}
