import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask; //change to recursive action if necessary
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.io.FileNotFoundException;
import java.io.File;

public class ParaBFinder{
    static long startTime = 0;

    private static void tick() {
     startTime = System.currentTimeMillis();
    }

    private static float tock() {
    return (System.currentTimeMillis() - startTime)/1000.0f;
    }


    static final ForkJoinPool fjpool = new ForkJoinPool();
    //
    static String[] FJPFind(float[][] mapPass) { //each strips basins returned and added to main output string array
    return fjpool.invoke(new BFinderStrip(mapPass,)); //first element of array is iBasin count
    }
    public static void main(String[] args) throws FileNotFoundException, InterruptedException, ExecutionException{
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

        //for output purposes
        String[] basinPositons = new String[iRows*iCols] ;

        String sPlots = input.nextLine();
        Scanner loadPlots = new Scanner(sPlots);

        float[][] map = new float[iRows][iCols];

        for(int i = 0; i<iRows; i++){ //populating matrix map full
            for (int j = 0; j<iCols; j++){
                map[i][j] = Float.parseFloat(loadPlots.next());
            }
        }//end populating array


        //  System.out.println("only non-edges");
        for(int i = 1; i<iRows-1; i++) { //miss first row and last row, first column and last column
            for(int j = 1; j<iCols-1; j++){
                // System.out.print(map[i][j]+ " ");
                // System.out.println();

                //find each non-edge plot
                //now check if basin by checking surrounding cells
                //System.out.println("Checking neighbours");
                int iHiNeighbours = 0;
                // -1 .. 0 .. 1
                for (int r = -1; r<2 ; r++){ //iterating through neighbours top row to one below
                    // -1 .. 0 .. 1
                    for(int c = -1; c<2 ; c++){ //1 left to 1 right
                        if (map[i+r][j+c] > (map[i][j]+0.01)){
                            // System.out.println(map[i+r][j+c] + " is Higher by 0.01 than " + map[i][j]);
                            iHiNeighbours++;
                        }
                    }

                }
                if (iHiNeighbours == 8){
                    System.out.println(map[i][j] + "has full high neighbours.. 8");
                    basinPositons[iBasinCount] = i + " " + j;
                    iBasinCount++;

                }
            }
            System.out.println();
            //System.out.println();
        }
        System.out.println("number of basins = " + iBasinCount);
        for (int i = 0; i<iBasinCount;i++){
            System.out.println("Basin number: " + i + " at " + basinPositons[i]);
        }
    }
    }

}