//Type plaintext in lowercase without special characters, spaces, or punctuation

import com.decryptors.Decryptool;
import com.decryptors.CipherText;
import com.decryptors.SubstitutionCipherText;
import com.decryptors.englishDict.EnglishDict;
import com.decryptors.frequencyAttack.CommonWords;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        EnglishDict dict = new EnglishDict();
        System.out.println(dict.getWordByLengthCharacter(4,'r', 0));

        SubstitutionCipherText ct = new SubstitutionCipherText("glb obxiiv ifhbp zelzlixqb zxhb yly");
        ct.setSubMap('x',  'e');
        ct.substitute();
        ct.crackCode();

    }



}



