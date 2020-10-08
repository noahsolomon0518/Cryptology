package com.dataCollection;

import java.io.*;

public class FollowingWordProbs {
    public FollowingWordProbs() throws IOException {
        cleanTxt();
    }


    public void cleanTxt() throws IOException {

        FileWriter writer = new FileWriter("src/com/dataCollection/bookNew.txt");
        FileReader fileReader = new FileReader("src/com/dataCollection/book.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            if(line!=""){
                line = line.replaceAll("[.,?/!@#$%^&*()\"\';:1-9<>}{|+_-]","");
                line = line.toLowerCase();
                writer.write(line+" ");
            }
        }

        bufferedReader.close();
        writer.close();
    }

}
