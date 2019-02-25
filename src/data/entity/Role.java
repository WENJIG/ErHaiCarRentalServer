package data.entity;

public class Role {

    private long id;
    private long accountId;
    private short role;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"accountId\":")
                .append(accountId);
        sb.append(",\"role\":")
                .append(role);
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

    public short getRole() {
        return role;
    }

    public void setRole(short role) {
        this.role = role;
    }
}
