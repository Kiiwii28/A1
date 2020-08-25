/** takes whole array and recurses until part of array is small enough to be below sequential cut off,
 * which is the base case, then will use the whole array as a reference and find out whether that selected chunk has basins, and print those to System.out
 **/
import java.util.concurrent.RecursiveTask;
//recursive task includes a base case and then something that makes smaller array and calls the recursive task

/**
 * Here I am testing my actual recursion logic without testing it with thread handling and parallelism
 */
public class TestRecursion{
    int lo;
    int hi;
    float[][] map; //entire array 2d used for comparisons
    int SEQUENTIAL_CUTOFF=500; //change later//default, change with constructor?
    Plot[] map1D; // gets broken down during recursion
    int iBasinCount;

    TestRecursion(float[][] mapPass,Plot[] p,  int l,int h,int SQ){
        System.out.println("Creating TestRecursion");
        lo = l;
        hi = h;
        map1D = p;
        SEQUENTIAL_CUTOFF = SQ;
        map = mapPass;

    }
    //need to return total number of basins and actual basin co-ords
    protected String RecursionFunction(float[][] map,Plot[] map1D,int lo,int hi){
        System.out.println("Entering recursive function...");
        System.out.println();
        if ((hi-lo) < SEQUENTIAL_CUTOFF){
            //int iStripBasins = 0;
            String ans = "";
            System.out.println("ans = " + ans );
            System.out.println("inside base case: ");
            System.out.println("current strip length (1D array length is constant = " + lo + " .. " + hi + "("+(hi-lo)+")");
          //  System.out.println("hi = " + hi);
          //  System.out.println("lo = " + lo);
         //   System.out.println("expecting error.........");
        //    System.out.println(map1D[4]);
            for(int f=lo; f<hi; f++){ //iterate through each element in current strip
                //check if basin
                //get col and get row
              //  System.out.println(" recursive for loop in base case at f = " + f);
                int iRow = map1D[f].getiRow();
                int iCol = map1D[f].getiCol();
                int iHiNeighbours = 0;
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
                    System.out.println(map1D[f] + " has full high neighbours.. 8");
                    //potentially print out coords of basin which will be redirected to output to tf in makefile
                    //iStripBasins++;
                    ans = ans + map1D[f].toString() + "\n"; //can use '**' to deliminate different points
                }

            }//end base case loop
            System.out.println("end of base case loop");
            return ans;

        }
        else {  //could be this.RecursiveFunction?
            System.out.println("going left I think. " + lo + " = low + mid = " + (hi+lo)/2);
            String leftAns = RecursionFunction(map,map1D,lo,(hi+lo)/2);
            System.out.println("going left I think.");
            String rightAns = RecursionFunction(map,map1D,(hi+lo)/2,hi);

            return leftAns + rightAns;
        }
    }


}