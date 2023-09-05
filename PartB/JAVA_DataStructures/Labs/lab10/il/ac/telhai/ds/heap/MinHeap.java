package lab10.il.ac.telhai.ds.heap;

public class MinHeap<T extends Comparable<T>> {
    private T[] list;
    int count;

    // Constructor to create an empty MinHeap with a specified length.
    @SuppressWarnings("unchecked")
    public MinHeap(int length) {
        this.list = (T[]) new Comparable[length + 1];
        this.count = 0;
    }

    // Constructor to create a MinHeap from an array of elements.
    @SuppressWarnings("unchecked")
    public MinHeap(T[] arr) {
        this.count = arr.length;
        this.list = (T[]) new Comparable[count + 1];

        System.arraycopy(arr, 0, list, 1, count);

        for (int i = count / 2; i > 0; i--) {
            flipDown(i, count);
        }
    }

    public boolean isFull() {
        return this.count == list.length - 1;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    // Inserts an element into the MinHeap.
    public void insert(T element) {
        if (isFull()) {
            throw new RuntimeException("Heap is full u idiot!");
        }
        count++;
        list[count] = element;
        flipUp(count);
    }

    // Returns the minimum element in the MinHeap.
    public T getMin() {
        if (isEmpty()) {
            throw new RuntimeException("empty");
        }
        return list[1];
    }

    // Deletes and returns the minimum element in the MinHeap.
    public T deleteMin() {
        if (isEmpty()) {
            System.out.println("the Heap is empty! ");
            return null;
        }
        T ret = list[1];
        list[1] = list[count];
        count--;
        flipDown(1, count);
        return ret;
    }

    /**
     * return a string represents the heap. The order of the elements are according
     * to The string starts with "[", ends with "]", and the values are seperated
     * with a comma
     */
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 1; i <= count; i++) {
            res.append(list[i]);
            if (i != count) {
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    // Helper method to swap two elements in the heap.
    private void change(int from, int to) {
        list[0] = list[from];
        list[from] = list[to];
        list[to] = list[0];
    }

    // Helper method to perform the "flip-up" operation to maintain the heap property.
    private void flipUp(int i) {
        int dad = i / 2;
        if (i > 1 && list[i].compareTo(list[dad]) < 0) {
            change(i, dad);
            flipUp(dad);
        }
    }

    // Helper method to perform the "flip-down" operation to maintain the heap property.
    private void flipDown(int i, int n) {
        int smallest = i;
        int left = 2 * i;
        int right = 2 * i + 1;

        if (left <= n && list[left].compareTo(list[smallest]) < 0) {
            smallest = left;
        }
        if (right <= n && list[right].compareTo(list[smallest]) < 0) {
            smallest = right;
        }

        if (smallest != i) {
            change(smallest, i);
            flipDown(smallest, n);
        }
    }
}
