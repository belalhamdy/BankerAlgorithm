package com.company;

public class Utilities {
    public static int[][] makeCopy(int[][] array){
        if (array != null) {
            final int[][] copy = new int[array.length][];

            for (int i = 0; i < array.length; i++) {
                final int[] row = array[i];

                copy[i] = new int[row.length];
                System.arraycopy(row, 0, copy[i], 0, row.length);
            }

            return copy;
        }

        return null;

    }
    public static int[] makeCopy(int[] array){
        if (array != null) return array.clone();
        return null;
    }

}
