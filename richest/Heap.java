//Min Heap that will be used to store the 10,000 largest values
public class Heap{
    public int[] a;
    public int size;
    public int maxSize;
    public int[] sorted;

    //constructor initializes array a to specified size
    public Heap(int i){
        maxSize = i+1;
        a = new int[maxSize];
        size = 0;
    }

    //minheapify function, where i is the index of the node we want to
    //minheapify at.
    public void minheapify(int i){
        boolean lonely; //true if only one child, false otherwise
        int l;
        int r = 2;

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
        int curr = a[i];

        if(lonely){ //only left child
            if(l < curr){ //left child is less than its parent
                swap(i, 2*i);
                minheapify(2*i);
            }
            //else we have recursive condition: curr value is less than children
        }
        else{
            if(l <= r && l < curr){ //left child is less than right and its parent
                swap(i, 2*i);
                minheapify(2*i);
            }
            else if(r < l && r < curr){ //right child is less than left and parent
                swap(i, 2*i + 1);
                minheapify(2*i + 1);
            }
            //else, we have recursive condition: curr value is less than both children
        }
    }

    //Replaces top node with new value, minheapifies.
    public void dropTop(int val){
        a[1] = val;
        minheapify(1);
    }

    //Sorts the heap into descending order. Note that the original heap array
    //is lost after this is called
    public void heapSort(){
        sorted = new int[size];
        int i = 0;
        while(size > 0){
            sorted[i] = a[1];
            a[1] = a[size];
            size--;
            minheapify(1);
            i++;
        }
    }


    //Inserts element at end of array, then swaps it's way up
    //until it's parent is smaller than it.
    public void insert(int n){
        size++;
        a[size] = n; //insert into heap
        int i = size; //current index

        while(a[i/2] > a[i]){ //parent is larger than child
            swap(i, i/2); //swap the parent and child
            i = i/2; //go to parent node
        }
    }

    //Simple function to check if current node is a leaf node.
    public boolean checkLeaf(int i){
        return(2*i > size);
    }

    //swap values of two nodes
    public void swap(int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void print(){
        int i;
        int j = 1;
        int k = size+1;
        while(k>0){
            for(i=j;i<2*j && i < size+1;i++){
                System.out.print(a[i] + "  ");
            }
            System.out.println();
            k = k-j;
            j = 2 * j;
        }
    }
}