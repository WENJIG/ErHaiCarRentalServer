package data.entity;

public class Record {

    private long id;
    private long accountId;
    private long carId;
    private String outTime;
    private String inTime;
    private String dayPrice;
    private String allPrice;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"accountId\":")
                .append(accountId);
        sb.append(",\"carId\":")
                .append(carId);
        sb.append(",\"outTime\":\"")
                .append(outTime).append('\"');
        sb.append(",\"inTime\":\"")
                .append(inTime).append('\"');
        sb.append(",\"dayPrice\":\"")
                .append(dayPrice).append('\"');
        sb.append(",\"allPrice\":\"")
                .append(allPrice).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(String dayPrice) {
        this.dayPrice = dayPrice;
    }

    public String getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(String allPrice) {
        this.allPrice = allPrice;
    }
}
