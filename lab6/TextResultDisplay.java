/**
 * Виведення результатів у текстовому вигляді
 */
public class TextResultDisplay implements ResultDisplay {

    @Override
    public void display(CalculationData data) {

        System.out.println("Десяткове число: " + data.getDecimalNumber());
        System.out.println("Двійкове число: " + data.getBinaryRepresentation());
        System.out.println("Кількість повних тетрад: " + data.getFullTetrads());

    }
}