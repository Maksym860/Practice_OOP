/**
 * Фабрика для створення текстового відображення
 */
public class TextDisplayFactory implements DisplayFactory {

    @Override
    public ResultDisplay createDisplay() {
        return new TextResultDisplay();
    }

}