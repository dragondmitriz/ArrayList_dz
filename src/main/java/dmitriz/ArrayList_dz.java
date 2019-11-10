package dmitriz;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by user on 08.11.2019.
 */
public class ArrayList_dz<T> {

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
    public boolean add(T element) {
        validationSize(size + 1);
        array[size++] = element;
        return true;
    }

    public boolean add(int index, T element) {
        validationSize(size + 1);
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
        return true;
    }

    //добавить все элементы любой коллекции
    public boolean addAll(Collection<T> collection) {
        validationSize(size + collection.size());
        for (T item : collection) {
            array[size++] = item;
        }
        return true;
    }

    public boolean addAll(int index, Collection<T> collection) {
        validationSize(size + collection.size());
        System.arraycopy(array, index, array, index + collection.size(), size - index);
        for (T item : collection) {
            array[size++] = item;
        }
        return true;
    }

    //удалить элемент из структуры данных
    public void remove(int index) {
        System.arraycopy(array, index + 1, array, index, size - index - 1);
    }

    public void removeAll(Collection<T> collection){
        for(int i=0;i<size;i++){
            for(T item: collection){
                if (item.equals(array[i])){
                    remove(i--);
                    collection.remove(item);
                    break;
                }
            }
        }
    }

    //удалить все элементы указанной коллекции
    //очистить коллекцию
    //вернуть количество элементов коллекции
    //Iterable и своя реализация итератора

}
