/**
 * Клас тестування основної функціональності.
 */
public class Test {

    public static void main(String[] args) throws Exception {

        System.out.println("=== Тест обчислення тетрад ===");

        CalculationData data1 = new CalculationData(255);
        TetradCalculator calc1 = new TetradCalculator(data1);
        calc1.calculate();
        System.out.println("Вхід: 255, очікувано тетрад: 2, фактично: " + data1.getFullTetrads());

        CalculationData data2 = new CalculationData(0);
        TetradCalculator calc2 = new TetradCalculator(data2);
        // демонстрація перевантаженого методу calculate(int)
        calc2.calculate(15);
        System.out.println("Вхід: 15, очікувано тетрад: 1, фактично: " + data2.getFullTetrads());

        CalculationManager manager = new CalculationManager();
        manager.addResult(data1);
        manager.addResult(data2);

        System.out.println("\n=== Відображення (поліморфізм) ===");

        DisplayFactory textFactory = new TextDisplayFactory();
        ResultDisplay textDisplay = textFactory.createDisplay();
        System.out.println("\nТекстове відображення:");
        manager.displayResults(textDisplay);

        DisplayFactory tableFactory = new TableDisplayFactory(20, '-');
        ResultDisplay tableDisplay = tableFactory.createDisplay();
        System.out.println("\nТабличне відображення:");
        manager.displayResults(tableDisplay);
    }
}
