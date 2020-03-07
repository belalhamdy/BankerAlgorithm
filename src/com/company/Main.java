package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        try {
            InputHandler ih = new InputHandler(System.in,System.out);
            //ih.loadSampleInput();
            ih.fetchInputFromUser();

            Banker test = new Banker(ih.getAvailable(), ih.getAllocation(), ih.getMaximum());
            System.out.println(Arrays.toString(test.getSafeSequence()));
            System.out.println(Arrays.toString(test.request(1, new int[]{1, 0, 2})));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }
}
