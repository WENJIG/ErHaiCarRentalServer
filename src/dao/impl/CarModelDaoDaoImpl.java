package dao.impl;

import dao.CarModelDao;
import dao.ResultHandle;
import data.entity.CarModel;

import java.util.ArrayList;

public class CarModelDaoDaoImpl extends OracleDao implements CarModelDao {

    @Override
    public boolean add(CarModel carModel) {
        return reflectInit("insert into eh_model values(eh_model_seq.nextVal", carModel);
    }

    @Override
    public ArrayList<CarModel> findAll(int pageGross, int pageStart, int pageEnd) {
        String tempSql = "select * from (select rowNum as rn,eh_model.* from eh_model where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d";
        String sql = String.format(tempSql, pageGross, pageStart, pageEnd);
        ResultHandle resultHandle = resultSet -> {
            ArrayList<CarModel> arrayList = new ArrayList<>();
            while (resultSet.next()) {
                CarModel carModel = new CarModel();
                carModel.setModelId(resultSet.getLong("id"));
                carModel.setModelName(resultSet.getString("name"));
                arrayList.add(carModel);
            }
            return arrayList;
        };
        return (ArrayList<CarModel>) baseFind(sql, resultHandle);
    }
}
