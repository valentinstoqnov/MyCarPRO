package elsys.mycar.mycarpro.model;

import java.util.Date;

public class Insurance {

    private String id;
    private String companyName;
    private long price;
    private long odometer;
    private String date;
    private String expirationDate;
    private String note;

    public Insurance() {}

    public Insurance(String id, String companyName, long price, long odometer, String date, String expirationDate, String note) {
        this.id = id;
        this.companyName = companyName;
        this.price = price;
        this.odometer = odometer;
        this.date = date;
        this.expirationDate = expirationDate;
        this.note = note;
    }

    public Insurance(String companyName, long price, long odometer, String date, String expirationDate, String note) {
        this.companyName = companyName;
        this.price = price;
        this.odometer = odometer;
        this.date = date;
        this.expirationDate = expirationDate;
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
}
