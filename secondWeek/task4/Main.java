package com.company;

import java.util.*;

public class Main{
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        String str = new String();
        
        
        int k = in.nextInt();
        int t = in.nextInt();
        int N = in.nextInt();
        
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
        
        
        //boolean tt = true;
        for (int q = 0; q<20; q++) {
            for (int i = 0; i < t; i++ ) {
                int numR = (int) (Math.random() * (conKmers.get(i).length() - k));
                motifs.add(conKmers.get(i).substring(numR, numR+k));
            }
            
            for(int i = 0; i < N; i++ ) {
                int id = (int)(Math.random()*(t));
                //System.out.println(id);
                //System.out.println(conKmers.get(id));
                ArrayList<String> n_mot = new ArrayList<>();
                for(int w = 0; w < t; w++)
                    if (w != id) n_mot.add(motifs.get(w));
                //System.out.println(n_mot.size());
                
                float[][] profileMatrix = new float[4][k];
                for (int m = 0; m < k; m++) {
                    float pA = 1;
                    float pC = 1;
                    float pG = 1;
                    float pT = 1;
                    for (int n = 0; n < n_mot.size(); n++) {
                        if (n_mot.get(n).charAt(m) == 'A') pA++;
                        if (n_mot.get(n).charAt(m) == 'C') pC++;
                        if (n_mot.get(n).charAt(m) == 'G') pG++;
                        if (n_mot.get(n).charAt(m) == 'T') pT++;
                    }
                    profileMatrix[0][m] = pA / n_mot.size();
                    profileMatrix[1][m] = pC / n_mot.size();
                    profileMatrix[2][m] = pG / n_mot.size();
                    profileMatrix[3][m] = pT / n_mot.size();
                }
                float[] probs = new float[conKmers.get(id).length()-k+1];
                for(int r = 0; r < conKmers.get(id).length()-k+1; r++) {
                    String ran_str = conKmers.get(id).substring(r, r+k);
                    //System.out.println(ran_str);
                    float pScore = probScore(ran_str, profileMatrix);
                    //float[] probs = new float[conKmers.get(id).length()-k+1];
                    probs[r] = pScore;
                }
                //System.out.println("///////////////////////");
                int mostPr = 0;
                float maxx = probs[0];
                for(int u = 0; u<probs.length; u++){
                    if(maxx < probs[u]){maxx = probs[u]; mostPr = u;}
                }
                
                
                final Random random = new Random();
                int pos = random.nextInt(probs.length);
                //int numR1 = (int) (Math.random() * (conKmers.get(id).length() - k));
                String cho_str = conKmers.get(id).substring(mostPr,mostPr+k);
                n_mot.add(cho_str);
                //System.out.println(n_mot.size());
                int ccount = countingScore(n_mot, k);
                if (ccount < bcount) {
                    bcount = ccount;
                    //motifs = n_mot;
                    bestMotifs = new ArrayList<String>(n_mot);
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
            int cA = 1;
            int cT = 1;
            int cG = 1;
            int cC = 1;
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