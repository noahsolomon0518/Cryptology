package com.decryptors.frequencyAttack;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import com.decryptors.frequencyAttack.Util;



public class FrequencyAttack {


    HashMap<Character,Double> letterFreq = new HashMap<>();
    Character commonLetters[] = {'e', 't', 'a', 'o', 'i', 'n', 's', 'h', 'r', 'd', 'l', 'u'};
    String commonDoubleLetters[] = {"ss", "ee", "tt", "ff", "ll", "mm", "oo"};
    String common2Letters[] = {"of", "to", "in", "it", "is", "be", "as", "at", "so", "we", "he", "by", "or", "on", "do", "if", "me", "my", "up", "an", "go", "no", "us", "am"};


    public FrequencyAttack(){
        createLetterFreq();
    }

    private void createLetterFreq(){
        BufferedReader reader;
        int ASCIIchar = 97;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/com/decryptors/frequencyAttack/letterFreq.txt"));
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



    public void attackCommonLetters(String str, double thresh){
        HashMap<Character,Double> letterFreq = Util.letterFrequency(str);
        System.out.print(letterFreq);
    }


}
