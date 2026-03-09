/**
 * Клас для підрахунку повних тетрад
 */
public class TetradCalculator {

    private CalculationData data;

    public TetradCalculator(CalculationData data) {
        this.data = data;
    }

    public void calculate() {

        String binary = Integer.toBinaryString(data.getDecimalNumber());
        data.setBinaryRepresentation(binary);

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