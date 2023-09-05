package lab07.il.ac.telhai.ds.trees;

public class BinarySearchTree<T extends Comparable<T>> {

    BstNode root;

    // Binary Search Tree Node
    class BstNode {
        T val;
        BstNode left, right;
        int size;

        public BstNode(T val) {
            this.val = val;
            left = null;
            right = null;
            int size = 0;
        }

        // Calculates the size of the subtree rooted at this node.
        private int sizeN() {
            if (this.right == null && this.left == null) {
                return 1;
            } else if (this.left == null) {
                return 1 + this.right.sizeN();
            } else if (this.right == null) {
                return 1 + this.left.sizeN();
            } else {
                return 1 + this.left.sizeN() + this.right.sizeN();
            }
        }

    }

    // Enumeration to represent the direction (LEFT or RIGHT)
    public enum Direction {
        LEFT, RIGHT
    }

    // Returns the val given a path from the root.
    // Used for testing. DO NOT DELETE.
    public T getValInPath(Direction... direction) {
        BstNode node = root;
        for (Direction d : direction) {
            if (d.equals(Direction.LEFT) && node.left != null)
                node = node.left;
            else if (d.equals(Direction.RIGHT) && node.right != null)
                node = node.right;
            else
                return null;
        }
        return node.val;
    }

    /**
     * Constructs an empty BinarySearchTree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * returns the number of elements in the tree
     */
    public int size() {
        if (this.root == null) {
            return 0;
        }
        if (this.root.right == null && this.root.left == null) {
            return 1;
        } else if (this.root.left == null) {
            return 1 + this.root.right.sizeN();
        } else if (this.root.right == null) {
            return 1 + this.root.left.sizeN();
        } else {
            return 1 + this.root.left.sizeN() + this.root.right.sizeN();
        }
    }

    /**
     * Adds the object value to the tree as a leaf according to the parameter.
     *
     * @param val
     * @return True, if the element was added. Otherwise false.
     */
    public boolean add(T val) {
        if (this.root == null) {
            this.root = new BstNode(val);
            return true;
        }
        BstNode ad = new BstNode(val);
        BstNode main = this.root;
        while (true) {
            if (ad.val.compareTo(main.val) < 0) {
                if (main.left == null) {
                    main.left = ad;
                    break;
                } else {
                    main = main.left;
                }
            } else if (ad.val.compareTo(main.val) > 0) {
                if (main.right == null) {
                    main.right = ad;
                    break;
                } else {
                    main = main.right;
                }
            } else {
                // main.val = ad.val
                return false;
            }
        }
        return true;
    }

    /**
     * Removes the object in the tree which is equal to the parameter.
     * Nothing is done if not found
     *
     * @param val: the object to be looked for in the tree
     * @return True, if the element was removed. Otherwise false.
     */
    public boolean remove(T val) {
        if (!isContain(root, val)) {
            return false;
        }
        root = removeNode(root, val);
        return true;
    }

    private BstNode removeNode(BstNode node, T val) {
        if (node == null) {
            return null;
        }

        if (val.compareTo(node.val) < 0) {
            node.left = removeNode(node.left, val);
        } else if (val.compareTo(node.val) > 0) {
            node.right = removeNode(node.right, val);
        } else {
            if (node.left == null && node.right == null) {
                // Case 1: Node is a leaf (no children)
                return null;
            } else if (node.left == null) {
                // Case 2: Node has only a right child
                return node.right;
            } else if (node.right == null) {
                // Case 3: Node has only a left child
                return node.left;
            } else {
                // Case 4: Node has both left and right children
                // Find the successor (smallest value in the right subtree)
                BstNode successor = findMin(node.right);
                node.val = successor.val;
                node.right = removeNode(node.right, successor.val);
            }
        }

        return node;
    }

    private BstNode findMin(BstNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    /**
     * Looks for an object which is equal to the parameter.
     *
     * @param val: the object to be looked for in the tree
     * @return true if the tree contains this object. Otherwise, return false.
     */
    public boolean contains(T val) {
        return isContain(root, val);
    }

    private boolean isContain(BstNode node, T val) {
        if (node == null) {
            return false;
        }
        if (val.compareTo(node.val) == 0) {
            return true;
        } else if (val.compareTo(node.val) > 0) {
            return isContain(node.right, val);
        } else {
            return isContain(node.left, val);
        }
    }

    private BstNode getBSTNodeFatherR(BstNode node, T val) {
        if (node.left.val.compareTo(val) == 0) {
            return node;
        } else {
            return getBSTNodeFatherR(node.right, val);
        }
    }


    /**
     * Looks for the minimal object in the tree, which is greater than or equal to
     * the parameter.
     *
     * @param val: the object to be looked for in the tree
     * @return a reference to the found object.
     */
    public T findGe(T val) {
        if (isContain(root, val)) {
            return findeBST(root, val);
        }
        return findGBST(root, val);
    }

    private T findeBST(BstNode node, T val) {
        if (node == null) {
            return null;
        }

        if (val.compareTo(node.val) == 0) {
            return node.val;
        } else if (val.compareTo(node.val) < 0) {
            T result = findeBST(node.left, val);
            if (result != null) {
                return result;
            } else {
                return node.val;
            }
        } else {
            return findeBST(node.right, val);
        }
    }

    private T findGBST(BstNode node, T val) {
        if (node == null) {
            return null;
        }

        if (val.compareTo(node.val) < 0) {
            T result = findGBST(node.left, val);
            if (result != null) {
                return result;
            } else {
                return node.val;
            }
        } else {
            return findGBST(node.right, val);
        }
    }
}