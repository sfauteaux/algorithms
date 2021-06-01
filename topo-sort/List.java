/*
List class for the vertices. The DAG will have an array of Lists,
where for each index of the array List[0], List[1], .... List[n-1] will be all the connections
that vertex n has.

List only has insert function, since we won't need to delete any values for a DAG.
 */
public class List{
    private Node head;
    private Node tail;
    private int size;

    public List(){
        head = null;
        tail = null;
        size = 0;
    }

    public void insert(int i){
        Node n = new Node(i);
        if(size ==0){
            head = n;
        }
        else{
            tail.setNext(n);
        }
        tail = n;
        size++;
    }

    public Node getHead() {
        return head;
    }

    public void printList(){
        Node n = head;
        while(n!=null){
            System.out.print(n.getVal() + " ");
            n = n.getNext();
        }
    }

    //Prints list backwards, for correct topological order.
    //Rather than creating another function for inserting to the front of the list vs the back,
    //I decided it'd be easiest to just insert them normally and print the list backwards.
    public void printBw(){
        int[] bw = new int[size];
        int i = 0;
        Node n = head;
        while(n!=null){
            bw[i] = n.getVal();
            n=n.getNext();
            i++;
        }
        for(i = size-1;i>=0;i--){
            System.out.print(bw[i]);
            if(i!=0){
                System.out.print(",");
            }
        }
    }
}