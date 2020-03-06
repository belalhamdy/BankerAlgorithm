package com.company;

public class Utilities {
    public static int[][] makeCopy(int[][] array) {
        if (array == null) return null;

        final int[][] copy = new int[array.length][];

        for (int i = 0; i < array.length; i++)
            copy[i] = makeCopy(array[i]);

        return copy;

    }

    public static int[] makeCopy(int[] array) {
        if (array == null) return null;
        return array.clone();
    }

}
