package dmitriz;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by user on 08.11.2019.
 */
public class ArrayList_dz<T> implements Iterable<T> {

    int size = 0;//размерность коллекции

    Object array[];//рабочий массив коллекции

    public ArrayList_dz(int length) {
        size = length;
        array = new Object[length];
    }

    public T get(int index) {
        return (T) array[index];
    }

    public T set(int index, T element) {
        T oldElement = get(index);
        array[index] = element;
        return oldElement;
    }

    //вернуть количество элементов коллекции
    public int size() {
        return size;
    }

    private boolean validationSize(int new_size) {
        if (new_size >= array.length) {
            resize();
        }
        return true;
    }

    private void resize() {
        int new_size = size >> 1;
        array = Arrays.copyOf(array, new_size);
    }

    //добавить элемент в струкутуру данных
    public void add(T element) {
        validationSize(size + 1);
        array[size++] = element;
    }

    public void add(int index, T element) {
        validationSize(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    //добавить все элементы любой коллекции
    public void addAll(Collection<T> collection) {
        validationSize(size + collection.size());
        for (T item : collection) {
            array[size++] = item;
        }
    }

    public void addAll(int index, Collection<T> collection) {
        validationSize(size + collection.size());
        System.arraycopy(array, index, array, index + collection.size(), size - index);
        for (T item : collection) {
            array[size++] = item;
        }
    }

    //удалить элемент из структуры данных
    public void remove(int index) {
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    //удалить все элементы указанной коллекции
    public void removeAll(Collection<T> collection) {
        int count = collection.size();
        for (int i = 0; i < size; i++) {
            for (T item : collection) {
                if (item.equals(array[i])) {
                    remove(i--);
                    collection.remove(item);
                    break;
                }
            }
        }
        size -= count;
    }

    //очистить коллекцию
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        array = new Object[0];
        size = 0;
    }

    //Iterable и своя реализация итератора
    @Override
    public Iterator<T> iterator() {
        return new Iterator_dz();
    }

    private class Iterator_dz<T> implements Iterator<T> {

        int cursor;

        public Iterator_dz() {
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public T next() {
            return (T) array[cursor++];
        }

        @Override
        public void remove() {
            ArrayList_dz.this.remove(cursor - 1);
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            int i = cursor;
            while (i < size) {
                action.accept((T) array[i++]);
            }
        }
    }
}
