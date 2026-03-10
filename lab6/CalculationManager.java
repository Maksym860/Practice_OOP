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

    public synchronized void addResult(CalculationData data) {
        results.add(data);
    }

    public synchronized boolean removeResult(CalculationData data) {
        return results.remove(data);
    }

    public synchronized CalculationData removeLastResult() {
        if (results.isEmpty()) return null;
        return results.remove(results.size() - 1);
    }

    public synchronized void clear() {
        results.clear();
    }

    public synchronized int size() {
        return results.size();
    }

    /**
     * Незмінюване представлення поточної колекції (може бути небезпечним для паралельної ітерації,
     * якщо інші потоки модифікують колекцію). Для паралельної обробки використовуйте {@link #snapshot()}.
     */
    public synchronized List<CalculationData> getResultsView() {
        return Collections.unmodifiableList(results);
    }

    /**
     * Потокобезпечний знімок колекції для паралельної обробки (без ризику ConcurrentModificationException).
     */
    public synchronized List<CalculationData> snapshot() {
        return new ArrayList<>(results);
    }

    public void displayResults(ResultDisplay display) {
        for (CalculationData data : snapshot()) {
            display.display(data);
        }
    }

    public synchronized void save(String filename) throws Exception {

        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(filename));

        out.writeObject(results);
        out.close();
    }

    @SuppressWarnings("unchecked")
    public synchronized void load(String filename) throws Exception {

        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(filename));

        results = (ArrayList<CalculationData>) in.readObject();
        in.close();
    }
}