package mountains_in_france;

import java.util.Comparator;

public class MountainRangeComparator implements Comparator<Mountain>{

    @Override
    public int compare(Mountain o1, Mountain o2) {
        return o1.getRange().equals(o2.getRange()) ? o1.compareTo(o2) : o1.getRange().compareTo(o2.getRange());
    }
}
