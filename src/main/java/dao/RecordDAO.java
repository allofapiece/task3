package dao;

import entity.Record;
import entity.Request;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface RecordDAO {
    public void setRecords(ArrayList<Record> records);
    public void printAll();

    public String getLocale();
    public void setLocale(String locale);
    public List<Record> createRecords(Request request);
}
