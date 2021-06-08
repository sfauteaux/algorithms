/*
Sean Fauteaux
6/8/2021
CSCD 320
Program 5: Dijkstra's Algorithm

This program reads in a data file for a directed, weighted, acyclic graph,
then finds the shortest paths to each vertex from a given root vertex using Dijkstra's algorithm for
finding shortest path.
 */
import java.util.*;
import java.io.*;
public class Dijkstra{
    public static void main(String[] args) throws IOException{
        //Open file, get file size

        int root = Integer.parseInt(args[1]);
        String fname = args[0];
        Scanner inFile = new Scanner(new FileInputStream("data.txt"));

        int size = fileSize(inFile);
        inFile = new Scanner(new FileInputStream("data.txt"));

        //Send file scanner, file size to generateGraph.

        Graph g = readFile(inFile, size);

        //Send graph to Dijkstra's algorithm method, then trace the paths for each vertex (if possible)

        dij_algo(g,root);

        tracePath(g,root);

        //close scanner
        inFile.close();
    }

    //Main algorithmic function for using Dijkstra's algorithm
    //Takes Graph and the vertex number which we are to solve for
    public static void dij_algo(Graph g, int s){
        if(g.vertices[s].adj.size == 0){
            //edge case where there are no ways for vertex s to reach any other nodes
            //return
            return;
        }
        //Initialization, set each vertex distance to infinity, prevhop to null
        for(int i = 0;i<g.size;i++){
            g.vertices[i].dist = Integer.MAX_VALUE;
            g.vertices[i].prevHop = null;
        }
        g.vertices[s].dist = 0;

        //Fill min-priority queue
        Heap q = new Heap(g.size);
        for(int i = 0;i<g.size;i++){
            q.insert(g.vertices[i]);
        }


        while(q.size != 0){
            Node u = q.popTop();
            Edge e = u.adj.getHead();
            while(e!=null){
                Node v = e.end;
                //relax edge between u, v.
                //must check u.dist for max value to prevent stack overflow from giving a
                //false positive in the if statement
                if(v.dist > u.dist + e.weight && u.dist != Integer.MAX_VALUE){
                    v.dist = u.dist + e.weight;
                    //repair minheap
                    //first, find index of v, then minheapify at that index
                    int j;
                    for(j = 1;j<q.size;j++){
                        if(q.a[j] == v){
                            break;
                        }
                    }
                    q.minheapify(j);
                    v.prevHop = u;
                }
                e=e.nextEdge;
            }
        }
    }

    //Traces the previous hop paths for each vertex.
    //If vertex was unreachable, print unreachable. Else, print path and weight.
    public static void tracePath(Graph g, int root){
        //For each vertex in the graph that isn't the root node, trace back all previous hops
        //If it reaches null before reaching the root node, then we print unreachable.
        //else, print path and weight
        for(int i = 0;i<g.size;i++){
            if(i != root){
                Node n = g.vertices[i];
                List trace = new List(n);

                while(n.prevHop != null){
                    n = n.prevHop;
                    trace.insertNode(n);
                }
                System.out.printf("[%d]",i);
                //Unreachable
                if(trace.nhead.val != root){
                    System.out.print("unreachable\n");
                }
                else{
                    System.out.print("shortest path:");
                    trace.printTrace();
                    System.out.printf(" shortest distance:%d\n",g.vertices[i].dist);
                }
            }
        }
    }

    public static Graph readFile(Scanner inFile, int size){
        Graph g = new Graph(size);

        while(inFile.hasNext()){
            String line = inFile.next();
            int vertex, neighbor, weight;
            //First, get the value before the colon
            int i = 0;
            while(line.charAt(i) != ':'){
                i++;
            }
            vertex = Integer.parseInt(line.substring(0,i));
            i++;
            int j = i+1;

            //Parse all values after the colon, if existing
            while(j<=line.length()){
                //Iterate to next comma + semicolon, or end of text line
                if(line.charAt(j) == ','){
                    neighbor = Integer.parseInt(line.substring(i,j));
                    i = j+1;
                    j+=2;
                    //Nested loop to find the weight value
                    while(j<=line.length()-1 && line.charAt(j) != ';'){
                        j++;
                    }
                    weight = Integer.parseInt(line.substring(i,j));
                    i=j+1;
                    j+=2;
                    //Insert to edge adjacency list
                    g.addEdge(vertex, neighbor, weight);
                }
                else{
                    j++;
                }
            }
        }
        return g;
    }

    public static int fileSize(Scanner inFile){
        int i = 0;
        while(inFile.hasNext()){
            inFile.next();
            i++;
        }
        return i;
    }
}
