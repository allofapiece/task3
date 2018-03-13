package service;

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
import java.util.ArrayList;
import java.util.List;

public class Service extends HttpServlet {
    private static final String RELATIVE_PATH = "D:\\Soft\\apache-tomcat-9.0.2\\webapps\\test\\out\\artifacts\\test_war_exploded\\WEB-INF\\classes\\textbases\\";
    private static final String OUTPUT_PATH = "D:\\Soft\\apache-tomcat-9.0.2\\webapps\\test\\out\\artifacts\\test_war_exploded\\outputfiles\\";

    private Request request;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Mistake mistake = new Mistake();
        if(req.getParameter("insert") != null){
            mistake.addType("insert");
        }
        if(req.getParameter("replace") != null){
            mistake.addType("replace");
        }
        if(req.getParameter("delete") != null){
            mistake.addType("delete");
        }
        mistake.setMistakesAmount(Integer.parseInt(req.getParameter("mistakes-amount")));
        int recordsAmount = Integer.parseInt(req.getParameter("amount"));
        String locale = req.getParameter("region");
        request = new Request();
        request.setRegion(req.getParameter("region"));
        request.setAmountOfRecords(Integer.parseInt(req.getParameter("amount")));
        request.setMistakesAmount(Integer.parseInt(req.getParameter("mistakes-amount")));
        request.setTypeOfFile(req.getParameter("type"));
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
                    String line = mistake.makeMistakesInString(readCities.readRandomString()+"; "+readStreets.readRandomString()+"; "+
                            readFirstNames.readRandomString()+"; "+readSurnames.readRandomString()+"; "+readNumbers.readRandomString());
                    Paragraph purpose = new Paragraph(line, font);
                    purpose.setSpacingAfter(8);
                    document.add(purpose);
                }
                document.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        } else if(request.getTypeOfFile().equals("csv")){
            try {
                OutputStream os = new FileOutputStream(OUTPUT_PATH+"data.csv"); // класс записи байтов в файл
                for(int i=0; i<recordsAmount; i++){
                    String line = mistake.makeMistakesInString(readCities.readRandomString()+";"+readStreets.readRandomString()+";"+
                            readFirstNames.readRandomString()+";"+readSurnames.readRandomString()+";"+readNumbers.readRandomString());
                    os.write(line.getBytes("Cp1251") );
                    os.write("\n".getBytes());
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/result.jsp");
        requestDispatcher.forward(req, resp);
    }






}
