package dmitriz;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class GlobalTester {

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

        //empty arr
        System.out.println("size: " + list.size() + " " + myList.size());
        try {
            System.out.print("list get: " + list.get(0) + " ");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("list getError ");
        }
        try {
            System.out.println("myList get: " + myList.get(0));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("myList getError");
        }
        System.out.println("toArray: " + list.toArray().length + " " + myList.toArray().length);
        try {
            System.out.print("list set: " + list.set(0, 8) + " ");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("list toArrayError ");
        }
        try {
            System.out.println("myList set: " + myList.set(0, 8));
        } catch (IndexOutOfBoundsException u) {
            System.out.println("myList toArrayError");
        }
        System.out.println("size: " + list.size() + " " + myList.size());
        System.out.println("hasNext: " + list.iterator().hasNext() + " " + myList.iterator().hasNext());
        try {
            System.out.print("list iterator: " + list.iterator().next() + " ");
        } catch (NoSuchElementException e) {
            System.out.print("list iteratorError ");
        }
        try {
            System.out.println("myIterator toArray: " + myList.iterator().next());
        } catch (NoSuchElementException u) {
            System.out.println("myList iteratorError");
        }
        System.out.println("contains: " + list.contains(8) + " " + myList.contains(8));
        System.out.println("indexOf: " + list.indexOf(5) + " " + myList.indexOf(5));
        try {
            System.out.print("list remove idx: " + list.remove(0) + " ");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("list removeIdxError ");
        }
        try {
            System.out.println("myList remove idx: " + myList.remove(0));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("myList removeIdxError");
        }
        System.out.println("remove obj: " + list.remove(new Integer(0)) + " " + myList.remove(new Integer(0)));
        System.out.println("removeAll: " + list.removeAll(kek) + " " + myList.removeAll(kek));
        list.clear();
        myList.clear();
        System.out.println("---------------------------------add elem-----------------------------------------");
        System.out.println("add: " + list.add(1) + " " + myList.add(1));
        System.out.println("size: " + list.size() + " " + myList.size());
        try {
            System.out.print("list get: " + list.get(0) + " ");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("list getError ");
        }
        try {
            System.out.println("myList get: " + myList.get(0));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("myList getError");
        }
        System.out.println("toArray: " + list.toArray().length + " " + myList.toArray().length);
        try {
            System.out.print("list toArray: " + list.set(0, 8) + " ");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("list toArrayError ");
        }
        try {
            System.out.println("myList toArray: " + myList.set(0, 8));
        } catch (IndexOutOfBoundsException u) {
            System.out.println("myList toArrayError");
        }
        System.out.println("size: " + list.size() + " " + myList.size());
        System.out.println("hasNext: " + list.iterator().hasNext() + " " + myList.iterator().hasNext());
        try {
            System.out.print("list iterator: " + list.iterator().next() + " ");
        } catch (NoSuchElementException e) {
            System.out.print("list toArrayError ");
        }
        try {
            System.out.println("myIterator toArray: " + myList.iterator().next());
        } catch (NoSuchElementException u) {
            System.out.println("myList iteratorError");
        }
        System.out.println("contains: " + list.contains(8) + " " + myList.contains(8));
        System.out.println("indexOf: " + list.indexOf(5) + " " + myList.indexOf(5));
        list.forEach(System.out::print);
        System.out.println();
        myList.forEach(System.out::print);
        System.out.println();

        try {
            System.out.print("list remove idx: " + list.remove(0) + " ");
        } catch (IndexOutOfBoundsException e) {
            System.out.print("list removeIdxError ");
        }
        try {
            System.out.println("myList remove idx: " + myList.remove(0));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("myList removeIdxError");
        }
        System.out.println("remove obj: " + list.remove(new Integer(0)) + " " + myList.remove(new Integer(0)));
        System.out.println("removeAll: " + list.removeAll(kek) + " " + myList.removeAll(kek));

        System.out.println("-------------------------------------------------------------add all--------------------------------------------");
        System.out.println("addAll" + list.addAll(kek) + " " + myList.addAll(kek));
        list.forEach(System.out::print);
        System.out.println();
        myList.forEach(System.out::print);
    }
}