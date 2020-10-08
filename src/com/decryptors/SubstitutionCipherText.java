package com.decryptors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.decryptors.Decryptool.checkContradictions;

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
        List testDecryptions = super.testCommonWords(super.partialPlainText);


        checkContradictions(testDecryptions, super.cipherText);



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
