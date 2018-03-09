package util.FileReaderImlp;

import util.CustomFileReader;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileReader implements CustomFileReader {
    private File file;
    private String line;

    public FileReader(File file){
        this.file = file;
    }
    public String readRandomString() {
        try{
            RandomAccessFile readFile = new RandomAccessFile(file, "r");
            readFile.seek(((int)(Math.random() * (file.length()-20))));
            line = readFile.readLine();
            line = new String(readFile.readLine().getBytes("ISO-8859-1"), "windows-1251");
            return line;
        }catch (IOException ex){
            ex.printStackTrace();
            System.out.println(file.getAbsolutePath());
        }
        return null;
    }
}
