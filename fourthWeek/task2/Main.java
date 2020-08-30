package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        Long N = in.nextLong();
        
        List<Long> masses = Arrays.asList(71l, 103l, 115l, 129l, 147l, 57l, 137l, 128l, 113l,
                                          131l, 114l, 97l, 156l, 87l, 101l, 99l, 186l, 163l);
        Collections.sort(masses);
        
        HashMap<Long, Long> result = new HashMap<>();
        result.put(0l,1l);
        
        Long minn = masses.get(0);
        for (Long i = 0l; i < N-minn+1; i++){
            Long j = i + minn;
            for (int k = 0; k < masses.size(); k++){
                Long tmp = j-masses.get(k);
                if (result.containsKey(tmp)){
                    if (!result.containsKey(j)){result.put(j, 0l);}
                    Long val = result.get(j)+result.get(tmp);
                    result.put(j, val);
                    
                }
            }
        }
        
        
        System.out.println(result.get(N));
        
        
    }
}

