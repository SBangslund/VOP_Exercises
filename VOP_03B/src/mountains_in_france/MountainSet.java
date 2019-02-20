package mountains_in_france;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MountainSet {

    private Set<Mountain> mountains;

    public MountainSet(Set<Mountain> mountains) {
        this.mountains = mountains;
    }

    public static void main(String[] args) {
        MountainSet set = new MountainSet(readMountainsFromFile(new File("FranskeBjerge.csv")));
        set.mountains = set.sortByRange(new MountainRangeComparator());
        System.out.println(set);
    }

    public static Set<Mountain> readMountainsFromFile(File file) {
        Set<Mountain> mountainSet = new TreeSet<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] arr = scanner.nextLine().split(";");
                mountainSet.add(new Mountain(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file: " + file.getAbsolutePath());
        }
        return mountainSet;
    }

    public Set<Mountain> sortByRange(Comparator com) {
        Set<Mountain> sortedSet = new TreeSet<>(com);
        sortedSet.addAll(mountains);
        return sortedSet;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        mountains.stream().forEach(e -> builder.append(String.format("%s\n", e)));
        return builder.toString();
    }

    public Set<Mountain> getMountains() {
        return mountains;
    }
}
