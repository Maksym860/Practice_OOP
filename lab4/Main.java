import java.util.Scanner;

/**
 * Демонстраційний клас з діалоговим інтерфейсом.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        CalculationManager manager = new CalculationManager();

        System.out.print("Введіть десяткове число: ");
        int decimal = scanner.nextInt();

        CalculationData data = new CalculationData(decimal);
        TetradCalculator calc = new TetradCalculator(data);
        calc.calculate();

        manager.addResult(data);

        System.out.println("Оберіть спосіб відображення результатів:");
        System.out.println("1 - Звичайний текст");
        System.out.println("2 - Текстова таблиця");
        System.out.print("Ваш вибір: ");
        int choice = scanner.nextInt();

        DisplayFactory factory;

        if (choice == 2) {
            System.out.print("Вкажіть ширину колонки таблиці (наприклад, 20): ");
            int width = scanner.nextInt();
            System.out.print("Вкажіть символ рамки (наприклад, - або =): ");
            char border = scanner.next().charAt(0);

            factory = new TableDisplayFactory(width, border);
        } else {
            factory = new TextDisplayFactory();
        }

        ResultDisplay display = factory.createDisplay();

        manager.displayResults(display);

        manager.save("data.dat");

        System.out.println("Стан збережено у файл data.dat.");

        scanner.close();
    }
}
