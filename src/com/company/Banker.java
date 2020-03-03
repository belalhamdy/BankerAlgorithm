package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Banker {
    static int bankerId = 1;

    int nProcess, nResource;
    int[] available; //(available resources)
    int[][] maximum, allocation, need; //(maximum demand)/(allocated resources),(need) for each process

    boolean[] finished;
    int[] sequence; // will carry a valid sequence

    int[] copyAvailable; //(available resources)
    int[][] copyMaximum, copyAllocation; //(maximum demand)/(allocated resources),(need) for each process


    Banker(int[] available, int[][] allocation, int[][] maximum) throws Exception {
        this.available = available;
        this.allocation = allocation;
        this.maximum = maximum;

        ++bankerId;

        checkArrays();
        init();
    }

    private void init() {
        nProcess = maximum.length;
        nResource = available.length;

        finished = new boolean[nProcess];
        sequence = new int[nProcess];
    }

    private void makeCopy() {
        copyAllocation = Arrays.copyOf(allocation, allocation.length);
        copyAvailable = Arrays.copyOf(available, available.length);
        copyMaximum = Arrays.copyOf(maximum, maximum.length);
    }

    private void checkArrays() throws Exception {
        if (available == null || allocation == null || maximum == null)
            throw new Exception("Error: Array/s Cannot be null.");

        if (this.available.length == 0 || this.allocation.length == 0)
            throw new Exception("Error: Arrays cannot be empty.");
        if (this.available.length != this.allocation[0].length)
            throw new Exception("Error: Allocation array doesn't equal to other arrays.");

        int alloc, max;
        for (int i = 0; i < nProcess; ++i) {
            for (int j = 0; j < nResource; ++j) {
                // for out of index handling
                try {
                    alloc = this.allocation[i][j];
                    max = this.maximum[i][j];

                } catch (Exception e) {
                    throw new Exception("Error: Arrays must be the same size.");
                }
                if (alloc < 0 || max < 0) throw new Exception("Error: Positive values only.");
            }
        }
    }

    private void fillNeedArray() {
        for (int i = 0; i < nProcess; ++i)
            for (int j = 0; j < nResource; ++j)
                need[i][j] = maximum[i][j] - allocation[i][j];
    }

    private void logMessage(String message, boolean insertDataStructures) {
        // TODO: go to every part of code and place a log message
        String identifierMessage = "Banker Simulation (" + bankerId + ")";
        Logger.insertLog(identifierMessage + ": " + message);
        if (insertDataStructures) Logger.insertLog(arraysToString()); // inserts the data structures to log
    }

    private String arraysToString() {
        // TODO: Convert the arrays to proper string display (need,allocation,maximum,available)
        return "";
    }

    public void resetData() {
        allocation = copyAllocation;
        available = copyAvailable;
        maximum = copyMaximum;

        init();
    }

    public int[] getSafeSequence() {
        fillNeedArray();

        int c = 0, nFinished = 0;
        boolean flag = true;
        while (nFinished < nProcess && flag) {
            flag = false;
            for (int i = 0; i < nProcess; ++i) {
                if (!finished[i]) {

                    for (int j = 0; j <= nResource; ++j) {

                        if (j == nResource) { // satisfied all resources
                            sequence[c++] = i;
                            flag = true;

                            //update available
                            for (int k = 0; k < nResource; ++k) {
                                available[k] += allocation[i][j];
                            }
                        } else if (need[i][j] > available[j]) // cannot satisfy this resource
                            break;
                    }
                }
            }
        }

        if (nFinished != nProcess) sequence = null; // if not all processes in the sequence return null
        return sequence;
    }


    // to handle a request:
    // 1- request of process <= need of process
    // 2- request of process <= available
    // returns a new sequence
    public int[] request(int processId, int[] request) throws Exception {
        if (processId > nProcess || processId < 0) throw new Exception("Error: Process ID out of boundary.");

        boolean canBeHandled = false;

        for (int i = 0; i <= nResource; ++i) {
            if (i == nResource) {
                canBeHandled = true; // satisfied all needs / available
                break;
            }

            int currentRequest; // for out of index handling
            try {
                currentRequest = request[i];
            } catch (Exception e) {
                currentRequest = 0;
            }
            if (currentRequest > need[processId][i] || currentRequest > available[i]) break;
        }

        if (canBeHandled) {
            for (int i = 0; i < nResource; ++i) {
                int currentRequest; // for out of index handling
                try {
                    currentRequest = request[i];
                } catch (Exception e) {
                    currentRequest = 0;
                }
                available[i] -= currentRequest;
                allocation[processId][i] += currentRequest;
                need[processId][i] -= currentRequest;
            }
        }

        int[] ret = null;
        if (canBeHandled) ret = getSafeSequence();

        return ret;

    }

}
