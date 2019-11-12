package dmitriz.tests;

import dmitriz.MyArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;

public class StepanTest {

    List<Integer> list;
    List<Integer> myList;
    List kek;

    @Before
    public void setUp() throws Exception {
        list = new ArrayList<>();
        myList = new MyArrayList<>();
        kek = asList(1, 2, 3);
    }

    @Test
    public void ArrayList() {
        List<Integer> list = new ArrayList<>();
        List<Integer> myList = new MyArrayList<>();

        List kek = asList(1, 2, 3);

        Object resultExcepted, resultActual;

        //empty arr
        Assert.assertEquals(list.size(), myList.size());
        try {
            resultExcepted = list.get(0);
        } catch (IndexOutOfBoundsException e) {
            resultExcepted = "getError";
        }
        try {
            resultActual = myList.get(0);
        } catch (IndexOutOfBoundsException e) {
            resultActual = "getError";
        }
        Assert.assertEquals(resultExcepted, resultActual);
        Assert.assertEquals(list.toArray().length, myList.toArray().length);
        try {
            resultExcepted = list.set(0, 8);
        } catch (IndexOutOfBoundsException e) {
            resultExcepted = "setError";
        }
        try {
            resultActual = myList.set(0, 8);
        } catch (IndexOutOfBoundsException u) {
            resultActual = "setError";
        }
        Assert.assertEquals(resultExcepted, resultActual);
        Assert.assertEquals(list.size(), myList.size());
        Assert.assertEquals(list.iterator().hasNext(), myList.iterator().hasNext());
        try {
            resultExcepted = list.iterator().next();
        } catch (NoSuchElementException e) {
            resultExcepted = "iteratorError";
        }
        try {
            resultActual = myList.iterator().next();
        } catch (NoSuchElementException u) {
            resultActual = "iteratorError";
        }
        Assert.assertEquals(resultExcepted, resultActual);
        Assert.assertEquals(list.contains(8), myList.contains(8));
        Assert.assertEquals(list.indexOf(5), myList.indexOf(5));
        try {
            resultExcepted = list.remove(0);
        } catch (IndexOutOfBoundsException e) {
            resultExcepted = "removeIdxError";
        }
        try {
            resultActual = myList.remove(0);
        } catch (IndexOutOfBoundsException e) {
            resultActual = "removeIdxError";
        }
        Assert.assertEquals(resultExcepted, resultActual);
        Assert.assertEquals(list.remove(new Integer(0)), myList.remove(new Integer(0)));
        Assert.assertEquals(list.removeAll(kek), myList.removeAll(kek));
        list.clear();
        myList.clear();
        //---------------------------------add elem-----------------------------------------
        Assert.assertEquals(list.add(1), myList.add(1));
        Assert.assertEquals(list.size(), myList.size());
        try {
            resultExcepted = list.get(0);
        } catch (IndexOutOfBoundsException e) {
            resultExcepted = "getError";
        }
        try {
            resultActual = myList.get(0);
        } catch (IndexOutOfBoundsException e) {
            resultActual = "getError";
        }
        Assert.assertEquals(resultExcepted, resultActual);
        Assert.assertEquals(list.toArray().length, myList.toArray().length);
        try {
            resultExcepted = list.set(0, 8);
        } catch (IndexOutOfBoundsException e) {
            resultExcepted = "setError";
        }
        try {
            resultActual = myList.set(0, 8);
        } catch (IndexOutOfBoundsException u) {
            resultActual = "setError";
        }
        Assert.assertEquals(resultExcepted, resultActual);
        Assert.assertEquals(list.size(), myList.size());
        Assert.assertEquals(list.iterator().hasNext(), myList.iterator().hasNext());
        try {
            resultExcepted = list.iterator().next();
        } catch (NoSuchElementException e) {
            resultExcepted = "iteratorError";
        }
        try {
            resultActual = myList.iterator().next();
        } catch (NoSuchElementException u) {
            resultActual = "iteratorError";
        }
        Assert.assertEquals(resultExcepted, resultActual);
        Assert.assertEquals(list.contains(8), myList.contains(8));
        Assert.assertEquals(list.indexOf(5), myList.indexOf(5));
        list.forEach(System.out::print);
        System.out.print(" : ");
        myList.forEach(System.out::print);
        System.out.println();

        try {
            resultExcepted = list.remove(0);
        } catch (IndexOutOfBoundsException e) {
            resultExcepted = "removeIdxError";
        }
        try {
            resultActual = myList.remove(0);
        } catch (IndexOutOfBoundsException e) {
            resultActual = "removeIdxError";
        }
        Assert.assertEquals(resultExcepted, resultActual);
        Assert.assertEquals(list.remove(new Integer(0)), myList.remove(new Integer(0)));
        Assert.assertEquals(list.removeAll(kek), myList.removeAll(kek));
        //-------------------------------------------add all--------------------------------------------
        Assert.assertEquals(list.addAll(kek), myList.addAll(kek));
        list.forEach(System.out::print);
        System.out.print(" : ");
        myList.forEach(System.out::print);
    }
}