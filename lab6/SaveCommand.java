/**
 * Команда збереження результатів у файл.
 * Undo не підтримується (no-op), бо не змінює стан колекції у пам'яті.
 */
public class SaveCommand implements Command {
    private final CalculationManager manager;
    private final String filename;

    public SaveCommand(CalculationManager manager, String filename) {
        this.manager = manager;
        this.filename = filename;
    }

    @Override
    public void execute() throws Exception {
        manager.save(filename);
    }

    @Override
    public void undo() {
        // no-op
    }
}

