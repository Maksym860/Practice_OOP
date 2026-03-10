/**
 * Результат статистичної обробки колекції.
 */
public class ParallelStatsResult {
    private final int count;
    private final int minDecimal;
    private final int maxDecimal;
    private final double avgDecimal;

    private final int minFullTetrads;
    private final int maxFullTetrads;
    private final double avgFullTetrads;

    public ParallelStatsResult(
            int count,
            int minDecimal,
            int maxDecimal,
            double avgDecimal,
            int minFullTetrads,
            int maxFullTetrads,
            double avgFullTetrads
    ) {
        this.count = count;
        this.minDecimal = minDecimal;
        this.maxDecimal = maxDecimal;
        this.avgDecimal = avgDecimal;
        this.minFullTetrads = minFullTetrads;
        this.maxFullTetrads = maxFullTetrads;
        this.avgFullTetrads = avgFullTetrads;
    }

    public int getCount() {
        return count;
    }

    public int getMinDecimal() {
        return minDecimal;
    }

    public int getMaxDecimal() {
        return maxDecimal;
    }

    public double getAvgDecimal() {
        return avgDecimal;
    }

    public int getMinFullTetrads() {
        return minFullTetrads;
    }

    public int getMaxFullTetrads() {
        return maxFullTetrads;
    }

    public double getAvgFullTetrads() {
        return avgFullTetrads;
    }
}

