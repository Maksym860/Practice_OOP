import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Сервіс паралельної обробки колекції даних.
 */
public class ParallelStatisticsService {

    public ParallelStatsResult calculateStats(List<CalculationData> data) {
        Objects.requireNonNull(data, "data");
        if (data.isEmpty()) {
            return new ParallelStatsResult(0, 0, 0, 0.0, 0, 0, 0.0);
        }

        IntSummaryStatistics decimals = data
                .parallelStream()
                .mapToInt(CalculationData::getDecimalNumber)
                .summaryStatistics();

        IntSummaryStatistics tetrads = data
                .parallelStream()
                .mapToInt(CalculationData::getFullTetrads)
                .summaryStatistics();

        return new ParallelStatsResult(
                (int) decimals.getCount(),
                decimals.getMin(),
                decimals.getMax(),
                decimals.getAverage(),
                tetrads.getMin(),
                tetrads.getMax(),
                tetrads.getAverage()
        );
    }

    public List<CalculationData> filterByFullTetradsAtLeast(List<CalculationData> data, int minFullTetrads) {
        Objects.requireNonNull(data, "data");
        return data
                .parallelStream()
                .filter(d -> d.getFullTetrads() >= minFullTetrads)
                .collect(Collectors.toList());
    }
}

