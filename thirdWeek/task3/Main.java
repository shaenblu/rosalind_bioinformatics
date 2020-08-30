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
        
        System.out.println(Kmers.size());
        first.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        
        System.out.println(first.size());
        for(int ii=0; ii<first.size();ii++){
            System.out.println(first.get(ii));
        }
        
        //System.out.println(getPath(str1[0], k));
        //String genome1 = getPath(first, k, second,d);
        
        //System.out.println(genome1);
        //String ex = "GTGGTCGTGAGATGTTGA";
        //System.out.println(ex.compareTo(res));
        
        
        ArrayList<String> way = new ArrayList<>();
        for (int i = 0; i<first.size(); i++){
            int[] check = new int[first.size()];
            for(int j = 0; j < check.length; j++){
                check[j] = 0;
            }
            check[i] = 1;
            way.add(first.get(i));
            String tmp = first.get(i);
            //System.out.println(tmp);
            for(int p = 0; p < first.size(); p++){
                //System.out.println(p);
                if (first.get(p).startsWith(tmp)){
                    //System.out.println("number"+"\t"+p);
                    check[p] = 1;
                    tmp = first.get(p).substring(1);
                    //System.out.println(tmp);
                    way.add(first.get(p));
                    p = -1;
                }
            }
            if (!path(check)){
                System.out.println(i+"\t"+count_out_path(check));
                way.removeAll(way);
            } else{
                System.out.println("sdfsdf");
                break;
                
            }
            
        }
        
        
        String genome = new String();
        if (way.size()>1) {
            genome += way.get(0).substring(0, k - 1);
            //System.out.println(genome);
            for (int q = 1; q < way.size(); q++) {
                genome += way.get(q).substring(k - 1, k);
            }
            //String st1 = "TGCCCCTTTGATCGCGGTTCTCGAATCCATGTAAATACAAAGATCTTATGTCCGCCGCGTATAGCGGTCGTAAAAATCTACGAGTTTCGATAACTCCAGGATCAATGCGGAACTATGCCCTTATAATAAGGCCACAATTAGTGCGCGTATTAGTGCGATTCCCATTTGCTCCTTTTCTCAACGACCAACGTAGGCGGGGGATGAGTATGCACACGCCCACCCGCTACACTCGACCCTCTCGGCTCTTTTTGTACCGGGGGCCTATATCTCCTGCACCGCCACCATCGCGTTCTCTCTTATTTTGCTATTATTATTCTTTCCAGAACATATGACATATCAGTGCAAGCTGAATCGCGAAGCGGCACTTAATACGATTTCTTGCGATGTGTCTTCTCGCGGCAATTGCTAGTGCCTGGTAAGTCACCGTGATCGTGTCTATG";
            System.out.println(genome);
        }
    }
    
    
    public static boolean path(int[] check){
        boolean res = true;
        for (int i = 0; i < check.length; i++)
            if (check[i]!=1){
                res = false;
            }
        return res;
    }
    public static int count_out_path(int[] check){
        int res = 0;
        for (int i = 0; i < check.length; i++)
            if (check[i]!=1){
                res ++;
            }
        return res;
    }
    
    
    
    
}

