/**
 * Команда додавання результату до менеджера з підтримкою undo.
 */
public class AddResultCommand implements Command {
    private final CalculationManager manager;
    private final CalculationData data;
    private boolean executed = false;

    public AddResultCommand(CalculationManager manager, CalculationData data) {
        this.manager = manager;
        this.data = data;
    }

    @Override
    public void execute() {
        manager.addResult(data);
        executed = true;
    }

    @Override
    public void undo() {
        if (!executed) return;
        manager.removeResult(data);
        executed = false;
    }
}

