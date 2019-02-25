package dao.impl;

import dao.CarBrandDao;
import dao.ResultHandle;
import data.entity.CarBrand;

import java.util.ArrayList;

public class CarBrandDaoImpl extends OracleDao implements CarBrandDao {

    @Override
    public boolean add(CarBrand carBrand) {
        return reflectInit("insert into eh_brand values(eh_brand_seq.nextVal", carBrand);
    }

    /**
     * @Description: 查询所有汽车品牌 (分页总查询数，一页查询起始数，一页查询结束数)
     * @param [pageGross, pageStart, pageEnd]
     * @Return java.util.ArrayList<data.entity.CarBrand>
     */
    @Override
    public ArrayList<CarBrand> findAll(int pageGross, int pageStart, int pageEnd) {
        String tempSql = "select * from (select rowNum as rn,eh_brand.* from eh_brand where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d";
        String sql = String.format(tempSql, pageGross, pageStart, pageEnd);
        ResultHandle resultHandle = resultSet -> {
            ArrayList<CarBrand> arrayList = new ArrayList<>();
            while (resultSet.next()) {
                CarBrand carBrand = new CarBrand();
                carBrand.setId(resultSet.getLong("id"));
                carBrand.setBrandName(resultSet.getString("name"));
                arrayList.add(carBrand);
            }
            return arrayList;
        };
        return (ArrayList<CarBrand>) baseFind(sql, resultHandle);
    }

}
