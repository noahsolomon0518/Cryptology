package com.decryptors;

public class VigenereCipherText extends CipherText{
    public String encryptionKey;
    public VigenereCipherText(String _cipherText){
        super(_cipherText);
    }

    public void setEncryptionKey(String _key){
        encryptionKey = _key;
    }


}
