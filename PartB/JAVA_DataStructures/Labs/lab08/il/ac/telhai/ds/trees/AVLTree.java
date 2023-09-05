package lab08.il.ac.telhai.ds.trees;

public class AVLTree<T extends Comparable<T>> {
    private final T val;
    private AVLTree<T> left;
    private AVLTree<T> right;
    private int height;

    public AVLTree(T val) {
        this.left = this.right = null;
        this.val = val;
        this.height = 0;
    }

    //add the value to the tree, and return the updated root of the tree.
    public AVLTree<T> add(T value) {
        if (value.compareTo(this.val) >= 0) {// val need to add to right tree
            if (this.right == null) {
                this.right = new AVLTree<>(value);
            } else {
                this.right = this.right.add(value);
            }
        } else {
            if (this.left == null) {
                this.left = new AVLTree<>(value);
            } else {
                this.left = this.left.add(value);
            }
        }

        // After adding the value, update the height and check for imbalance.
        this.updateHeight();
        if (this.leftHeight() - this.rightHeight() == 2) {
            if (this.left.leftHeight() - this.left.rightHeight() >= 0) {//LL
                return this.RotateRight();
            } else {//LR
                this.left = this.left.RotateLeft();
                return this.RotateRight();
            }
        } else if (this.leftHeight() - this.rightHeight() == -2) {
            if (this.right.leftHeight() - this.right.rightHeight() == 1) {//RL
                this.right = this.right.RotateRight();
                return this.RotateLeft();
            } else {//RR
                return this.RotateLeft();
            }
        }
        return this;
    }

    private int leftHeight() {
        return (this.left == null) ? -1 : this.left.height;
    }

    private int rightHeight() {
        return (this.right == null) ? -1 : this.right.height;
    }

    //return the value in this node
    public T getValue() {
        return this.val;
    }

    //return the left subTree of this node
    public AVLTree<T> getLeft() {
        return this.left;
    }

    //return the right subTree of this node
    public AVLTree<T> getRight() {
        return this.right;
    }

    // Private helper method to perform a left rotation on the current node.
    private AVLTree<T> RotateLeft() {
        AVLTree<T> newRoot = this.right;
        this.right = newRoot.left;
        newRoot.left = this;
        this.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    // Private helper method to perform a right rotation on the current node.
    private AVLTree<T> RotateRight() {
        AVLTree<T> newRoot = this.left;
        this.left = newRoot.right;
        newRoot.right = this;
        this.updateHeight();
        newRoot.updateHeight();
        return newRoot;
    }

    // Private helper method to update the height of the current node.
    private void updateHeight() {
        int left = (this.left == null) ? -1 : this.left.height;
        int right = (this.right == null) ? -1 : this.right.height;
        this.height = 1 + Math.max(left, right);
    }

}
