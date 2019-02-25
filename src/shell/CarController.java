package shell;

import data.domain.*;
import data.entity.Car;
import data.entity.CarBrand;
import data.entity.CarCategory;
import data.entity.CarModel;
import service.CarService;
import service.impl.CarServiceImpl;
import util.JsonUtil;

import java.util.ArrayList;

public class CarController {

    private CarService carService = new CarServiceImpl();

    /**
     * @Description: 根据条件查询基础的车辆信息
     * @param [data]
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan10021(Object data,boolean pid) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            AllBaseCarDataDomain allBaseCarDataDomain = new AllBaseCarDataDomain();
            String[] strings = null;
            ArrayList<Car> carArrayList;
            try {
                RankCarDomain rankCarDomain = (RankCarDomain) JsonUtil.analysis(data, RankCarDomain.class);
                strings = new String[rankCarDomain.getRankStr().size()];
                rankCarDomain.getRankStr().toArray(strings);
                carArrayList = carService.findAllCarRank(10000, 1, 9999, pid, rankCarDomain.getCode(), strings);
            } catch (NullPointerException e) {
                carArrayList = carService.findAllCarRank(10000, 1, 9999, pid,-1, strings);
            }
            ArrayList<BeCutCar> beCutCarArrayList = new ArrayList<>();
            for (int i = 0; i < carArrayList.size(); i++) {
                BeCutCar beCutCar = new BeCutCar();
                beCutCar.setId(carArrayList.get(i).getId());
                beCutCar.setCarId(carArrayList.get(i).getCarId());
                beCutCar.setBrand(carArrayList.get(i).getBrand());
                beCutCar.setClassName(carArrayList.get(i).getClassName());
                beCutCar.setRankName(carArrayList.get(i).getRankName());
                beCutCar.setPrice(carArrayList.get(i).getPrice());
                beCutCar.setDayPrice(carArrayList.get(i).getDayPrice());
                beCutCar.setCashPledge(carArrayList.get(i).getCashPledge());
                beCutCar.setStatus(carArrayList.get(i).getStatus());
                beCutCarArrayList.add(beCutCar);
            }
            allBaseCarDataDomain.setCars(beCutCarArrayList);
            allBaseCarDataDomain.setCarBrands(carService.findAllCarBrand(10000,1,9999));
            allBaseCarDataDomain.setCarModels(carService.findAllCarModel(10000,1,9999));
            allBaseCarDataDomain.setCarCategories(carService.findAllCarCategory(10000,1,9999));
            toClientMessage.setStatus(200);
            toClientMessage.setData(JsonUtil.toJson(allBaseCarDataDomain));
            return toClientMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param [data]
     * @Description: 新增汽车
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20011(Object data) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            Car car = (Car) JsonUtil.analysis(data, Car.class);
            if (carService.addCar(car)) {
                toClientMessage.setStatus(200);
                toClientMessage.setData(carService.findAllCar(1000, 1, 10));
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
     * @param [data]
     * @Description: 新增汽车品牌
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20012(Object data) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            CarBrand carBrand = (CarBrand) JsonUtil.analysis(data, CarBrand.class);
            if (carService.addCarBrand(carBrand)) {
                toClientMessage.setStatus(200);
                toClientMessage.setData(carService.findAllCarBrand(1000, 1, 10));
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
     * @param [data]
     * @Description: 新增汽车类型
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20013(Object data) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            CarModel carModel = (CarModel) JsonUtil.analysis(data, CarModel.class);
            if (carService.addCarModel(carModel)) {
                toClientMessage.setStatus(200);
                toClientMessage.setData(carService.findAllCarModel(1000, 1, 10));
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
     * @param [data]
     * @Description: 新增汽车级别
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20014(Object data) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            CarCategory carCategory = (CarCategory) JsonUtil.analysis(data, CarCategory.class);
            if (carService.addCarCategory(carCategory)) {
                toClientMessage.setStatus(200);
                toClientMessage.setData(carService.findAllCarCategory(1000, 1, 10));
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
     * @param []
     * @Description: 查询所有汽车
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20021() {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            toClientMessage.setStatus(200);
            toClientMessage.setData(carService.findAllCar(10000, 1, 9999));
            return toClientMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param [data]
     * @Description: 根据条件查询所有汽车
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan200211(Object data, boolean pid) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            AllCarDataDomain allCarDataDomain = new AllCarDataDomain();
            RankCarDomain rankCarDomain = (RankCarDomain) JsonUtil.analysis(data, RankCarDomain.class);
            String[] strings = new String[rankCarDomain.getRankStr().size()];
            rankCarDomain.getRankStr().toArray(strings);
            allCarDataDomain.setCars(carService.findAllCarRank(10000, 1, 9999, pid, rankCarDomain.getCode(), strings));
            allCarDataDomain.setCarBrands(carService.findAllCarBrand(10000, 1, 9999));
            allCarDataDomain.setCarModels(carService.findAllCarModel(10000, 1, 9999));
            allCarDataDomain.setCarCategories(carService.findAllCarCategory(10000, 1, 9999));
            toClientMessage.setStatus(200);
            toClientMessage.setData(allCarDataDomain.toString());
            return toClientMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param []
     * @Description: 查询所有汽车品牌
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20022() {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            toClientMessage.setStatus(200);
            toClientMessage.setData(carService.findAllCarBrand(10000, 1, 9999));
            return toClientMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param []
     * @Description: 查询所有种类
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20023() {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            toClientMessage.setStatus(200);
            toClientMessage.setData(carService.findAllCarModel(10000, 1, 9999));
            return toClientMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param []
     * @Description: 查询所有汽车级别
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20024() {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            toClientMessage.setStatus(200);
            toClientMessage.setData(carService.findAllCarCategory(10000, 1, 9999));
            return toClientMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param []
     * @Description: 查询所有有关汽车的数据
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20025() {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            toClientMessage.setStatus(200);
            AllCarDataDomain allCarDataDomain = new AllCarDataDomain();
            allCarDataDomain.setCars(carService.findAllCar(10000, 1, 9999));
            allCarDataDomain.setCarBrands(carService.findAllCarBrand(10000, 1, 9999));
            allCarDataDomain.setCarModels(carService.findAllCarModel(10000, 1, 9999));
            allCarDataDomain.setCarCategories(carService.findAllCarCategory(10000, 1, 9999));
            toClientMessage.setData(allCarDataDomain.toString());
            return toClientMessage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param [data]
     * @Description: 更新上下架状态
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20031(Object data) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            SetCarIsLockDomain setCarIsLockDomain = (SetCarIsLockDomain) JsonUtil.analysis(data, SetCarIsLockDomain.class);
            if (carService.findStatusById(setCarIsLockDomain.getId()) != 0) {
                toClientMessage.setStatus(400);
                toClientMessage.setData("此车正被租凭，禁止下架！");
                return toClientMessage;
            }
            if (carService.updateCarIsLock(setCarIsLockDomain.getLock(), setCarIsLockDomain.getId())) {
                toClientMessage.setStatus(200);
                return toClientMessage;
            } else {
                toClientMessage.setStatus(201);
                return toClientMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 更新价格
     * @param [data]
     * @Return data.domain.ToClientMessage
     */
    public ToClientMessage scan20032(Object data) {
        try {
            ToClientMessage toClientMessage = new ToClientMessage();
            UpdateCarPriceDomain updateCarPriceDomain = (UpdateCarPriceDomain) JsonUtil.analysis(data, UpdateCarPriceDomain.class);
            if (carService.findStatusById(updateCarPriceDomain.getCarId()) != 0) {
                toClientMessage.setStatus(400);
                toClientMessage.setData("1.此车正被租凭，禁止修改价格！\n 2.此车不存在。");
                return toClientMessage;
            }
            if (carService.updateCarPrice(updateCarPriceDomain.getDay_price(), updateCarPriceDomain.getCarId())) {
                toClientMessage.setStatus(200);
                toClientMessage.setData("更新成功！");
                return toClientMessage;
            } else {
                toClientMessage.setStatus(201);
                toClientMessage.setData("更新失败！");
                return toClientMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
