package shell;

import data.domain.AccountBaseInfo;
import data.domain.LoginDomain;
import data.domain.ResponseLoginDomain;
import data.domain.ToClientMessage;
import data.entity.Account;
import service.AccountService;
import service.impl.AccountServiceImpl;
import service.impl.ServerManager;
import util.JsonUtil;
import util.KeyUtil;

public class AccountController {

    private AccountService accountService = new AccountServiceImpl();

    /**
     * @Description: 注册(新增账号) 会检查具有唯一性的字段
     * @param [data]
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan10011(Object data) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            Account account = (Account) JsonUtil.analysis(data, Account.class);
            if (accountService.findIdByUsername(account.getName()) > 0 || accountService.findIdByIdCard(account.getIdCard()) > 0
                    || accountService.findIdByDrivingLicence(account.getDrivingLicence()) > 0) {
                toClientMessage.setStatus(201);
                toClientMessage.setData("注册账户的一些关键信息已被使用。");
                return toClientMessage;
            }
            if (accountService.add(account)) {
                toClientMessage.setStatus(200);
                toClientMessage.setData("注册成功！");
                return toClientMessage;
            } else {
                toClientMessage.setStatus(201);
                toClientMessage.setData("注册信息不合规范。");
                return toClientMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 登录成功 将密钥以及ID添加进已登录账户管理器
     * @param [data]
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan10012(Object data) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            LoginDomain loginDomain = (LoginDomain) JsonUtil.analysis(data, LoginDomain.class);
            long accountId = accountService.exitsByLogin(loginDomain.getUsername(), loginDomain.getPassword());
            if (accountId > 0) {
                String keySet = KeyUtil.newLoginKey(accountId);
                ResponseLoginDomain resp = new ResponseLoginDomain();
                resp.setStatus(true);
                resp.setKey(keySet);
                toClientMessage.setStatus(200);
                toClientMessage.setData(resp.toString());
                /**
                 * 如果已有密钥存在，则删除已存在密钥，用新的覆盖(或直接删除可能存在的密钥)
                 */
                ServerManager.LOGIN_STATUS.getLoginManager().delete(accountId);
                ServerManager.LOGIN_STATUS.getLoginManager().add(keySet, accountId);
                return toClientMessage;
            } else {
                toClientMessage.setStatus(201);
                toClientMessage.setData(null);
                return toClientMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 返回账号基础信息
     * @param [key]
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan10013(String key) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            AccountBaseInfo accountBaseInfo = new AccountBaseInfo();
            accountBaseInfo.setInfo(ServerManager.LOGIN_STATUS.getLoginManager().findIsAdminAndName(key));
            toClientMessage.setStatus(200);
            toClientMessage.setData(accountBaseInfo.toString());
            return toClientMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
