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

    public static String get2DArrayAsString(String whatToPrint, int[][] arr){
        StringBuilder sb = new StringBuilder();
        sb.append(whatToPrint).append(": ");
        for (int i = 0; i < arr.length; ++i) {
            sb.append("Process ").append(i + 1).append(" ").append(whatToPrint);
            for(int j = 0; j < arr[i].length; ++j){
                sb.append(arr[i][j]);
                sb.append(" ");
            }
        }
        sb.append("\n");
        return sb.toString();
    }

}
