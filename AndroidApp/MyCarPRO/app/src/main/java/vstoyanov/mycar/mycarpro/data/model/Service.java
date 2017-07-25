package vstoyanov.mycar.mycarpro.data.model;

import com.google.gson.annotations.SerializedName;

import vstoyanov.mycar.mycarpro.data.Constants;

public class Service {

    @SerializedName(Constants.ID)
    private String id;
    @SerializedName(Constants.TYPE)
    private String type;
    @SerializedName(Constants.DATE_TIME)
    private String date;
    @SerializedName(Constants.PRICE)
    private long price;
    @SerializedName(Constants.ODOMETER)
    private long odometer;
    @SerializedName(Constants.NOTE)
    private String note;
    @SerializedName(Constants.VEHICLE_ID)
    private String vehicleId;

    public Service() {}

    public Service(String type, String date, long price, long odometer, String note, String vehicleId) {
        this.type = type;
        this.date = date;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
}
