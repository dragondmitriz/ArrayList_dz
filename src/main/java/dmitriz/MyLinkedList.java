package dmitriz;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MyLinkedList<T> extends AbstractList<T> implements Iterable<T> {

    private Node<T> first;
    private Node<T> last;

    private int size;

    public MyLinkedList() {
        size = 0;
    }

    public MyLinkedList(Collection<T> collection) {
        addAll(collection);
    }

    private Node<T> getNode(int index) {
        if (this.first == null) throw new ArrayIndexOutOfBoundsException();
        Node<T> node = this.first;
        while ((index > 0) && (node.next != null)) {
            node = node.next;
            index--;
        }
        return node;
    }

    @Override
    public T get(int index) {
        if (index + 1 > size) throw new ArrayIndexOutOfBoundsException();
        Node<T> node = getNode(index);
        return node.data;
    }

    public T set(int index, T element) {
        if (index + 1 > size) throw new ArrayIndexOutOfBoundsException();
        Node<T> node = getNode(index);
        T oldData = node.data;
        node.data = element;
        return oldData;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(T element) {
        Node<T> node = new Node<T>(element, null, this.last);
        if (this.last == null) {
            this.first = node;
            this.last = node;
        } else {
            last.next = node;
            this.last = node;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if ((index + 1 > size)) throw new ArrayIndexOutOfBoundsException();
        Node<T> nextNode = getNode(index);
        if (nextNode == this.first) {
            Node<T> node = new Node<T>(element, nextNode, null);
            this.first = node;
            nextNode.prev = node;
        } else {
            Node<T> prevNode = nextNode.prev;
            Node<T> node = new Node<T>(element, nextNode, prevNode);
            prevNode.next = node;
            nextNode.prev = node;
        }
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for (T item : collection) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, @NotNull Collection<? extends T> collection) {
        if (index + 1 > size) throw new ArrayIndexOutOfBoundsException();
        for (T item : collection) {
            add(index++, item);
        }
        return true;
    }

    @Override
    public T remove(int index) {
        if (index >= size) throw new ArrayIndexOutOfBoundsException();
        Node<T> rmElement = getNode(index);
        T rmData = rmElement.data;
        if ((rmElement == this.last) && (rmElement == this.first)) {
            this.first = null;
            this.last = null;
        } else if (rmElement == this.last) {
            rmElement.prev.next = null;
            this.last = rmElement.prev;
        } else if (rmElement == this.first) {
            rmElement.next.prev = null;
            this.first = rmElement.next;
        } else {
            rmElement.prev.next = rmElement.next;
            rmElement.next.prev = rmElement.prev;
        }
        rmElement = null;
        size--;
        return rmData;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Object[] arrCollection = collection.toArray();
        Node<T> node = this.first;
        boolean removeEvent = false;

        for (int i = 0; i < size; i++) {

            for (Object item : arrCollection) {

                if (item.equals(node.data)) {
                    removeEvent = true;

                    Node<T> rmNode = node;

                    if ((rmNode == this.last) && (rmNode == this.first)) {
                        this.first = null;
                        this.last = null;
                    } else if (rmNode == this.first) {
                        rmNode.next.prev = null;
                        this.first = rmNode.next;
                        i--;
                    } else if (rmNode == this.last) {
                        rmNode.prev.next = null;
                        this.last = rmNode.prev;
                    } else {
                        node = node.prev;
                        rmNode.prev.next = rmNode.next;
                        rmNode.next.prev = rmNode.prev;
                        i--;
                    }

                    rmNode = null;
                    size--;
                    break;
                }
            }

            if (node == null) {
                node = this.first;
            } else {
                node = node.next;
            }
        }
        return removeEvent;
    }

    @Override
    public void clear() {
        Node<T> node = this.first;
        while (node != null) {
            Node<T> nextNode = node.next;
            node = null;
        }
        size = 0;
    }

    @NotNull
    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        private Node cursor;
        private int index;

        MyIterator() {
        }

        @Override
        public boolean hasNext() {
            return this.index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (this.cursor == null) {
                this.cursor = first;
            } else {
                this.cursor = this.cursor.next;
            }
            index++;
            return (T) this.cursor.data;
        }

        @Override
        public void remove() {
            if (this.cursor == null) {
                return;
            }

            Node rmElement = this.cursor;
            if (rmElement.next == last) {
                rmElement.prev.next = null;
                last = rmElement.prev;
            } else if (rmElement == first) {
                rmElement.next.prev = null;
                first = rmElement.next;
            } else {
                rmElement.prev.next = rmElement.next;
                rmElement.next.prev = rmElement.prev;
            }
            this.cursor = this.cursor.next;
            rmElement = null;
            size--;
        }
    }

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
