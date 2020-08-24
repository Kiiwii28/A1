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
    float[] strip; // gets broken down during recursion
    int iBasinCount;

        /**
         * @param mapPass
         * @param l
         * @param h
         */


    BFinderStrip(float[][] mapPass,float[] s  int l,int h){
        low = l;
        hi = h;
        strip = s;
        map = mapPass;

    }
        //need to return total number of basins and actual basin co-ords
    protected Integer compute(){
        if ((hi-lo) < SEQUENTIAL_CUTOFF){
            int iStripBasins = 0;
            for(int f=lo; f<hi; f++){
                //check if basin

            }
        }
    }


}