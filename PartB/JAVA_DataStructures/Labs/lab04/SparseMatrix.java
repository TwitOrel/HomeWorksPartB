package lab04;

public class SparseMatrix<T> implements Matrix<T>{
    DLinkedList<SparseMatrixEntry> linkList;
    T defaultVal;
    int size;
    boolean transpose = false;

    public SparseMatrix (T defaultValue){
        this.linkList = new DLinkedList<>();
        this.defaultVal = defaultValue;
        this.size = MAX_SIZE;
    }

    public SparseMatrix (int size, T defaultValue){
        this.linkList = new DLinkedList<>();
        this.defaultVal = defaultValue;
        this.size = size;
    }

    @Override
    public T get(int row, int col) {
       isLegalIndex(row,col);
        SparseMatrixEntry tmp;
        if (transpose) {
            tmp = getEntry(col, row);
        }
        else {
            tmp = getEntry(row, col);
        }
        if (tmp == null){
            return this.defaultVal;
        }
        else {
            return tmp.getValue();
        }
    }
    @Override
    public void set(int row, int col, T element) {
        isLegalIndex(row,col);
        SparseMatrixEntry tmp;
        int Row;
        int Col;
        if (this.transpose) {
            Row = col;
            Col = row;
        }
        else{
            Row = row;
            Col = col;
        }
        tmp = getEntry(Row,Col);
        if(tmp != null){
            tmp.setValue(element);
        }
        else {
            SparseMatrixEntry node = new SparseMatrixEntry(Row,Col,element);
            this.linkList.insert(node);
        }
    }

    @Override
    public void transpose() {
        this.transpose = !transpose;
    }

    // Check if row and column indices are within the valid range
    private void isLegalIndex(int row,int col){
        if (row > this.size || row < 1 || col > this.size || col < 1) {
            throw new IndexOutOfBoundsException("wrong index.");
        }
    }

    // Retrieve an entry from the linked list based on row and column indices
    private SparseMatrixEntry getEntry(int row,int col){
        this.linkList.goToBeginning();
        SparseMatrixEntry tmp = this.linkList.getCursor();
        while (tmp != null){
            if (tmp.getCol() == col && tmp.getRow() == row){
                return tmp;
            }
            tmp = this.linkList.getNext();
        }
        return null;
    }

    // Private inner class representing an entry in the sparse matrix
    private class SparseMatrixEntry {

        private T value;
        private int row;
        private int col;

        public SparseMatrixEntry(int row, int col, T val) {
            this.row = row;
            this.col = col;
            this.value = val;
        }

        public int getRow() {return row;}
        public void setRow(int row) {this.row = row;}
        public int getCol() {return col;}
        public void setCol(int col) {this.col = col;}
        public T getValue() {return this.value;}
        public void setValue(T newVal) {this.value = newVal;}
    }

}