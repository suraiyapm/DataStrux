import java.lang.StringBuilder;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class DoublyLinkedList<E> implements Iterable<E> {
    private static class Node<E> {
        private E data;
        private Node<E> previous; // pointer to previous node
        private Node<E> next;     // pointer to next node

        public Node(E data, Node<E> previous, Node<E> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }

        public Node(E data) {
            this(data, null, null);
        }
    }

    private Node<E> head;   // pointer to the first node of this list
    private Node<E> tail;   // pointer to the last node of this list
    private int size;       // the number of nodes currently in this list

    public DoublyLinkedList() {
        tail = head = null;
        size = 0;
    }

    // the rest of the class goes here

    public void addFirst(E element) {
        Node<E> newNo=new Node<>(element, null, head);
        if (isEmpty()) {
            tail = newNo;
        } else {
            head.previous=newNo;
        }
        head=newNo;
        size++;
    }
    public void addLast(E element) {
        if (isEmpty()) {
            addFirst(element);
        } else {
            tail = tail.next = new Node<>(element, tail, null);
            size++;
        }
    }
    public E getFirst() {
        if (isEmpty()) throw new NoSuchElementException("Empty list");
        return head.data;
    }
    public E getLast() {
        if (isEmpty()) throw new NoSuchElementException("Empty");
        return tail.data;
    }
    public int size() { return size; }
    public boolean isEmpty() { return size()==0; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr!=null) {
            sb.append(curr.data);
            if (curr!=tail) {
                sb.append(", ");
            }
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }
    public String toReverseString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        while (curr!=null) {
            sb.append(curr.data);
            if (curr.previous != null) sb.append(", ");
            curr = curr.previous;
        }
        sb.append("]");
        return sb.toString();
    }
    //added for DLL 2
    public E removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("empty");
        E toReturn = head.data;
        if (head==tail) {
            head=tail=null;
        } else {
            head = head.next;
            head.previous = null;
        }
        size--;
        return toReturn;
    }
    public E removeLast() {
        if (isEmpty()) throw new NoSuchElementException("empty");
        E toReturn = tail.data;
        if (tail==head) {
            tail = head = null;
        } else {
            tail = tail.previous;

            tail.next = null;
        }
        size--;
        return toReturn;
    }

    //DLL 3

    public void clear() {
        size=0; head=null; tail=null;
    }
    public boolean contains(Object o) {
        if (isEmpty()) return false;
        Node<E> curr=head;
        while(curr!=null) {
            if (curr.data.equals(o)) {
                return true;
            } else {
                curr=curr.next;
            }
        }
        return false;
    }
    public boolean add(E e) {
        if (e==null) return false;
        addLast(e);
        return true;
    }
    public boolean remove(Object o) {
        if (isEmpty()) return false;
        if (head.data.equals(o)) {
            removeFirst();
            return true;
        }
        Node<E> curr = head;
        while (curr!=null) {
            if (curr.data.equals(o)) {
                if (curr.next!=null) {
                    curr.previous.next = curr.next;
                    curr.next.previous = curr.previous;
                    size--;
                } else {
                    removeLast();
                }
                return true;
            }
            curr=curr.next;
        }
        return false;
    }

    //DLL 4 Additions

    public int indexOf(Object o) {
        if (isEmpty()) return -1;
        if (head.data.equals(o)) return 0;
//        if (tail.data.equals(o)) return size()-1;
        Node<E> curr=head.next;
        int indx=1;
        while (curr!=null) {
            if (curr.data.equals(o)) return indx;
            curr=curr.next;
            indx++;
        }
        return -1;
    }
    public int lastIndexOf(Object o) {
        if (isEmpty()) return -1;
        if (tail.data.equals(o)) return size()-1;
        int indx=size()-1;
        Node<E> curr=tail;
        while (curr!=null) {
            if (curr.data.equals(o)) return indx;
            curr=curr.previous;
            indx--;
        }
        return -1;
    }

    //DLL 5!

    public E get(int index) {
        if (index<0 || index>=size) throw new IndexOutOfBoundsException("outta bounds");
        Node<E> curr;
        if (index > size() / 2) {
            curr = tail;
            for (int i = size() - 1; i > index; i--) {
                curr = curr.previous;
            }
        } else {
            curr=head;
            for (int i=0; i<index; i++) {
                curr=curr.next;
            }
        }
        return curr.data;
    }
    public E set(int index,E element) {
        if (index<0 || index>=size) throw new IndexOutOfBoundsException("outta bounds");
        E toReturn = get(index);
        Node<E> curr;
        if (index>size()/2) {
            curr=tail;
            for (int i=size()-1; i>index; i--) {
                curr = curr.previous;
            }
        } else {
            curr=head;
            for (int i=0; i<index; i++) {
                curr=curr.next;
            }
        }
        curr.data=element;
        return toReturn;
    }
    public void add(int index, E element) {
        if (index<0 || index>size()) throw new IndexOutOfBoundsException("outta boundies");
        if (index==0) {
            addFirst(element);
            return;
        }
        if (index==size()) {
            addLast(element);
            return;
        }
        Node<E> curr = index>size()/2? tail : head;
        if (curr==head) {
            int i=0;
            while (i<index) {
                curr = curr.next;
                i++;
            }
        } else {
            int i=size()-1;
            while (i>index) {
                curr=curr.previous;
                i--;
            }
        }
        Node<E> newNo = new Node<E>(element, curr.previous, curr);
        curr.previous.next=newNo;
        curr.previous=newNo;
        size++;
    }
    public E remove(int index) {
        if (index<0 || index>=size) throw new IndexOutOfBoundsException("outta bounds");
        if (index==0) return removeFirst();
        if (index==size()-1) return removeLast();
        E toReturn;
        Node<E> curr = index>size()/2? tail : head;
        int i;
        if (curr==head) {
            i=0;
            while (i<index) {
                curr=curr.next;
                i++;
            }
        } else {
            i=size()-1;
            while(i>index) {
                curr=curr.previous;
                i--;
            }
        }
        toReturn=curr.data;
        curr.previous.next=curr.next;
        curr.next.previous=curr.previous;
        size--;

        return toReturn;
    }

    //DLL 6! Adding Iterable implementation, method
    @Override
    public Iterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }
    private class DoublyLinkedListIterator implements Iterator<E> {
        private Node<E> curr;
        public DoublyLinkedListIterator() {
            curr=head;
        }
        @Override
        public boolean hasNext() {
            return curr!=null;
        }
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E res = curr.data;
            curr=curr.next;
            return res;
        }
    }

}
