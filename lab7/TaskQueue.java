import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Потокобезпечна черга завдань для Worker Thread.
 */
public class TaskQueue {
    private final BlockingQueue<Command> queue = new LinkedBlockingQueue<>();

    public void enqueue(Command task) {
        queue.add(task);
    }

    public Command take() throws InterruptedException {
        return queue.take();
    }
}

