package dmitriz;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Created by user on 08.11.2019.
 */
public class MyArrayList<T> extends AbstractList<T> implements Iterable<T> {

    private int size;//размерность коллекции

    private Object[] array;//рабочий массив коллекции

    private void validationSize(int nextSize) {
        if (nextSize >= array.length) {
            resize(nextSize);
        }
    }

    private void resize(int nextSize) {
        int newSize = this.size + this.size >> 1;
        if (nextSize > newSize) {
            newSize = nextSize;
        }
        this.array = Arrays.copyOf(this.array, newSize);
    }

    public MyArrayList() {
        this.size = 0;
        this.array = new Object[0];
    }

    public MyArrayList(int length) {
        this.size = length;
        this.array = new Object[length];
    }

    @Override
    public T get(int index) {
        if (index >= this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) this.array[index];
    }

    public T set(int index, T element) {
        if (index > this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T oldElement = get(index);
        this.array[index] = element;
        return oldElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean add(T element) {
        validationSize(this.size + 1);
        this.array[this.size++] = element;
        return true;
    }

    @Override
    public void add(int index, T element) {
        validationSize(this.size + 1);
        System.arraycopy(this.array, index, this.array, index + 1, this.size - index);
        this.array[index] = element;
        this.size++;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        validationSize(this.size + collection.size());
        Object[] arrCollect = collection.toArray();
        System.arraycopy(arrCollect, 0, this.array, this.size, arrCollect.length);
        this.size += arrCollect.length;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        validationSize(this.size + collection.size());
        System.arraycopy(this.array, index, this.array, index + collection.size(), this.size - index);
        Object[] arrCollect = collection.toArray();
        System.arraycopy(arrCollect, 0, this.array, index, arrCollect.length);
        this.size += arrCollect.length;
        return true;
    }

    @Override
    public T remove(int index) {
        T removeElement = (T) this.array[index];
        System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
        this.size--;
        return removeElement;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Object[] arrCollection = collection.toArray();
        int count = collection.size();
        for (int i = 0; i < this.size; i++) {
            for (Object item : collection) {
                if (item.equals(this.array[i])) {
                    remove(i--);
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.array[i] = null;
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    private class MyIterator<T> implements Iterator<T> {

        int cursor;

        MyIterator() {
            this.cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return this.cursor < MyArrayList.this.size;
        }

        @Override
        public T next() {
            return (T) MyArrayList.this.array[this.cursor++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(this.cursor - 1);
        }
    }
}
