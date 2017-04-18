package elsys.mycar.mycarpro.data;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Data {

    public static final SortedMap<String, String> MAKES = new TreeMap<>();

    static {
        MAKES.put("Audi", "audi.com");
        MAKES.put("BMW", "bmw.com");
        MAKES.put("Mercedes", "mercedes-benz.com");
        MAKES.put("Opel", "opel.com");
        MAKES.put("Ford", "ford.com");
        MAKES.put("Peugeot", "peugeot.com");
        MAKES.put("Renault", "renault.com");
        MAKES.put("Citroen", "citroen.com");
    }

    public static final SortedSet<String> SERVICE_TYPES = new TreeSet<>();

    static {
        SERVICE_TYPES.add("Lights");
        SERVICE_TYPES.add("Windows");
        SERVICE_TYPES.add("Oil change");
        SERVICE_TYPES.add("Transmission");
        SERVICE_TYPES.add("Filter change");
        SERVICE_TYPES.add("Exhaust system");
        SERVICE_TYPES.add("Brake fluid");
        SERVICE_TYPES.add("Tyre change");
    }

    public static final SortedSet<String> INSURANCE_COMPANIES = new TreeSet<>();

    static {
        INSURANCE_COMPANIES.add("Armeec");
        INSURANCE_COMPANIES.add("Allianz");
        INSURANCE_COMPANIES.add("Euroins");
        INSURANCE_COMPANIES.add("Lev Inc");
        INSURANCE_COMPANIES.add("HDI");
        INSURANCE_COMPANIES.add("DZI");
        INSURANCE_COMPANIES.add("UNIQUA");
        INSURANCE_COMPANIES.add("Bul Inc");
        INSURANCE_COMPANIES.add("AXA");
        INSURANCE_COMPANIES.add("USAA");
        INSURANCE_COMPANIES.add("GEICO");
        INSURANCE_COMPANIES.add("Amica");
        INSURANCE_COMPANIES.add("Progressive");
    }

    public static final SortedSet<String> GAS_STATION_COMPANIES = new TreeSet<>();

    static {
        GAS_STATION_COMPANIES.add("OMV");
        GAS_STATION_COMPANIES.add("Petrol");
        GAS_STATION_COMPANIES.add("Lukoil");
        GAS_STATION_COMPANIES.add("Shell");
        GAS_STATION_COMPANIES.add("Gasprom");
        GAS_STATION_COMPANIES.add("EKO");
    }
}
