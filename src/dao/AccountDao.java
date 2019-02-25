package dao;

import data.entity.Account;

public interface AccountDao {

    boolean addAccount(Account account);

    long findIdByLogin(String username, String password);

    long findIdByUsername(String username);

    long findIdByIdCard(String idCard);

    long findIdByDrivingLicence(String drivingLicence);

    long findRoleById(long id);

    String findNameById(long id);

}
