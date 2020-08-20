import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class BFinder{
    public static void main(String [] args) throws FileNotFoundException{
            System.out.println("hello");

          String sFileIN = args[0];
          String sFileOUT = args[1];

          System.out.println(sFileIN + " " + sFileOUT);

          Scanner input = new Scanner(new File(sFileIN));
          Scanner sSizes = new Scanner(input.nextLine());

          int iRows = sSizes.nextInt();
          int iCols = sSizes.nextInt();
          System.out.println(iRows + " = rows");
        System.out.println(iCols + " = Columns");

        String sPlots = input.nextLine();
        //System.out.println(sPlots);
         Scanner loadPlots = new Scanner(sPlots);
         //loadPlots.Delimiter();
        //System.out.println("1 " + loadPlots.next());
       // System.out.println("2 " + loadPlots.next());

        float[][] map = new float[iRows][iCols];
       // System.out.println(loadPlots.nextFloat());
       // String sTest = loadPlots.next();
       // float test = sTest.valueOf();
       // float test = Float.parseFloat(sTest);
       // System.out.println(test + " = test.");
        for(int i = 0; i<iRows; i++){
            for (int j = 0; j<iCols; j++){
                map[i][j] = Float.parseFloat(loadPlots.next());
                //System.out.println(map[i][j]);
            }
            //System.out.println();
            //System.out.println("NEXT ROW");
            //System.out.println();
        }

        //System.out.println(map[0][3]);

        for(int i = 1; i<iRows-1; i++) { //miss first row and last row, first column and last column
            for(int j = 1; j<iCols-1; j++){
                System.out.print(map[i][j]+ " ");

                //find each non-edge plot
                //now check if basin by checking surrounding cells
                int iHiNeighbours = 0;
                for (int r = 0; r<2 ; r++){
                    for(int c = 0; c<2 ; c++){
                        
                    }

                }

            }
            System.out.println();
            //System.out.println();
        }

    }


}