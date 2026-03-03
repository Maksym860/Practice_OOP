
public class Test { // клас для тестування коректності обчислень.

    public static void main(String[] args) {

        TetradData data = new TetradData(255); // 11111111
        TetradCalculator calculator = new TetradCalculator(data);

        calculator.calculate();

        if (data.getFullTetrads() == 2) {
            System.out.println("Тест пройдено успішно.");
        } else {
            System.out.println("Помилка у розрахунках.");
        }
    }
}
