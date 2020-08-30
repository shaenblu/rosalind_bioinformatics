package com.company;

import java.util.*;

public class Main{
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        int k = in.nextInt();
        
        ArrayList<String> Kmers = new ArrayList<>();
        
        while (in.hasNext()) {
            String currSeq = in.next();
            Kmers.add(currSeq);
        }
        //for(int j = 0; j<Kmers.size(); j++) {
        //    System.out.println(Kmers.get(j));
        //}
        Kmers.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        
        ArrayList<String> way = new ArrayList<>();
        for (int i = 0; i<Kmers.size(); i++){
            int[] check = new int[Kmers.size()];
            for(int j = 0; j < check.length; j++){
                check[j] = 0;
            }
            check[i] = 1;
            way.add(Kmers.get(i));
            String tmp = Kmers.get(i);
            //System.out.println(tmp);
            for(int p = 0; p < Kmers.size(); p++){
                //System.out.println(p);
                if (Kmers.get(p).startsWith(tmp)){
                    //System.out.println("number"+"\t"+p);
                    check[p] = 1;
                    tmp = Kmers.get(p).substring(1);
                    //System.out.println(tmp);
                    way.add(Kmers.get(p));
                    p = -1;
                }
            }
            if (!path(check)){
                way.removeAll(way);
            } else{
                //System.out.println("sdfsdf");
                break;
                
            }
            
        }
        String genome = new String();
        genome += way.get(0).substring(0,k-1);
        //System.out.println(genome);
        for(int q = 1; q < way.size(); q++){
            genome += way.get(q).substring(k-1, k);
        }
        //String st1 = "TGCCCCTTTGATCGCGGTTCTCGAATCCATGTAAATACAAAGATCTTATGTCCGCCGCGTATAGCGGTCGTAAAAATCTACGAGTTTCGATAACTCCAGGATCAATGCGGAACTATGCCCTTATAATAAGGCCACAATTAGTGCGCGTATTAGTGCGATTCCCATTTGCTCCTTTTCTCAACGACCAACGTAGGCGGGGGATGAGTATGCACACGCCCACCCGCTACACTCGACCCTCTCGGCTCTTTTTGTACCGGGGGCCTATATCTCCTGCACCGCCACCATCGCGTTCTCTCTTATTTTGCTATTATTATTCTTTCCAGAACATATGACATATCAGTGCAAGCTGAATCGCGAAGCGGCACTTAATACGATTTCTTGCGATGTGTCTTCTCGCGGCAATTGCTAGTGCCTGGTAAGTCACCGTGATCGTGTCTATG";
        System.out.println(genome);
    }
    public static boolean path(int[] check){
        boolean res = true;
        for (int i = 0; i < check.length; i++)
            if (check[i]!=1){
                res = false;
            }
        return res;
    }
    
}