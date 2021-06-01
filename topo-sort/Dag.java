import java.util.*;
public class Dag{
    private List[] adj; //list of adjacency lists for each node
    private List top; //list for topology sort, for nodes to be added to
    Set<Integer> visited; //set of verticies that has already been viited

    public Dag(int size){
        adj = new List[size];
        for(int i = 0;i<size;i++){
            adj[i] = new List(); //initialize each list, first node of list will be the vertex i
            adj[i].insert(i);
        }
        visited = new HashSet<Integer>();
        top = new List();
    }

    //'i' is the index value of list,
    //con is the connecting value
    public void connect(int i, int con){
        adj[i].insert(con);
    }


    //depth first search initial call, starting from vertex i
    public void dfs_init(){
        //reset all visited values to false
        visited.clear();

        for(List list : adj){
            if(!visited.contains(list.getHead().getVal())){ //if vertex hasn't been visited
                graph_DFS(list.getHead()); //Call the search function for that node
            }
        }
        top.printBw();
    }

    //Actual DFS function that goes to the end of each adjacency list and adds nodes in topological order
    public void graph_DFS(Node u){
        visited.add(u.getVal());
        Node v = u.getNext();
        //For each vertex in u adjacency list
        while(v != null){
            if(!visited.contains(v.getVal())){
                graph_DFS(adj[v.getVal()].getHead()); //go to the adjacency list for this node
            }
            v = v.getNext(); //go to next node in adjacency list of u
        }
        top.insert(u.getVal());
    }


    //Print function for testing
    public void printDag(){
        for(int i = 0;i<adj.length;i++){
            System.out.print(i + ":");
            Node n = adj[i].getHead();
            while(n!=null){
                System.out.print(n.getVal());
                if(n.getNext() == null){
                    break;
                }
                else{
                    System.out.print(",");
                    n=n.getNext();
                }
            }
            System.out.print("\n");
        }
    }
}