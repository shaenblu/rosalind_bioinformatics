package com.company;

import java.util.*;

public class Main{
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        String str = new String();
        
        int k = in.nextInt();
        int t = in.nextInt();
        
        ArrayList<String> conKmers = new ArrayList<>();
        for (int i = 0; i < t ; i++){
            str = in.next();
            conKmers.add(str);
        }
        
        ArrayList<String> bestMotifs = new ArrayList<>();
        for (int i = 0; i < t; i++ )
            bestMotifs.add(conKmers.get(i).substring(0, k));
        
        
        int bcount = countingScore(bestMotifs, k);
        //System.out.println(bcount);
        
        for (int i = 0; i < conKmers.get(0).length()-k+1; i++){
            String str1 = conKmers.get(0).substring(i, i+k);
            ArrayList<String> motifs = new ArrayList<>();
            motifs.add(str1);
            
            for (int j = 1; j < t; j++){
                float[][] profileMatrix = new float[4][k];
                for (int m = 0; m < k; m++){
                    float pA = 0;
                    float pC = 0;
                    float pG = 0;
                    float pT = 0;
                    for (int n =0; n < motifs.size(); n++){
                        if (motifs.get(n).charAt(m) == 'A') pA++;
                        if (motifs.get(n).charAt(m) == 'C') pC++;
                        if (motifs.get(n).charAt(m) == 'G') pG++;
                        if (motifs.get(n).charAt(m) == 'T') pT++;
                    }
                    profileMatrix[0][m] = pA/motifs.size();
                    profileMatrix[1][m] = pC/motifs.size();
                    profileMatrix[2][m] = pG/motifs.size();
                    profileMatrix[3][m] = pT/motifs.size();
                }
                
                String strKmer = conKmers.get(j);
                String mostProb = strKmer.substring(0,k);
                float pScore = probScore(strKmer.substring(0,k), profileMatrix);
                for (int p = 1; p <strKmer.length()-k+1; p++){
                    String str2 = strKmer.substring(p, p+k);
                    float cScore = probScore(str2, profileMatrix);
                    if (pScore < cScore){
                        mostProb = str2;
                        pScore = cScore;
                    }
                }
                //System.out.println(pScore);
                motifs.add(mostProb);
                
            }
            int ccount = countingScore(motifs, k);
            //System.out.println(ccount);
            //for(int w = 0; w < motifs.size(); w++)
            //  System.out.println(motifs.get(w));
            
            if (ccount < bcount){
                bcount = ccount;
                bestMotifs = new ArrayList<String>(motifs);
                //System.out.println(bcount);
                
            }
            
            //System.out.println(bcount);
        }
        
        
        for(int i = 0; i < bestMotifs.size(); i++)
            System.out.println(bestMotifs.get(i));
        
        
    }
    
    private static float probScore(String str2, float[][] profileMatrix){
        float pScore = 1;
        for (int f = 0; f < str2.length(); f++){
            float mid = 0;
            if (str2.charAt(f) == 'A') mid = profileMatrix[0][f];
            if (str2.charAt(f) == 'C') mid = profileMatrix[1][f];
            if (str2.charAt(f) == 'G') mid = profileMatrix[2][f];
            if (str2.charAt(f) == 'T') mid = profileMatrix[3][f];
            pScore = pScore * mid;
        }
        return pScore;
    }
    
    private static int countingScore(ArrayList<String> str, int k){
        int score = 0;
        for(int i=0; i<k; i++){
            int cA = 0;
            int cT = 0;
            int cG = 0;
            int cC = 0;
            for(int j=0; j<str.size(); j++){
                if (str.get(j).charAt(i) == 'A')cA++;
                if (str.get(j).charAt(i) == 'C')cC++;
                if (str.get(j).charAt(i) == 'G')cG++;
                if (str.get(j).charAt(i) == 'T')cT++;
            }
            int maxx = cA;
            if( cC > maxx) maxx = cC;
            if( cG > maxx) maxx = cG;
            if( cT > maxx) maxx = cT;
            score = score + str.size() - maxx-1;
        }
        return score;
    }
    
}