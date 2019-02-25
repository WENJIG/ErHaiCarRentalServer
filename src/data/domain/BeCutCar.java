package data.domain;

public class BeCutCar {

    private long id;
    private String carId;
    private String brand;
    private String className;
    private String rankName;
    private String price;
    private String dayPrice;
    private String cashPledge;
    private int status;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"carId\":\"")
                .append(carId).append('\"');
        sb.append(",\"brand\":\"")
                .append(brand).append('\"');
        sb.append(",\"className\":\"")
                .append(className).append('\"');
        sb.append(",\"rankName\":\"")
                .append(rankName).append('\"');
        sb.append(",\"price\":\"")
                .append(price).append('\"');
        sb.append(",\"dayPrice\":\"")
                .append(dayPrice).append('\"');
        sb.append(",\"cashPledge\":\"")
                .append(cashPledge).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append('}');
        return sb.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(String dayPrice) {
        this.dayPrice = dayPrice;
    }

    public String getCashPledge() {
        return cashPledge;
    }

    public void setCashPledge(String cashPledge) {
        this.cashPledge = cashPledge;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
