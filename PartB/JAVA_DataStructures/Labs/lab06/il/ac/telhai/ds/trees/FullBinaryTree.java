package lab06.il.ac.telhai.ds.trees;

public class FullBinaryTree<T> extends BinaryTree<T> implements BinaryTreeI<T> {
    // Constructor for creating a FullBinaryTree with a given value
    public FullBinaryTree(T val) {
        super(val);
    }

    // Constructor for creating a FullBinaryTree with a given value and left and right subtrees
    public FullBinaryTree(T val, FullBinaryTree<T> left, FullBinaryTree<T> right) {
        super(val, left, right);
        if ((left == null && right != null) || (left != null && right == null)) {
            throw new RuntimeException("in fullBinaryTree left & right cant be null");
        }
    }

    // Method to check if a given FullBinaryTree is full (every non-leaf node has two children or it's a leaf)
    private boolean ifFull(FullBinaryTree<T> tree) {
        return ((tree.getRight() != null && tree.getLeft() != null)) || tree.isLeaf();
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {
        if (left == null && getRight() == null) {
            super.setLeft(null);
        } else if (left != null && getRight() != null && left instanceof FullBinaryTree<T>) {
            super.setLeft(left);
        } else {
            throw new RuntimeException("in fullBinaryTree left & right cant be null or not null");
        }
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        if (right == null && getLeft() == null) {
            super.setRight(null);
        } else if (right != null && getLeft() != null && right instanceof FullBinaryTree<T>) {
            super.setRight(right);
        } else {
            throw new RuntimeException("in fullBinaryTree left & right cant be null or not null");
        }
    }
}
