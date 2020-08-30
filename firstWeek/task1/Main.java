package com.company;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String genome = new String();
        genome = in.next();

        int k = in.nextInt();
        int l = in.nextInt();
        int t = in.nextInt();
        Set<String> freqPat = new HashSet<>();
        int count = 0;


        for (int i = 0; i < genome.length()-l+1; i++){
            String subStr1 = genome.substring(i, i+l);
            for(int j = 0; j < subStr1.length()-k+1; j++){
                String subStr2 = genome.substring(j+i, j+k);
                count = 0;
                for (int p = 0; p < subStr1.length()-k+1; p++) {
                    if (subStr1.substring(p, p + k).equals(subStr2)) count++;
                }
                if (count>=t) freqPat.add(subStr2);
            }
        }


        for(int i = 0; i < freqPat.toArray().length; i++)
            System.out.print(freqPat.toArray()[i]+" ");
    }
}