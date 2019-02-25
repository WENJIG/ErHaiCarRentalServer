package shell;

import data.domain.FromClientMessage;
import data.domain.ToClientMessage;
import service.impl.ServerManager;

/**
 * 根据操作码 调用控制器
 */
public class ScanController {

    private AccountController accountController = new AccountController();
    private CarController carController = new CarController();
    private RecordController recordController = new RecordController();

    public ScanController() {}

    /**
     * @Description: 负责解析客户端发送的数据包,data.domain 或者 data.entity 中创建了许多数据结构用来保存数据包。
     * @param [fromClientMessage]
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan(FromClientMessage fromClientMessage) {
        //System.out.println(fromClientMessage.toString());
        ToClientMessage toClientMessage = null;
        if (fromClientMessage == null) {
            toClientMessage = respError();
            return toClientMessage;
        }
        /**
         * 如果访问非 10 开头的需要管理员权限的接口，对权限不足的请求进行拦截
         */
        boolean pid = ServerManager.LOGIN_STATUS.getLoginManager().isAdmin(fromClientMessage.getUserKey());
        if (!String.valueOf(fromClientMessage.getHandleCode()).substring(0, 2).equals("10")
            && !pid) {
            toClientMessage = respPermissionDenied();
            return toClientMessage;
        }
        switch (fromClientMessage.getHandleCode()) {
            case 10011: // 注册
                toClientMessage = accountController.scan10011(fromClientMessage.getData());
                break;
            case 10012:
                /**
                 * 登录 成功后会返回给客户端一个密钥(P1:密钥会随客户端随后的信息发送,以保证登录的有效性,P2:客户端关闭或者注销或者切换登录，
                 * 密钥会成功丢失)，密钥直至下次登录会被更新。
                 * 重复登录会更新密钥，使前一个密钥不可用，实现登录唯一性(简单的挤人下线)
                 */
                toClientMessage = accountController.scan10012(fromClientMessage.getData());
                break;
            case 10013: // 查询已登录账号基础信息
                toClientMessage = accountController.scan10013(fromClientMessage.getUserKey());
                break;
            case 10021: //查询汽车的基础信息
                toClientMessage = carController.scan10021(fromClientMessage.getData(), pid);
                break;
            case 10051: // 租车
                toClientMessage = recordController.scan10051(fromClientMessage.getData(), fromClientMessage.getUserKey());
                break;
            case 10052: // 还车
                toClientMessage = recordController.scan10052(fromClientMessage.getData(), fromClientMessage.getUserKey());
                break;
            case 10053: // 查看自己的订单
                toClientMessage = recordController.scan10053(fromClientMessage.getUserKey());
                break;
            case 20011: // 新增汽车
                toClientMessage = carController.scan20011(fromClientMessage.getData());
                break;
            case 20012: // 新增汽车品牌
                toClientMessage = carController.scan20012(fromClientMessage.getData());
                break;
            case 20013: // 新增汽车种类
                toClientMessage = carController.scan20013(fromClientMessage.getData());
                break;
            case 20014: // 新增汽车级别
                toClientMessage = carController.scan20014(fromClientMessage.getData());
                break;
            case 20021: // 查询所有汽车
                toClientMessage = carController.scan20021();
                break;
            case 200211: // 查询所有汽车 按特定方法排序
                toClientMessage = carController.scan200211(fromClientMessage.getData(), pid);
                break;
            case 20022: // 查询所有汽车品牌
                toClientMessage = carController.scan20022();
                break;
            case 20023: // 查询所有汽车种类
                toClientMessage = carController.scan20023();
                break;
            case 20024: // 查询所有汽车级别
                toClientMessage = carController.scan20024();
                break;
            case 20025: // 查询所有汽车信息
                toClientMessage = carController.scan20025();
                break;
            case 20031: // 更新汽车上下架状态
                toClientMessage = carController.scan20031(fromClientMessage.getData());
                break;
            case 20032: // 更新汽车价格
                toClientMessage = carController.scan20032(fromClientMessage.getData());
                break;
            case 20041: // 查看所有订单信息
                toClientMessage = recordController.scan20041();
                break;
                default:
                    toClientMessage = respError();
                    break;
        }
        //System.out.println(toClientMessage.toString());
        if (toClientMessage == null) {
            toClientMessage = respError();
        }
        return toClientMessage;
    }

    /**
     * @Description: 如果数据包格式与解析函数不一致，则返回此函数
     * @param []
     * @Return data.domain.ToClientMessage
     */
    private ToClientMessage respError() {
        ToClientMessage toClientMessage = new ToClientMessage();
        toClientMessage.setStatus(403);
        toClientMessage.setData("非法参数，调用失败。");
        return toClientMessage;
    }

    /**
     * @Description: 权限不足，访问被拒绝
     * @param []
     * @Return data.domain.ToClientMessage
     */
    private ToClientMessage respPermissionDenied() {
        ToClientMessage toClientMessage = new ToClientMessage();
        toClientMessage.setStatus(407);
        toClientMessage.setData("权限不足，访问被拒绝。");
        return toClientMessage;
    }

}
