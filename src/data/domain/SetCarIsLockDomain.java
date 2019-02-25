package data.domain;

public class SetCarIsLockDomain {

    private int lock;
    private long id;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"lock\":")
                .append(lock);
        sb.append(",\"id\":")
                .append(id);
        sb.append('}');
        return sb.toString();
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
