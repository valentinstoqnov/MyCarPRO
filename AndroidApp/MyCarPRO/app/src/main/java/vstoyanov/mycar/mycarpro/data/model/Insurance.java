package vstoyanov.mycar.mycarpro.data.model;

import com.google.gson.annotations.SerializedName;

import vstoyanov.mycar.mycarpro.data.Constants;

public class Insurance {

    @SerializedName(Constants.ID)
    private String id;
    @SerializedName(Constants.COMPANY_NAME)
    private String companyName;
    @SerializedName(Constants.PRICE)
    private long price;
    @SerializedName(Constants.ODOMETER)
    private long odometer;
    @SerializedName(Constants.DATE_TIME)
    private String date;
    @SerializedName(Constants.EXPIRATION_DATE)
    private String expirationDate;
    @SerializedName(Constants.NOTE)
    private String note;
    @SerializedName(Constants.VEHICLE_ID)
    private String vehicleId;

    public Insurance() {}

    public Insurance(String companyName, long price, long odometer, String date, String expirationDate, String note, String vehicleId) {
        this.companyName = companyName;
        this.price = price;
        this.odometer = odometer;
        this.date = date;
        this.expirationDate = expirationDate;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
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
