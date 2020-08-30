package com.company;


import java.util.*;



public class Main {
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        int n = in.nextInt();
        int m = in.nextInt();
        
        int[][] matrixDown = new int[n][m+1];
        int[][] matrixRight = new int[n+1][m];
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m+1; j++) {
                matrixDown[i][j] = in.nextInt();
            }
        }
        
        String str = in.next();
        
        for (int i=0; i<n+1; i++) {
            for (int j=0; j<m; j++) {
                matrixRight[i][j] = in.nextInt();
            }
        }
        
        System.out.println(longestPath(n, m, matrixDown, matrixRight));
        
    }
    
    public static int longestPath(int n, int m, int[][] matrixDown, int[][] matrixRight){
        int[][] sc = new int[n + 1][m + 1];
        sc[0][0] = 0;
        
        for (int i=1; i<n+1; i++) {
            sc[i][0]=sc[i-1][0]+matrixDown[i-1][0];
        }
        
        for (int j=1; j<m+1; j++) {
            sc[0][j]=sc[0][j-1] + matrixRight[0][j-1];
        }
        
        for (int i=1; i<n+1; i++) {
            for (int j=1; j<m+1; j++) {
                sc[i][j] = sc[i-1][j] + matrixDown[i-1][j];
                if (sc[i][j-1] + matrixRight[i][j-1] > sc[i][j]) {
                    sc[i][j] = sc[i][j-1] + matrixRight[i][j-1];
                }
            }
        }
        
        return sc[n][m];
    }
}

