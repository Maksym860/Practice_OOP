import java.io.*;
import java.util.Scanner;


//демонстраційний клас для збереження та відновлення об'єкта.

public class Demo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть десяткове число: ");
        int number = scanner.nextInt();

        TetradData data = new TetradData(number);
        TetradCalculator calculator = new TetradCalculator(data);

        calculator.calculate();

        System.out.println("Двійкове представлення: " + data.getBinaryRepresentation());
        System.out.println("Кількість повних тетрад: " + data.getFullTetrads());

        // Серіалізація
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream("data.ser"))) {

            oos.writeObject(data);
            System.out.println("Об'єкт збережено.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Десеріалізація
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream("data.ser"))) {

            TetradData restoredData = (TetradData) ois.readObject();

            System.out.println("Об'єкт відновлено.");
            System.out.println("Десяткове число: " + restoredData.getDecimalNumber());
            System.out.println("Кількість тетрад: " + restoredData.getFullTetrads());

            // Показуємо особливість transient
            System.out.println("Binary (transient поле): " +
                    restoredData.getBinaryRepresentation());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
