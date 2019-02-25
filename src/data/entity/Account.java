package data.entity;

public class Account //implements Serializable
                     // 已经将数据结构对象的toString 转成json。序列化能否加快传输？
{

    private long id;
    private String name;
    private String password;
    private String email;
    private String sex;
    private String idCard;
    private String drivingLicence;
    private String tel;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"email\":\"")
                .append(email).append('\"');
        sb.append(",\"sex\":\"")
                .append(sex).append('\"');
        sb.append(",\"idCard\":\"")
                .append(idCard).append('\"');
        sb.append(",\"drivingLicence\":\"")
                .append(drivingLicence).append('\"');
        sb.append(",\"tel\":\"")
                .append(tel).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
