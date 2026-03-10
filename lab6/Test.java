/**
 * Клас тестування основної функціональності.
 */
public class Test {

    public static void main(String[] args) throws Exception {

        System.out.println("=== Тест: Singleton менеджер ===");
        CalculationManager m1 = CalculationManager.getInstance();
        CalculationManager m2 = CalculationManager.getInstance();
        System.out.println("Один екземпляр? " + (m1 == m2));

        m1.clear();

        System.out.println("\n=== Тест: обчислення (calculate) ===");
        CalculationData data1 = new CalculationData(255);
        Command calcCmd1 = new CalculateCommand(data1);
        calcCmd1.execute();
        System.out.println("Вхід: 255, очікувано тетрад: 2, фактично: " + data1.getFullTetrads());

        System.out.println("\n=== Тест: макрокоманда (calculate + add) ===");
        CalculationData data2 = new CalculationData(15);
        MacroCommand macro = new MacroCommand(java.util.List.of(
                new CalculateCommand(data2),
                new AddResultCommand(m1, data2)
        ));
        macro.execute();
        System.out.println("Додано записів: " + m1.size() + " (очікувано 1)");

        System.out.println("\n=== Тест: undo для макрокоманди ===");
        CommandHistory history = new CommandHistory();
        history.push(macro);
        history.undoLast();
        System.out.println("Після undo записів: " + m1.size() + " (очікувано 0)");

        System.out.println("\n=== Тест: відображення (Factory Method + поліморфізм) ===");
        CalculationData data3 = new CalculationData(255);
        new CalculateCommand(data3).execute();
        new AddResultCommand(m1, data3).execute();

        DisplayFactory textFactory = new TextDisplayFactory();
        ResultDisplay textDisplay = textFactory.createDisplay();
        System.out.println("\nТекстове відображення:");
        m1.displayResults(textDisplay);

        DisplayFactory tableFactory = new TableDisplayFactory(20, '-');
        ResultDisplay tableDisplay = tableFactory.createDisplay();
        System.out.println("\nТабличне відображення:");
        m1.displayResults(tableDisplay);
    }
}
