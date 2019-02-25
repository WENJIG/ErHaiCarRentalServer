package data.domain;

public class AccountBaseInfo {

    private String info;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"info\":\"")
                .append(info).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
