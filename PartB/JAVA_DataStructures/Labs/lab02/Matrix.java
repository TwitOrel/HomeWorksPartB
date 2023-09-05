package lab02;

public interface Matrix {
    public static final int MAX_SIZE = 100;

    //Precondition: 1 ≤ i,j ≤ n
    //Postcondition: returns the value of the element at position (i,j).
    public double get(int i, int j);

    //Precondition: 1 ≤ i,j ≤ n.
    //Postcondition: update the value of the element at position (i,j) to x.
    public void set(int i, int j, double x);

    //Postcondition: replace the current matrix wıth its transpose.
    public void transpose( );

    //Postcondition: returns a new matrix which equals to the transpose of the current matrix.
    public Matrix getTranspose( );
}
