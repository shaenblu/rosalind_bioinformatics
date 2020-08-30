package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    //static ArrayList<String> por = new ArrayList<>();
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        String dna = in.next();
        String peptide = in.next();
        
        String[][] all_codons = {
            {"TTT", "F"}, {"CTT", "L"}, {"ATT", "I"}, {"GTT", "V"},
            {"TTC", "F"}, {"CTC", "L"}, {"ATC", "I"}, {"GTC", "V"},
            {"TTA", "L"}, {"CTA", "L"}, {"ATA", "I"}, {"GTA", "V"},
            {"TTG", "L"}, {"CTG", "L"}, {"ATG", "M"}, {"GTG", "V"},
            {"TCT", "S"}, {"CCT", "P"}, {"ACT", "T"}, {"GCT", "A"},
            {"TCC", "S"}, {"CCC", "P"}, {"ACC", "T"}, {"GCC", "A"},
            {"TCA", "S"}, {"CCA", "P"}, {"ACA", "T"}, {"GCA", "A"},
            {"TCG", "S"}, {"CCG", "P"}, {"ACG", "T"}, {"GCG", "A"},
            {"TAT", "Y"}, {"CAT", "H"}, {"AAT", "N"}, {"GAT", "D"},
            {"TAC", "Y"}, {"CAC", "H"}, {"AAC", "N"}, {"GAC","D"},
            {"TAA", "Stop"}, {"CAA", "Q"}, {"AAA", "K"}, {"GAA", "E"},
            {"TAG", "Stop"}, {"CAG", "Q"}, {"AAG",  "K"}, {"GAG","E"},
            {"TGT", "C"}, {"CGT", "R"}, {"AGT", "S"}, {"GGT", "G"},
            {"TGC", "C"}, {"CGC", "R"}, {"AGC", "S"}, {"GGC", "G"},
            {"TGA", "Stop"}, {"CGA", "R"}, {"AGA", "R"}, {"GGA","G"},
            {"TGG", "W"}, {"CGG", "R"}, {"AGG", "R"}, {"GGG", "G"}
        };
        
        ArrayList<String> result1 = allfun(all_codons, peptide, dna);
        String rev_dna = reverseOfPattern(dna);
        ArrayList<String> result2 = allfun(all_codons, peptide, rev_dna);
        for(int i = 0; i<result2.size(); i++){
            result1.add(reverseOfPattern(result2.get(i)));
        }
        for(int i = 0; i<result1.size(); i++){
            System.out.println(result1.get(i));
        }
        
        
        
    }
    
    public static String reverseOfPattern(String s){
        String out = new String();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == 'A') out += 'T';
            if (s.charAt(i) == 'T') out += 'A';
            if (s.charAt(i) == 'G') out += 'C';
            if (s.charAt(i) == 'C') out += 'G';
        }
        String outt = new String();
        for (int i = out.length()-1; i >= 0; i--)
            outt += out.charAt(i);
        
        return outt;
    }
    
    
    public static ArrayList<String> allfun(String[][] all_codons, String peptide, String dna){
        ArrayList<String> Kmers = new ArrayList<>();
        char startt = peptide.charAt(0);
        ArrayList<String> first_codons = new ArrayList<>();
        
        for(int i =0 ; i <all_codons.length; i++){
            if (all_codons[i][1].charAt(0) == startt){
                first_codons.add(all_codons[i][0]);
            }
        }
        
        for (int j =0; j<first_codons.size();j++) {
            for (int i = 0; i < dna.length() - peptide.length()*3+1; i++) {
                String pat = dna.substring(i, i + 3);
                if (pat.equals(first_codons.get(j))){
                    Kmers.add(dna.substring(i, i+peptide.length()*3));
                }
            }
        }
        ArrayList<String> candidates = new ArrayList<>();
        for(int i =0; i< Kmers.size(); i++){
            String npep = toPeptide(Kmers.get(i),all_codons);
            if(npep.equals(peptide)){
                candidates.add(Kmers.get(i));
            }
        }
        /*
         System.out.println(candidates.size());
         for(int  j =0; j<candidates.size(); j++){
         System.out.println(candidates.get(j));
         }
         */
        return candidates;
        
        
    }
    
    
    public static String toPeptide(String s, String[][] all_codons){
        //System.out.println(s);
        String outt = new String();
        for(int j = 0; j < s.length()-3+1; j+=3) {
            String tmer = s.substring(j, j+3);
            for (int i = 0; i < all_codons.length; i++) {
                if(tmer.equals(all_codons[i][0])){
                    outt += all_codons[i][1];
                }
            }
        }
        return outt;
    }
}

