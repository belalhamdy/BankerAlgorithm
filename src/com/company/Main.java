package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
/*
    int[][] max2 = {{7,5,3},{3,2,2},{9,0,2},{2,2,2},{4,3,3}};
	int[][] alloc2 = {{0,1,0},{2,0,0},{3,0,2},{2,1,1},{0,0,2}};
    int[] avail2 =  {3,3,2};
*/
        try {
            InputHandler ih = new InputHandler(System.in,System.out);
            ih.loadSampleInput1();
            //ih.fetchInputFromUser();

            Banker test = new Banker(ih.getAvailable(), ih.getAllocation(), ih.getMaximum());
            System.out.println(Arrays.toString(test.getSafeSequence()));
            System.out.println(Arrays.toString(test.request(1, new int[]{1, 0, 2})));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
/*
        try {
            Banker test = new Banker(avail2,alloc2,max2);
            System.out.println(Arrays.toString(test.getSafeSequence()));
            System.out.println(Arrays.toString(test.request(1,new int[]{1,0,2})));
            System.out.println(Arrays.toString(test.request(4,new int[]{3,3,0})));
            System.out.println(Arrays.toString(test.request(0,new int[]{0,2,0})));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
*/

    }
}
