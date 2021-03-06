package decryptors.englishDict;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class EnglishDict {
    // number of letter -> character looking for -> position character in
    private final HashMap<Integer, HashMap<Character, HashMap<Integer, LinkedList<String>>>> words = new HashMap<>();
    private final HashSet<String> wordList = new HashSet<>();


    public EnglishDict() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/decryptors/englishDict/100words.txt"));
            String line = reader.readLine();
            while (line != null) {
                if(!wordList.contains(line)){
                    wordList.add(line);
                }
                for(int i =0; i<line.length(); i++){
                    if(!words.containsKey(line.length())){
                        words.put(line.length(), new HashMap<>());
                        words.get(line.length()).put(line.charAt(i), new HashMap<Integer, LinkedList<String>>());
                        words.get(line.length()).get(line.charAt(i)).put(i, new LinkedList<>());
                    }
                    else if(!words.get(line.length()).containsKey(line.charAt(i))){
                        words.get(line.length()).put(line.charAt(i), new HashMap<Integer, LinkedList<String>>());
                        words.get(line.length()).get(line.charAt(i)).put(i, new LinkedList<>());
                    }else if(!words.get(line.length()).get(line.charAt(i)).containsKey(i)){
                        words.get(line.length()).get(line.charAt(i)).put(i, new LinkedList<String>());
                    }
                    words.get(line.length()).get(line.charAt(i)).get(i).add(line.toLowerCase());
                }

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //Retrieves all words that fit into a partial plaintext thats in the form of x___x_x. Something like that
    public List<String> getWordByLengthCharacter(int length, Character c, int pos){
        if(words.containsKey(length)){
            if(words.get(length).containsKey(c)){
                if(words.get(length).get(c).containsKey(pos)){
                    return words.get(length).get(c).get(pos);
                }
            }
        }


        return new LinkedList<>();
    }


    public Boolean isWord(String str){
        return wordList.contains(str);
    }

    public List<String> possibleWords(String x){
        int nKnownLetters = 0;
        HashMap<String,Integer> checkIntersect = new HashMap<>();
        List<String> possibleWords = new ArrayList<>();
        for(int i = 0; i<x.length(); i++){
            if(x.charAt(i)!='_' && x.charAt(i)!= ' '){
                nKnownLetters+=1;

                possibleWords.addAll(getWordByLengthCharacter(x.length(),x.charAt(i),i));

            }
        }

        possibleWords.forEach(posWord->{
            if(checkIntersect.containsKey(posWord)){
                checkIntersect.put(posWord,checkIntersect.get(posWord)+1);
            }else{
                checkIntersect.put(posWord,1);
            }
        });
        possibleWords.clear();
        for (Map.Entry mapElement : checkIntersect.entrySet()) {
            if((int)mapElement.getValue()==nKnownLetters){
                possibleWords.add(mapElement.getKey().toString());
            }
        }

        if(possibleWords.size()<=1){
            possibleWords.add(x);
        }
        System.out.println(possibleWords);



        return possibleWords;
    }
}
