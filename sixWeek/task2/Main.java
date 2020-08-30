package com.company;

import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();
        String str2 = str1.substring(1, str1.length() - 1);
        String[] arr = str2.split(" ");
        int[] a = new int[arr.length+1];
        a[0] = 0;
        for (int i=0; i<arr.length; ++i) {
            a[i+1] = Integer.parseInt(arr[i]);
        }
        /*
         for (int i=0; i<a.length; ++i) {
         System.out.println(a[i]);
         }
         */
        a[a.length-1]=a.length-1;
        int k=0;
        for (int i=1; i< a.length; ++i) {
            if (a[i] - a[i - 1] != 1) {
                k=k+1;
            }
        }
        System.out.println(k);
    }
    
}