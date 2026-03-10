import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

/**
 * Графічний спостерігач, який малює стовпчикову діаграму
 * кількості повних тетрад для кожного елемента колекції.
 */
public class GraphPanel extends JPanel implements CalculationObserver {

    private List<CalculationData> currentData = new ArrayList<>();

    public GraphPanel() {
        setPreferredSize(new Dimension(600, 300));
        setBackground(Color.WHITE);
    }

    @Override
    public void collectionChanged(final List<CalculationData> snapshot) {
        // Виконуємо оновлення на EDT
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                currentData = new ArrayList<>(snapshot);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        if (currentData.isEmpty()) {
            g2.setColor(Color.DARK_GRAY);
            g2.drawString("Колекція порожня. Додайте елементи.", 10, 20);
            return;
        }

        int n = currentData.size();
        int barWidth = Math.max(10, width / Math.max(n, 1));

        int maxTetrads = 1;
        for (CalculationData d : currentData) {
            if (d.getFullTetrads() > maxTetrads) {
                maxTetrads = d.getFullTetrads();
            }
        }

        int baseLine = height - 30;

        g2.setColor(Color.LIGHT_GRAY);
        g2.drawLine(30, baseLine, width - 10, baseLine);

        g2.setColor(new Color(70, 130, 180));
        for (int i = 0; i < n; i++) {
            CalculationData d = currentData.get(i);
            int value = d.getFullTetrads();
            int barHeight = (int) ((double) value / maxTetrads * (height - 60));
            int x = 30 + i * barWidth;
            int y = baseLine - barHeight;

            g2.fillRect(x, y, barWidth - 4, barHeight);
        }

        g2.setColor(Color.DARK_GRAY);
        g2.drawString("Графік: кількість повних тетрад для кожного результату", 30, 20);
    }
}

