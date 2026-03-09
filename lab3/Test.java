/**
 * Клас тестування
 */
public class Test {

    public static void main(String[] args) throws Exception {

        CalculationManager manager = new CalculationManager();

        manager.load("data.dat");

        DisplayFactory factory = new TextDisplayFactory();
        ResultDisplay display = factory.createDisplay();

        manager.displayResults(display);

    }
}