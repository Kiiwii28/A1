/** takes whole array and recurses until part of array is small enough to be below sequential cut off,
 * which is the base case, then will use the whole array as a reference and find out whether that selected chunk has basins, and print those to System.out
**/
import java.util.concurrent.RecursiveTask;
    //recursive task includes a base case and then something that makes smaller array and calls the recursive task
public class BFinderStrip extends RecursiveTask<Integer>{ //# changed it to single array
    int lo;
    int hi;
    float[][] map; //entire array 2d used for comparisons
    static final int SEQUENTIAL_CUTOFF=500; //change later//default, change with constructor?
    plot[] map1D; // gets broken down during recursion
    int iBasinCount;

        /**
         * @param mapPass
         * @param l
         * @param h
         */


    BFinderStrip(float[][] mapPass,Plot[] p  int l,int h){
        low = l;
        hi = h;
        map1D = p;
        map = mapPass;

    }
        //need to return total number of basins and actual basin co-ords
    protected String compute(){
        if ((hi-lo) < SEQUENTIAL_CUTOFF){
            //int iStripBasins = 0;
            String ans = "";
            for(int f=lo; f<hi; f++){ //for this plot in this piece of thread
                //check if basin
                //get col and get row
                int iRow = strip[f].getiRow();
                int iCol = strip[f].getiCol();
                int iHiNeighbours = 0;
                /**
                 * here we make sure strip of array is small enough to fit below sequetial threshold
                 * then we loop through all elements of the sub array
                 * for each element, we check the row above, the same row and the row below, along with the column to the left
                 * column to the right and same column, effectively all neighbours with that one element to check if its a basin
                 */
                // -1 .. 0 .. 1 one row above, same row, one row below
                for (int r = -1; r<2 ; r++){ //iterating through neighbours top row to one below
                    // -1 .. 0 .. 1
                    for(int c = -1; c<2 ; c++){ //1 left to 1 right
                        if (map[iRow+r][iCol+c] > (map1D[f].getfHeight()+0.01)){ //f stays the same but iRow and iCol are used to put it into the 2d array's reference to look at neighbours
                            iHiNeighbours++;
                        }
                    }

                }
                if (iHiNeighbours == 8){
                    System.out.println(map1D[f] + "has full high neighbours.. 8");
                    //potentially print out coords of basin which will be redirected to output to tf in makefile
                    //iStripBasins++;
                    ans = ans + " ** " + map1D[f].toString(); //can use '**' to deliminate different points
                }

            }//end base case loop
            return ans;

        }
        else {
            BFinderStrip left = new BFinderStrip(map,map1D,lo,(hi+lo)/2);
            BFinderStrip(map,map1D,(hi+lo)/2,hi);

            left.fork();
            String rightAns = right.compute();
        }
    }


}