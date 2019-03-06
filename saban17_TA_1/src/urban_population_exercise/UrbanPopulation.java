package urban_population_exercise;

/**
 * Udleveret kodeskelet til VOP re-eksamen 20. august 2014
 *
 */
public class UrbanPopulation implements Comparable<UrbanPopulation> {

    private final String name;
    private final int percent1980;
    private final int percent2008;

    public UrbanPopulation(String name, int percent1980, int percent2008) {
        this.name = name;
        this.percent1980 = percent1980;
        this.percent2008 = percent2008;
    }

    private int getDiff() {
        return (int) Math.round(percent2008 - percent1980);
    }

    @Override
    public String toString() {
        return String.format("%s 1980: %s 2008: %s Dif: %s\n", name, percent1980, percent2008, getDiff());
    }

    @Override
    public int compareTo(UrbanPopulation o) {
        return this.getDiff() == o.getDiff() ? this.name.compareTo(o.name) : this.getDiff() - o.getDiff();
    }

    // Antager, eftersom variablerne skulle instantieres ved constructoren, at der kun skal laves accessors.
    // Har derfor også lavet variablerne final.
    
    public String getName() {
        return name;
    }

    public int getPercent1980() {
        return percent1980;
    }

    public int getPercent2008() {
        return percent2008;
    }
}
