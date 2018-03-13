package entity;

public class Request {
    private String region;
    private String typeOfMistake;
    private int amountOfRecords;
    private int mistakesAmount;
    private String typeOfFile;

    public Request() {

    }

    public String getRegion() {
        return region;
    }




    public int getAmountOfRecords() {
        return amountOfRecords;
    }

    public String getTypeOfFile() {
        return typeOfFile;
    }


    public int getMistakesAmount() {
        return mistakesAmount;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setMistakesAmount(int mistakesAmount) {
        this.mistakesAmount = mistakesAmount;
    }

    public void setAmountOfRecords(int amountOfRecords) {
        this.amountOfRecords = amountOfRecords;
    }

    public void setTypeOfFile(String typeOfFile) {
        this.typeOfFile = typeOfFile;
    }
}
