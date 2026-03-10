import java.io.*;
import java.util.ArrayList;

/**
 * Клас керування колекцією результатів
 */
public class CalculationManager {

    private ArrayList<CalculationData> results = new ArrayList<>();

    public void addResult(CalculationData data) {
        results.add(data);
    }

    public void displayResults(ResultDisplay display) {

        for (CalculationData data : results) {
            display.display(data);
        }

    }

    public void save(String filename) throws Exception {

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(filename));

        out.writeObject(results);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public void load(String filename) throws Exception {

        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(filename));

        results = (ArrayList<CalculationData>) in.readObject();
        in.close();
    }
}