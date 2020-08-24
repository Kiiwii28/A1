/**
 * allows each point in map to be stored in 1d array but with 2d grid co-ordinates to allow for recursion compatibility
 */
public class Plot{
    private float fHeight;
    private int iRow;
    private int iCol;
    private boolean bEdge; //ensuring edges aren't compared prevents off-by-one and null-pointer errors

    public Plot(float h, int r, int c, boolean b){
        fHeight = h;
        iRow = r;
        iCol = c;
        bEdge = b;
    }

    public float getfHeight() {
        return fHeight;
    }

    public boolean isbEdge() {
        return bEdge;
    }

    public int getiCol() {
        return iCol;
    }

    public int getiRow() {
        return iRow;
    }
}
















