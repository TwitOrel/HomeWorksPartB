package lab06.il.ac.telhai.ds.trees;

public class BinaryTree<T> implements BinaryTreeI<T> {
    private T val;
    private BinaryTreeI<T> left;
    private BinaryTreeI<T> right;

    public BinaryTree(T val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public BinaryTree(T val, BinaryTree<T> left, BinaryTree<T> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public BinaryTreeI<T> getLeft() {
        return this.left;
    }

    @Override
    public BinaryTreeI<T> getRight() {
        return this.right;
    }

    @Override
    public T getValue() {
        return this.val;
    }

    @Override
    public void setValue(T value) {
        this.val = value;
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {
        this.left = left;
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        this.right = right;
    }

    @Override
    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    // Method to calculate the height of the binary tree
    @Override
    public int height() {
        if (isLeaf()) return 0;
        else if (this.left == null) {
            return 1 + this.right.height();
        } else if (this.right == null) {
            return 1 + this.left.height();
        }
        if (this.left.height() > this.right.height()) {
            return 1 + this.left.height();
        } else {
            return 1 + this.right.height();
        }
    }

    // Method to calculate the size (number of nodes) of the binary tree
    @Override
    public int size() {
        if (isLeaf()) return 1;
        else if (this.right == null) {
            return 1 + this.left.size();
        } else if (this.left == null) {
            return 1 + this.right.size();
        }
        return 1 + this.left.size() + this.right.size();
    }

    @Override
    public void clear() {
        this.left = null;
        this.right = null;
    }

    // Method to perform a pre-order traversal of the binary tree and return a string representation
    @Override
    public String preOrder() {
        return this.preOrder(" ", " ");
    }

    // Method to perform a pre-order traversal of the binary tree with custom separators and return a string representation
    @Override
    public String preOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder tmp = new StringBuilder();
        if (isLeaf()) {
            tmp.append(separationBeforeVal).append(this.val).append(separationAfterVal);
        } else if (this.left != null && this.right != null) {
            tmp.append(separationBeforeVal).append(this.val).append(separationAfterVal).append(this.left.preOrder(separationBeforeVal, separationAfterVal)).append(this.right.preOrder(separationBeforeVal, separationAfterVal));
        } else if (this.right == null && this.left != null)
            tmp.append(separationBeforeVal).append(this.val).append(separationAfterVal).append(this.left.preOrder(separationBeforeVal, separationAfterVal));
        else if (this.right != null) {
            tmp.append(separationBeforeVal).append(this.val).append(separationAfterVal).append(this.right.preOrder(separationBeforeVal, separationAfterVal));
        }
        return tmp.toString();
    }

    // Method to perform an in-order traversal of the binary tree and return a string representation
    @Override
    public String inOrder() {
        return this.inOrder(" ", " ");
    }

    // Method to perform an in-order traversal of the binary tree with custom separators and return a string representation
    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder tmp = new StringBuilder();
        if (isLeaf()) {
            tmp.append(separationBeforeVal).append(this.val).append(separationAfterVal);
        } else if (this.right == null) {
            tmp.append(this.left.inOrder(separationBeforeVal, separationAfterVal)).append(separationBeforeVal).append(this.val).append(separationAfterVal);
        } else if (this.left == null)
            tmp.append(separationBeforeVal).append(this.val).append(separationAfterVal).append(this.right.inOrder(separationBeforeVal, separationAfterVal));
        else {
            tmp.append(this.left.inOrder(separationBeforeVal, separationAfterVal)).append(separationBeforeVal).append(this.val).append(separationAfterVal).append(this.right.inOrder(separationBeforeVal, separationAfterVal));
        }
        return tmp.toString();
    }

    // Method to perform a post-order traversal of the binary tree and return a string representation
    @Override
    public String postOrder() {
        return this.postOrder(" ", " ");
    }

    // Method to perform a post-order traversal of the binary tree with custom separators and return a string representation
    public String postOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder tmp = new StringBuilder();
        if (isLeaf()) {
            tmp.append(separationBeforeVal).append(this.val).append(separationAfterVal);
        } else if (this.right == null) {
            tmp.append(this.left.postOrder(separationBeforeVal, separationAfterVal)).append(separationBeforeVal).append(this.val).append(separationAfterVal);
        } else if (this.left == null) {
            tmp.append(this.right.postOrder(separationBeforeVal, separationAfterVal)).append(separationBeforeVal).append(this.val).append(separationAfterVal);
        } else {
            tmp.append(this.left.postOrder(separationBeforeVal, separationAfterVal)).append(this.right.postOrder(separationBeforeVal, separationAfterVal)).append(separationBeforeVal).append(this.val).append(separationAfterVal);
        }
        return tmp.toString();
    }

}
