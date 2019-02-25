package dao;

import data.entity.Car;

import java.util.ArrayList;

public interface CarDao {

    boolean addCar(Car car);

    ArrayList<Car> findAll(int pageGross, int pageStart, int pageEnd);

    ArrayList<Car> findAllRank(int pageGross, int pageStart, int pageEnd, boolean pid, int code, String... rankStr);

    long findIdByCarName(String carName);

    int findStatusById(long id);

    String findCarPriceById(long id);

    boolean updateCarIsLock(int lock, long id);

    boolean updateCarStatus(int status, long id);

    boolean updateCarPrice(String price, long id);

}
