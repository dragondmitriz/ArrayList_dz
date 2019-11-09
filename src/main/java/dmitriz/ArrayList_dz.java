package dmitriz;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 08.11.2019.
 */
public class ArrayList_dz<T> extends AbstractList<T> implements List<T> {

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

    @Override
    public int size() {
        return size;
    }

    //добавить элемент в струкутуру данных


    //добавить все элементы любой коллекции
    //удалить элемент из структуры данных
    //удалить все элементы указанной коллекции
    //очистить коллекцию
    //вернуть количество элементов коллекции
    //Iterable и своя реализация итератора

}
