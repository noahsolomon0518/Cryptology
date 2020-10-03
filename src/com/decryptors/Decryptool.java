package com.decryptors;

import java.util.HashMap;

public class Decryptool {
    public static String letterDiff(Character plain[], Character cipher[]){
        System.out.println("Plaintext -> Ciphertext");
        for(int i = 0; i<cipher.length; i++){
            int key26 = mod26((int)cipher[i] - (int)plain[i]);
            System.out.println(plain[i] +  "+" + key26 + "=" + cipher[i]);
        }
        System.out.println("Ciphertext -> Plaintext");
        for(int i = 0; i<cipher.length; i++){
            int key26 = 26 - mod26((int)cipher[i] - (int)plain[i]);
            System.out.println(cipher[i] +  "+" + key26 + "=" + plain[i]);
        }
        return new String("Hello");
    }




    public static int mod26(Character letter){
        return mod26((int)letter);
    }

    public static int mod26(int num){
        if(num<0){
            int modNum = 26 + num - (26*(num/26));
            if(modNum == 26){
                modNum =0;
            }
            return modNum;
        }
        else{
            return  num - (num/26)*26;
        }
    }


    public static Character charMod26(int num){
        num = mod26(num);
        return (char)(97+num);
    }
}
