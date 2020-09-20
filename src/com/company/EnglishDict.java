package com.company;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class EnglishDict {

    private HashMap<String, Integer> words = new HashMap<>();

    EnglishDict() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/com/company/words.txt"));
            String line = reader.readLine();
            while (line != null) {
                words.put(line,1);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean isWord(String str) {
        return words.containsKey(str);
    }
}
