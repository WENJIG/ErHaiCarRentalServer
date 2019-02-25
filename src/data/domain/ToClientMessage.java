package data.domain;

public class ToClientMessage {

    private int status;
    private Object data;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"status\":")
                .append(status);
        sb.append(",\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
