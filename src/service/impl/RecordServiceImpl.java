package service.impl;

import dao.RecordDao;
import dao.impl.RecordDaoImpl;
import data.entity.Record;
import service.RecordService;

import java.util.ArrayList;

public class RecordServiceImpl implements RecordService {

    private RecordDao recordDao = new RecordDaoImpl();

    @Override
    public boolean addRecord(Record record) {
        return recordDao.addRecord(record);
    }

    @Override
    public ArrayList<Record> findAllRecord(int pageGross, int pageStart, int pageEnd) {
        return recordDao.findAllRecord(pageGross, pageStart, pageEnd);
    }

    @Override
    public ArrayList<Record> findAllRecordByAccountId(int pageGross, int pageStart, int pageEnd, long id) {
        return recordDao.findAllRecordByAccountId(pageGross, pageStart, pageEnd, id);
    }

    @Override
    public Record findById(long id) {
        return recordDao.findById(id);
    }

    @Override
    public boolean updateIntimeById(String date, long id) {
        return recordDao.updateIntimeById(date, id);
    }

    @Override
    public boolean updateOuttimeById(String date, long id) {
        return recordDao.updateOuttimeById(date, id);
    }

    @Override
    public boolean updateAllPriceById(String allPrice, long id) {
        return recordDao.updateAllPriceById(allPrice, id);
    }
}
