//Type plaintext in lowercase without special characters, spaces, or punctuation

import cryptologyMethods.EnglishAtbash;
import dataCollection.FollowingWordProbs;
import decryptors.SubstitutionCipherText;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        EnglishAtbash ab = new EnglishAtbash();

        SubstitutionCipherText ct = new SubstitutionCipherText(ab.encrypt("what are you doing today i am doing my homework so please do not bother me"));
        ct.setSubMap(new Character[]{'v','l','n','x'},  new Character[]{'e','o','m','c'});
        ct.substitute();
        ct.crackCode();


        FollowingWordProbs wordProbs = new FollowingWordProbs();


    }



}



