package dmitriz.tests;

import dmitriz.MyLinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;

public class MyLinkedListTest {

    private MyLinkedList list_dz;
    private LinkedList origin;
    private List collection;
    private int count = 10;

    @Before
    public void setUp() throws Exception {
        list_dz = new MyLinkedList<Integer>();
        origin = new LinkedList<Integer>();
        collection = new ArrayList<Integer>();
        for (int i = 0; i < count; i++) {
            list_dz.add(i);
            origin.add(i);
            collection.add(i * 2);
        }
    }

    @Test
    public void get() {
        Assert.assertEquals(1, list_dz.get(1));
    }

    @Test
    public void set() {
        list_dz.set(0, 2);
        Assert.assertEquals(2, list_dz.get(0));
    }

    @Test
    public void size() {
        Assert.assertEquals(count, list_dz.size());
    }

    @Test
    public void add() {
        list_dz.add(777);
        Assert.assertEquals(777, list_dz.get(list_dz.size() - 1));
    }

    @Test
    public void addToIndex() {
        list_dz.add(3, 333);
        Assert.assertEquals(333, list_dz.get(3));
    }

    @Test
    public void addAll() {
        origin.addAll(collection);
        list_dz.addAll(collection);
        Assert.assertEquals(origin, list_dz);
    }

    @Test
    public void addAllToIndex() {
        List finalCollect = new LinkedList<Integer>(list_dz);
        finalCollect.addAll(1, collection);
        list_dz.addAll(1, collection);
        Assert.assertEquals(finalCollect, list_dz);
    }

    @Test
    public void remove() {
        origin.remove(2);
        list_dz.remove(2);
        Assert.assertEquals(origin, list_dz);
    }

    @Test
    public void removeAll() {
        origin.removeAll(collection);
        list_dz.removeAll(collection);
        Assert.assertEquals(origin, list_dz);
    }

    @Test
    public void clear() {
        origin.clear();
        list_dz.clear();
        Assert.assertEquals(origin, list_dz);
    }

    @Test
    public void iterator_hasNext() {
        Iterator iterator = list_dz.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void iterator_next() {
        Iterator iterator = list_dz.iterator();
        Assert.assertEquals(list_dz.get(0), iterator.next());
    }

    @Test
    public void iterator_remove() {
        Iterator iterator = origin.iterator();
        iterator.next();
        iterator.remove();
        iterator = list_dz.iterator();
        iterator.next();
        iterator.remove();
        Assert.assertEquals(origin, list_dz);
    }
}