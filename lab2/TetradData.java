import java.io.Serializable;

//клас для зберігання параметрів та результатів обчислень. Реалізує Serializable для збережея стану об'єкта



public class TetradData implements Serializable {

    private static final long serialVersionUID = 1L;

    /** Десяткове число */
    private int decimalNumber;

    /** Результат – кількість повних тетрад */
    private int fullTetrads;

    /** Тимчасове поле (не серіалізується) */
    private transient String binaryRepresentation;

    /**
     * Конструктор
     * @param decimalNumber задане десяткове число
     */
    public TetradData(int decimalNumber) {
        this.decimalNumber = decimalNumber;
    }

    public int getDecimalNumber() {
        return decimalNumber;
    }

    public int getFullTetrads() {
        return fullTetrads;
    }

    public void setFullTetrads(int fullTetrads) {
        this.fullTetrads = fullTetrads;
    }

    public String getBinaryRepresentation() {
        return binaryRepresentation;
    }

    public void setBinaryRepresentation(String binaryRepresentation) {
        this.binaryRepresentation = binaryRepresentation;
    }
}
