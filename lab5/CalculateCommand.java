/**
 * Команда обчислення кількості тетрад для заданих даних.
 * Undo не змінює колекцію результатів, лише повертає об'єкт даних у початковий стан.
 */
public class CalculateCommand implements Command {
    private final CalculationData data;
    private final TetradCalculator calculator;

    private boolean executed = false;
    private String prevBinary;
    private int prevFullTetrads;

    public CalculateCommand(CalculationData data) {
        this.data = data;
        this.calculator = new TetradCalculator(data);
    }

    @Override
    public void execute() {
        prevBinary = data.getBinaryRepresentation();
        prevFullTetrads = data.getFullTetrads();

        calculator.calculate();
        executed = true;
    }

    @Override
    public void undo() {
        if (!executed) return;
        data.setBinaryRepresentation(prevBinary);
        data.setFullTetrads(prevFullTetrads);
        executed = false;
    }
}

