package com.company;


import com.sun.deploy.util.ArrayUtil;
import com.sun.tools.javac.util.ArrayUtils;

import java.util.*;



public class Main {
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        String str1 = in.next();
        String str2 = in.next();
        
        int[][] blosum = {
            {4,  0, -2, -1, -2,  0, -2, -1, -1, -1, -1, -2, -1, -1, -1,  1,  0,  0, -3, -2},
            {0,  9, -3, -4, -2, -3, -3, -1, -3, -1, -1, -3, -3, -3, -3, -1, -1, -1, -2, -2},
            {-2, -3,  6,  2, -3, -1, -1, -3, -1, -4, -3,  1, -1,  0, -2, 0, -1, -3, -4, -3},
            {-1, -4,  2,  5, -3, -2,  0, -3,  1, -3, -2,  0, -1,  2,  0,  0, -1, -2, -3, -2},
            {-2, -2, -3, -3,  6, -3, -1,  0, -3,  0,  0, -3, -4, -3, -3, -2, -2, -1,  1, 3},
            {0, -3, -1, -2, -3,  6, -2, -4, -2, -4, -3,  0, -2, -2, -2,  0, -2, -3, -2, -3},
            {-2, -3, -1,  0, -1, -2,  8, -3, -1, -3, -2, 1, -2,  0,  0, -1, -2, -3, -2, 2},
            {-1, -1, -3, -3,  0, -4, -3,  4, -3,  2,  1, -3, -3, -3, -3, -2, -1,  3, -3, -1},
            {-1, -3, -1,  1, -3, -2, -1, -3,  5, -2, -1,  0, -1,  1,  2,  0, -1, -2, -3, -2},
            {-1, -1, -4, -3,  0, -4, -3,  2, -2,  4,  2, -3, -3, -2, -2, -2, -1, 1, -2, -1},
            {-1, -1, -3, -2,  0, -3, -2,  1, -1,  2,  5, -2, -2,  0, -1, -1, -1,  1, -1, -1},
            {-2, -3,  1,  0, -3,  0,  1, -3,  0, -3, -2,  6, -2,  0,  0,  1,  0, -3, -4, -2},
            {-1, -3, -1, -1, -4, -2, -2, -3, -1, -3, -2, -2,  7, -1, -2, -1, -1, -2, -4, -3},
            {-1, -3,  0,  2, -3, -2,  0, -3,  1, -2,  0, 0, -1,  5,  1,  0, -1, -2, -2, -1},
            {-1, -3, -2,  0, -3, -2,  0, -3,  2, -2, -1,  0, -2,  1,  5, -1, -1, -3, -3, -2},
            {1, -1,  0,  0, -2,  0, -1, -2,  0, -2, -1,  1, -1,  0, -1,  4,  1, -2, -3, -2},
            {0, -1, -1, -1, -2, -2, -2, -1, -1, -1, -1,  0, -1, -1, -1,  1,  5,  0, -2, -2},
            {0, -1, -3, -2, -1, -3, -3,  3, -2,  1,  1, -3, -2, -2, -3, -2,  0,  4, -3, -1},
            {-3, -2, -4, -3,  1, -2, -2, -3, -3, -2, -1, -4, -4, -2, -3, -3, -2, -3, 11, 2},
            {-2, -2, -3, -2,  3, -3,  2, -1, -2, -1, -1, -2, -3, -1, -2, -2, -2, -1,  2,  7}
            
        };
        String[] alphabet = {"A","C","D","E","F","G","H", "I",  "K", "L","M", "N", "P", "Q", "R", "S","T","V", "W", "Y"};
        //System.out.println(Arrays.asList(alphabet).indexOf("V"));
        
        int n;
        int m;
        String max = new String();
        String min = new String();
        if(str1.length()>=str2.length()){
            n = str1.length();
            m = str2.length();
            max = str1;
            min = str2;
        }else{
            n = str2.length();
            m = str1.length();
            max = str2;
            min = str1;
        }
        int[][] scores = new int[m][n];
        for(int i = 0; i < m ; i++){
            String tmp1 = min.substring(i,i+1);
            int ind1 = Arrays.asList(alphabet).indexOf(tmp1);
            for(int j=0; j<n; j++) {
                String tmp2 = max.substring(j,j+1);
                int ind2 = Arrays.asList(alphabet).indexOf(tmp2);
                scores[i][j] = blosum[ind1][ind2];
            }
        }
        //System.out.println(m);
        //System.out.println(n);
        
