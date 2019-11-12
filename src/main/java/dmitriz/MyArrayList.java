package dmitriz;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by user on 08.11.2019.
 */
public class MyArrayList<T> extends AbstractList<T> implements Iterable<T> {

    private int size;//размерность коллекции

    private Object[] array;//рабочий массив коллекции

    private void validationSize(int next_size) {
        if (next_size >= array.length) {
            resize(next_size);
        }
    }

    private void resize(int next_size) {
        int new_size = size + size >> 1;
        if (next_size > new_size) {
            new_size = next_size;
        }
        array = Arrays.copyOf(array, new_size);
    }

    public MyArrayList() {
        size = 0;
        array = new Object[0];
    }

    public MyArrayList(int length) {
        size = length;
        array = new Object[length];
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public T set(int index, T element) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T oldElement = get(index);
        array[index] = element;
        return oldElement;
    }

    //вернуть количество элементов коллекции
    @Override
    public int size() {
        return size;
    }

    //добавить элемент в струкутуру данных
    @Override
    public boolean add(T element) {
        validationSize(size + 1);
        array[size++] = element;
        return true;
    }

    @Override
    public void add(int index, T element) {
        validationSize(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    //добавить все элементы любой коллекции
    @Override
    public boolean addAll(Collection<? extends T> collection) {
        validationSize(size + collection.size());
        for (T item : collection) {
            array[size++] = item;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        validationSize(size + collection.size());
        System.arraycopy(array, index, array, index + collection.size(), size - index);
        for (T item : collection) {
            array[index++] = item;
            size++;
        }
        return true;
    }

    //удалить элемент из структуры данных
    @Override
    public T remove(int index) {
        T remove_element = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return remove_element;
    }

    //удалить все элементы указанной коллекции
    @Override
    public boolean removeAll(Collection<?> collection) {
        Object[] arr_collection = collection.toArray();
        int count = collection.size();
        for (int i = 0; i < size; i++) {
            for (Object item : collection) {
                if (item.equals(array[i])) {
                    remove(i--);
                    break;
                }
            }
        }
        return true;
    }

    //очистить коллекцию
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        array = new Object[0];
        size = 0;
    }

    //Iterable и своя реализация итератора
    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    private class MyIterator<T> implements Iterator<T> {

        int cursor;

        MyIterator() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            return (T) array[cursor++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(cursor - 1);
        }
    }
}
