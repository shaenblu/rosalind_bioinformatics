package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        String genome = new String();
        genome = in.next();
        int l = genome.length() + 1;
        int[] mas = new int[l];
        
        for (int i = 0; i < genome.length(); i++) {
            switch (genome.charAt(i)) {
                case 'C':
                    mas[i + 1] = mas[i] - 1;
                    break;
                case 'G':
                    mas[i + 1] = mas[i] + 1;
                    break;
                default:
                    mas[i + 1] = mas[i];
                    break;
            }
        }
        
        int minS = 1000;
        List<Integer> mas1 = new ArrayList<>();
        for (int i = 0; i < mas.length; i++) {
            if (mas[i] < minS) {
                mas1 = new ArrayList<>();
                mas1.add(i);
                minS = mas[i];
            } else if (mas[i] == minS) {
                mas1.add(i);
            }
        }
        
        for (int i = 0; i < mas1.size(); i++) {
            System.out.print(mas1.get(i) + " ");
        }
    }
}
