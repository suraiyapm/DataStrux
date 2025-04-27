import java.util.NoSuchElementException;
import java.util.Iterator;

public class ArrayDeque<E> implements Iterable<E> {

    private E[] elements;
    private int indexFirst;
    private int indexLast;
    private static final int DEFAULT_CAPACITY = 10;


    public ArrayDeque() {
        this(DEFAULT_CAPACITY);
    }
    @SuppressWarnings("unchecked")
    public ArrayDeque(int initialCapacity) {
        elements = (E[]) new Object[initialCapacity];
        indexFirst=indexLast=-1;
        //no elements, so invalid index. 0 means one el.
    }

    public int size() {
        if (indexFirst ==-1 && indexLast ==-1) {
            return 0;
        } else if (indexFirst<=indexLast) {
            return indexLast-indexFirst+1;
        } else { //if indexOfFirst is less than Last:
            return (indexLast+1) + (elements.length-indexFirst);
            //if array circled around, and first ends up after last, maybe with a lil blank chunk, chunk near beginning
            //(including Last) counted by IOL+1 (bc index starting from 0), then chunk near end including First would be
            //elements.length-IOF (no +1 because .length is full size, IOF is index.
        }
    }

    public boolean isEmpty() { return size()==0; }

    public int capacity() { return elements.length; }
    public boolean isFull() { return size()==capacity(); }

    public void incrementIndexFirst() {
        indexFirst = (indexFirst + 1) % elements.length;
    }
    public void incrementIndexLast() {
        indexLast = (indexLast + 1) % elements.length;
    }
    public void decrementIndexFirst() {
        indexFirst = (indexFirst -1 +elements.length) % elements.length;
    }
    public void decrementIndexLast() {
        indexLast = (indexLast -1 +elements.length) % elements.length;
    }

    public void addFirst(E element) {
        if (isFull()) {
            E[] newArr = (E[]) new Object[(elements.length * 2) + 1];
            int size = size();
            for (int i = 0; i < size; i++) {
                newArr[i + 1] = elements[(indexFirst + i) % elements.length];
            }
            elements = newArr;
            indexLast = size-1;
            indexFirst = 0;

        } else if (!isEmpty()) {
            decrementIndexFirst();
        } else {
            indexFirst=indexLast=0;
        }
        elements[indexFirst]=element;
    }
    public void addLast(E element) {
        if (isFull()) {
            E[] newArr = (E[]) new Object[(elements.length * 2) + 1];
            int size = size();
            for (int i = 0; i < size; i++) {
                newArr[i] = elements[(indexFirst + i) % elements.length];
            }
            elements = newArr;
            indexLast = size - 1;
            indexFirst = 0;
        }
        if (isEmpty()) {
            indexFirst=indexLast=0;
        } else {
           incrementIndexLast();
        }
        elements[indexLast]=element;
    }

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[indexFirst];
    }
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[indexLast];
    }

    public E removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        E removed = elements[indexFirst];
        elements[indexFirst]=null;
        if (indexFirst==indexLast) {
            indexFirst=indexLast=-1;
        } else {
            incrementIndexFirst();
        }
        return removed;
    }
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E removed = elements[indexLast];
        elements[indexLast]=null;
        if (indexFirst==indexLast) {
            indexFirst=indexLast=-1;
        } else {
            decrementIndexLast();
        }
        return removed;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for(int i=0; i<size(); i++) {
            sb.append(elements[(indexFirst+i)%elements.length]);
            if (i<size()-1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    public Iterator<E> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<E> {

        private int indx;
        private int left;
        public ArrayDequeIterator() {
            left=size();
            indx=indexFirst;
        }
        public boolean hasNext() {
            return left>0;
        }
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("empty");
            }
            E e = elements[indx];
            indx = (indx+1)%elements.length;
            left--;
            return e;
        }
    }
}