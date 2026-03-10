/**
 * Фонова команда для паралельної статистичної обробки колекції.
 */
public class ComputeStatisticsCommand implements Command {
    private final CalculationManager manager;
    private final ParallelStatisticsService service;
    private final TaskResults results;

    public ComputeStatisticsCommand(CalculationManager manager, ParallelStatisticsService service, TaskResults results) {
        this.manager = manager;
        this.service = service;
        this.results = results;
    }

    @Override
    public void execute() {
        ParallelStatsResult stats = service.calculateStats(manager.snapshot());
        results.setLastStats(stats);
        results.addLog("Stats: count=" + stats.getCount()
                + ", decimal[min=" + stats.getMinDecimal() + ", max=" + stats.getMaxDecimal() + ", avg=" + stats.getAvgDecimal() + "]"
                + ", fullTetrads[min=" + stats.getMinFullTetrads() + ", max=" + stats.getMaxFullTetrads() + ", avg=" + stats.getAvgFullTetrads() + "]");
    }

    @Override
    public void undo() {
        // Для статистики undo не застосовується (не змінює стан колекції)
    }
}

