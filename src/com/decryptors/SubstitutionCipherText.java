package com.decryptors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubstitutionCipherText extends CipherText{

    public SubstitutionCipherText(String _cipherText){
        super(_cipherText);
    }
    private Character cipherCharMap[];
    private Character plainCharMap[];
    public void setSubMap(Character cipherChar[], Character plainChar[]){
        cipherCharMap = cipherChar;
        plainCharMap = plainChar;
    }

    public void setSubMap(Character cipherChar, Character plainChar){
        cipherCharMap = new Character[]{cipherChar};
        plainCharMap = new Character[]{plainChar};
    }


    public void crackCode() {
        List testDecryptions = super.testCommonWords();
        System.out.println("TEST"+testDecryptions);
        checkContradictions(testDecryptions);



    }


    public void checkContradictions(List _testDecryptions){
        HashMap<Character, Character> contraMap = new HashMap<>();
        String _cipherText = super.cipherText;

        _testDecryptions.forEach( x ->{
            boolean isContra = false;
            String possibleMapping = x.toString();
            contraMap.clear();
            for(int i = 0; i<possibleMapping.length(); i++){
                if(contraMap.containsKey(_cipherText.charAt(i)) ){
                    if(possibleMapping.charAt(i) != contraMap.get(_cipherText.charAt(i))){
                        isContra = true;
                        break;
                    }

                }else{
                    contraMap.put(_cipherText.charAt(i), possibleMapping.charAt(i));
                }

            }
            if(!isContra){
                System.out.println(possibleMapping + " is possible");
            }
        });
    }

    //Can throw in multiple characters
    public String substitute(){
        String subText;
        String plainTextCharacters = "";
        subText = cipherText;
        int length = cipherCharMap.length<plainCharMap.length ? cipherCharMap.length:plainCharMap.length;
        for(int i = 0; i<length; i++){
            if(plainCharMap[i]!=cipherCharMap[i]){
                subText = subText.replaceAll(""+plainCharMap[i],"_");

            }
            System.out.println(subText);
            subText = subText.replaceAll(""+cipherCharMap[i], ""+plainCharMap[i]);
            System.out.println("Replace " + cipherCharMap[i] + " with" + plainCharMap[i]);
            System.out.println(subText);
            plainTextCharacters+=plainCharMap[i];
        }
        subText = subText.replaceAll("[^"+plainTextCharacters+"\\s]", "_");
        System.out.println(subText);

        super.setPartialPlain(subText);
        return subText;
    }
}
