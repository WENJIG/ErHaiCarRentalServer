package dao.impl;

import dao.RecordDao;
import dao.ResultHandle;
import data.entity.Record;
import util.SystemDateFormat;

import java.util.ArrayList;

public class RecordDaoImpl extends OracleDao implements RecordDao {


    @Override
    public boolean addRecord(Record record) {
        String sql = "insert into eh_record(id,account_id,car_id,out_time,day_price,all_price) values(eh_record_seq.nextVal,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?)";
        return baseSave(sql, record.getAccountId(), record.getCarId(), record.getOutTime(), record.getDayPrice(), record.getAllPrice());
    }

    @Override
    public ArrayList<Record> findAllRecord(int pageGross, int pageStart, int pageEnd) {
        String tempSql = "select * from (select rowNum as rn,eh_record.* from eh_record where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d order by id asc";
        String sql = String.format(tempSql, pageGross, pageStart, pageEnd);
        return (ArrayList<Record>) baseFind(sql, findAllResultHandle());
    }

    @Override
    public ArrayList<Record> findAllRecordByAccountId(int pageGross, int pageStart, int pageEnd, long id) {
        String tempSql = "select * from (select rowNum as rn,eh_record.* from eh_record where rownum<=%d) tb where tb.rn>=%d and tb.rn<=%d and account_id=? order by id asc";
        String sql = String.format(tempSql, pageGross, pageStart, pageEnd);
        return (ArrayList<Record>) baseFind(sql, findAllResultHandle(), id);
    }

    @Override
    public Record findById(long id) {
        String sql = "select * from eh_record where id=?";
        ResultHandle resultHandle = resultSet -> {
            Record record = null;
            while (resultSet.next()) {
                record = new Record();
                record.setId(resultSet.getLong("id"));
                record.setAccountId(resultSet.getLong("account_id"));
                record.setCarId(resultSet.getLong("car_id"));
                record.setOutTime(SystemDateFormat.getSystemPreciseDate2(resultSet.getTimestamp("out_time")));
                record.setInTime(SystemDateFormat.getSystemPreciseDate2(resultSet.getTimestamp("in_time")));
                record.setDayPrice(resultSet.getString("day_price"));
                record.setAllPrice(resultSet.getString("all_price"));
            }
            return record;
        };
        return (Record) baseFind(sql, resultHandle, id);
    }

    @Override
    public boolean updateIntimeById(String date, long id) {
        String sql = "update eh_record set in_time=to_date(?,'yyyy-mm-dd hh24:mi:ss') where id=?";
        return baseUpdate(sql, date, id);
    }

    @Override
    public boolean updateOuttimeById(String date, long id) {
        String sql = "update eh_record set out_time=to_date(?,'yyyy-mm-dd hh24:mi:ss') where id=?";
        return baseUpdate(sql, date, id);
    }

    @Override
    public boolean updateAllPriceById(String allPrice, long id) {
        String sql = "update eh_record set all_price=? where id=?";
        return baseUpdate(sql, allPrice, id);
    }

    private ResultHandle findAllResultHandle() {
        ResultHandle resultHandle = resultSet -> {
            ArrayList<Record> arrayList = new ArrayList<>();
            while (resultSet.next()) {
                Record record = new Record();
                record.setId(resultSet.getLong("id"));
                record.setAccountId(resultSet.getLong("account_id"));
                record.setCarId(resultSet.getLong("car_id"));
                record.setOutTime(SystemDateFormat.getSystemPreciseDate2(resultSet.getTimestamp("out_time")));
                record.setInTime(SystemDateFormat.getSystemPreciseDate2(resultSet.getTimestamp("in_time")));
                record.setDayPrice(resultSet.getString("day_price"));
                record.setAllPrice(resultSet.getString("all_price"));
                arrayList.add(record);
            }
            return arrayList;
        };
        return resultHandle;
    }
}
