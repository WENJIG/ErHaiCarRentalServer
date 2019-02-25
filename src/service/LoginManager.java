package service;

public interface LoginManager {

    void add(String key, long id);

    void delete(long id);

    long find(String key);

    boolean isAdmin(String key);

    String findIsAdminAndName(String key);

}
