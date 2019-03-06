package vop_04a;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * VOP eksamen F2014 Kodeskelet til opgave 2
 *
 * @author erso
 */
public class NumberPlates {

    public static final int LENGTH = 7;         // Noejagtig laengde paa nummerplade

    private Map<String, String> districtMap;    // Kendingsbogstaver -> Politikreds

    private VehicleType[] vehicleTypes = { // Intervaller for anvendelse
        new VehicleType("Motorcykel", 10000, 19999),
        new VehicleType("Privat personvogn", 20000, 45999),
        new VehicleType("Udlejningsvogn", 46000, 46999),
        new VehicleType("Hyrevogn", 47000, 48999),
        new VehicleType("Skolevogn", 49000, 49899),
        new VehicleType("Ambulance el. lign.", 49900, 49999),
        new VehicleType("Diverse andre ", 50000, 84999)
    };

    public NumberPlates() {
        // opg 2a) initialiser districtMap
        districtMap = new HashMap<>();
        readFile();
    }

    public void readFile() {
        // opg 2a) Indlaes filen og put i mappen
        File file = new File("Nummerplader.txt");
        int lineCounter = 1;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] words = scanner.nextLine().split(":");
                try {
                    districtMap.put(words[0].toUpperCase(), words[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("There was an error on line: " + lineCounter + " in file: " + file.getAbsolutePath());
                }
                lineCounter++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't find the file: " + file.getAbsolutePath());
        }
    }

    public String validate(String plate) {
        // Opg 2b) Tjek nummerpladen og returner anvendelse og politidistrikt
        String result;
        try {
            result = plate.length() != 7 ? "Illegal længde!" : validateVehicleType(Integer.valueOf(plate.substring(2))) + " fra " + validateDistrict(plate.substring(0, 2));
        } catch (NumberFormatException e) {
            result = "Wrong format!";
        }
        return result;
    }

    private String validateDistrict(String districtCode) {
        // Opg 2b) Tjek kendingsbogstaver og returner politidistrikt
        return districtMap.containsKey(districtCode.toUpperCase()) ? districtMap.get(districtCode.toUpperCase()) : "Kreds findes ikke";
    }

    private String validateVehicleType(int number) {
        // Opg 2b) Tjek hvilket interval number ligger i og returner anvendelse
        List<VehicleType> types = Arrays.asList(vehicleTypes).stream().filter(e -> e.isA(number)).collect(Collectors.toList());
        return types.size() > 0 ? types.get(0).getVehicleType() : "Illegalt nummer: " + number;
    }
}
