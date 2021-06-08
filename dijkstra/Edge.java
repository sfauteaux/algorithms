/*
Edge class that keeps track of the edges between nodes. This is the class that will
be stored in the adjacency lists of each vector for graph G.
 */
public class Edge{
    public Node start; //Node a of the edge
    public int weight;
    public Node end; //Node b of the edge

    public Edge nextEdge;

    public Edge(Node s, Node e, int w){
        start = s;
        end = e;
        weight = w;
        nextEdge = null;
    }
}