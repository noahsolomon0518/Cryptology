package com.decryptors;

import java.util.HashMap;
import java.util.List;

public class Decryptool {

    // *Checks whether there are contradictions in a potential decryption. As there might be overlapping letters between words they must be mapped to the same letter(At least in a substitute cipher)
    // *or there is a contradiction
    public static void checkContradictions(List _testDecryptions, String _cipherText){
        HashMap<Character, Character> contraMap = new HashMap<>();

        _testDecryptions.forEach( x ->{
            boolean isContra = false;
            String possibleMapping = x.toString();
            contraMap.clear();
            for(int i = 0; i<possibleMapping.length(); i++){
                if(contraMap.containsKey(_cipherText.charAt(i)) ){
                    if(possibleMapping.charAt(i) != contraMap.get(_cipherText.charAt(i)) && possibleMapping.charAt(i) != '_'){
                        isContra = true;
                        break;
                    }else if(possibleMapping.charAt(i) == '_'){
                        char[] chars = possibleMapping.toCharArray();
                        chars[i] = contraMap.get(_cipherText.charAt(i));
                        possibleMapping = String.valueOf(chars);
                    }

                }else if(possibleMapping.charAt(i)!='_'){
                    contraMap.put(_cipherText.charAt(i), possibleMapping.charAt(i));
                }

            }
            if(!isContra){
                System.out.println(possibleMapping + " is possible");
            }
        });
    }


    //Based on cipherText placement of letters, fills in partial plain text
    public static void fillIn(String _partialPlainText, String _cipherText){
        for(int i = 0; i<_cipherText.length(); i++){

        }
    }



}
