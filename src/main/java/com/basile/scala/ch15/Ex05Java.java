package com.basile.scala.ch15;

import java.io.IOException;

/**
 * Created by basile.duplessis on 09/01/14.
 */
public class Ex05Java {
    public static void main (String[] args){
        Ex05 myEx05 = new Ex05();

        try {
            String result = myEx05.fileToString("/ch15_ex05.txt");
            System.out.println(result);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
