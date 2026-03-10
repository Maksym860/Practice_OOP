import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Демонстраційний клас з діалоговим інтерфейсом.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        CalculationManager manager = CalculationManager.getInstance();
        CommandHistory history = new CommandHistory();

        boolean running = true;
        while (running) {
            System.out.println("\n=== Меню ===");
            System.out.println("1 - Обчислити та додати результат (макрокоманда)");
            System.out.println("2 - Показати результати");
            System.out.println("3 - Зберегти у файл (data.dat)");
            System.out.println("4 - Завантажити з файла (data.dat)");
            System.out.println("5 - Undo (скасувати останню команду)");
            System.out.println("0 - Вихід");
            System.out.print("Ваш вибір: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.next());
            } catch (NumberFormatException ex) {
                System.out.println("Некоректний ввід. Спробуйте ще раз.");
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Введіть десяткове число: ");
                    int decimal;
                    try {
                        decimal = Integer.parseInt(scanner.next());
                    } catch (NumberFormatException ex) {
                        System.out.println("Некоректне число.");
                        break;
                    }

                    CalculationData data = new CalculationData(decimal);
                    List<Command> commands = new ArrayList<>();
                    commands.add(new CalculateCommand(data));
                    commands.add(new AddResultCommand(manager, data));
                    Command macro = new MacroCommand(commands);

                    macro.execute();
                    history.push(macro);

                    System.out.println("Готово. Поточна кількість результатів: " + manager.size());
                    break;
                }
                case 2: {
                    ResultDisplay display = createDisplayInteractively(scanner);
                    Command cmd = new DisplayCommand(manager, display);
                    cmd.execute();
                    break;
                }
                case 3: {
                    Command cmd = new SaveCommand(manager, "data.dat");
                    cmd.execute();
                    System.out.println("Стан збережено у файл data.dat.");
                    break;
                }
                case 4: {
                    Command cmd = new LoadCommand(manager, "data.dat");
                    cmd.execute();
                    history.push(cmd);
                    System.out.println("Стан завантажено з файла data.dat. Записів: " + manager.size());
                    break;
                }
                case 5: {
                    if (!history.canUndo()) {
                        System.out.println("Немає команд для скасування.");
                        break;
                    }
                    history.undoLast();
                    System.out.println("Undo виконано. Записів: " + manager.size());
                    break;
                }
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Невідома команда.");
            }
        }

        scanner.close();
    }

    private static ResultDisplay createDisplayInteractively(Scanner scanner) {
        System.out.println("Оберіть спосіб відображення результатів:");
        System.out.println("1 - Звичайний текст");
        System.out.println("2 - Текстова таблиця");
        System.out.print("Ваш вибір: ");

        int choice;
        try {
            choice = Integer.parseInt(scanner.next());
        } catch (NumberFormatException ex) {
            choice = 1;
        }

        DisplayFactory factory;
        if (choice == 2) {
            System.out.print("Вкажіть ширину колонки таблиці (наприклад, 20): ");
            int width;
            try {
                width = Integer.parseInt(scanner.next());
            } catch (NumberFormatException ex) {
                width = 20;
            }

            System.out.print("Вкажіть символ рамки (наприклад, - або =): ");
            String s = scanner.next();
            char border = (s.isEmpty()) ? '-' : s.charAt(0);

            factory = new TableDisplayFactory(width, border);
        } else {
            factory = new TextDisplayFactory();
        }
        return factory.createDisplay();
    }
}
