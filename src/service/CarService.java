package service;

import data.entity.Car;
import data.entity.CarBrand;
import data.entity.CarCategory;
import data.entity.CarModel;

import java.util.ArrayList;

public interface CarService {

    boolean addCar(Car car);

    boolean addCarBrand(CarBrand carBrand);

    boolean addCarModel(CarModel carModel);

    boolean addCarCategory(CarCategory carCategory);

    ArrayList<Car> findAllCar(int pageGross, int pageStart, int pageEnd);

    ArrayList<CarBrand> findAllCarBrand(int pageGross, int pageStart, int pageEnd);

    ArrayList<CarModel> findAllCarModel(int pageGross, int pageStart, int pageEnd);

    ArrayList<CarCategory> findAllCarCategory(int pageGross, int pageStart, int pageEnd);

    ArrayList<Car> findAllCarRank(int pageGross, int pageStart, int pageEnd, boolean pid, int code, String... rankStr);

    long findIdByCarName(String carName);

    int findStatusById(long id);

    String findCarPriceById(long id);

    boolean updateCarIsLock(int lock, long id);

    boolean updateCarStatus(int status, long id);

    boolean updateCarPrice(String price, long id);

}
