import java.io.FileInputStream;
import java.io.FileReader;
import java.nio.file.NoSuchFileException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Created by JJ's Lappy on 3/26/2016.
 */
public class TextReader {

    public static ArrayList<Process> parseText(String fileName) {
        ArrayList<Process> processes = new ArrayList<>();
        List<String> unParsedStrings = new ArrayList<>();
        Path path = Paths.get(fileName);
        try {
            unParsedStrings = Files.readAllLines(path);
        }
        catch (NoSuchFileException e) {
            System.out.println("Error. No such file: \"" + fileName + "\".");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for (String unParsedString : unParsedStrings) {
            Scanner scanner = new Scanner(unParsedString.replace(',', ' '));
            int pid, burstTime, arrivalTime, priority;
            pid = scanner.nextInt();
            burstTime = scanner.nextInt();
            arrivalTime = scanner.nextInt();
            priority = scanner.nextInt();
            processes.add(new Process(pid, burstTime, arrivalTime, priority));
        }

        return processes;
    }
}
