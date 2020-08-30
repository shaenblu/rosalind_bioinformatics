package com.company;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        List<Integer> masses = Arrays.asList(71, 103, 115, 129, 147, 57, 137, 128, 113,
                                             131, 114, 97, 156, 87, 101, 99, 186, 163);
        Collections.sort(masses);
        
        ArrayList<Integer> spectrum = new ArrayList<>();
        while(in.hasNext()) {
            spectrum.add(in.nextInt());
        }
        ArrayList<Integer> twin = new ArrayList<>();
        for(int i =0; i < spectrum.size(); i++){
            twin.add(spectrum.get(i));
        }
        
        
        ArrayList<String> one_pep = new ArrayList<>();
        for(int i =0; i<masses.size(); i++){
            if(spectrum.indexOf(masses.get(i))!=-1){
                one_pep.add(String.valueOf(masses.get(i)));
            }
        }
        
        ArrayList<String> None= new ArrayList<>();
        ArrayList<String> cur_pep = new ArrayList<>();
        for(int i =0; i<one_pep.size(); i++){
            cur_pep.add(one_pep.get(i));
        }
        
        ArrayList<String> result = new ArrayList<>();
        boolean ind = true;
        int kk = 0;
        int t = 0;
        while(ind) {
            ArrayList<String> allpep = expand(one_pep, cur_pep);
            cur_pep.removeAll(cur_pep);
            for (int j = 0; j < allpep.size(); j++) {
                String[] elems = allpep.get(j).split("\\s");
                if (matchingSpectrum(elems, twin)){
                    //System.out.println(sum);
                    if(summary(elems, spectrum.get(spectrum.size()-1))){
                        String str = new String();
                        for(int i = 0; i<elems.length; i++){
                            if(i != elems.length-1) {
                                //System.out.println(elems[i]);
                                str += elems[i]+'-';
                            } else {str+= elems[i]; t = 100; }
                        }
                        result.add(str);
                    }
                    cur_pep.add(allpep.get(j));
                }
                twin.removeAll(twin);
                for(int i = 0; i < spectrum.size(); i++){
                    twin.add(spectrum.get(i));
                }
            }
            if(t!=0){ind = false;}
        }
        
        //System.out.println(result.size());
        
        
        for(int i = 0 ; i<result.size(); i++){
            String start = result.get(i);
            ArrayList<String> cyclo = isCyclopeptide(start);
            //for(int h = 0; h<cyclo.size(); h++)
            //    System.out.println(cyclo.get(h));
            
            //System.out.println(cyclo.size());
            int p = 0;
            for(int ii = 0; ii<cyclo.size(); ii++){
                if(result.indexOf(cyclo.get(ii))!=-1){
                    p++;
                }
            }
            //System.out.println(p);
            if(p==cyclo.size()){
                for(int tt =0; tt<cyclo.size(); tt++){
                    System.out.print(cyclo.get(tt)+' ');
                }
                break;
            }
            
        }
    }
    
    
    public static boolean summary(String[] arr, Integer end){
        int sum = 0;
        boolean res = false;
        for(int i = 0; i<arr.length; i++){
            sum+=Integer.parseInt(arr[i]);
        }
        if (end == sum){res = true;}
        return res;
    }
    
    public static ArrayList<String> isCyclopeptide(String result){
        ArrayList<String> res = new ArrayList<>();
        String elem = result;
        String new_elem = elem;
        new_elem += "-" + elem;
        String[] ed = new_elem.split("-");
        String str = new String();
        
        for(int i = 0; i<ed.length/2; i++){
            for(int j = i; j<ed.length/2+i; j++){
                str += ed[j]+'-';
            }
            res.add(str.substring(0,str.length()-1));
            str = "";
        }
        
        Collections.reverse(Arrays.asList(ed));
        
        
        for(int i = 0; i<ed.length/2; i++){
            for(int j = i; j<ed.length/2+i; j++){
                str += ed[j]+'-';
            }
            res.add(str.substring(0,str.length()-1));
            str = "";
        }
        
        return res;
    }
    
    
    public static  boolean matchingSpectrum(String[] arr, ArrayList<Integer> spectrum){
        int len = 0;
        int sum = 0;
        boolean res = false;
        for(int i = 0; i<arr.length; i++){
            sum+=Integer.parseInt(arr[i]);
            Integer num = Integer.parseInt(arr[i]);
            if(spectrum.indexOf(num)!=-1){
                len++;
                spectrum.remove(spectrum.indexOf(num));
            }
        }
        
        //System.out.println(sum);
        if (len==arr.length && spectrum.indexOf(sum)!=-1){
            res = true;
        }
        return res;
    }
    
    
    public static ArrayList<String> expand(ArrayList<String>base, ArrayList<String> curr){
        ArrayList<String> result = new ArrayList<>();
        for(int i = 0; i<base.size(); i++){
            for(int j = 0; j<curr.size(); j++){
                result.add(curr.get(j)+' '+base.get(i));
            }
        }
        
        return result;
    }
    
    
}

