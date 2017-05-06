package elsys.mycar.mycarpro.data.model;

public class Service {

    private String id;
    private String type;
    private String date;
    private long price;
    private long odometer;
    private String note;

    public Service(String id, String type, String date, long price, long odometer, String note) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.price = price;
        this.odometer = odometer;
        this.note = note;
    }

    public Service(String type, String date, long price, long odometer, String note) {
        this.type = type;
        this.date = date;
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
}
