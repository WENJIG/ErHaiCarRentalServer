package dao;

import data.entity.CarModel;

import java.util.ArrayList;

/**
 * 汽车种类
 */
public interface CarModelDao {

    boolean add(CarModel carModel);

    ArrayList<CarModel> findAll(int pageGross, int pageStart, int pageEnd);

}
