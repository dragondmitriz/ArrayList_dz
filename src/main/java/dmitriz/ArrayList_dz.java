package dmitriz;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by user on 08.11.2019.
 */
public class ArrayList_dz extends AbstractList<T> implements List<T>{

    int size=0;

    Object mass[];

    public ArrayList_dz(int length) {
        size=length;
        mass=new Object[length];
    }

    @Override
    public T get(int index) {
        T obj = mass[index];
        return obj;
    }

    @Override
    public int size() {
        return size;
    }
}
