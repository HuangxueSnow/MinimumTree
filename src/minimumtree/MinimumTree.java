/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minimumtree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class MinimumTree {
static Scanner sc = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Graph graph = new Graph();
        graph.readInput(sc);
        Graph tree = graph.getMinimmTree();
        tree.print();
    }
}
class Graph{
    int[][] data;
    HashSet<Integer> nodes=new HashSet();

    void readInput(Scanner sc) {
        int nodes = sc.nextInt();
        int paths = sc.nextInt();
        data = new int[nodes][nodes];
        for(int i =0;i<nodes;i++){
            for(int j=0;j<nodes;j++){
                data[i][j]=-1;
            }
        }
        for(int i=0; i<paths;i++){
            int node1= sc.nextInt()-1;
            int node2= sc.nextInt()-1;
            int pathLength = sc.nextInt();
            data[node1][node2] = pathLength;
            data[node2][node1] = pathLength;
        }
    }

    Graph getMinimmTree() {
        Graph tree = new Graph();
        tree.data=new int[this.data.length][this.data.length];
        for(int i=0;i<this.data.length;i++){
            for(int j=0;j<this.data.length;j++){
                tree.data[i][j]=-1;
            }
        }
        tree.addNode(0);
        while (!tree.containsAllNodes()){
            expandTree(tree);
        }
        return tree;
    }

    void print() {
        System.out.println(data.length);
        for(int i=0;i<data.length;i++){
            for(int j=0;j<data.length;j++){
                if(data[i][j]!=-1)
                    System.out.println((i+1)+" "+(j+1)+" "+data[i][j]);
            }
        }
    }

    private void addNode(int i) {
        this.nodes.add(i);
    }

    private boolean containsAllNodes() {
        return this.nodes.size()==this.data.length;
    }

    private void expandTree(Graph tree) {
        int minPath = Integer.MAX_VALUE;
        int minConnectedNode = -1;
        int internalNode=-1;
        for(Integer treeNode:tree.nodes){
            for(int connectedNode=0;connectedNode< this.data.length;connectedNode++){
                int connectedPath = this.data[treeNode][connectedNode];
                if(connectedPath==-1) continue;
                if(tree.nodes.contains(connectedNode))continue;
                if(minPath>connectedPath){
                    minPath= connectedPath;
                    minConnectedNode = connectedNode;
                    internalNode=treeNode;
                }
            }
        }
        if(minConnectedNode!=-1){
            tree.nodes.add(minConnectedNode);
            tree.data[internalNode][minConnectedNode]=minPath;
            tree.data[minConnectedNode][internalNode]=minPath;
        }
    }
}