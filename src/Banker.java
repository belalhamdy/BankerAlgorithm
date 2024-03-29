import java.util.Arrays;

public class Banker {
    static int bankerId = 0;

    private int nProcess, nResource;
    private int[] available; //(available resources)
    private int[][] maximum, allocation, need; //(maximum demand)/(allocated resources),(need) for each process

    private boolean[] finished;
    private int[] sequence; // will carry a valid sequence
    private int[] work;

    private int[] copyAvailable; //(available resources)
    private int[][] copyMaximum, copyAllocation; //(maximum demand)/(allocated resources),(need) for each process

    private int[] copySequence; // will carry a valid sequence


    Banker(int[] available, int[][] allocation, int[][] maximum,int[][] need) throws Exception {
        this.available = available;
        this.allocation = allocation;
        this.maximum = maximum;
        this.need = need;

        ++bankerId;

        init();
    }

    private void init() throws Exception {
        logMessage("Initializing Data.", false);
        nProcess = maximum.length;
        nResource = available.length;
    }

    private void makeCopy() throws Exception {
        logMessage("Making backups for arrays.", false);
        copyAllocation = Utilities.makeCopy(allocation);
        copyMaximum = Utilities.makeCopy(maximum);
        copySequence = Utilities.makeCopy(sequence);
        copyAvailable = Utilities.makeCopy(available);
    }

    private void retrieveCopy() throws Exception {
        logMessage("Retrieving backups for arrays.", false);
        allocation = Utilities.makeCopy(copyAllocation);
        maximum = Utilities.makeCopy(copyMaximum);
        sequence = Utilities.makeCopy(copySequence);
        available = Utilities.makeCopy(copyAvailable);
    }


    private void logMessage(String message, boolean insertDataStructures) throws Exception {
        String identifierMessage = "Banker Simulation (" + bankerId + ")";
        Logger.insertLog(identifierMessage + ": " + message);
        if (insertDataStructures)
            Logger.insertLog(logArrays() + "\n----------------------------"); // inserts the data structures to log
    }

    private String logArrays() {
        return Utilities.arrayToString(available, "Current Available") + "\n" +
                Utilities.arrayToString(maximum, "Current Maximum") + "\n" +
                Utilities.arrayToString(allocation, "Current Allocation") + "\n" +
                Utilities.arrayToString(need, "Current Need") + "\n" +
                Utilities.arrayToString(finished, "Current Finished") + "\n" +
                Utilities.arrayToString(work, "Current Work Array") + "\n" +
                Utilities.arrayToString(sequence, "Current Sequence");
    }

    public int[] getSafeSequence() throws Exception {
        finished = new boolean[nProcess];
        sequence = new int[nProcess];

        int c = 0, nFinished = 0;
        boolean flag = true;

        work = Arrays.copyOf(available, available.length);
        logMessage("Trying to get a safe sequence for these values", true);
        while (nFinished < nProcess && flag) {
            flag = false;
            for (int i = 0; i < nProcess; ++i) {
                if (!finished[i]) {

                    for (int j = 0; j <= nResource; ++j) {

                        if (j == nResource) { // satisfied all resources

                            sequence[c++] = i;
                            flag = true;
                          
                            ++nFinished;
                            finished[i] = true;

                            //update available
                            for (int k = 0; k < nResource; ++k) {
                                work[k] += allocation[i][k];
                            }
                            logMessage("Satisfied process #" + (i+1), true);
                        } else if (need[i][j] > work[j]) // cannot satisfy this resource
                            break;
                    }
                }
            }
        }
        if (nFinished != nProcess) {
            logMessage("Failed to find a safe sequence.", false);
            sequence = null; // if not all processes in the sequence return null
        } else logMessage("Found a safe sequence!", false);
        return sequence;
    }


    // to handle a request:
    // 1- request of process <= need of process
    // 2- request of process <= available
    // returns a new sequence
    public int[] request(int processId, int[] request) throws Exception {
        if (processId > nProcess || processId < 0) throw new Exception("Error: Process ID out of boundary.");

        boolean canBeHandled = false;
        logMessage("Trying to handle a request for process " + processId + " .", false);
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

        int[] ret = null;
        if (canBeHandled) {
            makeCopy(); // save old state
            logMessage("Request for process " + processId + " can be handled.", false);
            logMessage("Updating values for the new request. {old values}", true);
            for (int i = 0; i < nResource; ++i) {
                int currentRequest; // for out of index handling
                try {
                    currentRequest = request[i];
                } catch (Exception e) {
                    currentRequest = 0;
                }
                available[i] -= currentRequest;
                allocation[processId][i] += currentRequest;
                maximum[processId][i] += currentRequest;
                need[processId][i] -= currentRequest;
            }
            logMessage("Successfully updated values for the new request. {new values}", true);
            ret = getSafeSequence();
        }
        if (ret == null) {
            logMessage("Failed to handle the request for process " + processId + " .", false);
            retrieveCopy(); // retrieve the old state, if success save it
        } else logMessage("Request for " + processId + " will be handled, saving the new values.", true);

        return ret;

    }

}
