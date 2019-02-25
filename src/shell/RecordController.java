package shell;

import data.domain.ToClientMessage;
import data.entity.Record;
import service.CarService;
import service.RecordService;
import service.impl.CarServiceImpl;
import service.impl.RecordServiceImpl;
import service.impl.ServerManager;
import util.JsonUtil;
import util.MoneyUtil;
import util.SystemDateFormat;

/**
 * 订单
 */
public class RecordController {

    private CarService carService = new CarServiceImpl();
    private RecordService recordService = new RecordServiceImpl();

    /**
     * @Description: 租车并添加订单
     * @param [data, key]
     * @Return data.domain.ToClientMessage
     */
    public synchronized ToClientMessage scan10051(Object data, String key) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            long uid = ServerManager.LOGIN_STATUS.getLoginManager().find(key);
            if (uid == -1) {
                toClientMessage.setStatus(404);
                toClientMessage.setData("非法访问，用户未登录。");
                return toClientMessage;
            }
            Record record = (Record) JsonUtil.analysis(data, Record.class);
            if (carService.findStatusById(record.getCarId()) != 0) {
                toClientMessage.setStatus(201);
                toClientMessage.setData("此车已被出租。");
                return toClientMessage;
            }
            // 用服务器默认数据覆盖,以免非法数据传入
            record.setAccountId(uid);
            record.setOutTime(SystemDateFormat.getSystemPreciseDate());
            record.setInTime(null);
            record.setAllPrice("还未产生");
            record.setDayPrice(carService.findCarPriceById(record.getCarId()));
            if (carService.updateCarStatus(1, record.getCarId()) && recordService.addRecord(record)) {
                toClientMessage.setStatus(200);
                toClientMessage.setData("租车成功！订单已受理。");
                return toClientMessage;
            } else {
                toClientMessage.setStatus(201);
                toClientMessage.setData("租车失败！");
                return toClientMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 还车并修改订单
     * @param [data, key]
     * @Return data.domain.ToClientMessage
     */
    public synchronized ToClientMessage scan10052(Object data, String key) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            long uid = ServerManager.LOGIN_STATUS.getLoginManager().find(key);
            long rid = Long.parseLong(String.valueOf(data));
            Record record = recordService.findById(rid);
            if (record == null) {
                toClientMessage.setStatus(404);
                toClientMessage.setData("没有此订单。");
                return toClientMessage;
            }
            if (record.getAccountId() != uid) {
                toClientMessage.setStatus(403);
                toClientMessage.setData("非法访问。你不是此订单的创建者。");
                return toClientMessage;
            }
            if (record.getInTime() != null) {
                toClientMessage.setData(201);
                toClientMessage.setData("此订单已结束。");
                return toClientMessage;
            }
            long tempDay = SystemDateFormat.daySub(record.getOutTime(), SystemDateFormat.getSystemPreciseDate());
            long day = tempDay >= 1 ? tempDay : 1;
            String allPrice;
            try {
                allPrice = MoneyUtil.multiplication(record.getDayPrice(), String.valueOf(day)).toString();
            } catch (NumberFormatException e) {
                toClientMessage.setData(500);
                toClientMessage.setData("还车失败！计算价格时参数错误！出现此错误请联系管理员修改。");
                return toClientMessage;
            }
            if (recordService.updateIntimeById(SystemDateFormat.getSystemPreciseDate(), record.getId())
                && recordService.updateAllPriceById(allPrice, record.getId())
                && carService.updateCarStatus(0, record.getCarId())) {
                toClientMessage.setData(200);
                toClientMessage.setData("还车成功！订单已结束。");
                return toClientMessage;
            } else {
                toClientMessage.setData(500);
                toClientMessage.setData("还车失败！请重试。");
                return toClientMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 查看自己的订单
     * @param [key]
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan10053(String key) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            toClientMessage.setStatus(200);
            long uid = ServerManager.LOGIN_STATUS.getLoginManager().find(key);
            if (uid != -1) {
                toClientMessage.setData(JsonUtil.toJson(recordService.findAllRecordByAccountId(10000,1,9999,uid)));
            } else {
                toClientMessage.setData("非法访问，未登录账号。");
            }
            return toClientMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 查询所有订单
     * @param []
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20041() {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            toClientMessage.setStatus(200);
            toClientMessage.setData(JsonUtil.toJson(recordService.findAllRecord(10000,1,9999)));
            return toClientMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
