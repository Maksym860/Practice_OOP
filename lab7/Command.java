/**
 * Інтерфейс команди (Command) з підтримкою скасування (undo).
 */
public interface Command {
    void execute() throws Exception;

    void undo() throws Exception;
}

