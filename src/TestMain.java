import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask; //change to recursive action if necessary
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.io.FileNotFoundException;
import java.io.File;

public class TestMain{
    static long startTime = 0;

    private static void tick() {
        startTime = System.currentTimeMillis();
    }

    private static float tock() {
        return (System.currentTimeMillis() - startTime) / 1000.0f; //returns seconds
    }


    static final ForkJoinPool fjpool = new ForkJoinPool();

    //
  //  static String[] FJPFind(float[][] mapPass, Plot[] p) { //each strips basins returned and added to main output string array
    //    return fjpool.invoke(new BFinderStrip(mapPass, p, 0, p.length)); //first element of array is iBasin count
    //}

    public static void main(String[] args) throws FileNotFoundException, InterruptedException, ExecutionException {
        System.out.println("Test Main running");
        int iBasinCount = 0;

        String sFileIN = args[0];
        String sFileOUT = args[1];

        System.out.println(sFileIN + " " + sFileOUT);

        Scanner input = new Scanner(new File(sFileIN));
        Scanner sSizes = new Scanner(input.nextLine());

        int iRows = sSizes.nextInt();
        int iCols = sSizes.nextInt();
        System.out.println(iRows + " = rows");
        System.out.println(iCols + " = Columns");

        //String[] basinPositons = new String[iRows * iCols];

        String sPlots = input.nextLine();
        Scanner loadPlots = new Scanner(sPlots);

        float[][] map = new float[iRows][iCols];
        Plot[] map1D = new Plot[iRows * iCols];
        int iPlots = 0; //map1D's counter
        //int SQCUTTOFF = (iRows-2); //if iRow = 4, then cuttoff is 2.
            int SQCUTTOFF = iRows ; //trivial = 4
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

        System.out.println("iPlots = " + iPlots + ". (iR-2)(iC-2) = " + (iRows-2)*(iCols-2) );

        /**
        System.out.println("Only non-edges in 1D array");
            for(int f = 0; f<iPlots; f++){
                System.out.println(map1D[f] + " = " + f + "// height = " + map1D[f].getfHeight());

            }
         **/

//array not always full, so use iPlot as first upper limit
        TestRecursion TR = new TestRecursion(map,map1D,0,iPlots,SQCUTTOFF);
            System.out.println("map1d length = " + map1D.length);
        System.out.println("plots number is  = " + iPlots);
            String ans = TR.RecursionFunction(map,map1D,0,iPlots);
            String[] lines = ans.split("\n");
            iBasinCount = lines.length;
            System.out.println("Number of basins = " + iBasinCount );
            for(int f = 0; f< lines.length;f++){
                System.out.println(lines[f]);
            }

            System.out.println(" ans = ");
            System.out.println(ans);

           //Scanner output = new Scanner(ans); //indexes should be integers
           //System.out.println(output.next() + output.next());
           //System.out.println(output.nextInt() + " " + output.nextInt());
            //output.useDelimiter("**");

           /** while(output.hasNext()){
                System.out.println(output.next().strip());
            }
            **/

        /**
        tick();
        String[] results = FJPFind(map,map1D);
        float time = tock();

        System.out.println("Run took " + time + " seconds");

        //output below





    **/


    }

}
