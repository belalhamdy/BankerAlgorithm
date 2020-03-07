import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class InputHandler {
    private int[] available; //(available resources)
    private int[][] maximum, allocation, need; //(maximum demand)/(allocated resources),(need) for each process

    private InputStream inStream;
    private PrintStream out;
    private Scanner in;

    public InputHandler(InputStream inStream, PrintStream outputStream) {
        this.inStream = inStream;
        this.out = outputStream;
        in = new Scanner(inStream);

    }
    private int nProcesses = 0;
    private int nResources = 0;
    public void fetchInputFromUser() throws Exception{
        out.println("Enter number of processes: ");
        nProcesses = in.nextInt();
        out.println("Enter number of resources: ");
        nResources = in.nextInt();

        available = new int[nResources];
        maximum = new int[nProcesses][nResources];
        allocation = new int[nProcesses][nResources];
        need = new int[nProcesses][nResources];

        out.println("Enter number of available units per resource: ");
        for (int i = 0; i < nResources; i++) {
            available[i] = in.nextInt();
        }
        for (int i = 0; i < nProcesses; ++i) {
            out.println("For process " + (i+1) +  ", enter max units per resource: ");
            for (int j = 0; j < nResources; j++) {
                maximum[i][j] = in.nextInt();
            }
        }

        for (int i = 0; i < nProcesses; ++i) {
            out.println("For process " + (i+1) +  ", enter allocated units per resource: ");
            for (int j = 0; j < nResources; j++) {
                allocation[i][j] = in.nextInt();
            }
        }


        for (int i = 0; i < nProcesses; ++i) {
            for (int j = 0; j < nResources; j++) {
                need[i][j] = maximum[i][j] - allocation[i][j];
            }
        }

        checkArrays();
    }

    public int[] getAvailable() {
        return available;
    }

    public int[][] getMaximum() {
        return maximum;
    }

    public int[][] getAllocation() {
        return allocation;
    }

    public int[][] getNeed() {
        return need;
    }

    private void checkArrays() throws Exception {
        if (available == null || allocation == null || maximum == null)
            throw new Exception("Error: Array/s Cannot be null.");

        if (this.available.length == 0 || this.allocation.length == 0)
            throw new Exception("Error: Arrays cannot be empty.");
        if (this.available.length != this.allocation[0].length)
            throw new Exception("Error: Allocation array doesn't equal to other arrays.");

        int alloc, max, needing;
        for (int i = 0; i < nProcesses; ++i) {
            for (int j = 0; j < nResources; ++j) {
                // for out of index handling
                try {
                    alloc = this.allocation[i][j];
                    max = this.maximum[i][j];
                    needing = this.need[i][j];

                } catch (Exception e) {
                    throw new Exception("Error: Arrays must be the same size.");
                }
                if (alloc < 0 || max < 0) throw new Exception("Error: Positive values only.");
                if (needing < 0) throw  new Exception("Process allocation exceeds maximum allowed.");
            }
        }
    }

    int[] lastRequest;
    int lastRequestIdx;
    public boolean fetchRequest(){
        String input = in.next();
        if (input.toLowerCase().equals("rq")){
            lastRequestIdx = in.nextInt();
            lastRequest = new int[nResources];
            for (int i = 0; i < nResources; i++) {
                lastRequest[i] = in.nextInt();
            }
            return true;
        }else
        return false;
    }

}
