package com.company;

import java.util.*;



public class Main {
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();
        String str2 = str1.substring(1, str1.length() - 1);
        String[] arr = str2.split(" ");
        int end = arr.length;
        
        for(int k = 1; k<=end; k++){
            String kk = String.valueOf(k);
            int rr = indexOf(arr, "+"+kk);
            if (rr!=-1){
                if(rr-k+1!=0) {
                    String[] tmp = revv(arr, rr, k - 1);
                    System.out.print("(");
                    for (int i = 0; i < tmp.length; i++) {
                        if (i == tmp.length - 1) {
                            System.out.println(tmp[i] + ")");
                        } else {
                            System.out.print(tmp[i] + " ");
                        }
                        
                    }
                    tmp[k - 1] = "+" + tmp[k - 1].substring(1);
                    System.out.print("(");
                    for (int i = 0; i < tmp.length; i++) {
                        if (i == tmp.length - 1) {
                            System.out.println(tmp[i] + ")");
                        } else {
                            System.out.print(tmp[i] + " ");
                        }
                        
                    }
                    arr = tmp;
                }
                //System.out.println(rr+" "+"+"+kk);
            } else{
                int ind = indexOf(arr, "-"+kk);
                String[] tmp = revv(arr, ind, k-1);
                System.out.print("(");
                for(int i =0; i< tmp.length;i++){
                    if (i==tmp.length-1) {
                        System.out.println(tmp[i] + ")");
                    } else{
                        System.out.print(tmp[i] + " ");
                    }
                    
                }
                arr = tmp;
                //System.out.println(ind+" "+"-"+kk);
                
            }
        }
        
    }
    public static int indexOf(String[] tmp, String str){
        int res = -1;
        for(int i = 0; i<tmp.length; i++){
            if (tmp[i].equals(str)){
                res = i;
            }
        }
        return res;
    }
    public static String[] revv(String[] tmp, int ind, int st){
        String[] res = new String[tmp.length];
        ArrayList<String> lst = new ArrayList<>();
        if (st!=0) {
            for (int i = 0; i < st; i++) {
                lst.add(tmp[i]);
            }
        }
        for(int i = ind; i>=st; i--){
            if (tmp[i].startsWith("-")){
                lst.add("+"+tmp[i].substring(1));
            } else{
                lst.add("-"+tmp[i].substring(1));
            }
        }
        if (ind<tmp.length-1){
            for(int i = ind+1; i<tmp.length; i++){
                lst.add(tmp[i]);
            }
        }
        //System.out.println(lst.size());
        //System.out.println(tmp.length);
        for(int i = 0; i<lst.size(); i++){
            res[i]=lst.get(i);
        }
        return res;
    }
}
