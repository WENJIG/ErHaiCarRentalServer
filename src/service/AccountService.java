package service;

import data.entity.Account;

public interface AccountService {

    boolean add(Account account);

    long exitsByLogin(String username, String password);

    long findIdByUsername(String username);

    long findIdByIdCard(String idCard);

    long findIdByDrivingLicence(String drivingLicence);

    String findNameById(long id);

}
