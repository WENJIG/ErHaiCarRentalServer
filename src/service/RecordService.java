package service;

import data.entity.Record;

import java.util.ArrayList;

public interface RecordService {

    boolean addRecord(Record record);

    ArrayList<Record> findAllRecord(int pageGross, int pageStart, int pageEnd);

    ArrayList<Record> findAllRecordByAccountId(int pageGross, int pageStart, int pageEnd, long id);

    Record findById(long id);

    boolean updateIntimeById(String date, long id);

    boolean updateOuttimeById(String date, long id);

    boolean updateAllPriceById(String allPrice, long id);

}
