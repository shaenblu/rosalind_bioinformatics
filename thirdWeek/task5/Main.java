package com.company;

import java.util.*;



public class Main{
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int d = in.nextInt();
        
        
        ArrayList<String> Kmers = new ArrayList<>();
        
        while (in.hasNext()) {
            String currSeq = in.next();
            Kmers.add(currSeq);
        }
        
        ArrayList<String> first = new ArrayList<>();
        ArrayList<String> second = new ArrayList<>();
        
        for (int i = 0; i<Kmers.size(); i++){
            String[] strr= Kmers.get(i).split("\\|");
            first.add(strr[0]);
            second.add(strr[1]);
        }
        
        String genome1 = first.get(0);
        String suffix = first.get(0).substring(1);
        for (int i = 1; i < first.size(); i++){
            String prefix = first.get(i).substring(0,k-1);
            if (suffix.equals(prefix)) {
                genome1 += first.get(i).substring(k - 1, k);
            }
            suffix = first.get(i).substring(1);
        }
        //System.out.println(genome1);
        
        String genome2 = second.get(0);
        String suffix1 = second.get(0).substring(1);
        for (int i = 1; i < second.size(); i++) {
            String prefix1 = second.get(i).substring(0, k - 1);
            if (suffix1.equals(prefix1)) {
                genome2 += second.get(i).substring(k - 1, k);
            }
            suffix1 = second.get(i).substring(1);
        }
        //System.out.println(genome2);
        
        int len = genome2.length();
        String res = genome1;
        if (genome1.substring(k+d).equals(genome2.substring(0, len-k-d))){
            res += genome2.substring(len-k-d);
        }
        
        System.out.println(res);
        
    }
}

