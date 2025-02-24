import java.util.ArrayList;
import java.util.Objects;

public class ArrayStringBoundedList implements StringBoundedList {
    private String[] listie;
    private int size;
    public ArrayStringBoundedList(int capacity) {
        if (capacity<0) throw new IllegalArgumentException("Gimme a nonzero");
        listie = new String[capacity];
        size=0;
    }
    public int capacity() {
        return listie.length;
    }
    public int size() {
        return size;
    }
    public void add(String s) {
        if (isFull()) throw new IllegalStateException("Full");
        listie[size++] = s;
    }
    public String get(int index) {
        if (index<0||index>=size) throw new IndexOutOfBoundsException("Invalid");
        return listie[index];
    }
    public String set(int indx, String s) {
        if (indx<0||indx>=size) throw new IndexOutOfBoundsException("Invalid");
        String temp=listie[indx];
        listie[indx]=s;
        return temp;
    }
    public int indexOf(String s) {
        for (int i=0; i<size; i++) {
            if (Objects.equals(listie[i],s)) {
                return i;
            }
        }
        return -1;
    }
    public int lastIndexOf(String s) {
        for (int i=size-1; i>=0; i--) {
            if (Objects.equals(listie[i],s)) {
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

    public void add(int index, String s) {
        if (isFull()) throw new IllegalStateException("Full");
        if (index<0||index>size) throw new IndexOutOfBoundsException("invalid");

        for (int i=size; i>index; i--) {
            listie[i]=listie[i-1];
        }
        listie[index]=s;
        size++;
    }

    public boolean remove(String s) {
        int index = indexOf(s);
        if (index<0) return false;
        remove(index);
        return true;
    }
    public String remove(int index) {
        if (index<0||index>=size) throw new IndexOutOfBoundsException("invalid");

        String removed = listie[index];
        for (int i=index; i<size-1; i++) {
            listie[i]=listie[i+1];
        }
        listie[size-1]=null;
        size--;
        return removed;
    }

}
