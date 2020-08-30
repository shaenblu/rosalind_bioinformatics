package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        List<Integer> masses = Arrays.asList(71, 103, 115, 129, 147, 57, 137, 128, 113,
                                             131, 114, 97, 156, 87, 101, 99, 186, 163);
        Collections.sort(masses);
        
        ArrayList<Integer> spectrum = new ArrayList<>();
        while(in.hasNext()) {
            spectrum.add(in.nextInt());
        }
        
        ArrayList<Integer> one_pep = new ArrayList<>();
        for(int i =0; i<masses.size(); i++){
            if(spectrum.indexOf(masses.get(i))!=-1){
                one_pep.add(masses.get(i));
            }
        }
        
        ArrayList<Integer> twin = new ArrayList<>();
        for(int i =0; i < spectrum.size(); i++){
            twin.add(spectrum.get(i));
        }
        
        ArrayList<String> leaderBoard = new ArrayList<>();
        String[] leaderPeptide = new String[30];
        for(int i =0; i<leaderPeptide.length; i++){
            leaderPeptide[i] = "-1";
        }
        
        int parentMass = spectrum.get(spectrum.size()-1);
        
        leaderBoard.add(new String());
        while (!leaderBoard.isEmpty()) {
            leaderBoard = expand(one_pep, leaderBoard);
            
            ArrayList<String> tmpLeaderBoard = new ArrayList<>();
            for (int i = 0; i < leaderBoard.size(); i++) {
                tmpLeaderBoard.add(leaderBoard.get(i));
            }
            
            for (int i = 0; i < leaderBoard.size(); i++) {
                String[] elems = leaderBoard.get(i).split("\\s+");
                //for(int j =0; j<elems.length;j++)
                //    System.out.println(elems[j]);
                int mass = sum_mas(elems);
                if(matchingSpectrum(elems,twin)) {
                    //System.out.println("ololo");
                    if (mass == parentMass) {
                        if (score(elems, spectrum) > score(leaderPeptide, spectrum)) {
                            //leaderPeptide = elems;
                            for (int p = 0; p < elems.length; p++) {
                                leaderPeptide[p] = elems[p];
                            }
                            
                            
                        }
                    } else if (mass > parentMass) {
                        tmpLeaderBoard.remove(leaderBoard.get(i));
                    }
                } else{
                    tmpLeaderBoard.remove(leaderBoard.get(i));
                }
                
                twin.removeAll(twin);
                for(int ii = 0; ii < spectrum.size(); ii++){
                    twin.add(spectrum.get(ii));
                }
                
                //leaderBoard = cutt(tmpLeaderBoard, N, spectrum);
                
            }
            
            leaderBoard = cutt(tmpLeaderBoard, N, spectrum);
        }
        
        
        String str = new String();
        for(int i =0 ; i<leaderPeptide.length;i++)
            if(!leaderPeptide[i].equals("-1")) {
                str += leaderPeptide[i] + '-';
            }
        System.out.println(str.substring(0,str.length()-1));
    }
    
    public static int score(String[] arr, ArrayList<Integer> spectrum){
        int score = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length() > 0) {
                if (spectrum.indexOf(Integer.parseInt(arr[i])) != -1) {
                    score++;
                }
            }
        }
        return score;
    }
    
    
    public static int sum_mas(String[] arr){
        int res = 0;
        for(int i = 0; i<arr.length; i++){
            res += Integer.parseInt(arr[i]);
        }
        return res;
    }
    
    public static ArrayList<String> cutt(ArrayList<String> lst, int N, ArrayList<Integer> spectrum){
        ArrayList<String> scores = new ArrayList<>();
        ArrayList<String> res= new ArrayList<>();
        if (lst.size()>N) {
            for (int i = 0; i < lst.size(); i++) {
                String[] tmp = lst.get(i).split("\\s");
                scores.add(String.valueOf(score(tmp, spectrum)) + "\t" + i);
            }
            //System.out.println(N + " " + scores.size());
            
            Collections.sort(scores);
            for (int i = scores.size() - 1; i > scores.size() - N - 1; i--) {
                //System.out.println(i);
                String tmp = scores.get(i);
                String[] tmpp = tmp.split("\t");
                res.add(lst.get(Integer.parseInt(tmpp[1])));
            }
        } else{
            return lst;
        }
        
        return res;
        
    }
    
    
    public static ArrayList<String> expand(List<Integer>base, ArrayList<String> curr){
        
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < base.size(); i++) {
            for (int j = 0; j < curr.size(); j++) {
                if (curr.get(j).length() > 0) {
                    result.add(curr.get(j) + ' ' + base.get(i));
                }else{
                    result.add(String.valueOf(base.get(i)));
                }
            }
        }
        
        return result;
    }
    public static  boolean matchingSpectrum(String[] arr, ArrayList<Integer> spectrum){
        int len = 0;
        //int sum = 0;
        boolean res = false;
        for(int i = 0; i<arr.length; i++){
            //sum+=Integer.parseInt(arr[i]);
            Integer num = Integer.parseInt(arr[i]);
            if(spectrum.indexOf(num)!=-1){
                len++;
                spectrum.remove(spectrum.indexOf(num));
            }
        }
        
        //System.out.println(sum);
        if (len==arr.length){
            res = true;
        }
        return res;
    }
    
    
    
}

