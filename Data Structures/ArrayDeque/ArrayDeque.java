
public class ArrayDeque<E> {
    private final int DEFAULT_CAPACITY = 10;
    int capacity;
    int size;
    public ArrayDeque() {
        this.capacity = DEFAULT_CAPACITY;
    }
    public ArrayDeque(int initialCapacity) {
        this.capacity = initialCapacity;
    }
    public int size() { return size; }
    public boolean isEmpty() { return size==0; }
    public void addFirst(E element) {

    }
}