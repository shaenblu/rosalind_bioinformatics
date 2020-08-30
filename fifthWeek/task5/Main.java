package com.company;

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.*;



public class Main{
    //static ArrayList<String> por = new ArrayList<>();
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
        
        
        //String[][] str1 = new String[2][Kmers.size()];
        for (int i = 0; i<Kmers.size(); i++){
            String[] strr= Kmers.get(i).split("\\|");
            first.add(strr[0]);
            second.add(strr[1]);
        }
        
        //System.out.println(first.size());
        
        //System.out.println(second.size());
        
        
        String genome1 = first.get(0);
        String suffix = first.get(0).substring(1);
        for (int i = 1; i < first.size(); i++){
            String prefix = first.get(i).substring(0,k-1);
            if (suffix.equals(prefix)) {
                genome1 += first.get(i).substring(k - 1, k);
            }
            suffix = first.get(i).substring(1);
        }
        String genome2 = second.get(0);
        String suffix1 = second.get(0).substring(1);
        for (int i = 1; i < second.size(); i++) {
            String prefix1 = second.get(i).substring(0, k - 1);
            if (suffix1.equals(prefix1)) {
                genome2 += second.get(i).substring(k - 1, k);
            }
            suffix1 = second.get(i).substring(1);
        }
        String res = genome1;
        res += genome2.substring(d);
        System.out.println(res);
        
        /*String str = "CCAATTGTTGGCAACAAAGAATCGCTTATGCTAGGGTGACGTGCCAATCGACTGATTTGACTGGCCGGGGGATCGGCTGCGTAAAACCGGTGTCAGAATAAATAGTCATGGCCGGCGTCGACAGGCGCCCCGAGGGATAGGTAACGGGCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTGTCAACTACGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTGAGCCTGAGGCCCGTGAAGAAGCCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTGGTTCTGGGTGCATAGCCGCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTGACGCCACGAAGTGTCAACTAGTGTTGTCATGAGAGAGTTATTATAGCAGGCCTACTTGTAGGTAAATACACTCTAGGTTATTCGCTCTGCTCCCCTCCTGCGTAACCCCTACCGTGAAGAAGCGGTCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTGTGTTACTACCCATAGCGTCGGCCTCGTGAAGAAGCGGTTCTGGGCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTTGCATAGCCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGACGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTAGTGTCAACTCGGACGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTCGCCACGAAGTGTCAACTACGTGGCAATCATCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTGTACTAGTTTAGCTGTAGGGCTTGAGGCAATTCCACGATCAGCGGGAACAGCGATATAACCCTTACATATCTAAACGCTGGACTGCATAAAGTAAGCAAGGAAATTGACTGAGGCGCTTACCCCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTCCAGTATCAAGCCGCAACCGGGCCCGTGACTCATCCTCCTGCATACCCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTGAACGGGGCCTGGTCCCGTTTTCGAAGGGTGAGTTCTGCTTAGCGTTGTCTTTCATTCGCTCAAAAGTCCCGCGTAAGAGCATCCTGGATTGTTCGCCCTGTAAGCGGGACTACGCGTGCCGATGGTGGGCTTGCAATTATCATAGCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTTCCTGTTCCGTCAATTCCTCTCTAAATACTATCTAACCTGGTCGCAGAACTCGAAGAACTACCGGCCGTCAGCAATTCTAGCTTAATACCTCGTCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTTGAATAGTGCGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTGCCCCTCGGAACGGTATGTACTGCAAGCGTAGAAACCCTGATAGCTTGGATGACGAAACTGTTAGATGTACTGCCAACGGTTAGTCGCGCTGTCGGTTTCGTTAACGATGCATTAAGTCGAACTCGTACCTAGAAACGTGAAGAAGCGGTTCTGGGTGCATAGCCGGACGCCACGAAGTGTCAACTAGTGGGATATTGGTGAAGCAGAGGACGAATTGCGATATCCAAGATGAGAACTGTTTGTCAGTCGGGGAAGACCCAGCTGACTACGCTCAGAGCCCGGTCATGTGTCTGAATCAATCTAAAAACGTATAGTTTGGCTACTGGGGCGCTAGGTGC";
         int[] mas = new int[10000];
         int p =0;
         System.out.println(str.length());
         System.out.println(res.length());
         if (str.length()==res.length()) {
         for (int i = 0; i < str.length(); i++) {
         if (str.charAt(i) != res.charAt(i)){
         mas[p] = i;
         p++;
         }
         
         }
         
         }
         
         for (int j = 0; j<mas.length;j++){
         System.out.println(mas[j]);
         }
         
         System.out.println(str.compareTo(res));
         */
    }
    
    
}

