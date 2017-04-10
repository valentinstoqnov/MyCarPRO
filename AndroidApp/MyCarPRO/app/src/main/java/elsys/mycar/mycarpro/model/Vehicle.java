package elsys.mycar.mycarpro.model;

public class Vehicle {

    private String id;
    private String name;
    private String make;
    private String model;
    private String manufactureDate;
    private int horsePower;
    private int odometer;
    private String fuelType;
    private int fuelTankCapacity;
    private double fuelConsumption;
    private String note;

    public Vehicle(String name, String make, String model, String manufactureDate, int horsePower, int odometer, String fuelType, int fuelTankCapacity, double fuelConsumption, String note) {
        this.name = name;
        this.make = make;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.horsePower = horsePower;
        this.odometer = odometer;
        this.fuelType = fuelType;
        this.fuelTankCapacity = fuelTankCapacity;
        this.fuelConsumption = fuelConsumption;
        this.note = note;
    }

    public Vehicle(String name, String make, String model, String manufactureDate, int horsePower, int odometer, String note) {
        this.name = name;
        this.make = make;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.horsePower = horsePower;
        this.odometer = odometer;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(String manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(int fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

