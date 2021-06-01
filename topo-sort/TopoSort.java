import java.util.*;
import java.io.*;
public class TopoSort{
    public static void main(String[] args) throws IOException{

        String fname = args[0];
        //Get size of file to initialize DAG
        Scanner inFile = new Scanner(new FileInputStream(fname));
        int size = fileSize(inFile);

        //Input the data from the txt file into the DAG
        inFile = new Scanner(new FileInputStream(fname));
        Dag dag = generateDag(inFile,size);

        dag.dfs_init();

        inFile.close();
    }

    public static Dag generateDag(Scanner inFile, int size){
        Dag dag = new Dag(size);
        //Extract data from each line of text
        //Example line of text: "12:3,9,11,2"
        //Naming conventions for each int: "ind:edge,edge,...,edge"
        while(inFile.hasNext()){
            String line = inFile.next();
            int ind, edge;
            //First, get the value before the colon
            int i = 0;
            while(line.charAt(i) != ':'){
                i++;
            }
            ind = Integer.parseInt(line.substring(0,i));
            i++;
            int j = i+1;

            //Parse all values after the colon, if existing
            while(j<=line.length()){
                //Iterate to next comma, or end of text line
                //Case 2: End of text line is reached
                if(j==line.length()){
                    edge = Integer.parseInt(line.substring(i,j));
                    dag.connect(ind,edge);
                    break;
                }
                //Case 1: comma is reached
                if(line.charAt(j) == ','){
                    edge = Integer.parseInt(line.substring(i,j));
                    dag.connect(ind,edge);
                    i = j+1; //move starting index
                    j+=2;
                }
                else{
                    j++;
                }

            }
        }
        return dag;
    }

    public static int fileSize(Scanner inFile){
        int i = 0;
        while(inFile.hasNext()){
            inFile.next();
            i++;
        }
        return i;
    }
}