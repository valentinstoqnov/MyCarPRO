package elsys.mycar.mycarpro.data.model;

import com.google.gson.annotations.SerializedName;

public class Refueling {

    @SerializedName("_id")
    private String id;
    @SerializedName("gas_station_name")
    private String companyName;
    @SerializedName("date_time")
    private String date;
    @SerializedName("quantity")
    private double quantity;
    @SerializedName("price")
    private long price;
    @SerializedName("odometer")
    private long odometer;
    @SerializedName("note")
    private String note;
    @SerializedName("vehicle_id")
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

    public Refueling(String companyName, String date, double quantity, long price, long odometer, String note) {
        this.companyName = companyName;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.odometer = odometer;
        this.note = note;
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
