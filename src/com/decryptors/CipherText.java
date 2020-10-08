package com.decryptors;

import com.decryptors.englishDict.EnglishDict;
import com.decryptors.frequencyAttack.CommonWords;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CipherText {
    public String cipherText;
    public String partialPlainText;
    private HashMap<Character, Double> letterFreq = new HashMap<>();
    private List testDecryptions = new ArrayList<String>();
    public CipherText(String _cipherText){
        cipherText = _cipherText;
    }
    private String tmp[];
    private EnglishDict dict = new EnglishDict();


    public void setPartialPlain(String _partialPlainText){
        partialPlainText = _partialPlainText;
    }



    //Based on partialPlainText, method test if common words fit into string. Unknown parts of string are "_".
    public List testCommonWords(String _partialPlainText){
        String subTextByWord[] = _partialPlainText.split("\\s");

        List subTextByWordList = Arrays.stream(subTextByWord)
                .map(x->dict.possibleWords(x))
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println(subTextByWordList);
        String tmp[] = new String[subTextByWordList.size()];
        recCollectToStrings(subTextByWordList,0, tmp);

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

    private void createLetterFreq(){
        letterFreq.clear();
        BufferedReader reader;
        int ASCIIchar = 97;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/com/decryptors/frequencyAttack/letterFreq.txt"));
            String line = reader.readLine();
            while (line != null) {
                letterFreq.put((char)ASCIIchar,Double.parseDouble(line));
                // read next line
                line = reader.readLine();
                ASCIIchar+=1;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
