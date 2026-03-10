import java.util.ArrayList;
import java.util.List;

/**
 * Команда завантаження колекції результатів з файла з підтримкою undo.
 */
public class LoadCommand implements Command {
    private final CalculationManager manager;
    private final String filename;

    private boolean executed = false;
    private List<CalculationData> backup;

    public LoadCommand(CalculationManager manager, String filename) {
        this.manager = manager;
        this.filename = filename;
    }

    @Override
    public void execute() throws Exception {
        backup = new ArrayList<>(manager.getResultsView());
        manager.load(filename);
        executed = true;
    }

    @Override
    public void undo() {
        if (!executed) return;
        manager.clear();
        for (CalculationData d : backup) {
            manager.addResult(d);
        }
        executed = false;
    }
}

