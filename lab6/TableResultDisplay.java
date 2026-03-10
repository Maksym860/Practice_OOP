/**
 * Виведення результатів у вигляді текстової таблиці.
 * Параметри відображення (ширина стовпця, символ рамки) задаються користувачем.
 */
public class TableResultDisplay implements ResultDisplay {

    private int columnWidth;
    private char borderChar;

    /**
     * Створює табличне відображення результатів.
     *
     * @param columnWidth ширина однієї колонки таблиці (не менше 5)
     * @param borderChar  символ, яким малюється рамка таблиці
     */
    public TableResultDisplay(int columnWidth, char borderChar) {
        this.columnWidth = Math.max(columnWidth, 5);
        this.borderChar = borderChar;
    }

    @Override
    public void display(CalculationData data) {

        String[] headers = {
                "Десяткове число",
                "Двійкове число",
                "Кількість повних тетрад"
        };

        String[] values = {
                String.valueOf(data.getDecimalNumber()),
                data.getBinaryRepresentation(),
                String.valueOf(data.getFullTetrads())
        };

        printBorder(headers.length);
        printRow(headers);
        printBorder(headers.length);
        printRow(values);
        printBorder(headers.length);
    }

    private void printBorder(int columns) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            sb.append('+');
            for (int j = 0; j < columnWidth; j++) {
                sb.append(borderChar);
            }
        }
        sb.append('+');
        System.out.println(sb.toString());
    }

    private void printRow(String[] cells) {
        StringBuilder sb = new StringBuilder();
        for (String cell : cells) {
            sb.append('|');
            sb.append(padOrTrim(cell));
        }
        sb.append('|');
        System.out.println(sb.toString());
    }

    private String padOrTrim(String text) {
        if (text == null) {
            text = "";
        }
        if (text.length() > columnWidth) {
            return text.substring(0, columnWidth);
        }

        StringBuilder sb = new StringBuilder(text);
        while (sb.length() < columnWidth) {
            sb.append(' ');
        }
        return sb.toString();
    }
}

