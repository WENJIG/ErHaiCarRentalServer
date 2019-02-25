package dao.impl;

import dao.AccountDao;
import dao.ResultHandle;
import data.entity.Account;

public class AccountDaoImpl extends OracleDao implements AccountDao {

    public AccountDaoImpl () {}

    @Override
    public boolean addAccount(Account account) {
        return reflectInit("insert into eh_account values(eh_account_seq.nextVal", account);
    }

    @Override
    public long findIdByLogin(String username, String password) {
        String sql = "select * from eh_account where name=? and password=?";
        return (long) baseFind(sql, idHandle(), username, password);
    }

    @Override
    public long findIdByUsername(String username) {
        String sql = "select id from eh_account where name=?";
        return (long) baseFind(sql, idHandle(), username);
    }

    @Override
    public long findIdByIdCard(String idCard) {
        String sql = "select id from eh_account where idCard=?";
        return (long) baseFind(sql, idHandle(), idCard);
    }

    @Override
    public long findIdByDrivingLicence(String drivingLicence) {
        String sql = "select id from eh_account where driving_licence=?";
        return (long) baseFind(sql, idHandle(), drivingLicence);
    }

    @Override
    public long findRoleById(long id) {
        String sql = "select role from eh_role where account_id=?";
        ResultHandle resultHandle = resultSet -> {
            long role = 0;
            while (resultSet.next()) {
                role = resultSet.getLong("role");
            }
            return role;
        };
        return (long) baseFind(sql, resultHandle, id);
    }

    @Override
    public String findNameById(long id) {
        String sql = "select name from eh_account where id=?";
        ResultHandle resultHandle = resultSet -> {
            String name = null;
            while (resultSet.next()) {
                name = resultSet.getString("name");
            }
            return name;
        };
        return (String) baseFind(sql, resultHandle ,id);
    }

    /**
     * @Description: long 类型的数字
     * @param []
     * @Return dao.ResultHandle
     */
    private ResultHandle idHandle() {
        return resultSet -> {
            long id = 0;
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
            return id;
        };
    }


}
