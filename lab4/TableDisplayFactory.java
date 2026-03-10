/**
 * Фабрика для створення табличного відображення результатів.
 * Використовується шаблон Factory Method.
 */
public class TableDisplayFactory implements DisplayFactory {

    private int columnWidth;
    private char borderChar;

    /**
     * Створює фабрику для табличного відображення.
     *
     * @param columnWidth ширина колонки таблиці
     * @param borderChar  символ рамки
     */
    public TableDisplayFactory(int columnWidth, char borderChar) {
        this.columnWidth = columnWidth;
        this.borderChar = borderChar;
    }

    @Override
    public ResultDisplay createDisplay() {
        return new TableResultDisplay(columnWidth, borderChar);
    }
}

