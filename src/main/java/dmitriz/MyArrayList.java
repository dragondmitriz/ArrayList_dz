package dmitriz;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class MyArrayList<T> extends AbstractList<T> implements Iterable<T> {

    private int size;

    private Object[] array;

    public MyArrayList() {
        this.array = new Object[10];
        this.size = 0;
    }

    public MyArrayList(int length) {
        if (length < 0) {
            throw new IllegalArgumentException("Illegal Size: " + length);
        }
        this.size = length;
        this.array = new Object[length];
    }

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

    @Override
    public T get(int index) {
        if (index < 0) throw new IllegalArgumentException("Illegal Index: " + index);
        if (index >= this.size)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds: " + index + " >  " + (size - 1));

        return (T) this.array[index];
    }

    public T set(int index, T element) {
        if (index < 0) throw new IllegalArgumentException("Illegal Index: " + index);
        if (index >= this.size)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds: " + index + " >  " + (size - 1));

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
        if (index < 0) throw new IllegalArgumentException("Illegal Index: " + index);
        if (index >= this.size)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds: " + index + " >  " + (size - 1));

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
        if (index < 0) throw new IllegalArgumentException("Illegal Index: " + index);
        if (index >= this.size)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds: " + index + " >  " + (size - 1));

        validationSize(this.size + collection.size());
        System.arraycopy(this.array, index, this.array, index + collection.size(), this.size - index);
        Object[] arrCollect = collection.toArray();
        System.arraycopy(arrCollect, 0, this.array, index, arrCollect.length);
        this.size += arrCollect.length;
        return true;
    }

    @Override
    public T remove(int index) {
        if (index < 0) throw new IllegalArgumentException("Illegal Index: " + index);
        if (index >= this.size)
            throw new ArrayIndexOutOfBoundsException("Index out of bounds: " + index + " >  " + (size - 1));

        T removeElement = (T) this.array[index];
        System.arraycopy(this.array, index + 1, this.array, index, this.size - index - 1);
        this.size--;
        return removeElement;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        Object[] arrCollection = collection.toArray();
        boolean removeEvent = false;
        for (int i = 0; i < this.size; i++) {
            for (Object item : arrCollection) {
                if (item.equals(this.array[i])) {
                    remove(i--);
                    removeEvent = true;
                    break;
                }
            }
        }
        return removeEvent;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.array[i] = null;
        }
        size = 0;
    }

    @NotNull
    @Override
    public Iterator iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        int cursor;

        MyIterator() {
        }

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public T next() {
            if (this.hasNext()) return (T) MyArrayList.this.array[this.cursor++];
            else throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(this.cursor - 1);
        }
    }
}
