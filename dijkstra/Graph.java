/*
Graph class that stores a reference to each of the vertices in the graph.
 */
public class Graph{
    public Node[] vertices; //list of adjacency lists for each node
    public int size;

    public Graph(int size){
        this.size = size;
        vertices = new Node[size];
        for(int i = 0;i<size;i++){
            Node n = new Node(i);
            vertices[i] = n;
        }
    }

    //i = adjacency list being modified, j = neighboring vertex, w = weight of edge
    public void addEdge(int i, int j, int w){
        Node ni = vertices[i];
        Node nj = vertices[j];
        ni.addEdge(nj,w);
    }

    //Print function for testing
    public void printGraph(){
        for(int i = 0;i<size;i++){
            vertices[i].adj.printList();
        }
    }
}