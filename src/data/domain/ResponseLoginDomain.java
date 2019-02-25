package data.domain;

public class ResponseLoginDomain {

    private boolean status;
    private String key;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"status\":")
                .append(status);
        sb.append(",\"key\":\"")
                .append(key).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
