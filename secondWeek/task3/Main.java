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
        ArrayList<String> motifs = new ArrayList<>();
        
        for (int i = 0; i < t; i++ ) {
            int numR = (int) (Math.random() * (conKmers.get(i).length() - k));
            bestMotifs.add(conKmers.get(i).substring(numR, numR+k));
            motifs.add(conKmers.get(i).substring(numR, numR+k));
        }
        
        
        int bcount = countingScore(bestMotifs, k);
        //System.out.println(bcount);
        
        boolean tt = true;
        for (int q = 0; q<1000; q++) {
            for (int i = 0; i < t; i++ ) {
                int numR = (int) (Math.random() * (conKmers.get(i).length() - k));
                motifs.add(conKmers.get(i).substring(numR, numR+k));
            }
            
            while (tt) {
                float[][] profileMatrix = new float[4][k];
                for (int m = 0; m < k; m++) {
                    float pA = 1;
                    float pC = 1;
                    float pG = 1;
                    float pT = 1;
                    for (int n = 0; n < motifs.size(); n++) {
                        if (motifs.get(n).charAt(m) == 'A') pA++;
                        if (motifs.get(n).charAt(m) == 'C') pC++;
                        if (motifs.get(n).charAt(m) == 'G') pG++;
                        if (motifs.get(n).charAt(m) == 'T') pT++;
                    }
                    profileMatrix[0][m] = pA / motifs.size();
                    profileMatrix[1][m] = pC / motifs.size();
                    profileMatrix[2][m] = pG / motifs.size();
                    profileMatrix[3][m] = pT / motifs.size();
                }
                ArrayList<String> cmotifs = new ArrayList<>();
                
                for (int j = 0; j < t; j++) {
                    String strKmer = conKmers.get(j);
                    String mostProb = strKmer.substring(0, k);
                    float pScore = probScore(strKmer.substring(0, k), profileMatrix);
                    for (int p = 1; p < strKmer.length() - k + 1; p++) {
                        String str2 = strKmer.substring(p, p + k);
                        float cScore = probScore(str2, profileMatrix);
                        if (pScore < cScore) {
                            mostProb = str2;
                            pScore = cScore;
                        }
                    }
                    cmotifs.add(mostProb);
                    
                }
                int ccount = countingScore(cmotifs, k);
                
                if (ccount < bcount) {
                    bcount = ccount;
                    motifs = cmotifs;
                    bestMotifs = new ArrayList<String>(cmotifs);
                    
                } else {
                    tt = false;
                }
            }
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
            score = score + str.size() - maxx;
        }
        return score;
    }
    
}