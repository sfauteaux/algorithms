/*
Node class that stores the information of each vertex, including it's index value (val),
adjacency list of connected edges (adj), prev hop, and total distance from given root vertex.
 */
public class Node{
    public Node next;
    public int val;
    public List adj;

    public Node prevHop;
    public int dist;

    public Node(int v){
        val = v;
        next = null;
        prevHop = null;
        dist = Integer.MAX_VALUE;
        adj = new List();
    }

    //Adds an edge to the adjacency list for this node, given the other node
    public void addEdge(Node v, int w){
        Edge e = new Edge(this,v,w); //create edge
        adj.insert(e);
    }

}