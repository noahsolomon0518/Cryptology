//Type plaintext in lowercase without special characters, spaces, or punctuation

import com.cryptologyMethods.EnglishAtbash;
import com.decryptors.Decryptool;
import com.decryptors.CipherText;
import com.decryptors.SubstitutionCipherText;
import com.decryptors.englishDict.EnglishDict;
import com.decryptors.frequencyAttack.CommonWords;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        SubstitutionCipherText ct = new SubstitutionCipherText("dsviv blf gsviv ziv yvvm xlnv lm");
        ct.setSubMap(new Character[]{'v','l','n','x'},  new Character[]{'e','o','m','c'});
        ct.substitute();
        ct.crackCode();

    }



}



