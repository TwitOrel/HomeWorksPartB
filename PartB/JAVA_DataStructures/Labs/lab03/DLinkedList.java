package lab03;

public class DLinkedList<T> implements List<T> {
    private DNode head;
    private DNode tail;
    private DNode cursor;

    public DLinkedList() {
        clear();
    }

    @Override
    public void insert(T newElement) {
        DNode tmp = new DNode(newElement);
        if (newElement == null) {
            throw new RuntimeException("element is null");
        }
        else if (isEmpty()) {
            this.head = this.tail = this.cursor = tmp;
        }
        else {
            tmp.setNext(this.cursor.getNext());
            tmp.setPrev(this.cursor);
            this.cursor.setNext(tmp);
        }
        this.cursor = tmp;
        if (!hasNext()){
            this.tail = tmp;
        }
        else {
            tmp.getNext().setPrev(tmp);
        }
        if (!hasPrev()){
            this.head = tmp;
        }
        else {
            tmp.getPrev().setNext(tmp);
        }
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T element = this.cursor.getElement();
        if (hasPrev()){
            this.cursor.getPrev().setNext(this.cursor.getNext());
        }
        else {
            this.head = this.cursor.getNext();
        }
        if (hasNext()){
            this.cursor.getNext().setPrev(this.cursor.getPrev());
            this.cursor = this.cursor.getNext();
        }
        else {
            this.tail = this.cursor.getPrev();
            this.cursor = this.head;
        }
        return element;
    }
    @Override
    public T remove(T element) {
        for (DNode node = this.head; node != null; node = node.getNext()){
            if (node.getElement().equals(element)){
                this.cursor = node;
                return remove();
            }
        }
        return null;
    }

    @Override
    public void clear() {
        this.head = this.tail = this.cursor = null;
    }

    @Override
    public void replace(T newElement) {
        if (isEmpty()){
            throw new RuntimeException("the list is empty!");
        } else if (newElement == null) {
            throw new RuntimeException("new element is null");
        }
        else {
            DNode replace = new DNode(newElement);
            replace.setNext(this.cursor.getNext());
            replace.setPrev(this.cursor.getPrev());
            if (hasPrev()){
                this.cursor.getPrev().setNext(replace);
            }
            else {
                this.head = replace;
            }
            if (hasNext()){
                this.cursor.getNext().setPrev(replace);
            }
            else {
                this.tail = replace;
            }
            this.cursor = replace;
        }

    }

    @Override
    public boolean isEmpty() {
        return (this.head == null);
    }

    @Override
    public boolean goToBeginning() {
        if (isEmpty()){
            return false;
        }
        else {
            this.cursor = this.head;
            return true;
        }
    }

    @Override
    public boolean goToEnd() {
        if (isEmpty()){
            return false;
        }
        else {
            this.cursor = this.tail;
            return true;
        }
    }
    @Override
    public T getNext() {
        if (!hasNext()){
            return null;
        }
        else {
            this.cursor = this.cursor.getNext();
            return this.cursor.getElement();
        }
    }
    @Override
    public T getPrev() {
        if (!hasPrev()) {
            return null;
        }
        else {
            this.cursor = this.cursor.getPrev();
            return this.cursor.getElement();
        }
    }

    @Override
    public T getCursor() {
        if (isEmpty()){
            return null;
        }
        else {
            return this.cursor.getElement();
        }
    }

    @Override
    public boolean hasNext() {
        if (isEmpty()){
            return false;
        }
        return this.cursor.getNext() != null;
    }

    @Override
    public boolean hasPrev() {
        if (isEmpty()){
            return false;
        }
        return this.cursor.getPrev() != null;
    }

    // Custom method to represent the doubly linked list as a string
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("[ ");
        for (DNode node = this.head; node != null; node = node.getNext()){
            if (node == this.tail){
                res.append(node.getElement()).append(" ");
            }
            else {
                res.append(node.getElement()).append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    // Private inner class representing a node in the doubly linked list
    private class DNode {
        private final T  element; // element in the list
        private DNode next; // reference to the next element
        private DNode prev; // reference to the previous element

        public DNode(T element) {
            this.element = element;
        }

        public T getElement() {
            return this.element;
        }

        public void setNext(DNode next) {
            this.next = next;
        }

        public DNode getNext() {
            return this.next;
        }

        public void setPrev(DNode prev) {
            this.prev = prev;
        }

        public DNode getPrev() {
            return this.prev;
        }
    }
}
