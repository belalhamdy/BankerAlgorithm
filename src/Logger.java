import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    static List<String> log;
    static String fileName = "log.txt";
    static PrintWriter printWriter;


    static {
        log = new ArrayList<>();
        try{
            new File(fileName);
            printWriter = new PrintWriter(fileName);
            printWriter.write(""); // clears the file
        }catch (Exception e){
           log = null;
        }
    }
    public static void insertLog(String message) throws Exception {
        if (log == null)  throw new Exception("Error: Unhandled error in log file please contact with developer.");

        log.add(message);

        printWriter.append(message).append("\n");
        printWriter.flush();
    }
}
