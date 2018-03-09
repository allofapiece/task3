package dao.DAOImpl;


import dao.RecordDAO;
import entity.Record;
import entity.Request;
import util.CustomFileReader;
import util.FileReaderImlp.FileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class RecordDAOImpl implements RecordDAO {
    private ArrayList<Record> records;
    private String locale;
    private List<FileReader> files;
    private static final String RELATIVE_PATH = "D:\\Soft\\apache-tomcat-9.0.2\\webapps\\test\\src\\main\\resources\\textbases\\";
    public RecordDAOImpl() {
        records = new ArrayList<Record>();
    }
    public RecordDAOImpl(int size) {
        records = new ArrayList<Record>(size);
    }

    public void addNewRecord(Record record){
        records.add(record);
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setRecords(ArrayList<Record> records){
        this.records = records;
    }

    public void printAll(){
        System.out.println("Done");
        for(Record record : records){
            System.out.println(record.getFirstName()+" "+record.getSurname()+" "+record.getCity()+" "+record.getStreet());
        }
    }

    public List<Record> createRecords(Request request){
        setLocale(request.getRegion());
        int amountOfRecords = request.getAmountOfRecords();
        records.ensureCapacity(amountOfRecords);
        CustomFileReader readCities = new FileReader(new File(RELATIVE_PATH+locale+".cities.txt"));
        CustomFileReader readStreets = new FileReader(new File(RELATIVE_PATH + locale + ".streets.txt"));
        CustomFileReader readFirstNames = new FileReader(new File(RELATIVE_PATH+locale+".name.txt"));
        CustomFileReader readSurnames = new FileReader(new File(RELATIVE_PATH+locale+".surname.txt"));
        CustomFileReader readNumbers = new FileReader(new File(RELATIVE_PATH+locale+".numbers.txt"));
        for(int i=0; i<amountOfRecords; i++) {
            Record record = new Record();
            record.setCity(readCities.readRandomString());
            record.setStreet(readStreets.readRandomString());
            record.setFirstName(readFirstNames.readRandomString());
            record.setSurname(readSurnames.readRandomString());
            record.setNumber(readNumbers.readRandomString());
            records.add(record);
            System.out.println(i);
        }
        /*createCities();
        createStreets();
        createFirstNames();
        createSurnames();
        createNumbers();*/
        /*addNewSourceFiles();
        for(int i=0; i<amountOfRecords; i++){
            Record record = new Record();
            record.setCity(files.get(0).readRandomString());
            record.setFirstName(files.get(1).readRandomString());
            record.setSurname(files.get(2).readRandomString());
            record.setNumber(files.get(3).readRandomString());
            record.setStreet(files.get(4).readRandomString());
            records.add(record);
            System.out.println(i);
        }*/

        return records;
    }

    public void addNewSourceFiles(){
        files = new ArrayList<FileReader>();
        String cities = locale+".cities.txt";
        String firstNames = locale+".name.txt";;
        String surnames = locale+".surname.txt";;
        String phones = locale+".numbers.txt";;
        String streets = locale+".streets.txt";;

        files.add(new FileReader(new File(RELATIVE_PATH+cities)));
        files.add(new FileReader(new File(RELATIVE_PATH+firstNames)));
        files.add(new FileReader(new File(RELATIVE_PATH+surnames)));
        files.add(new FileReader(new File(RELATIVE_PATH+phones)));
        files.add(new FileReader(new File(RELATIVE_PATH+streets)));
    }
}
