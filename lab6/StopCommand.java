/**
 * Спеціальна команда для коректної зупинки WorkerThread (poison pill).
 */
public class StopCommand implements Command {
    @Override
    public void execute() {
        // no-op
    }

    @Override
    public void undo() {
        // no-op
    }
}

