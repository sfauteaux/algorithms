/*
Sean Fauteaux
CSCD 320
Prog 2: Richest 10,000

This program simulates a scenario where a dataset is too large to load into the RAM
all at once, and finds the 10,000 largest values from that dataset. It then sorts the
resulting 10,000 values into descending order and outputs the result into a file
in the working directory called "richest.output"
 */
import java.io.*;
import java.util.*;

public class Richest {
    public static void main(String[] args) throws IOException{
        Heap h = new Heap(10000);
        //Open scanner, begin reading in data to the heap until it is full
        Scanner inFile = new Scanner(new FileInputStream(args[0]));

        //inFile should always have next for the initial fill, since it is assumed
        //our data set is larger than our max heap size.
        while(h.size+1 < h.maxSize){
            int next = Integer.parseInt(inFile.next());
            h.insert(next);
        }

        //Check next value to see if it's greater than the smallest value in heap
        //If yes, replace current top node with new value, discard old value
        while(inFile.hasNext()) {
            int next = Integer.parseInt(inFile.next());
            if (next > h.a[1]) {
                h.dropTop(next);
            }
        }

        h.heapSort();

        //Create output file, open FileWriter
        File outFile = new File("richest.output");
        outFile.createNewFile();
        FileWriter outWrite = new FileWriter("richest.output");

        //Write the sorted heap to the output file
        for(int i = 0;i<h.sorted.length;i++){
            outWrite.write(String.valueOf(h.sorted[i]) + "\n");
        }

        //Close I/O streams
        outWrite.close();
        inFile.close();
    }
}