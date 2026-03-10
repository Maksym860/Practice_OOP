import java.io.Serializable;

/**
 * Клас для збереження параметрів і результатів обчислення
 */
@Monitored("Елемент колекції результатів")
public class CalculationData implements Serializable {

    @Monitored("Вихідне десяткове число")
    private int decimalNumber;

    @Monitored("Рядкове двійкове представлення")
    private String binaryRepresentation;

    @Monitored("Кількість повних тетрад")
    private int fullTetrads;

    // transient поле (не серіалізується)
    private transient String tempInfo = "temporary";

    public CalculationData(int decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    public int getDecimalNumber() {
        return decimalNumber;
    }

    public void setBinaryRepresentation(String binaryRepresentation) {
        this.binaryRepresentation = binaryRepresentation;
    }

    public String getBinaryRepresentation() {
        return binaryRepresentation;
    }

    public void setFullTetrads(int fullTetrads) {
        this.fullTetrads = fullTetrads;
    }

    public int getFullTetrads() {
        return fullTetrads;
    }
}