/**
 * Клас для підрахунку повних тетрад.
 */
public class TetradCalculator {

    private CalculationData data;

    /**
     * Конструктор із передачею даних.
     *
     * @param data об'єкт даних обчислення
     */
    public TetradCalculator(CalculationData data) {
        this.data = data;
    }

    /**
     * Обчислення кількості повних тетрад для вже заданого десяткового числа.
     */
    public void calculate() {

        String binary = Integer.toBinaryString(data.getDecimalNumber());
        performCalculation(binary);
    }

    /**
     * Перевантажений метод обчислення для нового значення десяткового числа.
     *
     * @param decimalNumber нове десяткове число
     */
    public void calculate(int decimalNumber) {

        String binary = Integer.toBinaryString(decimalNumber);
        data.setBinaryRepresentation(binary);
        performCalculation(binary);
    }

    /**
     * Внутрішній допоміжний метод для підрахунку тетрад.
     *
     * @param binary двійкове подання числа
     */
    private void performCalculation(String binary) {

        int count = 0;

        while (binary.length() % 4 != 0) {
            binary = "0" + binary;
        }

        for (int i = 0; i < binary.length(); i += 4) {
            if (binary.substring(i, i + 4).equals("1111")) {
                count++;
            }
        }

        data.setFullTetrads(count);
    }
}
