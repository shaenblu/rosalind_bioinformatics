package com.company;

import java.util.*;

public class Main{
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        String patt = in.next();
        int k = patt.length();
        
        ArrayList<String> conKmers = new ArrayList<>();
        int t =0;
        while(in.hasNext()){
            String currSeq = in.next();
            conKmers.add(currSeq);
            
        }
        //for(int j = 0; j<conKmers.size(); j++) {
        //    System.out.println(conKmers.get(j));
        //}
        
        int dist = 0;
        int ham_dis = 1000;
        for(int i =0; i<conKmers.size();i++){
            String text = conKmers.get(i);
            for(int j =0; j<text.length()-k+1; j++){
                String patt1 = text.substring(j, j+k);
                if (ham_dis >= hammingD(patt, patt1)){
                    ham_dis = hammingD(patt, patt1);
                    //System.out.println(ham_dis);
                }
            }
            //System.out.println("for "+ i + "  row dist is");
            dist = dist + ham_dis;
            //System.out.println(dist);
            ham_dis = 1000;
        }
        System.out.println(dist);
        
    }
    
    private static int hammingD(String p1, String p2){
        int d = 0;
        for (int i = 0; i<p1.length(); i++){
            if (p1.charAt(i)!=p2.charAt(i)) d++;
        }
        return d;
    }
}