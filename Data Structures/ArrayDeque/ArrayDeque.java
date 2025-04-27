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

    public void addFirst(E element) {
        if (size()==elements.length) {
            E[] newArr = (E[]) new Object[(elements.length * 2) + 1];
            int size = size();
            for (int i = 0; i < size; i++) {
                newArr[i + 1] = elements[(indexFirst + i) % elements.length];
            }
            elements = newArr;
            indexLast = size-1;
            indexFirst = 0;

        } else if (!isEmpty()) {
            indexFirst=(indexFirst-1 + elements.length)%elements.length;
        } else {
            indexFirst=indexLast=0;
        }
        elements[indexFirst]=element;
    }
    public void addLast(E element) {
        if (size()==elements.length) {
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
            indexLast=(indexLast+1)%elements.length;
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
        E removedEl = elements[indexFirst];
        elements[indexFirst]=null;
        if (indexFirst==indexLast) {
            indexFirst=indexLast=-1;
        } else {
            indexFirst=(indexFirst+1)%elements.length;
        }
        return removedEl;
    }
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E removedEl = elements[indexLast];
        elements[indexLast]=null;
        if (indexFirst==indexLast) {
            indexFirst=indexLast=-1;
        } else {
            indexLast = (indexLast-1+elements.length)%elements.length;
        }
        return removedEl;
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
            E el = elements[indx];
            indx = (indx+1)%elements.length;
            left--;
            return el;
        }
    }
}

//improved lol
//
//public class ArrayDeque<E> implements Iterable<E> {
//    private E[] elements;
//    private int indexOfFirst;
//    private int indexOfLast;
//    private static final int DEFAULT_INITIAL_CAPACITY = 10;
//
//    //Default constructor (making sure always a meaningful state)
//    public ArrayDeque() {
//        this(DEFAULT_INITIAL_CAPACITY);
//    }
//    //Constructor for specified initial cap
//    public ArrayDeque(int initialCapacity) {
//        if (initialCapacity < 0) {
//            throw new IllegalArgumentException();
//        }
//        elements = (E[]) new Object[initialCapacity];
//        indexOfFirst = indexOfLast = -1; //0 would be one element. -1 for invalid index.
//    }
//
//
//    public int size() {
//        if (indexOfFirst == -1 || indexOfLast == -1) {
//            return 0;
//        } else if (indexOfFirst <= indexOfLast) { //for normal order, before circling
//            return indexOfLast - indexOfFirst + 1;
//        } else { //For circular scenario, number in the beginning but last, plus first but later.
//            return (indexOfLast + 1) + elements.length - indexOfFirst;
//        }
//    }
//
//    public boolean isEmpty() {
//        return size() == 0;
//    }
//    public boolean isFull() {
//        return size() == capacity();
//    }
//    private int capacity() {
//        return elements.length;
//    }
//
//    @SuppressWarnings("unchecked")
//    public void ensureCapacity(int desiredCapacity) {
//        if (capacity() < desiredCapacity) {
//            E[] newArr = (E[]) new Object[desiredCapacity];
//            //Create a new array of the correct new size first ^
//            //Then, copy array elements to new array, no need to push by one for indexOfFirst becaust we can maintain
//            //its circular nature.
//            for (int i = 0; i < size(); i++) {
//                newArr[i] = elements[(indexOfFirst + i) % elements.length];
//            }
//            elements = newArr; //make "elements" (ref var for prev array) point to new array.
//            int currSize = size() - 1;
//            indexOfFirst = 0;
//            indexOfLast = currSize - 1;
//
//        }
//    }
//
//    public void addFirst(E element) {
//        if (isFull()) {
//            ensureCapacity(2 * capacity() + 1);
//        } else if (!isEmpty()) { //If not empty, increment first with this:
//            indexOfFirst = (indexOfFirst - 1 + elements.length) % elements.length;
//        } else { //So, if empty, adding one would set both indices to 0, the first, one el.
//            indexOfFirst = indexOfLast = 0;
//        }
//        elements[indexOfFirst] = element;
//
//    }
//}