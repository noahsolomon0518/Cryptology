package decryptors;

import dataCollection.FollowingWordProbs;
import decryptors.englishDict.EnglishDict;

import java.io.IOException;
import java.util.*;

public class CipherText {
    public String cipherText;
    public String partialPlainText;
    private final List<String> testDecryptions = new ArrayList<String>();
    public CipherText(String _cipherText){
        cipherText = _cipherText;
    }
    private final EnglishDict dict = new EnglishDict();


    public void setPartialPlain(String _partialPlainText){
        partialPlainText = _partialPlainText;
    }



    //Based on partialPlainText, method test if common words fit into string. Unknown parts of string are "_".
    public List testCommonWords(String _partialPlainText) throws IOException {
        String partialPlainTextTextByWord[] = _partialPlainText.split("\\s");
        List subTextByWordList = Decryptool.findFittingWords(dict, partialPlainTextTextByWord);
        recCollectToStrings(subTextByWordList,0, new String[subTextByWordList.size()]);
        Decryptool.getFollowProbs(testDecryptions, new FollowingWordProbs());
        return testDecryptions;
    }

    private void recCollectToStrings(List<List> _subTextByWordList, int wordPos, String _tmp[]){
        _subTextByWordList.get(wordPos).forEach( x -> {
            _tmp[wordPos] = x.toString();
            if(wordPos< _subTextByWordList.size()-1){
                recCollectToStrings(_subTextByWordList, wordPos+1, _tmp);
            }else{
                testDecryptions.add(String.join(" ",_tmp));
            }
        });

    }


}
