package com.mikkiko.elevator.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return "Error";
        }
    }

    public static int readInt() {
        try {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return 0;
        }
    }

    public static void print(String str) {
        System.out.println(str);
    }
}
