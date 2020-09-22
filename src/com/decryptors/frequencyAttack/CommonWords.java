package com.decryptors.frequencyAttack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class CommonWords {
    private String[][] commonWords = new String[4][];


    public CommonWords(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/com/decryptors/frequencyAttack/commonWords.txt"));
            String line = reader.readLine();
            int iteration = 0;
            while (line != null) {
                line = line.replaceAll("\\s","");
                String[] words = line.split(",");
                commonWords[iteration] = words;
                line = reader.readLine();
                iteration+=1;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public HashMap<Integer, String[]> filteredCommonWords(Character letters[]){
        HashMap<Integer,String[]> filteredCommonWords = new HashMap<>();
        for(int i = 1; i<4;i++){
            filteredCommonWords.put(i+1,Arrays.stream(commonWords[i]).filter(x -> containsOneChar(letters,x)).toArray(String[]::new));
        }

        for(int i = 1; i<4;i++){
            System.out.println(Arrays.toString(filteredCommonWords.get(i+1)));
        }

        return filteredCommonWords;
    }


    private Boolean containsOneChar(Character letters[], String word){
        for(Character letter:letters){
            if(word.indexOf(letter)!=-1){
                return true;
            }
        }
        return false;

    }
}
