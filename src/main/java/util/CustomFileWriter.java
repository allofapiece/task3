package util;

import entity.Record;

import java.util.ArrayList;

public interface CustomFileWriter {
    public void writeRecordInFile(ArrayList<Record> records);
}
