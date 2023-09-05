package lab09.il.ac.telhai.ds.hash;

import lab03.DLinkedList;
import lab03.List;

public class HashTable<V> {
    public static final int DEF_MAX_HASH_SIZE = 10;
    private final List<V>[] l;

    /**
     * constructor
     * constructs a hash-table of max-size "DEF_MAX_HASH_SIZE".
     */
    public HashTable() {
        // TODO add your implementation.
        this(DEF_MAX_HASH_SIZE);

    }

    /**
     * constructs a hash-table of size 'hashSize'.
     *
     * @param hashSize, the size of the constructed hash-table.
     */
    public HashTable(int hashSize) {
        // TODO add your implementation.
        l = new DLinkedList[hashSize];
        for (int i = 0; i < DEF_MAX_HASH_SIZE; i++) {
            l[i] = new DLinkedList<>();
        }
    }

    private int index(V val) {
        return Math.abs(val.hashCode());
    }

    /**
     * @param val
     * @return true if the hash-table contains val, otherwise return false
     */
    public boolean contains(V val) {
        // TODO add your implementation.
        l[index(val) % l.length].goToBeginning();
        List<V> LL = l[index(val) % l.length];
        Object tmp = LL.getCursor();
        while (tmp != null) {
            if (tmp.equals(val)) {
                return true;
            }
            tmp = LL.getNext();
        }
        return false;
    }

    /**
     * Add val to the hash-table.
     *
     * @param val
     * @return If the val has already existed in the the hash-table, it will not be
     * added again. Return true if the val was added successfully. Otherwise
     * return false.
     */
    public boolean add(V val) {
        if (contains(val)) {
            return false;
        } else {
            l[index(val) % l.length].insert(val);
            return true;
        }
    }

    /**
     * Remove val from the hash-table.
     *
     * @param val
     * @return Return true if the val was removed successfully. Otherwise return
     * false.
     */
    public boolean remove(V val) {
        return l[index(val) % l.length].remove(val) != null;
        // TODO add your implementation.
    }

    /**
     * clear all the data in the hash-table
     */
    public void clear() {
        // TODO add your implementation.
        for (List<V> ll : l) {
            ll.clear();
        }
    }

    /**
     * @return true if the hash-table is empty, otherwise return false.
     */
    public boolean isEmpty() {
        for (int i = 0; i < l.length; i++) {
            if (!l[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
