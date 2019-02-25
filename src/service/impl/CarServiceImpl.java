package service.impl;

import dao.CarBrandDao;
import dao.CarCategoryDao;
import dao.CarDao;
import dao.CarModelDao;
import dao.impl.CarBrandDaoImpl;
import dao.impl.CarCategoryDaoImpl;
import dao.impl.CarDaoImpl;
import dao.impl.CarModelDaoDaoImpl;
import data.entity.Car;
import data.entity.CarBrand;
import data.entity.CarCategory;
import data.entity.CarModel;
import service.CarService;

import java.util.ArrayList;

public class CarServiceImpl implements CarService {

    private CarDao carDao = new CarDaoImpl();
    private CarBrandDao carBrandDao = new CarBrandDaoImpl();
    private CarModelDao carModelDao = new CarModelDaoDaoImpl();
    private CarCategoryDao carCategoryDao = new CarCategoryDaoImpl();

    @Override
    public boolean addCar(Car car) {
        return carDao.addCar(car);
    }

    @Override
    public boolean addCarBrand(CarBrand carBrand) {
        return carBrandDao.add(carBrand);
    }

    @Override
    public boolean addCarModel(CarModel carModel) {
        return carModelDao.add(carModel);
    }

    @Override
    public boolean addCarCategory(CarCategory carCategory) {
        return carCategoryDao.add(carCategory);
    }

    @Override
    public ArrayList<Car> findAllCar(int pageGross, int pageStart, int pageEnd) {
        return carDao.findAll(pageGross, pageStart, pageEnd);
    }

    @Override
    public ArrayList<CarBrand> findAllCarBrand(int pageGross, int pageStart, int pageEnd) {
        return carBrandDao.findAll(pageGross, pageStart, pageEnd);
    }

    @Override
    public ArrayList<CarModel> findAllCarModel(int pageGross, int pageStart, int pageEnd) {
        return carModelDao.findAll(pageGross, pageStart, pageEnd);
    }

    @Override
    public ArrayList<CarCategory> findAllCarCategory(int pageGross, int pageStart, int pageEnd) {
        return carCategoryDao.findAll(pageGross, pageStart, pageEnd);
    }

    @Override
    public long findIdByCarName(String carName) {
        return carDao.findIdByCarName(carName);
    }

    @Override
    public int findStatusById(long id) {
        return carDao.findStatusById(id);
    }

    @Override
    public String findCarPriceById(long id) {
        return carDao.findCarPriceById(id);
    }

    @Override
    public boolean updateCarIsLock(int lock, long id) {
        return carDao.updateCarIsLock(lock, id);
    }

    @Override
    public boolean updateCarStatus(int status, long id) {
        return carDao.updateCarStatus(status, id);
    }

    @Override
    public boolean updateCarPrice(String price, long id) {
        return carDao.updateCarPrice(price, id);
    }

    @Override
    public ArrayList<Car> findAllCarRank(int pageGross, int pageStart, int pageEnd, boolean pid, int code, String... rankStr) {
        return carDao.findAllRank(pageGross, pageStart, pageEnd, pid, code, rankStr);
    }
}
