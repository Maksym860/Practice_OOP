import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Історія виконаних команд для підтримки undo.
 */
public class CommandHistory {
    private final Deque<Command> stack = new ArrayDeque<>();

    public void push(Command command) {
        stack.push(command);
    }

    public boolean canUndo() {
        return !stack.isEmpty();
    }

    public void undoLast() throws Exception {
        if (stack.isEmpty()) return;
        Command last = stack.pop();
        last.undo();
    }
}

