import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Клас керування колекцією результатів
 */
public class CalculationManager {

    private static volatile CalculationManager instance;

    private ArrayList<CalculationData> results = new ArrayList<>();

    private CalculationManager() {
    }

    /**
     * Повертає єдиний екземпляр менеджера (Singleton).
     */
    public static CalculationManager getInstance() {
        if (instance == null) {
            synchronized (CalculationManager.class) {
                if (instance == null) {
                    instance = new CalculationManager();
                }
            }
        }
        return instance;
    }

    public void addResult(CalculationData data) {
        results.add(data);
    }

    public boolean removeResult(CalculationData data) {
        return results.remove(data);
    }

    public CalculationData removeLastResult() {
        if (results.isEmpty()) return null;
        return results.remove(results.size() - 1);
    }

    public void clear() {
        results.clear();
    }

    public int size() {
        return results.size();
    }

    public List<CalculationData> getResultsView() {
        return Collections.unmodifiableList(results);
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