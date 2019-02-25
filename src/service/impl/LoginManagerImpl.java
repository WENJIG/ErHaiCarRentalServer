package service.impl;

import dao.AccountDao;
import dao.impl.AccountDaoImpl;
import service.LoginManager;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 用于维护所有已登录的账户的登录状态, 权限信息。
 */
public class LoginManagerImpl implements LoginManager {

    private AccountDao accountDao = new AccountDaoImpl();

    private HashMap<String, Long> keyV = new HashMap<>();
    private HashMap<Long,String> valueK = new HashMap<>();
    private HashSet<Long> roleId = new HashSet<>();

    @Override
    public void add(String key, long id) {
        keyV.put(key, id);
        valueK.put(id, key);
        if (autoFindRoleById(id) > 0 && autoFindRoleById(id) == 9) {
            roleId.add(id);
        }
    }

    @Override
    public void delete(long id) {
        keyV.remove(valueK.get(id));
        valueK.remove(id);
        roleId.remove(id);
    }

    @Override
    public long find(String key) {
        return keyV.get(key) != null ? keyV.get(key) : -1;
    }

    @Override
    public boolean isAdmin(String key) {
        if (roleId.contains(find(key))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String findIsAdminAndName(String key) {
        if (isAdmin(key)) {
            return accountDao.findNameById(find(key)) + "[管理员]";
        } else {
            return accountDao.findNameById(find(key)) + "[用户]";
        }
    }

    private long autoFindRoleById(long id) {
        return accountDao.findRoleById(id);
    }
}
