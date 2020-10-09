package com.dataCollection;

import com.decryptors.englishDict.EnglishDict;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FollowingWordProbs {
    public HashMap<String, HashMap<String, Double>> followWord;
    EnglishDict dict = new EnglishDict();
    public FollowingWordProbs() throws IOException {
        List<String> bookTextList = textToList("src/com/dataCollection/book.txt");
        HashMap<String, Integer> nOccurWord =  getWordFreq(bookTextList);
        followWord = followWordProbs(bookTextList, nOccurWord);

    }


    private HashMap<String, Integer> getWordFreq(List<String> listOfStrings) throws IOException {


        HashMap<String, Integer> wordFreq= new HashMap<>();
        for(int i =0; i<listOfStrings.size(); i++){
                if (wordFreq.containsKey(listOfStrings.get(i))) {
                    wordFreq.put(listOfStrings.get(i), wordFreq.get(listOfStrings.get(i)) + 1);
                } else {
                    wordFreq.put(listOfStrings.get(i), 1);
                }

        }
        return wordFreq;

    }


    private List<String> textToList(String src) throws IOException {
        FileReader fileReader = new FileReader(src);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String fullText = "";
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            if(line!=""){
                line = line.replaceAll("[.,?/!@#$%^&*()\"\';:1-9<>}{|+_-]","").toLowerCase();
                fullText += line+" ";
            }
        }
        bufferedReader.close();
        String fullTextStrings[] = fullText.split(" ");
        return Arrays.stream(fullTextStrings)
                .map(x->x.replaceAll(" ",""))
                .filter(x -> !x.equals(""))
                .collect(Collectors.toList());

    }


    private HashMap<String, HashMap<String, Double>> followWordProbs(List<String> textList, HashMap<String, Integer> nOccurences){
        HashMap<String, HashMap<String, Double>> wordProb = new HashMap<>();
        for(int i = 0; i<textList.size()-1; i++){
            String curWord = textList.get(i);
            String nextWord =  textList.get(i+1);

                if(!wordProb.containsKey(curWord)){
                    wordProb.put(curWord, new HashMap<>());
                    wordProb.get(curWord).put(nextWord, 0.0);
                }
                if(!wordProb.get(curWord).containsKey(nextWord)){
                    wordProb.get(curWord).put(nextWord, 0.0);
                }
                wordProb.get(curWord).put(nextWord, wordProb.get(curWord).get(nextWord)+1);


        }
        wordProb.forEach((curWordMap, nextWordMap) -> {
            nextWordMap.forEach((nextWord,freq)->{
                wordProb.get(curWordMap).put(nextWord,freq/nOccurences.get(curWordMap));
            });
        });
        System.out.println(wordProb);
        return wordProb;
    }

}
