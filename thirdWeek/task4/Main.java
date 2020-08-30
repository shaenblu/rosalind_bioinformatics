package com.company;

import java.util.*;



public class Main{
    
    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        
        
        ArrayList<String> Kmers = new ArrayList<>();
        
        while (in.hasNext()) {
            String currSeq = in.next();
            Kmers.add(currSeq);
        }
        //for(int j = 0; j<Kmers.size(); j++) {
        //    System.out.println(Kmers.get(j));
        //}
        Kmers.sort(new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });
        int k = Kmers.get(0).length();
        
        DBGraph graph = new DBGraph();
        for (int  i =0; i<Kmers.size(); i++){
            String k1 = Kmers.get(i).substring(0,k-1);
            String k2 = Kmers.get(i).substring(1, k);
            graph.addNode(k1);
            graph.addNode(k2);
            graph.addEdge(k1,k2);
            
        }
        //graph.print();
        //graph.path();
        graph.getText();
        
    }
    
}
package com.company;

import java.util.*;

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
            System.out.println("such key" + "\t" + e.getKey());
            System.out.println("such values:");
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
        HashMap<String, Integer> cc = new HashMap<String, Integer>();
        
        for(Map.Entry<String, List<String>> e : nodeMap.entrySet()) {
            cc.put(e.getKey(), e.getValue().size());
        }
        cc = sortByValues(cc);
        
        for(Map.Entry<String, Integer> e : cc.entrySet()) {
            nodes[i] = e.getKey();
            i++;
        }
        return nodes;
    }
    
    public int index(String[] str, String need){
        int t = -1;
        for (int i = 0; i < str.length; i++){
            if (str[i].equals(need)) t = i;
        }
        return t;
    }
    
    public void getText(){
        boolean[][] covered = new boolean[getNodes().length][];
        for (int i = 0; i < getNodes().length; ++i) {
            if (getNodes()[i] != null) {
                covered[i] = new boolean[getEdges(getNodes()[i]).size()];
            }
        }
        
        int[] counter = new int[getNodes().length];
        for (int  i =0; i<getNodes().length; i++) {
            //System.out.println(getNodes()[i]);
            counter[i] = getEdges(getNodes()[i]).size();
            //System.out.print(counter[i]+"\t");
        }
        
        //System.out.println();
        //Arrays.sort(counter);
        
        ArrayList<String> out = new ArrayList<>();
        for (int i = getNodes().length -1; i>=0; i--) {
            System.out.println(getNodes()[i]);
            
            for (int j = 0; j < getEdges(getNodes()[i]).size(); j++) {
                
                if (!covered[i][j]) {
                    covered[i][j] = true;
                    String edge = getEdges(getNodes()[i]).get(j);
                    //System.out.println(edge);
                    String str = getNodes()[i];
                    int ind = -1;
                    for (int t = 0; t < getNodes().length; t++)
                        if (getNodes()[t].equals(edge)) {
                            ind = t;
                            break;
                        }
                    //System.out.println(ind);
                    //System.out.println(counter[ind]);
                    if ((counter[ind] > 1) || (counter[ind] == 0)) {
                        String res = getNodes()[i];
                        res += edge.substring(edge.length() - 1, edge.length());
                        out.add(res);
                    } else {
                        ArrayList<String> seq = new ArrayList<>();
                        seq.add(str);
                        //seq.add(edge);
                        //System.out.println(str);
                        boolean stop = true;
                        while (stop) {
                            if (counter[ind] == 1) {
                                //System.out.println("sdfsfkjsdk");
                                seq.add(edge);
                                //for (int jj = 0; jj<seq.size(); jj++)
                                //    System.out.print(seq.get(jj)+"\t");
                                //System.out.println();
                                //System.out.println(getEdges(edge).size());
                                for (int q = 0; q < getEdges(edge).size(); q++) {
                                    if (!covered[ind][q]) {
                                        covered[ind][q] = true;
                                        String tmp = getEdges(edge).get(q);
                                        //System.out.println("tmptmptmptmptmp");
                                        //System.out.println(tmp);
                                        for (int t = 0; t < getNodes().length; t++)
                                            if (getNodes()[t].equals(tmp)) {
                                                ind = t;
                                                break;
                                            }
                                        //System.out.println(ind);
                                        //System.out.println(counter[ind]);
                                        edge = tmp;
                                        //seq.add(tmp);
                                        if (counter[ind] > 1) {
                                            stop = false;
                                            //seq.add(edge);
                                            //System.out.println("olololol");
                                            break;
                                        }
                                        if (counter[ind] ==  0) {
                                            stop = false;
                                            seq.add(edge);
                                            //System.out.println("pipipipipipip");
                                            break;
                                        }
                                        //if (!stop) break;
                                        
                                    } else{ stop = false;}
                                    
                                }
                                
                            }
                            
                        }
                        String ss = seq.get(0);
                        for (int ii = 1; ii < seq.size(); ii++)
                            ss += seq.get(ii).substring(seq.get(ii).length() - 1, seq.get(ii).length());
                        out.add(ss);
                        
                    }
                    
                    
                }
                
            }
        }
        for (int jj = 0; jj < out.size(); jj++){
            System.out.println(out.get(jj));
        }
        
        
    }
    
    
    public HashMap<String, Integer> sortByValues(HashMap<String, Integer> map) {
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o1)).getValue())
                .compareTo(((Map.Entry) (o2)).getValue());
            }
        });
        
        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
    
    
}