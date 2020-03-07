import java.util.Arrays;
public class Main {

    public static void main(String[] args) {
        //ih.fetchInputFromUser();
        try {
            InputHandler ih = new InputHandler(System.in, System.out);
            ih.fetchInputFromUser();

            Banker test = new Banker(ih.getAvailable(), ih.getAllocation(), ih.getMaximum());
            int[] safeSequence = test.getSafeSequence();
            System.out.println(Utilities.arrayToString(safeSequence,"Suggested Safe Sequence"));
            System.out.println("Enter RQ for a request, or EXIT to exit.");
            while (true) {
                if (!ih.fetchRequest()) return;
                int[] requestSequence = test.request(ih.lastRequestIdx, ih.lastRequest);
                if (requestSequence == null) System.out.println("No Sequence.");
                else System.out.println(Utilities.arrayToString(requestSequence,"Suggested Safe Sequence After Request"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }
}
