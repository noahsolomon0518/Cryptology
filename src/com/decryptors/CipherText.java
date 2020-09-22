package com.decryptors;

import com.decryptors.frequencyAttack.CommonWords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class CipherText {
    private String cipherText;
    private String subText;
    private Character cipherCharMap[];
    private Character plainCharMap[];
    private HashMap<Character, Double> letterFreq = new HashMap<>();
    private CommonWords cw = new CommonWords();

    public CipherText(String _cipherText){
        cipherText = _cipherText;
    }


    public void setSubMap(Character cipherChar[], Character plainChar[]){
        cipherCharMap = cipherChar;
        plainCharMap = plainChar;
    }

    public void setSubMap(Character cipherChar, Character plainChar){
        cipherCharMap = new Character[]{cipherChar};
        plainCharMap = new Character[]{plainChar};
    }




    //Can throw in multiple characters
    public String substitute(){
        String plainTextCharacters = "";
        subText = cipherText;
        int length = cipherCharMap.length<plainCharMap.length ? cipherCharMap.length:plainCharMap.length;
        for(int i = 0; i<length; i++){
            if(plainCharMap[i]!=cipherCharMap[i]){
                subText = subText.replaceAll(""+plainCharMap[i],"_");
                System.out.println(subText);
                System.out.println("Replace " + plainCharMap[i] + " with _");
            }
            System.out.println(subText);
            subText = subText.replaceAll(""+cipherCharMap[i], ""+plainCharMap[i]);
            System.out.println("Replace " + cipherCharMap[i] + " with" + plainCharMap[i]);
            System.out.println(subText);
            plainTextCharacters+=plainCharMap[i];
        }
        System.out.println(subText);
        subText = subText.replaceAll("[^"+plainTextCharacters+"\\s]", "_");
        System.out.println(subText);
        return subText;
    }


    public String[] testCommonWords(){
        HashMap<Integer, String[]> commonWords = cw.filteredCommonWords(plainCharMap);

        String subTextByWord[] = subText.split("\\s");

        subTextByWord = Arrays.stream(subTextByWord)
                .map(x->possibleCommonWord(x,commonWords.get(x.length())))
                .toArray(String[]::new);

        return subTextByWord;
    }

    private String possibleCommonWord(String testWord, String commonWord[]){
        if(commonWord!=null && !testWord.replaceAll("_", "").equals("")) {
            for (String word : commonWord) {
                for (int i = 0; i < testWord.length(); i++) {
                    if (testWord.charAt(i) != word.charAt(i) && testWord.charAt(i) != '_') {
                        break;
                    }
                }
                return word;
            }
        }
        return testWord;
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

}
