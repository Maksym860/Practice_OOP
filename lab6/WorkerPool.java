import java.util.ArrayList;
import java.util.List;

/**
 * Невеликий пул WorkerThread.
 */
public class WorkerPool {
    private final TaskQueue queue;
    private final TaskResults results;
    private final List<WorkerThread> workers = new ArrayList<>();

    public WorkerPool(int workerCount, TaskQueue queue, TaskResults results) {
        this.queue = queue;
        this.results = results;
        for (int i = 1; i <= workerCount; i++) {
            workers.add(new WorkerThread("worker-" + i, queue, results));
        }
    }

    public void startAll() {
        for (WorkerThread w : workers) {
            w.start();
        }
        results.addLog("WorkerPool: started " + workers.size() + " workers");
    }

    public void stopAndJoin() {
        for (int i = 0; i < workers.size(); i++) {
            queue.enqueue(new StopCommand());
        }
        for (WorkerThread w : workers) {
            try {
                w.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                results.addLog("WorkerPool: join interrupted");
                break;
            }
        }
        results.addLog("WorkerPool: stopped");
    }
}

