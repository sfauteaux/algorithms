/*
Sean Fauteaux
5/24/2021
CSCD 320
Prog 3: Fast Matrix Chain Multiplication
This program takes the dimensions of a matrix chain and calculates which
order of matrix multiplication is optimal for having the lowest time cost.
Uses dynamic programming to store the results of each calculation to prevent repeated
calculations that cause exponential time cost.
 */
import java.util.*;
import java.io.*;


public class FastMatrixMulti{
    public static void main(String[] args) throws IOException{

        //Read datafile into arraylist
        Scanner inFile = new Scanner(new FileInputStream(args[0]));
        ArrayList<Integer> matrices = new ArrayList<>();
        int i;
        while(inFile.hasNext()){
            String num = inFile.next();
            i = Integer.parseInt(num);
            matrices.add(i);
        }

        //convert arraylist to array
        int n = matrices.size();
        int[] p = new int[n];
        for(i = 0;i<n;i++){
            p[i] = matrices.get(i);
        }

        //Call matrixChain to get results
        Object[] results = matrixChain(p);
        int[][] m = (int[][]) results[0];
        int[][] s = (int[][]) results[1];

        //Call print function to print the locations of the optimal parentheses
        printOptimal(s,1,n-1);
        System.out.print("\n");
        System.out.println(m[1][n-1]);
    }

    //int[] p is the array of matrix dimensions as given from the input file
    public static Object[] matrixChain(int[] p){
        int n = p.length; //used to initialize the following arrays
        int[][] m = new int[n][n]; //m[i][j] stores the optimal time cost solution for Ai - Aj
        int[][] s = new int[n][n]; //s[i][j] stores the outermost parentheses location for Ai - Aj subchain

        //All locations [i][i] is not used, since one matrix by itself
        //has no time cost, no operations done.

        int i,j,k,l,q; //initialized outside of for loops so they aren't re-initialized with each iteration

        for(i = 1;i<n;i++){
            m[i][i] = 0;
        }

        for(l = 2;l<n;l++){ //l = length of subchain, must be minimum of 2 large
            for(i=1;i<n-l+1;i++){
                j = i+l-1; //i is start of subchain, j is end of subchain
                m[i][j] = -1; //arbitrary number used to check if m[i,j] has been changed yet

                for(k = i;k<j;k++){
                    q = m[i][k] + m[k+1][j]; //optimal time costs that have already been calculated
                    q += (p[i-1]*p[j]*p[k]); //calculated time cost of the current subchain
                    if(q<m[i][j] || m[i][j] == -1){ //if q has a lower time cost that current "optimal" solution
                        m[i][j] = q; //replace with q
                        s[i][j] = k; //location of optimal outermost parentheses
                    }
                }
            }
        }
        //create return array containing our two arrays
        Object[] ret = {m,s};
        return ret;
    }

    //Prints the optimal parentheses locations for fast matrix multiplication
    public static void printOptimal(int[][]s, int i, int j){
        if(i==j){
            System.out.printf("A%d",i);
        }
        else {
            System.out.print("(");
            printOptimal(s, i, s[i][j]);
            printOptimal(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }
}