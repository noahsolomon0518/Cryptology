package decryptors;

import dataCollection.FollowingWordProbs;
import decryptors.englishDict.EnglishDict;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class Decryptool {

    // *Checks whether there are contradictions in a potential decryption. As there might be overlapping letters between words they must be mapped to the same letter(At least in a substitute cipher)
    // *or there is a contradiction
    public static void checkContradictions(List _testDecryptions, String _cipherText){
        HashMap<Character, Character> contraMap = new HashMap<>();

        _testDecryptions.forEach( x ->{
            boolean isContra = false;
            String possibleMapping = x.toString();
            contraMap.clear();
            for(int i = 0; i<possibleMapping.length(); i++){
                if(contraMap.containsKey(_cipherText.charAt(i)) ){
                    if(possibleMapping.charAt(i) != contraMap.get(_cipherText.charAt(i)) && possibleMapping.charAt(i) != '_'){
                        isContra = true;
                        break;
                    }else if(possibleMapping.charAt(i) == '_'){
                        char[] chars = possibleMapping.toCharArray();
                        chars[i] = contraMap.get(_cipherText.charAt(i));
                        possibleMapping = String.valueOf(chars);
                    }

                }else if(possibleMapping.charAt(i)!='_'){
                    contraMap.put(_cipherText.charAt(i), possibleMapping.charAt(i));
                }

            }
            if(!isContra){
                System.out.println(possibleMapping + " is possible");
            }
        });
    }





    public static HashMap letterFrequency(String str) {
        HashMap<Character, Double> letterFreq = new HashMap<>();
        str = str.replaceAll("\\s+", "");
        for (int i = 0; i < str.length(); i++) {
            if (letterFreq.containsKey(str.charAt(i))) {
                letterFreq.put(str.charAt(i), letterFreq.get(str.charAt(i)) + 1);
            } else {
                letterFreq.put(str.charAt(i), 1.0);
            }
        }
        for (Map.Entry<Character, Double> entry : letterFreq.entrySet()) {
            letterFreq.put(entry.getKey(), entry.getValue() / str.length());
        }
        return letterFreq;
    }



    public static List findFittingWords(EnglishDict dict, String[] partialPlainText){
        return Arrays.stream(partialPlainText)
                .map(x->dict.possibleWords(x))
                .collect(Collectors.toCollection(LinkedList::new));
    }




    public static String substituteLetters(String text, Character[] ogLetters, Character[] subLetters) {
        String plainTextCharacters = "";
        text = text.toUpperCase();
        int length = ogLetters.length < subLetters.length ? ogLetters.length : subLetters.length;
        for (int i = 0; i < length; i++) {
            text = text.replaceAll(ogLetters[i].toString().toUpperCase(), "" + subLetters[i]);
            plainTextCharacters += subLetters[i];
        }
        text = text.replaceAll("[^" + plainTextCharacters + "\\s]", "_");
        System.out.println(text);
        return text;
    }



    public static List<HashMap<String,Double>> getFollowProbs(List<String> testDecryptions, FollowingWordProbs fwp){
        List<HashMap<String,Double>> orderedFollowProbs = new ArrayList<>();
        AtomicReference<Double> max = new AtomicReference<>(0.0);
        testDecryptions.forEach(x -> {
            List<String> testDecryptionSplit = Arrays.asList(x.split(" "));
            Double prob = 0.0;

            for(int i = 0; i<testDecryptionSplit.size()-1;i++){
                prob += fwp.getFollowWordProb(testDecryptionSplit.get(i), testDecryptionSplit.get(i+1));
            }
            if(prob> max.get()){
                System.out.println(x + ":" + prob);
                max.set(prob);
            }
            HashMap<String, Double> tmp = new HashMap<>();
            tmp.put(x,prob);
            orderedFollowProbs.add(tmp);
        });
        return new ArrayList<>();
    }

}
