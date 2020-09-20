package com.company;
import java.util.*;

public class EnglishAtbash {

    private HashMap<Character,Character> encryptKey = new HashMap<>();
    private HashMap<Character,Character> decryptKey = new HashMap<>();

    EnglishAtbash(){

        for(int i = 97; i<=122; i++){
            encryptKey.put((char) i, (char)(122-(i-97)));
            decryptKey.put((char) (122-(i-97)), (char) i);
        }

    }



    public String encrypt(String str){
        String encoded = "";
        for(int i = 0; i< str.length(); i++){
            encoded += encryptKey.get(str.charAt(i));
        }
        System.out.println(str + " encoded to:" + encoded);
        return encoded;
    }

    public String decrypt(String str){
        String decoded = "";
        for(int i = 0; i< str.length(); i++){
            decoded += decryptKey.get(str.charAt(i));
        }
        System.out.println(str + " decoded to:" + decoded);
        return decoded;
    }

}
