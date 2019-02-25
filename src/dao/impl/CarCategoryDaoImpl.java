package dao.impl;

import dao.CarCategoryDao;
import dao.ResultHandle;
import data.entity.CarCategory;

import java.util.ArrayList;

public class CarCategoryDaoImpl extends OracleDao implements CarCategoryDao {

    @Override
    public boolean add(CarCategory carCategory) {
        return reflectInit("insert into eh_category values(eh_category_seq.nextVal", carCategory);
    }

    @Override
    public ArrayList<CarCategory> findAll(int pageGross, int pageStart, int pageEnd) {
        String tempSql = "select * from (select rowNum as rn,eh_category.* from eh_category where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d";
        String sql = String.format(tempSql, pageGross, pageStart, pageEnd);
        ResultHandle resultHandle = resultSet -> {
            ArrayList<CarCategory> arrayList = new ArrayList<>();
            while (resultSet.next()) {
                CarCategory carCategory = new CarCategory();
                carCategory.setId(resultSet.getLong("id"));
                carCategory.setCategoryName(resultSet.getString("name"));
                arrayList.add(carCategory);
            }
            return arrayList;
        };
        return (ArrayList<CarCategory>) baseFind(sql, resultHandle);
    }

}
