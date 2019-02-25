package data.domain;

public class FindPageInit {

    private int pageGross;
    private int pageStart;
    private int pageEnd;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"pageGross\":")
                .append(pageGross);
        sb.append(",\"pageStart\":")
                .append(pageStart);
        sb.append(",\"pageEnd\":")
                .append(pageEnd);
        sb.append('}');
        return sb.toString();
    }

    public int getPageGross() {
        return pageGross;
    }

    public void setPageGross(int pageGross) {
        this.pageGross = pageGross;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
}
