package com.company;

import java.util.Arrays;

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
/*
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
  */
    public static String arrayToString(int[] array, String name) {
        if (array == null || array.length == 0 ) return "";
        return name + ": " + Arrays.toString(array);
    }
    public static String arrayToString(boolean[] array, String name) {
        if (array == null || array.length == 0 ) return "";
        return name + ": " + Arrays.toString(array);
    }
    public static String arrayToString(int[][] array, String name) {
        if (array == null || array.length == 0 ) return "";
        StringBuilder ret = new StringBuilder("{\n");
        for (int[] ints : array) ret.append(Arrays.toString(ints)).append("\n");
        return name + ": " + ret + "}";
    }

}
