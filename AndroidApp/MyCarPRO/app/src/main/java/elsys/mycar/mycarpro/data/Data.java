package elsys.mycar.mycarpro.data;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Data {

    private static final SortedSet<String> MAKES = new TreeSet<>();

    static {
        MAKES.add("Audi");
        MAKES.add("BMW");
        MAKES.add("Mercedes");
        MAKES.add("Opel");
        MAKES.add("Ford");
        MAKES.add("Peugeot");
        MAKES.add("Renault");
        MAKES.add("Citroen");
    }

    private static final SortedSet<String> SERVICE_TYPES = new TreeSet<>();

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

    private static final SortedSet<String> INSURANCE_COMPANIES = new TreeSet<>();

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

    private static final SortedSet<String> GAS_STATION_COMPANIES = new TreeSet<>();

    static {
        GAS_STATION_COMPANIES.add("OMV");
        GAS_STATION_COMPANIES.add("Petrol");
        GAS_STATION_COMPANIES.add("Lukoil");
        GAS_STATION_COMPANIES.add("Shell");
        GAS_STATION_COMPANIES.add("Gasprom");
        GAS_STATION_COMPANIES.add("EKO");
    }

    public static List<String> getMakes() {
        return new ArrayList<>(MAKES);
    }

    public static List<String> getServiceTypes() {
        return new ArrayList<>(SERVICE_TYPES);
    }

    public static List<String> getInsuranceCompanies() {
        return new ArrayList<>(INSURANCE_COMPANIES);
    }

    public static List<String> getGasStationCompanies() {
        return new ArrayList<>(GAS_STATION_COMPANIES);
    }
}
