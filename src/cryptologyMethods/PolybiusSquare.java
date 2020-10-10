package cryptologyMethods;

import java.util.*;

public class PolybiusSquare {

    HashMap<Character, Integer> encryptKey = new HashMap<>();
    HashMap<Integer, Character> decryptKey = new HashMap<>();


    public PolybiusSquare(){
        int curAsciiLetter = 97;
        for(int y = 1; y<5+1; y++){
            for(int x = 1; x<5+1; x++){
                char letter = (char)curAsciiLetter;
                int keyInt = Integer.parseInt(Integer.toString(y)+Integer.toString(x));
                encryptKey.put(letter, keyInt);
                decryptKey.put(keyInt, letter);
                curAsciiLetter+=1;
            }
        }
        //z = 56

        encryptKey.put('z',56);

    }

    public String encrypt(String str){
        String encoded = "";
        for(int i = 0; i<str.length(); i++){
            encoded+= Integer.toString(encryptKey.get(str.charAt(i)));
        }
        System.out.println(encoded);
        return encoded;
    }

    public String decrypt(String str){
        String decoded = "";
        String curNum;
        for(int i = 0; i<str.length(); i+=2){
            curNum = String.valueOf(str.charAt(i))+String.valueOf(str.charAt(i+1));
            decoded+= decryptKey.get(Integer.parseInt(curNum));
        }
        System.out.println(decoded);
        return decoded;
    }

}
