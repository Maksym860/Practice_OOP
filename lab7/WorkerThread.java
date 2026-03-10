/**
 * Робочий потік, який обробляє команди з черги (Worker Thread pattern).
 */
public class WorkerThread extends Thread {
    private final TaskQueue queue;
    private final TaskResults results;

    public WorkerThread(String name, TaskQueue queue, TaskResults results) {
        super(name);
        this.queue = queue;
        this.results = results;
    }

    @Override
    public void run() {
        while (true) {
            Command task;
            try {
                task = queue.take();
            } catch (InterruptedException e) {
                results.addLog(getName() + ": interrupted");
                return;
            }

            if (task instanceof StopCommand) {
                results.addLog(getName() + ": stopped");
                return;
            }

            try {
                results.addLog(getName() + ": start " + task.getClass().getSimpleName());
                task.execute();
                results.addLog(getName() + ": done " + task.getClass().getSimpleName());
            } catch (Exception ex) {
                results.addLog(getName() + ": error " + ex.getClass().getSimpleName() + " - " + ex.getMessage());
            }
        }
    }
}

