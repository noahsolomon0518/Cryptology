package com.decryptors;

import java.util.*;

public class AttackUtil {


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

    public static Character[] rankKeys(HashMap<Character,Double> hm){

        class CharacterMapping{
            public Character letter;
            public Double number;
        }

        CharacterMapping charMap[] = new CharacterMapping[26];
        int i;
        int y;
        for (Map.Entry<Character, Double> entry : hm.entrySet()) {
            i = 0;
            CharacterMapping curCharMap = new CharacterMapping();
            curCharMap.letter = entry.getKey();
            curCharMap.number = entry.getValue();
            while(charMap[i]!=null){
                if(curCharMap.number>charMap[i].number){
                    CharacterMapping tmp = curCharMap;
                    curCharMap = charMap[i];
                    charMap[i] = tmp;
                }
                i++;
            }
            y = 0;
            while(charMap[i+y]!=null){
                CharacterMapping tmp = charMap[i+y];
                charMap[i+y] = charMap[i+y+1];
                charMap[i+y+1] = tmp;
                y++;
            }
            charMap[i] = curCharMap;

        }

        Character rankedCharacters[] = new Character[26];
        for(int x = 0; x<26; x++){
            if(charMap[x]!=null){
                rankedCharacters[x] = charMap[x].letter;
            }
        }



        return rankedCharacters;



    }












}
