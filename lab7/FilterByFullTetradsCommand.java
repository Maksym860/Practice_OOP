import java.util.List;

/**
 * Фонова команда відбору елементів колекції за критерієм.
 */
public class FilterByFullTetradsCommand implements Command {
    private final CalculationManager manager;
    private final ParallelStatisticsService service;
    private final TaskResults results;
    private final int minFullTetrads;

    public FilterByFullTetradsCommand(
            CalculationManager manager,
            ParallelStatisticsService service,
            TaskResults results,
            int minFullTetrads
    ) {
        this.manager = manager;
        this.service = service;
        this.results = results;
        this.minFullTetrads = minFullTetrads;
    }

    @Override
    public void execute() {
        List<CalculationData> filtered = service.filterByFullTetradsAtLeast(manager.snapshot(), minFullTetrads);
        results.setLastFiltered(filtered);
        results.addLog("Filter: fullTetrads>=" + minFullTetrads + ", matched=" + filtered.size());
    }

    @Override
    public void undo() {
        // Не змінює стан колекції, undo не потрібен
    }
}

