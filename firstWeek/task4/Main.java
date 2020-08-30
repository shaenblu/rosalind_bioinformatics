package com.company;

import java.util.*;

public class Main{
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        String genome = new String();
        genome = in.next();
        
        int k = in.nextInt();
        int d = in.nextInt();
        
        
        ArrayList<String> base = allKMers(k);
        ArrayList<String> mostFreq = new ArrayList<>();
        int maxx = 0;
        
        for(int i = 0; i < base.size(); i++) {
            int count = countOfPattern(base.get(i), genome, d);
            if (count > maxx){
                mostFreq.clear();
                mostFreq.add(base.get(i));
                maxx = count;
            } else {
                if (count==maxx){
                    mostFreq.add(base.get(i));
                }
            }
            
        }
        
        for (int i =0; i < mostFreq.size(); i++){
            System.out.println(mostFreq.get(i));
        }
        
        
        
        
    }
    
    public static int countOfPattern(String str1, String str2, int d){
        int count1 = 0;
        int count = 0;
        for (int i = 0; i < str2.length()-str1.length()+1; i++){
            String s = str2.substring(i, i+str1.length());
            if (s.equals(str1)) count++;
            else {
                for (int j = 0; j < str1.length(); j++) {
                    if (str1.charAt(j) != s.charAt(j))
                        count1++;
                }
                if (count1 <= d) count++;
            }
            count1 = 0;
        }
        return count;
    }
    
    
    public static ArrayList<String> allKMers(int k){
        
        char[] kmer = new char[4];
        kmer[0] = 'A';
        kmer[1] = 'C';
        kmer[2] = 'G';
        kmer[3] = 'T';
        
        //int countt = (int)Math.pow(4, k);
        //String[][] out = new String[countt][countt];
        ArrayList<String> out = new ArrayList<String>();
        int c = 0;
        char[] l = new char[100];
        
        
        generateKMer(l, out, c, k, kmer);
        
        return out;
    }
    
    public static void generateKMer(char[] X, ArrayList<String> out, int k, int K, char[] mas){
        
        int t = 0;
        String str = new String();
        if (k == K){
            for (int i = 0; i < k; i++)
                str = str + X[i];
            //System.out.println(str);
            out.add(str);
        } else {
            for (int j = 0; j < 4; j++) {
                X[k] = mas[j];
                generateKMer(X, out, k+1, K, mas);
            }
        }
        
    }
    
}