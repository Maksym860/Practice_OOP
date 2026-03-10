import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Сховище результатів виконання фонових завдань.
 */
public class TaskResults {
    private final List<String> log = new ArrayList<>();
    private ParallelStatsResult lastStats;
    private List<CalculationData> lastFiltered = Collections.emptyList();

    public synchronized void addLog(String message) {
        log.add(message);
    }

    public synchronized List<String> getLogSnapshot() {
        return new ArrayList<>(log);
    }

    public synchronized void setLastStats(ParallelStatsResult stats) {
        this.lastStats = stats;
    }

    public synchronized ParallelStatsResult getLastStats() {
        return lastStats;
    }

    public synchronized void setLastFiltered(List<CalculationData> filtered) {
        this.lastFiltered = (filtered == null) ? Collections.emptyList() : new ArrayList<>(filtered);
    }

    public synchronized List<CalculationData> getLastFilteredSnapshot() {
        return new ArrayList<>(lastFiltered);
    }
}

