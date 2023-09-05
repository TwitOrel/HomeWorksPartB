package lab05.il.ac.telhai.ds.stack;

public class DLinkedListStack<T> implements Stack<T>{
    private final DLinkedList<T> ll;

    public DLinkedListStack(){
        this.ll = new DLinkedList<>();
    }

    @Override
    public void push(T t) {
        this.ll.insert(t);
    }

    @Override
    public T pop() {
        T tmp = this.ll.remove();
        this.ll.goToEnd();
        return tmp;
    }
    @Override
    public T top() {
        return this.ll.getCursor();
    }

    @Override
    public boolean isEmpty() {
        return this.ll.isEmpty();
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("[");
        if (!ll.isEmpty()){
            res.append(ll.getCursor());
            while (ll.hasPrev()){
                res.append(", ");
                res.append(ll.getPrev());
            }
        }
        res.append("]");
        ll.goToEnd();
        return res.toString();
    }
}
