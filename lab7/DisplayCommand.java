/**
 * Команда відображення результатів (без зміни стану, undo = no-op).
 */
public class DisplayCommand implements Command {
    private final CalculationManager manager;
    private final ResultDisplay display;

    public DisplayCommand(CalculationManager manager, ResultDisplay display) {
        this.manager = manager;
        this.display = display;
    }

    @Override
    public void execute() {
        manager.displayResults(display);
    }

    @Override
    public void undo() {
        // no-op
    }
}

