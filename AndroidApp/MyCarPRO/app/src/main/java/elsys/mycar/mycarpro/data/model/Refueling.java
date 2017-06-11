package elsys.mycar.mycarpro.data.model;

import com.google.gson.annotations.SerializedName;

import elsys.mycar.mycarpro.data.Constants;

public class Refueling {

    @SerializedName(Constants.ID)
    private String id;
    @SerializedName(Constants.GAS_STATION_NAME)
    private String companyName;
    @SerializedName(Constants.DATE_TIME)
    private String date;
    @SerializedName(Constants.QUANTITY)
    private double quantity;
    @SerializedName(Constants.PRICE)
    private long price;
    @SerializedName(Constants.ODOMETER)
    private long odometer;
    @SerializedName(Constants.IS_FULL_FUEL_TANK)
    private boolean isFuelTankFull;
    @SerializedName(Constants.NOTE)
    private String note;
    @SerializedName(Constants.VEHICLE_ID)
    private String vehicleId;

    public Refueling(String id, String companyName, String date, double quantity, long price, long odometer, String note) {
        this.id = id;
        this.companyName = companyName;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.odometer = odometer;
        this.note = note;
    }

    public Refueling(String companyName, String date, double quantity, long price, long odometer, String note, String vehicleId) {
        this.companyName = companyName;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.odometer = odometer;
        this.note = note;
        this.vehicleId = vehicleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getOdometer() {
        return odometer;
    }

    public void setOdometer(long odometer) {
        this.odometer = odometer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
