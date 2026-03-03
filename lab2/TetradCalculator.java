
// клас для обчислення кількості повних тетрад.Використовує агрегування з класом TetradData.

 
public class TetradCalculator {

    private TetradData data;

    /** 
     * Конструктор
     * @param data об'єкт з даними
     */
    public TetradCalculator(TetradData data) {
        this.data = data;
    }

    /**
     * Виконує обчислення
     */
    public void calculate() {

        String binary = Integer.toBinaryString(data.getDecimalNumber());
        data.setBinaryRepresentation(binary);
    
        int count = 0;
    
        // Робимо довжину кратною 4
        while (binary.length() % 4 != 0) {
            binary = "0" + binary;
        }
    
        // Перевіряємо кожні 4 біти
        for (int i = 0; i < binary.length(); i += 4) {
            if (binary.substring(i, i + 4).equals("1111")) {
                count++;
            }
        }
    
        data.setFullTetrads(count);
    }    
}
