package service;

import java.util.ArrayList;
import java.util.List;

 public class Mistake {
    private int mistakesAmount;
    private List<String> types;
    public Mistake(){
        types = new ArrayList<String>();
    }
     public int getMistakesAmount() {
         return mistakesAmount;
     }

     public void setMistakesAmount(int mistakesAmount) {
         this.mistakesAmount = mistakesAmount;
     }

     protected char getNewChar(int c){
        int r;
        if(c > 47 && c <58){ //Numbers
            r = (int)(48 + Math.random()*10);
        } else if(c > 64 && c < 91){ //Upper english characters
            r = (int)(65 + Math.random()*26);
        } else if(c > 96 && c < 123){ //Lower english characters
            r = (int)(97 + Math.random()*26);
        } else if((c > 1039 && c < 1071) || c == 1025 || c == 1038){ //Upper russian and belarussian characters
            r = (int)(1040 + Math.random()*32);
        } else if((c > 1071 && c < 1104) || c == 1105 || c == 1118 || c == 1110){//Lower russian and belarussian characters
            r = (int)(1072 + Math.random()*32);
        } else return (char)c;
        return (char)r;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
    public void addType(String type){
        this.types.add(type);
    }

     private double calculateFrequency(int length){
         return length > mistakesAmount ? length / mistakesAmount : 1;
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
    public String makeMistakesInString(String target){
         if(types.isEmpty()) return target;

        double frequency = calculateFrequency(target.length());
        double sum = 1;
        StringBuffer result = new StringBuffer(target);
        for(int i=0; i<result.length(); i++){
            if(sum >= frequency){
                String type = types.get((int)(Math.random()*types.size()));
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
