package com.decryptors.englishDict;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class EnglishDict {
    // number of letter -> character looking for -> position character in
    private final HashMap<Integer, HashMap<Character, HashMap<Integer, LinkedList>>> words = new HashMap<>();

    public EnglishDict() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/com/decryptors/englishDict/words.txt"));
            String line = reader.readLine();
            while (line != null) {
                for(int i =0; i<line.length(); i++){
                    if(!words.containsKey(line.length())){
                        words.put(line.length(), new HashMap<>());
                        words.get(line.length()).put(line.charAt(i), new HashMap<>());
                        words.get(line.length()).get(line.charAt(i)).put(i, new LinkedList());
                    }
                    else if(!words.get(line.length()).containsKey(line.charAt(i))){
                        words.get(line.length()).put(line.charAt(i), new HashMap<>());
                        words.get(line.length()).get(line.charAt(i)).put(i, new LinkedList());
                    }else if(!words.get(line.length()).get(line.charAt(i)).containsKey(i)){
                        words.get(line.length()).get(line.charAt(i)).put(i, new LinkedList());
                    }
                    words.get(line.length()).get(line.charAt(i)).get(i).add(line.toLowerCase());
                }

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List getWordByLengthCharacter(int length, Character c, int pos){

        return words.get(length).get(c).get(pos);
    }


    public List possibleWords(String x){
        List possibleWords = new ArrayList();
        for(int i = 0; i<x.length(); i++){
            if(x.charAt(i)!='_' && x.charAt(i)!= ' '){
                possibleWords.addAll(getWordByLengthCharacter(x.length(),x.charAt(i),i));
            }
        }
        possibleWords.add(x);
        return possibleWords;
    }
}
