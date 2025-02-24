import java.util.Objects;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayBoundedList<E> implements BoundedList<E> {
    private E[] listie;
    private int size;
    public ArrayBoundedList(int capacity) {
        if (capacity<0) throw new IllegalArgumentException("Gimme a nonzero");
        listie = (E[]) new Object[capacity];
        size=0;
    }
    public int capacity() {
        return listie.length;
    }
    public int size() {
        return size;
    }
    public void add(E e) {
        if (isFull()) throw new IllegalStateException("Full");
        listie[size++] = e;
    }
    public E get(int index) {
        if (index<0||index>=size) throw new IndexOutOfBoundsException("Invalid");
        return listie[index];
    }
    public E set(int indx, E e) {
        if (indx<0||indx>=size) throw new IndexOutOfBoundsException("Invalid");
        E temp=listie[indx];
        listie[indx]=e;
        return temp;
    }
    public int indexOf(E e) {
        for (int i=0; i<size; i++) {
            if (Objects.equals(listie[i],e)) {
                return i;
            }
        }
        return -1;
    }
    public int lastIndexOf(E e) {
        for (int i=size-1; i>=0; i--) {
            if (Objects.equals(listie[i],e)) {
                return i;
            }
        }
        return -1;
    }
    public void clear() {
        for (int i=0; i<size; i++) {
            listie[i]=null;
        }
        size=0;
    }
    public String toString() {
        String s="[";
        for (int i=0; i<size; i++) {
            if (i>0) {
                s += ", ";
            }
            s+=(listie[i]);
        }
        s+=("]");
        return s;
    }

    public void add(int index, E e) {
        if (isFull()) throw new IllegalStateException("Full");
        if (index<0||index>size) throw new IndexOutOfBoundsException("invalid");

        for (int i=size; i>index; i--) {
            listie[i]=listie[i-1];
        }
        listie[index]=e;
        size++;
    }

    public boolean remove(E e) {
        int index = indexOf(e);
        if (index<0) return false;
        remove(index);
        return true;
    }
    public E remove(int index) {
        if (index<0||index>=size) throw new IndexOutOfBoundsException("invalid");

        E removed = listie[index];
        for (int i=index; i<size-1; i++) {
            listie[i]=listie[i+1];
        }
        listie[size-1]=null;
        size--;
        return removed;
    }

    //hw4 additions
    public iterator() {
        return new Iterator();
    }

    private class IteratorIncorporator implements Iterator<E> {
        private int index;
        public IteratorIncorporator() {
            index = 0;
        }
        @Override
        public boolean hasNext() {
            return index<size;
        }
        @Override
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return listie[index++];
        }
    }

}
