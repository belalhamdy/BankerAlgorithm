package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //ih.fetchInputFromUser();

        try {
            InputHandler ih = new InputHandler(System.in, System.out);
            ih.loadSampleInput1();

            Banker test = new Banker(ih.getAvailable(), ih.getAllocation(), ih.getMaximum());
            System.out.println(Arrays.toString(test.getSafeSequence()));
            System.out.println(Arrays.toString(test.request(1, new int[]{1, 0, 2})));

            ih.loadSampleInput2();
            test = new Banker(ih.getAvailable(), ih.getAllocation(), ih.getMaximum());
            System.out.println(Arrays.toString(test.getSafeSequence()));
            System.out.println(Arrays.toString(test.request(1, new int[]{1, 0, 2})));
            System.out.println(Arrays.toString(test.request(4, new int[]{3, 3, 0})));
            System.out.println(Arrays.toString(test.request(0, new int[]{0, 2, 0})));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }
}
