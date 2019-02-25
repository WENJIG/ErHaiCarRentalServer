package data.domain;

public class UpdateCarPriceDomain {

    private long carId;
    private String day_price;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"carId\":")
                .append(carId);
        sb.append(",\"day_price\":\"")
                .append(day_price).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getDay_price() {
        return day_price;
    }

    public void setDay_price(String day_price) {
        this.day_price = day_price;
    }
}
