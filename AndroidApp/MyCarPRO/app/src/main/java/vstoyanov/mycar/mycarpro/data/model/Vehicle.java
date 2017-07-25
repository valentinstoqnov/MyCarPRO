package vstoyanov.mycar.mycarpro.data.model;

import com.google.gson.annotations.SerializedName;

import vstoyanov.mycar.mycarpro.data.Constants;

public class Vehicle {

    @SerializedName(Constants.ID)
    private String id;
    @SerializedName(Constants.NAME)
    private String name;
    @SerializedName(Constants.MAKE)
    private String make;
    @SerializedName(Constants.MODEL)
    private String model;
    @SerializedName(Constants.MANUFACTURE_DATE)
    private String manufactureDate;
    @SerializedName(Constants.HORSE_POWER)
    private int horsePower;
    @SerializedName(Constants.ODOMETER)
    private int odometer;
    @SerializedName(Constants.FUEL_TYPE)
    private String fuelType;
    @SerializedName(Constants.FUEL_TANK_CAPACITY)
    private int fuelTankCapacity;
    @SerializedName(Constants.FUEL_CONSUMPTION)
    private double fuelConsumption;
    @SerializedName(Constants.COLOR)
    private int color;
    @SerializedName(Constants.USER_ID)
    private String userId;
    @SerializedName(Constants.NOTE)
    private String note;

    public Vehicle(String name, String make, String model, String manufactureDate, int horsePower, int odometer, String fuelType, int fuelTankCapacity, double fuelConsumption, int color, String note, String userId) {
        this.name = name;
        this.make = make;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.horsePower = horsePower;
        this.odometer = odometer;
        this.fuelType = fuelType;
        this.fuelTankCapacity = fuelTankCapacity;
        this.fuelConsumption = fuelConsumption;
        this.color = color;
        this.note = note;
        this.userId = userId;
    }

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

    public Vehicle(String id, String name, String make, String model, String manufactureDate, int horsePower, int odometer, String note) {
        this.id = id;
        this.name = name;
        this.make = make;
        this.model = model;
        this.manufactureDate = manufactureDate;
        this.horsePower = horsePower;
        this.odometer = odometer;
        this.note = note;
    }

    public Vehicle(String id, String name, String make, String model, String manufactureDate, int horsePower, int odometer, String fuelType, int fuelTankCapacity, double fuelConsumption, String note) {
        this.id = id;
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

    public Vehicle() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

