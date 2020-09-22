package com.cryptologyMethods;

public class SpartanScytale {
    int nColChars;

    public SpartanScytale(int _nColChars){
        nColChars = _nColChars;
    }


    public String decrypt(String str){
        String[] rows = new String[nColChars];
        String encoded = "";
        for(int i = 0; i<str.length(); i++){
            if(i<4){
                rows[i] = "";
            }
            rows[i%4] += str.charAt(i);
        }
        for(int i = 0; i<nColChars; i++){
            encoded+=rows[i];
        }
        System.out.println(encoded);
        return encoded;
    }

}

