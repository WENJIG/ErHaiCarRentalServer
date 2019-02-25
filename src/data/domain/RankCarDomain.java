package data.domain;

import java.util.ArrayList;

public class RankCarDomain {

    private int code;
    private ArrayList<String> rankStr;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"code\":")
                .append(code);
        sb.append(",\"rankStr\":")
                .append(rankStr);
        sb.append('}');
        return sb.toString();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<String> getRankStr() {
        return rankStr;
    }

    public void setRankStr(ArrayList<String> rankStr) {
        this.rankStr = rankStr;
    }
}
