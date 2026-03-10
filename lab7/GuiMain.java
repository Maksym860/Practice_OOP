import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

/**
 * Графічний додаток для роботи з колекцією результатів.
 * Демонструє шаблон Observer та перерисовку графіка при зміні колекції.
 */
public class GuiMain {

    public static void main(String[] args) {
        // Демонстрація анотацій та Reflection у консолі
        ReflectionDemo.printAnnotationInfo();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGui();
            }
        });
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("Lab6 - Observer + Annotations + GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CalculationManager manager = CalculationManager.getInstance();

        GraphPanel graphPanel = new GraphPanel();
        ConsoleSortingObserver consoleObserver = new ConsoleSortingObserver();

        // реєструємо два різних спостерігачі
        manager.addObserver(graphPanel);
        manager.addObserver(consoleObserver);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Введіть десяткове число:");
        JTextField inputField = new JTextField(10);
        JButton addButton = new JButton("Додати");
        JButton removeLastButton = new JButton("Видалити останнє");
        JButton clearButton = new JButton("Очистити");

        controlPanel.add(label);
        controlPanel.add(inputField);
        controlPanel.add(addButton);
        controlPanel.add(removeLastButton);
        controlPanel.add(clearButton);

        addButton.addActionListener(e -> {
            String text = inputField.getText().trim();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Введіть ціле число.", "Помилка вводу",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                int value = Integer.parseInt(text);
                if (value < 0) {
                    JOptionPane.showMessageDialog(frame, "Число має бути невід'ємним.", "Помилка вводу",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                CalculationData data = new CalculationData(value);
                TetradCalculator calculator = new TetradCalculator(data);
                calculator.calculate();

                manager.addResult(data);
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Некоректне ціле число.", "Помилка вводу",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        removeLastButton.addActionListener(e -> manager.removeLastResult());

        clearButton.addActionListener(e -> manager.clear());

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(controlPanel, BorderLayout.NORTH);
        frame.getContentPane().add(graphPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

