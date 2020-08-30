package com.company;

import java.util.*;



public class Main{
    static ArrayList<String> kmers = new ArrayList<>();
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        int k = in.nextInt();
        
        GrayCode code = new GrayCode();
        code.main(k, kmers);
        
        //for(int i = 0; i<kmers.size(); i++)
        //    System.out.println(kmers.get(i));
        
        DBGraph graph = new DBGraph();
        for (int  i =0; i<kmers.size(); i++){
            String k1 = kmers.get(i).substring(0,k-1);
            String k2 = kmers.get(i).substring(1, k);
            graph.addNode(k1);
            graph.addNode(k2);
            graph.addEdge(k1,k2);
            
        }
        //graph.print();
        graph.path();
        
        
    }
    
}


public class DBGraph {
    
    private HashMap<String, List<String>> nodeMap = new HashMap<String, List<String>>();
    
    public void addNode(String nodeName) {
        if (!hasNode(nodeName)) {
            nodeMap.put(nodeName, new ArrayList<String>());
        }
    }
    
    public boolean hasNode(String nodeName) {
        return nodeMap.containsKey(nodeName);
    }
    
    public boolean hasEdge(String nodeName1, String nodeName2) {
        if (!hasNode(nodeName1)) return false;
        List<String> edges = nodeMap.get(nodeName1);
        return Collections.binarySearch(edges, nodeName2) != -1;
    }
    
    public void addEdge(String nodeName1, String nodeName2) {
        if (!hasNode(nodeName1)) addNode(nodeName1);
        if (!hasNode(nodeName2)) addNode(nodeName2);
        List<String> edges1 = nodeMap.get(nodeName1);
        edges1.add(nodeName2);
        Collections.sort(edges1);
    }
    
    public void print(){
        for(Map.Entry<String, List<String>> e : nodeMap.entrySet()) {
            //System.out.println("such key" + "\t" + e.getKey());
            //System.out.println("such values:");
            for (int  i =0 ; i< e.getValue().size(); i++)
                System.out.println(e.getValue().get(i));
        }
    }
    
    public ArrayList<String> getEdges(String e){
        ArrayList<String> out = new ArrayList<>();
        for(Map.Entry<String, List<String>> e1 : nodeMap.entrySet()) {
            if (e.equals(e1.getKey())){
                for (int i = 0; i< e1.getValue().size(); i++)
                    out.add(e1.getValue().get(i));
            }
        }
        return out;
    }
    
    public String[] getNodes(){
        String[] nodes = new String[nodeMap.entrySet().size()];
        int i = 0;
        for(Map.Entry<String, List<String>> e : nodeMap.entrySet()) {
            nodes[i] = e.getKey();
            i++;
        }
        Arrays.sort(nodes);
        return nodes;
    }
    
    public int index(String[] str, String need){
        int t = -1;
        for (int i = 0; i < str.length; i++){
            if (str[i].equals(need)) t = i;
        }
        return t;
    }
    
    public void path() {
        boolean[][] covered = new boolean[getNodes().length][];
        for (int i = 0; i < getNodes().length; ++i) {
            if (getNodes()[i] != null) {
                covered[i] = new boolean[getEdges(getNodes()[i]).size()];
            }
        }
        
        String str1 = getNodes()[0];
        String str2 = str1;
        ArrayList<String> cycle = new ArrayList<>();
        cycle.add(str1);
        
        getCycle(str1,str2,covered,cycle);
        
        while (true) {
            boolean end = true;
            for (int i = 0; i < covered.length; ++i) {
                for (int j = 0; covered[i] != null && j < covered[i].length; ++j) {
                    if (!covered[i][j]) {
                        str1 = getNodes()[i];
                        str2 = getNodes()[i];
                        end = false;
                    }
                }
                if (!end) {
                    break;
                }
            }
            if (end) {
                break;
            }
            
            ArrayList<String> a1 = new ArrayList<>();
            for (int i = 0; i<cycle.indexOf(str2)+1 ;i++)
                a1.add(cycle.get(i));
            
            ArrayList<String> a2 = new ArrayList<>();
            for (int i = cycle.indexOf(str2)+1; i<cycle.size() ;i++)
                a2.add(cycle.get(i));
            
            ArrayList<String> tmp = new ArrayList<>();
            getCycle(str1,str2,covered,tmp);
            
            cycle = new ArrayList<>();
            for (int i = 0; i < a1.size(); ++i) {
                cycle.add(a1.get(i));
            }
            for (int i = 0; i < tmp.size(); ++i) {
                cycle.add(tmp.get(i));
            }
            for (int i = 0; i < a2.size(); ++i) {
                cycle.add(a2.get(i));
            }
            
        }
        
        String out = cycle.get(0);
        int len = out.length();
        for(int i = 1; i < cycle.size()-len; ++i) {
            out += cycle.get(i).substring(len-1, len);
        }
        System.out.println(out);
        
    }
    
    public void getCycle(String str1,String str2, boolean[][] covered, ArrayList<String> cycle){
        while(true) {
            String next = "";
            int ind = index(getNodes(), str2);
            for (int i = 0; i < covered[ind].length; ++i) {
                if (!covered[ind][i]) {
                    next = getEdges(str2).get(i);
                    covered[ind][i] = true;
                    break;
                }
            }
            cycle.add(next);
            str2 = next;
            if (str2.equals(str1)){break;}
        }
    }
}

public class GrayCode {
    
    public static void rev(String prefix, int n, ArrayList<String> kmers) {
        if (n == 0) kmers.add(prefix);
        else {
            gray(prefix + "1", n - 1, kmers);
            rev(prefix + "0", n - 1, kmers);
        }
    }
    public static void gray(String prefix, int n, ArrayList<String> kmers) {
        if (n == 0) kmers.add(prefix);
        else {
            gray(prefix + "0", n - 1, kmers);
            rev(prefix + "1", n - 1, kmers);
        }
    }
    
    public static void main(int k, ArrayList<String> kmers) {
        
        gray("", k, kmers);
    }
    
}
