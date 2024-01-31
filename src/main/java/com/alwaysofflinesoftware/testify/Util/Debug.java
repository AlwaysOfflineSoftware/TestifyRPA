package com.alwaysofflinesoftware.testify.Util;

public class Debug {
    public static <T> void printi(T item) {
        System.out.println(item);
    }

    public static <T> void printi(T[] inputArray) {
        for (T element : inputArray) {
            System.out.printf("%s |", element);
        }
        System.out.println();
    }

    public static <T> void printi(T item, boolean newLine) {
        if (newLine) {
            System.out.println(item);
        } else {
            System.out.print(item);
        }
    }

    public static <T> void printi(T[] inputArray, boolean newLine) {
        if (newLine) {
            for (T element : inputArray) {
                System.out.printf("%s |", element);
            }
            System.out.println();
        } else {
            for (T element : inputArray) {
                System.out.printf("%s |", element);
            }
        }
    }
}
