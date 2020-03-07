import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //ih.fetchInputFromUser();
        try {
            InputHandler ih = new InputHandler(System.in, System.out);
            ih.fetchInputFromUser();

            Banker test = new Banker(ih.getAvailable(), ih.getAllocation(), ih.getMaximum());
            System.out.println(Arrays.toString(test.getSafeSequence()));
            System.out.println("Enter RQ for a request, or EXIT to exit.");
            while (true) {
                if (!ih.fetchRequest()) return;
                System.out.println(Arrays.toString(test.request(ih.lastRequestIdx, ih.lastRequest)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }
}
