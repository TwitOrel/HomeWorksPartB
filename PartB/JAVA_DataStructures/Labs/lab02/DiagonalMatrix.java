package lab02;

import static java.lang.System.exit;

public class DiagonalMatrix implements Matrix{
    private final int size;
    private final double []list;
    private boolean transpose;

    //Precondition: size>0
    //Postcondition: Initializes a size*size  diagonal matrix using an array of length 2*size-1.
    public DiagonalMatrix(int size){
        try {
            if (size < 0){
                throw new RuntimeException("size cant be smaller then 0!");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            exit(-1);
        }
        this.size = size;
        this.list = new double[2*size - 1];
        this.transpose = false;
    }
    //Initializes an MAX_SIZE * MAX_SIZE diagonal matrix
    public DiagonalMatrix( ){
        this(MAX_SIZE);
    }

    @Override
    public double get(int i, int j) {
            if ( ((i >= 1) && (j >= 1) && (this.size >= i ) && (this.size >= j)) ){
                if (!transpose) {
                    return this.list[(j - i) + (this.size - 1)];
                } else {
                    return this.list[(i - j) + (this.size - 1)];
                }
            }
            else{
                throw new IndexOutOfBoundsException("i , j cant be up to "+ this.size+ " or down to 0");
            }
    }

    @Override
    public void set(int i, int j, double x) {
        if ( ((i >= 1) && (j >= 1) && (this.size >= i ) && (this.size >= j)) ) {
            if (!transpose) {
                this.list[(j - i) + (this.size - 1)] = x;
            } else {
                this.list[(i - j) + (this.size - 1)] = x;
            }
        }
        else{
                throw new RuntimeException("i , j cant be up to " + this.size + " or down to 1");
            }
    }

    @Override
    public void transpose() {
        transpose = !transpose;
    }

    @Override
    public Matrix getTranspose() {
        DiagonalMatrix m = new DiagonalMatrix(this.size);
        int tmp = this.list.length-1;
        for (double i: this.list){
                m.list[tmp] = i;
                tmp--;
        }
        return m;
    }

    //Postcondition: Returns the matrix in its natural n*n form as a string (with \t between entries of the same row and \n between rows).
    public String toString( ){
        StringBuilder res = new StringBuilder();

        for (int i = 1; i <= this.size; i++){
            for (int j = 1; j <= this.size; j++) {
                res.append(get(i, j));
                if (j == this.size){
                    res.append("\n");
                }
                else{
                    res.append("\t");
                }
            }
        }
        return res.toString();
    }

}
