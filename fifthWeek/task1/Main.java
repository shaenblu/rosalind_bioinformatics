package com.company;


import java.util.*;



public class Main{
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int sum = in.nextInt();
        
        
        String curr = in.next();
        String[] ar_curr  = curr.split(",");
        int[] coins = new int[ar_curr.length];
        for(int i =0; i<ar_curr.length;i++){
            coins[i] = Integer.parseInt(ar_curr[i]);
        }
        
        System.out.println(minNumCoins(sum,coins));
        
    }
    public static int minNumCoins(int sum, int[] coins){
        int [] count = new int [sum + 1] ;
        int minCount;
        count[0] = 0;
        
        int tmp = 1000000;
        for(int i = 1; i < sum+1; i++){
            minCount = 1000000;
            for(int j = 0; j<coins.length; j++)
                if(i - coins[j] >= 0 ) {
                    minCount = Math.min(minCount, count[i - coins[j]]);
                }
            if(minCount!=tmp){
                count[i] = minCount+1;
            } else{
                count[i] = tmp;
            }
        }
        return count[sum];
    }
    
}

