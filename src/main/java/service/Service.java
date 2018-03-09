package service;


import au.com.bytecode.opencsv.CSVWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import dao.RecordDAO;
import entity.Record;
import entity.Request;
import util.CustomFileReader;
import util.FileReaderImlp.FileReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

public class Service extends HttpServlet {
    private static final String RELATIVE_PATH = "D:\\Soft\\apache-tomcat-9.0.2\\webapps\\test\\out\\artifacts\\test_war_exploded\\WEB-INF\\classes\\textbases\\";
    private static final String OUTPUT_PATH = "D:\\Soft\\apache-tomcat-9.0.2\\webapps\\test\\out\\artifacts\\test_war_exploded\\outputfiles\\";
    private List<Record> records;

    private Request request;
    private RecordDAO recordDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int recordsAmount = Integer.parseInt(req.getParameter("amount"));
        String locale = req.getParameter("region");
        request = new Request();
        request.setRegion(req.getParameter("region"));
        request.setTypeOfMistake(req.getParameter("mistake"));
        request.setPercent(Integer.parseInt(req.getParameter("percent")));
        request.setAmountOfRecords(Integer.parseInt(req.getParameter("amount")));
        request.setTypeOfFile(req.getParameter("type"));
        /*DAOFactory daoFactory = DAOFactory.getInstance();
        recordDAO = daoFactory.getRecordDAO();
        records = recordDAO.createRecords(request);*/
        CustomFileReader readCities = new FileReader(new File(RELATIVE_PATH+locale+".cities.txt"));
        CustomFileReader readStreets = new FileReader(new File(RELATIVE_PATH + locale + ".streets.txt"));
        CustomFileReader readFirstNames = new FileReader(new File(RELATIVE_PATH+locale+".name.txt"));
        CustomFileReader readSurnames = new FileReader(new File(RELATIVE_PATH+locale+".surname.txt"));
        CustomFileReader readNumbers = new FileReader(new File(RELATIVE_PATH+locale+".numbers.txt"));
        if(request.getTypeOfFile().equals("pdf")){
            try{
                Document document = new Document();
                PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_PATH+"data.pdf"));
                document.open();
                Font font = new Font(BaseFont.createFont("c:/windows/fonts/arial.ttf",
                        "Cp1251", BaseFont.EMBEDDED),10, Font.BOLD);
                for(int i=0; i<recordsAmount; i++){
                    String line = makeMistakesInString(readCities.readRandomString()+"; "+readStreets.readRandomString()+"; "+
                            readFirstNames.readRandomString()+"; "+readSurnames.readRandomString()+"; "+readNumbers.readRandomString(), request.getTypeOfMistake());
                    Paragraph purpose = new Paragraph(line, font);
                    purpose.setSpacingAfter(8);
                    document.add(purpose);
                    //System.out.println(makeMistakesInString(records.get(i).getCity()+" "+records.get(i).getStreet()+" "+
                    //        records.get(i).getFirstName()+" "+records.get(i).getSurname()));
                }
                document.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        } else if(request.getTypeOfFile().equals("csv")){
            try {
                OutputStream os = new FileOutputStream(OUTPUT_PATH+"data.csv"); // класс записи байтов в файл

                String city, street, firstName, surname, phone;
                for(int i=0; i<recordsAmount; i++){
                    String line = makeMistakesInString(readCities.readRandomString()+";"+readStreets.readRandomString()+";"+
                            readFirstNames.readRandomString()+";"+readSurnames.readRandomString()+";"+readNumbers.readRandomString(), request.getTypeOfMistake());
                    os.write(line.getBytes("Cp1251") );
                    os.write("\n".getBytes());
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/result.jsp");
        requestDispatcher.forward(req, resp);
        //recordDAO.printAll();
    }


    private double calculateFrequency(int lenghtOfString){
        //int mistakeAmountInData = (int)(Math.ceil(lenghtOfString*request.getPercent()/100+1));
        double frequency = 100/request.getPercent();
        return frequency;
    }
    private char getNewChar(int c){
        int r;
        if(c > 47 && c <58){ //Numbers
            r = (int)(48 + Math.random()*10);
        } else if(c > 64 && c < 91){ //Upper english characters
            r = (int)(65 + Math.random()*26);
        } else if(c > 96 && c < 123){ //Lower english characters
            r = (int)(97 + Math.random()*26);
        } else if((c > 1039 && c < 1071) || c == 1025 || c == 1038){ //Upper russian and belarussian characters
            r = (int)(1040 + Math.random()*32);
        } else if((c > 1071 && c < 1104) || c == 1105 || c == 1118){//Lower russian and belarussian characters
            r = (int)(1072 + Math.random()*32);
        } else return (char)c;
        return (char)r;
    }

    private boolean isSpecialSymbol(char c){
        String symbols = "+-,;(). ";
        for(int i=0; i<symbols.length(); i++){
            if(c == symbols.charAt(i)){
                return true;
            }
        }
        return false;
    }
    
    public String makeMistakesInString(String target, String type){
        double frequency = calculateFrequency(target.length());
        double sum = 1;
        StringBuffer result = new StringBuffer(target);
        for(int i=0; i<result.length(); i++){
            if(sum > frequency){
                if(type.equals("replace")){
                    result.setCharAt(i, getNewChar(result.charAt(i)));
                } else if(type.equals("insert")){
                    if(!isSpecialSymbol(result.charAt(i))){
                        result.insert(i+1,getNewChar(result.charAt(i)));
                    } else {
                        continue;
                    }
                } else if(type.equals("delete")){
                    if(!isSpecialSymbol(result.charAt(i))){
                        result.deleteCharAt(i);
                    } else {
                        continue;
                    }
                }
                sum -= frequency;
            }
            sum++;
        }
        return result.toString();
    }
}
