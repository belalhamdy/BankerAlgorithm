package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
	int[][] max = {{7,5,3},{3,2,2},{9,0,2}};
	int[][] alloc = {{0,1,0},{2,0,0},{3,0,2}};
    int[] avail =  {5,4,5};

	int[][] need = {{7,4,3},{1,2,2},{6,0,0}};

        try {
            Banker test = new Banker(avail,alloc,max);
            System.out.println(Arrays.toString(test.getSafeSequence()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
