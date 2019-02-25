package dao;

import data.entity.CarBrand;

import java.util.ArrayList;

/**
 * 汽车品牌
 */
public interface CarBrandDao {

    boolean add(CarBrand carBrand);

    ArrayList<CarBrand> findAll(int pageGross, int pageStart, int pageEnd);

}
