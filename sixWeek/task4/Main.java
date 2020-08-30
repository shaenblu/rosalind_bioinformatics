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
            {2, -2,  0,  0, -3,  1, -1, -1, -1, -2, -1,  0,  1,  0, -2,  1,  1,  0, -6, -3},
            {-2, 12, -5, -5, -4, -3, -3, -2, -5, -6, -5, -4, -3, -5, -4,  0, -2, -2, -8, 0},
            {0, -5,  4,  3, -6,  1,  1, -2,  0, -4, -3,  2, -1,  2, -1,  0,  0, -2, -7, -4},
            {0, -5,  3,  4, -5,  0,  1, -2,  0, -3, -2,  1, -1,  2, -1,  0,  0, -2, -7, -4},
            {-3, -4, -6, -5,  9, -5, -2,  1, -5,  2,  0, -3, -5, -5, -4, -3, -3, -1,  0, 7},
            {1, -3,  1,  0, -5,  5, -2, -3, -2, -4, -3,  0, 0, -1, -3,  1,  0, -1, -7, -5},
            {-1, -3,  1,  1, -2, -2,  6, -2,  0, -2, -2,  2,  0, 3,  2, -1, -1, -2, -3, 0},
            {-1, -2, -2, -2,  1, -3, -2,  5, -2,  2,  2, -2, -2, -2, -2, -1,  0, 4, -5, -1},
            {-1, -5,  0,  0, -5, -2,  0, -2,  5, -3,  0,  1, -1,  1,  3,  0,  0, -2, -3, -4},
            {-2, -6, -4, -3,  2, -4, -2,  2, -3,  6,  4, -3, -3, -2, -3, -3, -2,  2, -2, -1},
            {-1, -5, -3, -2,  0, -3, -2,  2,  0,  4,  6, -2, -2, -1,  0, -2, -1,  2, -4, -2},
            {0, -4,  2,  1, -3,  0,  2, -2,  1, -3, -2,  2,  0,  1,  0,  1,  0, -2, -4, -2},
            {1, -3, -1, -1, -5,  0,  0, -2, -1, -3, -2,  0,  6,  0,  0,  1,  0, -1, -6, -5},
            {0, -5,  2,  2, -5, -1,  3, -2,  1, -2, -1,  1,  0,  4,  1, -1, -1, -2, -5, -4},
            {-2, -4, -1, -1, -4, -3,  2, -2,  3, -3,  0,  0,  0,  1,  6,  0, -1, -2,  2, -4},
            {1, 0,  0,  0, -3,  1, -1, -1,  0, -3, -2,  1,  1, -1,  0,  2,  1, -1, -2, -3},
            {1, -2,  0,  0, -3,  0, -1,  0,  0, -2, -1,  0,  0, -1, -1,  1,  3,  0, -5, -3},
            {0, -2, -2, -2, -1, -1, -2,  4, -2,  2,  2, -2, -1, -2, -2, -1,  0,  4, -6, -2},
            {-6, -8, -7, -7,  0, -7, -3, -5, -3, -2, -4, -4, -6, -5,  2, -2, -5, -6, 17, 0},
            {-3,  0, -4, -4,  7, -5,  0, -1, -4, -1, -2, -2, -5, -4, -4, -3, -3, -2,  0, 10}
            
        };
        String[] alphabet = {"A","C","D","E","F","G","H", "I",  "K", "L","M", "N", "P", "Q", "R", "S","T","V", "W", "Y"};
        
        
        String max = new String();
        String min = new String();
        if(str1.length()>=str2.length()){
            max = str1;
            min = str2;
        }else{
            max = str2;
            min = str1;
        }
        int gap = -5;
        
        
        int[][] opt = new int[min.length() + 1][max.length() + 1];
        opt[0][0] = 0;
        
        for (int i = 1; i < min.length()+1; i++)
            opt[i][0] = 0;
        for (int j = 1; j < max.length()+1; j++)
            opt[0][j] = 0;
        
        
        int best = 0;
        int[] arr = new int[2];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 1; i <= min.length(); i++) {
            for (int j = 1; j <= max.length(); j++) {
                int ind1 = Arrays.asList(alphabet).indexOf(min.substring(i-1,i));
                int ind2 = Arrays.asList(alphabet).indexOf(max.substring(j-1,j));
                int scoreDiag = opt[i - 1][j - 1] + blosum[ind1][ind2];
                int scoreLeft = opt[i][j - 1] + gap; // insertion
                int scoreUp = opt[i - 1][j] + gap; // deletion
                // we take the minimum
                opt[i][j] = Math.max(Math.max(Math.max(scoreDiag, scoreLeft), scoreUp),0);
                if(opt[i][j]>best){
                    best = opt[i][j];
                    arr[0]=i;
                    arr[1]=j;
                }
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
        System.out.println(best);
        System.out.println(arr[0]+"\t"+arr[1]);
        String res1 = new String();
        String res2 = new String();
        
        
        //System.out.println(min);
        //System.out.println(max);
        
        int ii = arr[0];
        int jj = arr[1];
        System.out.println(opt[ii][jj]);
        System.out.println(String.valueOf(min.charAt(ii-1)));
        System.out.println(String.valueOf(max.charAt(jj-1)));
        while((opt[ii][jj] > 0) && (ii>0) && (jj>0)) {
            int ind1 = Arrays.asList(alphabet).indexOf(String.valueOf(min.charAt(ii-1)));
            int ind2 = Arrays.asList(alphabet).indexOf(String.valueOf(max.charAt(jj-1)));
            //System.out.println(ind1);
            //System.out.println(ind2);
            //System.out.println(ii);
            //System.out.println(jj);
            //System.out.println(String.valueOf(min.charAt(ii)));
            //System.out.println(String.valueOf(max.charAt(jj)));
            if (opt[ii][jj] - blosum[ind1][ind2] == opt[ii-1][jj-1]) {
                //System.out.println("diagdiag");
                //System.out.println(res1);
                res1 += String.valueOf(max.charAt(jj-1));
                res2 += String.valueOf(min.charAt(ii-1));
                //System.out.println(res1);
                //System.out.println(res2);
                
                ii = ii - 1;
                jj = jj - 1;
                
            } else {
                if (opt[ii][jj] - gap == opt[ii][jj-1]) {
                    res1 += String.valueOf(max.charAt(jj-1));
                    res2 += "-";
                    //System.out.println(res1);
                    //System.out.println(res2);
                    jj = jj - 1;
                    //ii = ii-1;
                    
                } else {
                    if (opt[ii][jj] - gap == opt[ii-1][jj]) {
                        res1 += "-";
                        res2 += String.valueOf(min.charAt(ii-1));
                        ii = ii - 1;
                        //System.out.println(res1);
                        //System.out.println(res2);
                        //jj = jj-1;
                    }
                }
            }
        }
        
        System.out.println(ii);
        System.out.println(jj);
        
        //System.out.println(String.valueOf(min.charAt(ii)));
        //System.out.println(String.valueOf(max.charAt(jj)));
        //System.out.println(opt[min.length()][max.length()]);
        if(max.equals(str1)){
            System.out.println(new StringBuffer(res1).reverse().toString());
            System.out.println(res1.length());
            System.out.println(new StringBuffer(res2).reverse().toString());
            System.out.println(res2.length());
        }else {
            System.out.println(new StringBuffer(res2).reverse().toString());
            System.out.println(res2.length());
            System.out.println(new StringBuffer(res1).reverse().toString());
            System.out.println(res1.length());
        }
        
        String check = "YQAGIIRQPPRGD-RGVSDRNYSQCGKQ-NQ-AQLDNNPTWTKYEIEWRVQI-LPPGAGVFEGDNGQNQCLCPNW--A-W-EQPCQW----GALHS-NEQYPNRIHLWAPMSKLHIKIEKSSYN-RNAQ-FPNRCMYECE-FPSY-REQVDSCHYENVQIAF-TIFSGAEQKRKFCSCHFWSNFIDQAVFSTGLI-PWCYRRDDHSAFFMPNWNKQ--YKHPQLQFRVAGEGTQCRPFYTREMFTKVSAWRIAGRFAGPYERHHDAHLELWY-QHHKVRT-GQQLGIIWNNRDKTRNPCPFSAY-Y-NK--LP-WWK-I-NQ-N-AFYNCLQNIAHSTHDETHEFNPVKCIDWLQGTMV-P------TECKKGFVHEKCECYRNPGPPLHDMYHQMEDIFGVRFDCLTGWKHLS------D---YNPC-QERRNINDFYIFAYEIAPAVKNLVLSPQPLADATKKCAFNYTPLDQSPVVIACK---WYIHQPI-CMLL----IVLIC-AMDKYNAHMIVIRTTEGQQPMHACRMTEGPGMCMKEPLVTFTLPAQWQWPNHEFKYVYMYVLNYHLSQYTYTDEGHAGGQHYSFNVAVDVGMAWGHNRCYCQPACYSQQETQTRTIDYEKWQYMKHQAFKWGLWFCEQER-HA--WFKGQNRCEMFTAKMTRMGADSNLDQYKLMLAQNYEEQWEQPIMECGMSEIIEIDPPYRSELIFTFWPFCTYSPWQNLIKCRCNNVIEEMDQCVP-LTF-IGFGVKQAGGIQA-WAFYKE--EWTSTYYLMCQCMKSDKAQYPYEIILFWMQ--P-MDTGE--QEPPQQNMWIFLPHSWFFDWCCNAPWSEICSSRHD--H---GQ-CQDAFYPCELFTVF";
        System.out.println(check.length());
    }
}

