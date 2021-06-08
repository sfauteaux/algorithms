/*
List class for the adjacency list of edges that each vertex has.
 */
public class List{
    private Edge head;
    private Edge tail;
    public int size;

    public Node nhead;

    public List(){
        head = null;
        tail = null;
        nhead = null;
        size = 0;
    }
    //If passed a node value, we create a node list instead of edge list
    //This is used to print the final results
    public List(Node n){
        nhead = n;
        head = null;
        tail = null;
        size = 1;
    }

    public void insert(Edge n){
        if(size ==0){
            head = n;
        }
        else{
            tail.nextEdge = n;
        }
        tail = n;
        size++;
    }

    public Edge getHead() {
        return head;
    }

    //insert node at front of list
    public void insertNode(Node n){
        n.next = nhead;
        nhead = n;
        size++;
    }

    public void printList(){
        Edge e = head;
        while(e!=null){
            System.out.println(e.start.val + "-->" + e.end.val + " weight " + e.weight);
            e = e.nextEdge;
        }
    }

    //Prints the trace path for vertices that were able to be reached from the root vertex
    public void printTrace(){
        System.out.print("(");
        Node n = nhead;
        while(true){
            System.out.print(n.val);
            n=n.next;
            if(n!=null){
                System.out.print(",");
            }
            else{
                System.out.print(")");
                break;
            }
        }
    }
}