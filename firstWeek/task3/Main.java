package com.company;

import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        String pattern = new String();
        pattern = in.next();
        
        String genome = new String();
        genome = in.next();
        
        
        int d = in.nextInt();
        int count = 0;
        
        List<Integer> mas = new ArrayList<>();
        
        for (int i = 0; i < genome.length() - pattern.length() + 1; i++) {
            String subStr = genome.substring(i, i + pattern.length());
            if (subStr.equals(pattern)) mas.add(i);
            else {
                for (int j = 0; j < subStr.length(); j++) {
                    if (subStr.charAt(j) != pattern.charAt(j))
                        count++;
                }
                if (count <= d) mas.add(i);
            }
            count = 0;
        }
        
        
        for (int i = 0; i < mas.size(); i++) {
            System.out.print(mas.get(i) + " ");
        }
    }
}