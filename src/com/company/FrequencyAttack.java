package com.company;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class FrequencyAttack {


    HashMap<Character,Double> letterFreq = new HashMap<>();
    Character commonLetters[] = {'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'u'};
    String commonDoubleLetters[] = {"ss", "ee", "tt", "ff", "ll", "mm", "oo"};
    String common2Letters[] = {"of", "to", "in", "it", "is", "be", "as", "at", "so", "we", "he", "by", "or", "on", "do", "if", "me", "my", "up", "an", "go", "no", "us", "am"};


    FrequencyAttack(){
        createLetterFreq();
    }

    private void createLetterFreq(){
        BufferedReader reader;
        int ASCIIchar = 97;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/com/company/letterFreq.txt"));
            String line = reader.readLine();
            while (line != null) {
                letterFreq.put((char)ASCIIchar,Double.parseDouble(line));
                // read next line
                line = reader.readLine();
                ASCIIchar+=1;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void attack2Letters(String encodedStr){
        HashMap encodedLetterFreq = getLetterFreq(encodedStr, 0.8);
        for(Character letter: commonLetters){
            System.out.print("");
        }
    }

    private HashMap getLetterFreq(String encodedRawStr, Double thresh){
        HashMap<Character,Double> letterFreq = new HashMap<>();
        String encodedStr = encodedRawStr.replaceAll("\\s+","");
        for(int i = 0; i<encodedStr.length(); i++){
            if(letterFreq.containsKey(encodedStr.charAt(i))){
                letterFreq.put(encodedStr.charAt(i), letterFreq.get(encodedStr.charAt(i))+1);
            }else{
                letterFreq.put(encodedStr.charAt(i), 1.0);
            }
        }
        for (Map.Entry<Character, Double> set : letterFreq.entrySet()) {
            Character key = set.getKey();
            letterFreq.put(key,set.getValue()/encodedStr.length());
            if(letterFreq.get(key)<thresh){
                letterFreq.remove(key);
            }
        }
        return letterFreq;
    }
}
