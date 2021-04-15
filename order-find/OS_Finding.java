/*
Sean Fauteaux
4/15/2021
CSCD 320
Prog 1: Finding ith order statistic.

This program takes an unsorted array of integers and finds the requested ith order statistic
using a divide-and-conquer method based on the QuickSort algorithm.
 */

import java.util.*;
import java.io.*;

public class OS_Finding{
    public static void main(String[] args) throws IOException{
        //First, read data into arraylist, convert to array
        Scanner inFile = new Scanner(new FileInputStream(args[0]));
        ArrayList<Integer> input = new ArrayList<Integer>();

        while(inFile.hasNextInt()){
            input.add(inFile.nextInt());
        }

        int size = input.size();
        int A[] = new int[size];

        for(int i = 0;i<size;i++){
            A[i] = input.get(i);
        }

        //the ith order in which we are looking for
        int order = Integer.parseInt(args[1]);
        if(order > size){
            System.out.println("null");
        }
        else {
            int orderFind = randSelect(A, 0, size - 1, order);
            System.out.println(orderFind);
        }
    }

    //Randomly selects an element to use as the pivot and determines what order that number is
    //p = lower index, r = upper index, i = the ith order in which we are looking for
    public static int randSelect(int[] A, int p, int r, int i){
        if(p==r){ //recursive condition
            return A[p];
        }

        int q = randPartition(A, p, r);
        int k = q-p+1; //k is the order of our randomly selected pivot value q
        if(i==k){ //this means we've found the answer
            return A[q];
        }
        else if (i<k){ //k order is too far right
            return randSelect(A,p,q-1,i);//move q left by 1
        }
        else{ //move our p index up to q, adjust the ith order by k
            return randSelect(A,q+1,r,i-k);
        }
    }

    //Randomly swaps one of the values within the bounds, with the rightmost value.
    //Because we're using the rightmost value as the pivot, this randomly selects a new pivot
    //value when called, the partitions around that pivot.
    public static int randPartition(int[] A, int left, int right){
        Random rand = new Random();
        int i = rand.nextInt(right-left+1) + left;
        return partition(A,left,right);
    }

    //Partition function from the QuickSort algorithm, using the right index as the pivot
    //int left, right are the indices for the boundaries that we are partitioning
    public static int partition(int[] A, int left, int right){
        int piv = A[right];
        int i = left;
        //parse through subarray
        for(int j = left; j<right;j++){
            if(A[j] <= piv){ //if current value is less than pivot val
                swap(A,i,j); //swap values at curr, index
                i++; //advance index by 1
            }
        }
        swap(A,i,right); //swap the value at index with rightmost value
        return i;
    }

    //i, j are the indices of the values that are to be swapped
    public static void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

}