        /*
         for(int i = 0;i<m;i++){
         for(int j = 0; j<n; j++){
         System.out.print(scores[i][j]+"\t");
         }
         System.out.println();
         }
         
         int[][] tmp = scores;
         
         int[] maxx = new int[m];
         for(int i=0; i<m; i++){
         Arrays.sort(tmp[i]);
         maxx[i] = tmp[i][n-1];
         }
         Arrays.sort(maxx);
         */
        int gap = -5;
        //int match = -5;
        
        int[][] opt = new int[min.length() + 1][max.length() + 1];
        opt[0][0] = 0;
        
        for (int i = 1; i < min.length()+1; i++)
            opt[i][0] = i*gap;
        for (int j = 1; j < max.length()+1; j++)
            opt[0][j] = j*gap;
        
        
        for (int i = 1; i <= min.length(); i++) {
            for (int j = 1; j <= max.length(); j++) {
                int ind1 = Arrays.asList(alphabet).indexOf(min.substring(i-1,i));
                int ind2 = Arrays.asList(alphabet).indexOf(max.substring(j-1,j));
                //System.out.println(min.substring(i-1,i));
                //System.out.println(max.substring(j-1,j));
                //System.out.println(ind1);
                //System.out.println(ind2);
                int scoreDiag = opt[i - 1][j - 1] + blosum[ind1][ind2];
                int scoreLeft = opt[i][j - 1] + gap; // insertion
                int scoreUp = opt[i - 1][j] + gap; // deletion
                // we take the minimum
                opt[i][j] = Math.max(Math.max(scoreDiag, scoreLeft), scoreUp);
            }
        }
        
        /*
         for (int i = 0; i <= min.length(); i++) {
         for (int j = 0; j <= max.length(); j++)
         System.out.print(opt[i][j] + "\t");
         System.out.println();
         }
         */
        System.out.println(opt[min.length()][max.length()]);
        String res1 = new String();
        String res2 = new String();
        
        
        //System.out.println(min);
        //System.out.println(max);
        
        int ii = min.length()-1;
        int jj = max.length()-1;
        while(ii >=0 && jj>=0) {
            int ind1 = Arrays.asList(alphabet).indexOf(String.valueOf(min.charAt(ii)));
            int ind2 = Arrays.asList(alphabet).indexOf(String.valueOf(max.charAt(jj)));
            //System.out.println(ind1);
            //System.out.println(ind2);
            //System.out.println(ii);
            //System.out.println(jj);
            //System.out.println(String.valueOf(min.charAt(ii)));
            //System.out.println(String.valueOf(max.charAt(jj)));
            if (opt[ii+1][jj+1] - blosum[ind1][ind2] == opt[ii][jj]) {
                //System.out.println("diagdiag");
                //System.out.println(res1);
                res1 += String.valueOf(max.charAt(jj));
                res2 += String.valueOf(min.charAt(ii));
                //System.out.println(res1);
                //System.out.println(res2);
                
                ii = ii - 1;
                jj = jj - 1;
                
            } else {
                if (opt[ii+1][jj+1] - gap == opt[ii+1][jj]) {
                    res1 += String.valueOf(max.charAt(jj));
                    res2 += "-";
                    //System.out.println(res1);
                    //System.out.println(res2);
                    jj = jj - 1;
                    //ii = ii-1;
                    
                } else {
                    if (opt[ii+1][jj+1] - gap == opt[ii][jj+1]) {
                        res1 += "-";
                        res2 += String.valueOf(min.charAt(ii));
                        ii = ii - 1;
                        //System.out.println(res1);
                        //System.out.println(res2);
                        //jj = jj-1;
                    }
                }
            }
        }
        
        //System.out.println(jj);
        //System.out.println(ii);
        //System.out.println("theretherethere");
        
        if (jj>=0){
            while(jj>=0){
                res1 += String.valueOf(max.charAt(jj));
                res2 += "-";
                jj = jj - 1;
            }
        }
        
        if (ii>=0){
            while(ii >= 0){
                res1 += "-";
                res2 += String.valueOf(min.charAt(ii));
                ii = ii - 1;
            }
        }
        
        
        //System.out.println(opt[min.length()][max.length()]);
        if(max.equals(str1)){
            System.out.println(new StringBuffer(res1).reverse().toString());
            System.out.println(new StringBuffer(res2).reverse().toString());
        }else {
            System.out.println(new StringBuffer(res2).reverse().toString());
            System.out.println(new StringBuffer(res1).reverse().toString());
        }
    }
}

