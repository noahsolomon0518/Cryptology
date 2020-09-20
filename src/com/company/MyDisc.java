package com.company;

import java.util.*;

public class MyDisc {

    HashMap<Character, Character> encyrptKey = new HashMap<>();
    HashMap<Character, Character> decyrptKey = new HashMap<>();
    EnglishDict dict = new EnglishDict();
    ArrayList<String> allValidWordsPerShift = new ArrayList<>();


    MyDisc(int totShift){

        shift(totShift);
    }

    public void shift(int totShift){
        for(int i = 97; i<122+1; i++){
            int curChar = (i+totShift)%123;
            if(curChar<97){
                curChar+=97;
            }
            encyrptKey.put((char)i,(char)curChar);
            decyrptKey.put((char)curChar,(char)i);
        }


    }

    public String encrypt(String str){
        String encoded = "";
        for(int i = 0; i<str.length(); i++){
            encoded += encyrptKey.get(str.charAt(i));
        }
        return encoded;
    }


    public String decrypt(String str){
        String decoded = "";
        for(int i = 0; i<str.length(); i++){
            decoded += decyrptKey.get(str.charAt(i));
        }
        return decoded;
    }


    public void crackCode(String encodedStr){
        System.out.println("\n[Decoding]: Encoded string is "+ encodedStr + "\n");
        int mostWords = 0;
        int shiftCode = 0;

        for(int i = 0; i<26;i++){
            allValidWordsPerShift.removeAll(allValidWordsPerShift);
            shift(i);
            String possibleDecrypt = decrypt(encodedStr);
            wordIteration(possibleDecrypt);
            if(allValidWordsPerShift.size()>mostWords){
                mostWords=allValidWordsPerShift.size();
                shiftCode = i;
            }

        }


        System.out.println("Most likely the shift code is: " + shiftCode);
        shift(shiftCode);
        System.out.println("With this shift code, "+ encodedStr + " -------> " + decrypt(encodedStr) + "\n\n\n");
    }

    private void wordIteration(String str){
        for(int i = 3; i<str.length(); i++){

            String possibleWord = str.substring(0,i);
            if(dict.isWord(possibleWord)){
                allValidWordsPerShift.add(possibleWord);
            }

        }
        if(str.length()>1){
            wordIteration(str.substring(1));
        }
    }



}
