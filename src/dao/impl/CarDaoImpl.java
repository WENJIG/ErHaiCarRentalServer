package dao.impl;

import dao.CarDao;
import dao.ResultHandle;
import data.entity.Car;

import java.util.ArrayList;

public class CarDaoImpl extends OracleDao implements CarDao {

    @Override
    public boolean addCar(Car car) {
        return reflectInit("insert into eh_car values(eh_car_seq.nextVal", car);
    }

    /**
     * @Description: 查询全部的car (分页总查询数，一页查询起始数，一页查询结束数)
     * @param [pageGross, pageStart, pageEnd]
     * @Return java.util.ArrayList<data.entity.Car>
     */
    @Override
    public ArrayList<Car> findAll(int pageGross, int pageStart, int pageEnd) {
        String tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d order by id asc";
        String sql = String.format(tempSql, pageGross, pageStart, pageEnd);
        return (ArrayList<Car>) baseFind(sql, findAllResultHandle());
    }

    /**
     * @Description: 根据查询条件 查询
     * @param [pageGross, pageStart, pageEnd, code, rankStr]
     * @Return java.util.ArrayList<data.entity.Car>
     */
    @Override
    public ArrayList<Car> findAllRank(int pageGross, int pageStart, int pageEnd, boolean pid, int code, String... rankStr) {
        String tempSql;
        String tempLock = "";
        if (!pid) {
            tempLock = "and is_lock=0";
        }
        String sql = null;
        switch (code) {
            case 1:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and brand=? %s order by id asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 14:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and brand=? %s order by to_number(day_price) asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 15:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and brand=? %s order by to_number(day_price) desc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 2:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and class=? %s order by id asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 24:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and class=? %s order by to_number(day_price) asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 25:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and class=? %s order by to_number(day_price) desc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 3:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and rank=? %s order by id asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 34:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and rank=? %s order by to_number(day_price) asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 35:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and rank=? %s order by to_number(day_price) desc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 4:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d %s order by to_number(day_price) asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 5:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d %s order by to_number(day_price) desc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 12:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and brand=? and class=? %s order by id asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 124:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and brand=? and class=? %s order by to_number(day_price) asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 125:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and brand=? and class=? %s order by to_number(day_price) desc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 123:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and brand=? and class=? and rank=? %s order by id asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 1234:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and brand=? and class=? and rank=? %s order by to_number(day_price) asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 1235:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and brand=? and class=? and rank=? %s order by to_number(day_price) desc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 23:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and class=? and rank=? %s order by id asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 234:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and class=? and rank=? %s order by to_number(day_price) asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
            case 235:
                tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and class=? and rank=? %s order by to_number(day_price) asc";
                sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                break;
                default:
                    tempSql = "select * from (select rowNum as rn,eh_car.* from eh_car where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d %s order by id asc";
                    sql = String.format(tempSql, pageGross, pageStart, pageEnd, tempLock);
                    break;
        }
        if (rankStr == null) {
            return (ArrayList<Car>) baseFind(sql, findAllResultHandle());
        } else {
            return (ArrayList<Car>) baseFind(sql, findAllResultHandle(), rankStr);
        }
    }

    @Override
    public long findIdByCarName(String carName) {
        String sql = "select id from eh_car where car_id=?";
        ResultHandle resultHandle = resultSet -> {
            long id = -1;
            while (resultSet.next()) {
                id = resultSet.getLong("id");
            }
            return id;
        };
        return (long) baseFind(sql, resultHandle, carName);
    }

    @Override
    public int findStatusById(long id) {
        String sql = "select status from eh_car where id=?";
        ResultHandle resultHandle = resultSet -> {
            int status = -1;
            while (resultSet.next()) {
                status = resultSet.getInt("status");
            }
            return status;
        };
        return (int) baseFind(sql, resultHandle, id);
    }

    @Override
    public String findCarPriceById(long id) {
        String sql = "select day_price from eh_car where id=?";
        ResultHandle resultHandle = resultSet -> {
            String price = null;
            while (resultSet.next()) {
                price = resultSet.getString("day_price");
            }
            return price;
        };
        return (String) baseFind(sql, resultHandle, id);
    }

    @Override
    public boolean updateCarIsLock(int lock, long id) {
        String sql = "update eh_car set is_lock=? where id=?";
        return baseUpdate(sql, lock, id);
    }

    @Override
    public boolean updateCarStatus(int status, long id) {
        String sql = "update eh_car set status=? where id=?";
        return baseUpdate(sql, status, id);
    }

    @Override
    public boolean updateCarPrice(String price, long id) {
        String sql = "update eh_car set day_price=? where id=?";
        return baseUpdate(sql, price, id);
    }

    private ResultHandle findAllResultHandle() {
        ResultHandle resultHandle = resultSet -> {
            ArrayList<Car> arrayList = new ArrayList();
            while (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setCarId(resultSet.getString("car_id"));
                car.setBrand(resultSet.getString("brand"));
                car.setClassName(resultSet.getString("class"));
                car.setRankName(resultSet.getString("rank"));
                car.setPrice(resultSet.getString("price"));
                car.setDayPrice(resultSet.getString("day_price"));
                car.setCashPledge(resultSet.getString("cash_pledge"));
                car.setStatus(resultSet.getInt("status"));
                car.setIsLock(resultSet.getInt("is_lock"));
                arrayList.add(car);
            }
            return arrayList;
        };
        return resultHandle;
    }

}
