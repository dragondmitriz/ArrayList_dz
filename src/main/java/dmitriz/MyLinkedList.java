package dmitriz;

import java.util.*;

public class MyLinkedList<T> extends AbstractList<T> implements Iterable<T>, List<T> {

    private Node first;
    private Node last;

    private int size;

    public MyLinkedList() {
        size = 0;
    }

    public MyLinkedList(Collection<T> collection) {
        addAll(collection);
    }

    private Node<T> getNode(int index) {
        if (first == null) throw new ArrayIndexOutOfBoundsException();
        Node<T> node = first;
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
        T old_data = node.data;
        node.data = element;
        return old_data;

    }

    //вернуть количество элементов коллекции
    @Override
    public int size() {
        return size;
    }

    //добавить элемент в струкутуру данных
    @Override
    public boolean add(T element) {
        Node<T> node = new Node<T>(element, null, last);
        if (last == null) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
        return true;
    }

    @Override
    public void add(int index, T element) {
        if ((index + 1 > size)) throw new ArrayIndexOutOfBoundsException();
        Node nextNode = getNode(index);
        if (nextNode == first) {
            Node node = new Node<T>(element, nextNode, null);
            first = node;
            nextNode.prev = node;
        } else {
            Node prevNode = nextNode.prev;
            Node node = new Node<T>(element, nextNode, prevNode);
            prevNode.next = node;
            nextNode.prev = node;
        }
        size++;
    }

    //добавить все элементы любой коллекции
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for (T item : collection) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (index + 1 > size) throw new ArrayIndexOutOfBoundsException();
        for (T item : collection) {
            add(index++, item);
        }
        return true;
    }

    //удалить элемент из структуры данных
    @Override
    public T remove(int index) {
        if (index + 1 > size) throw new ArrayIndexOutOfBoundsException();
        Node<T> rm_element = getNode(index);
        T rm_data = rm_element.data;
        if (rm_element == last) {
            rm_element.prev.next = null;
            last = rm_element.prev;
        } else if (rm_element == first) {
            rm_element.next.prev = null;
            first = rm_element.next;
        } else {
            rm_element.prev.next = rm_element.next;
            rm_element.next.prev = rm_element.prev;
        }
        rm_element = null;
        size--;
        return rm_data;
    }

    //удалить все элементы указанной коллекции
    @Override
    public boolean removeAll(Collection<?> collection) {
        Object[] arr_collection = collection.toArray();
        Node<T> node = first;

        for (int i = 0; i < size; i++) {

            for (Object item : collection) {

                if (item.equals(node.data)) {
                    Node<T> rmNode = node;

                    if (rmNode == first) {
                        rmNode.next.prev = null;
                        first = rmNode.next;
                        i--;
                    } else if (rmNode == last) {
                        rmNode.prev.next = null;
                        last = rmNode.prev;
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
                node = first;
            } else {
                node = node.next;
            }
        }
        return true;
    }

    //очистить коллекцию
    @Override
    public void clear() {
        Node<T> node = first;
        while (node != null) {
            Node<T> nextNode = node.next;
            node = null;
        }
        size = 0;
    }

    //Iterable и своя реализация итератора
    @Override
    public Iterator<T> iterator() {
        return new MyLinkedList.MyIterator();
    }

    private class MyIterator<T> implements Iterator<T> {

        private Node cursor;

        public MyIterator() {
            cursor = null;
        }

        @Override
        public boolean hasNext() {
            if (cursor == null) {
                return MyLinkedList.this.first != null;
            }
            return cursor.next != null;
        }

        @Override
        public T next() {
            if (cursor == null) {
                cursor = MyLinkedList.this.first;
                return (T) cursor.data;
            }
            cursor = cursor.next;
            return (T) cursor.data;
        }

        @Override
        public void remove() {
            if (cursor == null) {
                return;
            }

            Node<T> rm_element = cursor;
            if (rm_element.next == last) {
                rm_element.prev.next = null;
                last = rm_element.prev;
            } else if (rm_element == first) {
                rm_element.next.prev = null;
                first = rm_element.next;
            } else {
                rm_element.prev.next = rm_element.next;
                rm_element.next.prev = rm_element.prev;
            }
            cursor = cursor.next;
            rm_element = null;
            size--;
        }
    }

    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
