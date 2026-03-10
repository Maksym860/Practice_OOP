import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Спостерігач, який відстежує зміни колекції та
 * виводить її у відсортованому вигляді в консоль.
 */
@InternalObserverTag(purpose = "Відлагоджувальний консольний спостерігач")
public class ConsoleSortingObserver implements CalculationObserver {

    @Override
    public void collectionChanged(List<CalculationData> snapshot) {
        List<CalculationData> copy = new ArrayList<>(snapshot);

        copy.sort(Comparator.comparingInt(CalculationData::getDecimalNumber));

        System.out.println("[Observer] Поточні результати (відсортовано за десятковим значенням):");
        for (CalculationData data : copy) {
            System.out.printf("  dec=%d, bin=%s, fullTetrads=%d%n",
                    data.getDecimalNumber(),
                    data.getBinaryRepresentation(),
                    data.getFullTetrads());
        }
    }
}

