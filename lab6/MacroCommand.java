import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Макрокоманда: об'єднує декілька команд в одну операцію.
 */
public class MacroCommand implements Command {
    private final List<Command> commands;

    public MacroCommand(List<Command> commands) {
        this.commands = new ArrayList<>(commands);
    }

    public List<Command> getCommandsView() {
        return Collections.unmodifiableList(commands);
    }

    @Override
    public void execute() throws Exception {
        for (Command c : commands) {
            c.execute();
        }
    }

    @Override
    public void undo() throws Exception {
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}

