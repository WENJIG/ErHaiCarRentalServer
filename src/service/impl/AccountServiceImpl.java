package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import data.entity.Account;
import service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();

    @Override
    public boolean add(Account account) {
        return accountDao.addAccount(account);
    }

    @Override
    public long exitsByLogin(String username, String password) {
        return accountDao.findIdByLogin(username, password);
    }

    @Override
    public long findIdByUsername(String username) {
        return accountDao.findIdByUsername(username);
    }

    @Override
    public long findIdByIdCard(String idCard) {
        return accountDao.findIdByIdCard(idCard);
    }

    @Override
    public long findIdByDrivingLicence(String drivingLicence) {
        return accountDao.findIdByDrivingLicence(drivingLicence);
    }

    @Override
    public String findNameById(long id) {
        return accountDao.findNameById(id);
    }
}
