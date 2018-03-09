package entity;

public class Request {
    private String region;
    private String typeOfMistake;
    private int percent;
    private int amountOfRecords;
    private String typeOfFile;

    public Request() {

    }

    public String getRegion() {
        return region;
    }

    public String getTypeOfMistake() {
        return typeOfMistake;
    }

    public int getPercent() {
        return percent;
    }

    public int getAmountOfRecords() {
        return amountOfRecords;
    }

    public String getTypeOfFile() {
        return typeOfFile;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setTypeOfMistake(String typeOfMistake) {
        this.typeOfMistake = typeOfMistake;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setAmountOfRecords(int amountOfRecords) {
        this.amountOfRecords = amountOfRecords;
    }

    public void setTypeOfFile(String typeOfFile) {
        this.typeOfFile = typeOfFile;
    }
}
