package com.decryptors.frequencyAttack;

import java.util.*;

public class Util {


    public static HashMap letterFrequency(String str){
        HashMap<Character, Double> letterFreq = new HashMap<>();
        str = str.replaceAll("\\s+","");
        for(int i = 0; i<str.length(); i++){
            if(letterFreq.containsKey(str.charAt(i))){
                letterFreq.put(str.charAt(i), letterFreq.get(str.charAt(i))+1);
            }else{
                letterFreq.put(str.charAt(i), 1.0);
            }
        }
        for (Map.Entry<Character, Double> entry : letterFreq.entrySet()) {
            letterFreq.put(entry.getKey(), entry.getValue()/str.length());
        }
        return letterFreq;
    }





}
