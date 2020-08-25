import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask; //change to recursive action if necessary
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.io.FileNotFoundException;
import java.io.File;


public class ParaBFinder {
    static long startTime = 0;

    private static void tick() {
        startTime = System.currentTimeMillis();
    }

    private static float tock() {
        // (System.currentTimeMillis() - startTime) / 1000.0f; /
        return (System.currentTimeMillis() - startTime); //returns miliseconds
    }


    static final ForkJoinPool fjpool = new ForkJoinPool();

    //hi must be iPlots so that it only checks the full part of the 1D array

    static String FJPFind(float[][] mapPass, Plot[] p,int lo, int iPlots, int SQ) { //each strips basins returned and added to main output string array

        return fjpool.invoke(new BFinderStrip(mapPass, p, 0, iPlots,SQ)); //first element of array is iBasin count
    }

    //function for this class which invokes forkjoin pool which creates and executes many BFinderStrip objects

    public static void main(String[] args) throws FileNotFoundException, InterruptedException, ExecutionException {
        //System.out.println("Main para running");
        int iBasinCount = 0;

        String sFileIN = args[0];
        String sFileOUT = args[1];

        //System.out.println(sFileIN + " " + sFileOUT);

        Scanner input = new Scanner(new File(sFileIN));
        Scanner sSizes = new Scanner(input.nextLine());

        int iRows = sSizes.nextInt();
        int iCols = sSizes.nextInt();
        //System.out.println(iRows + " = rows");
      //  System.out.println(iCols + " = Columns");

        //for output purposes
       // String[] basinPositons = new String[iRows * iCols];

        String sPlots = input.nextLine();
        Scanner loadPlots = new Scanner(sPlots);

        //instantiate 2D and 1D array
        float[][] map = new float[iRows][iCols];
        Plot[] map1D = new Plot[iRows * iCols]; //theoretically should be size (iRows-2)*(iCol-2) but iPlots will equal the index of the last non-null element in the array
        int iPlots = 0; //map1D's counter

        for (int i = 0; i < iRows; i++) { //populating matrix map full
            for (int j = 0; j < iCols; j++) {
                map[i][j] = Float.parseFloat(loadPlots.next());
                //map1D[iPlots] = new Plot(map[i][j], i, j, false); //pass in height, that heights row and col and default non-edge
                //iPlots++;
            }
        }//end populating array 2D and 1D of object type Plot

        //populate 1D array with all non-edges
        //This way you avoid off by 1 errors and null-pointer exceptions in BFinderStrip
        for(int i = 1; i<iRows-1; i++) { //miss first row and last row, first column and last column
            for(int j = 1; j<iCols-1; j++) { //miss first column and last column
            map1D[iPlots] = new Plot(map[i][j], i , j, false); //pass in height, that heights row and col and default non-edge
            iPlots++;
            }
        }
       // System.out.println("iPlots = " + iPlots + ". (iR-2)(iC-2) = " + (iRows-2)*(iCols-2));

        /**
         * System.out.println("Only non-edges in 1D array");
         *             for(int f = 0; f<iPlots; f++){
         *                 System.out.println(map1D[f] + " = " + f + "// height = " + map1D[f].getfHeight());
         *
         *             }
         * checks output for non-edges passed in.
         *
         */

      //  System.out.println("map1D length = " + map1D.length);
     //   System.out.println("plots number is  = " + iPlots);

        int sq = Math.round((iRows-2)/2);
        System.out.println(sq + " = sq");
        tick();
        //String[] results = FJPFind(map,map1D);
        //String ans = FJPFind(map,map1D,0,iPlots,iRows-2); //backup

        //String ans = FJPFind(map,map1D,0,iPlots,sq); //DC
        FJPFind(map,map1D,0,iPlots,sq);
        //LIKE  String ans = TR.RecursionFunction(map,map1D,0,iPlots); //where fjpool just calls compute. (which is recursive function)
        float time = tock();

        /**
        //OUTPUT
        String[] lines = ans.split("\n");
        iBasinCount = lines.length;
        System.out.println("Number of basins = " + iBasinCount );
        for(int f = 0; f< lines.length;f++){
            System.out.println(lines[f]);
        }

        //System.out.println(" ans = ");
      //  System.out.println(ans);
        System.out.println("Run took " + time + " miliseconds");
        **/
        System.out.println("Run took " + time + " miliseconds");
        //output below





























    }

}
