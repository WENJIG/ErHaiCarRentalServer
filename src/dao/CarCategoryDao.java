package dao;

import data.entity.CarCategory;

import java.util.ArrayList;

/**
 * 汽车级别
 */
public interface CarCategoryDao {

    boolean add(CarCategory carCategory);

    ArrayList<CarCategory> findAll(int pageGross, int pageStart, int pageEnd);

}
