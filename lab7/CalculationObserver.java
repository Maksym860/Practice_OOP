import java.util.List;

/**
 * Спостерігач за змінами колекції результатів.
 * Реалізація шаблону Observer.
 */
public interface CalculationObserver {

    /**
     * Викликається щоразу, коли колекція результатів змінюється.
     *
     * @param snapshot незмінюваний знімок поточного стану колекції
     */
    void collectionChanged(List<CalculationData> snapshot);
}

