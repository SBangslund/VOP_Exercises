import java.io.Serializable;

public class Fruit implements Serializable {
    private final String name;
    private final String color;
    private final int number;
    private final boolean edible;

    public Fruit(String name, String color, int number, boolean edible) {
        this.name = name;
        this.color = color;
        this.number = number;
        this.edible = edible;
    }

    @Override
    public String toString() {
        return String.format("[Fruit](%s;%s;%s;%s)",name, color, number, edible);
    }
}
