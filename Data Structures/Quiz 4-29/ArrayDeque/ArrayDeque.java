import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        indexFirst = indexLast = -1;
    }

    public int size() {
        if (indexFirst == -1 && indexLast == -1) {
            return 0;
        } else if (indexFirst <= indexLast) {
            return indexLast - indexFirst + 1;
        } else {
            return (indexLast + 1) + (elements.length - indexFirst);
        }
    }
    public boolean isEmpty() {
        return size() == 0;
    }
    public boolean isFull() {
        return size() == capacity();
    }
    public int capacity() {
        return elements.length;
    }

    @SuppressWarnings("unchecked")
    public void ensureCapacity(int desiredCapacity) {
        if (capacity() < desiredCapacity) {
            E[] newArray = (E[]) new Object[desiredCapacity];
            for (int i = 0; i < size(); i++) {
                newArray[i] = elements[(indexFirst + i) % elements.length];
            }
            elements = newArray;
            int currentSize = size();
            indexFirst = 0;
            indexLast = currentSize - 1;
        }
    }

    public void addFirst(E e) {
        if (isFull()) {
            ensureCapacity(2 * capacity() + 1);
        }
        if (isEmpty()) {
            indexFirst = indexLast = 0;
            elements[indexFirst] = e;
        } else {
            indexFirst--;
            if (indexFirst == -1) {
                indexFirst = elements.length -1;
            } else {
                indexFirst = indexFirst % elements.length;
            }
            elements[indexFirst] = e;
        }
    }

    public void addLast(E e) {
        if (isFull()) {
            ensureCapacity(2 * capacity() + 1);
        }
        if (isEmpty()) {
            indexFirst = indexLast = 0;
            elements[indexFirst] = e;
        } else {
            if (indexLast <= elements.length - 1) {
                indexLast++;
            } else {
                indexLast = (indexLast - 1) % elements.length;
            }
            elements[indexLast] = e;
        }
    }

    public E getFirst() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        return elements[indexFirst];
    }
    public E getLast() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        return elements[indexLast];
    }
    public E removeFirst() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        E result = elements[indexFirst];
        elements[indexFirst] = null;
        if (indexFirst == indexLast) {
            indexFirst = indexLast = -1;
        } else {
            indexFirst = (indexFirst + 1) % elements.length;
        }
        return result;
    }
    public E removeLast() {
        if (isEmpty()) { throw new NoSuchElementException(); }
        E result = elements[indexLast];
        elements[indexLast] = null;
        if (indexLast == indexFirst) {
            indexFirst = indexLast = -1;
        } else if (indexLast == 0) {
            indexLast = elements.length - 1;
        } else {
            indexLast = (indexFirst - 1) % elements.length;
        }
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i=0; i<size(); i++) {
            sb.append(elements[(indexFirst + i) % elements.length]);
            if (i < size() - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }

    public Iterator<E> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<E> {
        private int cursor;
        private int remaining;
        public ArrayDequeIterator() {
            cursor = indexFirst;
            remaining = size();
        }
        public boolean hasNext() {
            return remaining != 0;
        }
        public E next() {
            if (!hasNext()) { throw new NoSuchElementException(); }
            E result = elements[cursor];
            cursor = (cursor + 1) % elements.length;
            remaining--;
            return result;
        }
    }
}