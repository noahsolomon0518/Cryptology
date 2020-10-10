package decryptors;

import java.io.IOException;
import java.util.List;

import static decryptors.Decryptool.checkContradictions;

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


    public void crackCode() throws IOException {
        List testDecryptions = super.testCommonWords(super.partialPlainText);
        checkContradictions(testDecryptions, super.cipherText);



    }




    //Can throw in multiple characters
    public String substitute(){
        String subText;
        subText = Decryptool.substituteLetters(cipherText, cipherCharMap, plainCharMap);
        super.setPartialPlain(subText);
        return subText;
    }
}
