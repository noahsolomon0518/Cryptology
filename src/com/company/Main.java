package com.company;

//Type plaintext in lowercase without special characters, spaces, or punctuation

public class Main {

    public static void main(String[] args) {

        FrequencyAttack attack = new FrequencyAttack();
        attack.attack2Letters("te uht dqdv ojdlo at jrd ohud vaqdv jnakd wev ajo tej jrd ohud vaqdv htg rdo tej jrd ohud uht rdvhkfajso");
    }


    public static void englishAtbashTest(){
        EnglishAtbash encryptor = new EnglishAtbash();
        encryptor.encrypt("hello");
        encryptor.decrypt("svool");
    }
    public static void spartanScytaleTest(){
        SpartanScytale encryptor = new SpartanScytale(4);
        encryptor.decrypt("AUSHTMKEHMNIEYORNCCSSIKTITOASYVTALEUCERERTTS");
    }
    public static void polybiusSquareTest(){
        PolybiusSquare encryptor = new PolybiusSquare();
        encryptor.encrypt("iamhere");
        encryptor.decrypt("24113323154315");
    }

}



