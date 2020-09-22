//Type plaintext in lowercase without special characters, spaces, or punctuation

import com.cryptologyMethods.EnglishAtbash;
import com.cryptologyMethods.PolybiusSquare;
import com.cryptologyMethods.SpartanScytale;
import com.decryptors.CipherText;
import com.decryptors.frequencyAttack.CommonWords;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        CommonWords cw  = new CommonWords();
        cw.filteredCommonWords(new Character[]{'e'});
        CipherText ct = new CipherText("uijt jt b dppm xbz up ep");
        ct.setSubMap(new Character[]{'u', 'i'}, new Character[]{'t','h'});
        System.out.println(ct.substitute());
        System.out.println(Arrays.toString(ct.testCommonWords()));
    }



}



