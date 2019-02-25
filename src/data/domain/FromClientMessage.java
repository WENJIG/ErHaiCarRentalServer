package data.domain;

public class FromClientMessage {

    private int handleCode;
    private String userKey;
    private Object data;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"handleCode\":")
                .append(handleCode);
        sb.append(",\"userKey\":\"")
                .append(userKey).append('\"');
        sb.append(",\"data\":")
                .append(data);
        sb.append('}');
        return sb.toString();
    }

    public int getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(int handleCode) {
        this.handleCode = handleCode;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
