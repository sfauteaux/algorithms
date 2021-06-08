/*
Min-Heap class that uses an array of Nodes to keep track of which nodes haven't been checked with
Dijkstra's algorithm yet.
 */
public class Heap{
    public Node[] a;
    public int size;
    public int maxSize;


    //constructor initializes array a to specified size
    public Heap(int i){
        maxSize = i+1;
        a = new Node[maxSize];
        size = 0;
    }

    //minheapify function, where i is the index of the node we want to
    //minheapify at.
    public void minheapify(int i){
        boolean lonely; //true if only one child, false otherwise
        Node l;
        Node r = null;

        if(2*i + 1 <= size){ //two kids exist
            lonely = false;
            l = a[2*i];
            r = a[2*i +1];
        }
        else if(2*i <= size){ //only left kid exists
            lonely = true;
            l = a[2*i];
        }
        //Recursive condition: if current node is leaf
        else{ //2i > size, so i is a leaf node.
            return;
        }
        Node curr = a[i];

        if(lonely){ //only left child
            if(l.dist < curr.dist){ //left child is less than its parent
                swap(i, 2*i);
                minheapify(2*i);
            }
            //else we have recursive condition: curr value is less than children
        }
        else{
            if(l.dist <= r.dist && l.dist < curr.dist){ //left child is less than right and its parent
                swap(i, 2*i);
                minheapify(2*i);
            }
            else if(r.dist < l.dist && r.dist < curr.dist){ //right child is less than left and parent
                swap(i, 2*i + 1);
                minheapify(2*i + 1);
            }
            //else, we have recursive condition: curr value is less than both children
        }
    }

    //Inserts element at end of array, then swaps it's way up
    //until it's parent is smaller than it.
    public void insert(Node n){
        size++;
        a[size] = n; //insert into heap
        if(size==1){
            return;
        }
        int i = size; //current index

        while(a[i/2].dist > a[i].dist){ //parent is larger than child
            swap(i, i/2); //swap the parent and child
            i = i/2; //go to parent node
            if(i<=1){
                break;
            }
        }
    }

    //Simple function to check if current node is a leaf node.
    public boolean checkLeaf(int i){
        return(2*i > size);
    }

    public Node popTop(){
        Node n = a[1];
        a[1] = a[size];
        size--;
        minheapify(1);
        return n;
    }

    //swap values of two nodes
    public void swap(int i, int j){
        Node temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void print(){
        int i;
        int j = 1;
        int k = size+1;
        while(k>0){
            for(i=j;i<2*j && i < size+1;i++){
                System.out.print(a[i].val + "  ");
            }
            System.out.println();
            k = k-j;
            j = 2 * j;
        }
    }
}