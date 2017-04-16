package elsys.mycar.mycarpro.model;

public class Refueling {

    private String id;
    private String companyName;
    private String date;
    private double quantity;
    private long price;
    private long odometer;
    private String note;

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
