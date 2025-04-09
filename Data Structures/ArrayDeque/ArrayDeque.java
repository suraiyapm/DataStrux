import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Iterable<E> {
    private E[] elements;
    private int indxOfFirst;
    private int indxOfLast;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }
    public ArrayDeque(int initialCapacity) {
        elements = (E[]) new Object[initialCapacity];
        indxOfFirst=indxOfLast=-1;
    }
    public int size() {
        if (indxOfFirst==-1 && indxOfLast==-1) {
            return 0;
        } else if (indxOfFirst<=indxOfLast) {
            return indxOfLast-indxOfFirst+1;
        } else {
            return (indxOfLast+1) + (elements.length-indxOfFirst); //circular/buffer
        }
    }
    public boolean isEmpty() {
        return size()==0;
    }
    public void addFirst(E element) {
        if (size()==elements.length) {
            E[] newArr = (E[]) new Object[2*elements.length+1];
            newArr[0] = element;
            for (int i=1; i<elements.length; i++) {
                newArr[i] = elements[i-1];
            }
        }
    }
}