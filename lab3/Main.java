/**
 * Демонстраційний клас
 */
public class Main {

    public static void main(String[] args) throws Exception {

        CalculationManager manager = new CalculationManager();

        CalculationData data = new CalculationData(255);

        TetradCalculator calc = new TetradCalculator(data);
        calc.calculate();

        manager.addResult(data);

        DisplayFactory factory = new TextDisplayFactory();
        ResultDisplay display = factory.createDisplay();

        manager.displayResults(display);

        manager.save("data.dat");

        System.out.println("Стан збережено.");

    }
}