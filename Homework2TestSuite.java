package sol;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.IList;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class Homework2TestSuite {

    private MutableList<Integer> emptyList;
    private IList<Integer> myIntegerList;
    private MutableList<String> myStringList;

    private MutableList<Integer> myRepeatList = new MutableList<>();

    @Before
    public void setup() {
        // TODO: set up any testing data here
        //  (the setup method runs before *each* test method, thus resetting the data)
        MutableList<Integer> O = new MutableList<>();
        emptyList = O;

        MutableList<Integer> a = new MutableList<>();
        a.addFirst(5);
        a.addFirst(7);
        a.addFirst(3);
        a.addFirst(9);
        myIntegerList = a;
        // List = (9, 3, 7, 5)

        MutableList<String> b = new MutableList<>();
        b.addFirst("s");
        b.addFirst("q");
        b.addFirst("waldo");
        b.addFirst("n");
        myStringList = b;
        // List = ("n", "waldo", "q", "s")

        MutableList<Integer> c = new MutableList<>();
        c.addFirst(1);
        c.addLast(2);
        c.addLast(3);
        c.addLast(3);
        c.addLast(4);
        myRepeatList = c;
        // List = (1, 2, 3, 3, 4)

    }

    //need more cases

    // Task 1-A

    // Task 8

    @Test
    public void testGet() {
        assertEquals(myIntegerList.get(0).intValue(), 9);
        assertEquals(myIntegerList.get(2).intValue(), 7);
        assertEquals(myStringList.get(0), "n");
        assertEquals(myStringList.get(2), "q");
    }

    //Task 1-C

    @Test(expected = RuntimeException.class)
    public void testGetRuntimeException() {
        emptyList.get(0);
        emptyList.get(5);
        myIntegerList.get(10);
        myIntegerList.get(100);
        myIntegerList.get(100);
        myIntegerList.get(-1);
        myIntegerList.get(-23);
    }

    // Task 2-A,D

    @Test
    public void testAddLast() {
        MutableList<Integer> x = new MutableList<Integer>();
        x.addFirst(3);
        x.addLast(7);
        x.addFirst(9);
        assertFalse(compareTwoLists(x, myIntegerList));
        x.addLast(5);
        assertTrue(compareTwoLists(x, myIntegerList));
    }

    //need more cases

    // Task 8

    // Task 3-A,D

    @Test
    public void testSize() {
        MutableList<Integer> blank = new MutableList<Integer>();
        blank.addFirst(2);
        blank.addLast(3);
        blank.addFirst(1);
        assertEquals(blank.size(), 3);
        blank.addLast(4);
        blank.addLast(0);
        blank.remove(2);
        assertEquals(blank.size(), 4);
    }

    //need more cases

    // Tasks 5-A, 6-A,F,G and 8

    // Task 6-G

    /*
    these tests (edge case tests for the beginning and end of list) should not
    work because they do not modify the first and last fields in mutable list
     */
    @Test
    public void testRemove() {
        MutableList<Integer> onefour = new MutableList<>();
        onefour.addFirst(1);
        onefour.addLast(3);
        onefour.addLast(4);
        System.out.println(myRepeatList.size());
        assertFalse(myRepeatList.remove(8));
        System.out.println(myRepeatList);
        assertEquals(myRepeatList.size(), 5);
        System.out.println(myRepeatList);
        assertTrue(myRepeatList.remove(3));
        assertEquals(myRepeatList.size(), 4);
        assertTrue(myRepeatList.remove(2));
        assertEquals(myRepeatList.size(), 3);
        assertTrue(compareTwoLists(myRepeatList, onefour));
    }

    @Test
    public void testRemoveWithNodes() {
        MutableList<Integer> onefour = new MutableList<>();
        onefour.addFirst(1);
        onefour.addLast(3);
        onefour.addLast(4);
        assertEquals(myRepeatList.size(), 5);
        assertEquals(myRepeatList.removeWithNodes(74), false);
        assertEquals(myRepeatList.removeWithNodes(3), true);
        assertEquals(myRepeatList.size(), 4);
        assertEquals(myRepeatList.removeWithNodes(2), true);
        assertEquals(myRepeatList.size(), 3);
        assertTrue(compareTwoLists(myRepeatList, onefour));
        assertEquals(myRepeatList.removeWithNodes(1), true);
        assertEquals(myRepeatList.removeWithNodes(4), true);
        assertEquals(myRepeatList.get(0).intValue(), 3);

        // failing in case where list is one node long
        MutableList<Integer> numList = new MutableList<>();
//        numList.addLast(3);
//        numList.addLast(2);
        numList.addLast(1);
        assertTrue(numList.removeWithNodes(1));
    }

    // Task 7-F

    /**
     * I would normally expect this to work by removing the node 8 and then
     * not functioning on the second call of remove. It seems to work just fine
     * and only removes 8 from the list. Nothing else goes wrong. Did I mess
     * something up?
     */

//    @Test
//    public void testDoubleRemove() {
//        MutableList<Integer> M = new MutableList<>();
//        M.addFirst(5);
//        M.addLast(8);
//        M.addLast(3);
//        System.out.println(M);
//        Node N = M.getNode(8);
//        M.removeNode(N);
//        System.out.println(M);
//        M.removeNode(N);
//        System.out.println(M);
//
//    }

    // based on toString in the test method, it is expected that
    @Test
    public void testGetNode() {
        // myStringList = ("n", "waldo", "q", "s")
        MutableList<String> walList = new MutableList<>();
        walList.addFirst("waldo");
        walList.addLast("q");
        walList.addLast("s");
        Node<String> waldoNode = new Node<>("waldo");
        Node<String> nNode = new Node<>("n");
        assertTrue(myStringList.getNode("waldo").hasItem("waldo"));
        assertTrue(myStringList.getNode("n").hasItem("n"));
        assertFalse(myStringList.getNode("n").hasItem("t"));
        //Assert.assertNull(myStringList.getNode("DNE"));
    }
    @Test
    public void testRemoveNode() {
        MutableList<String> waldoB = new MutableList<>();
        waldoB.addFirst("s");
        waldoB.addFirst("q");
        waldoB.addFirst("waldo");
        Node<String> n = new Node<String>("n");
        myStringList.removeNode(n);
        //assertEquals(myStringList, waldoB);

    }


    /**
     * Checks if the two lists of integers are equal
     *
     * @param l1 - an Integer IList
     * @param l2 - an Integer IList
     * @return true if l1 and l2 have the same items in the same locations.
     */

    public boolean compareTwoLists(IList<Integer> l1, IList<Integer> l2) {
        if (l1.size() != l2.size()) {
            return false;
        } else {
            for (int i = 0; i < l1.size(); i++) {
                if (!(l1.get(i).equals(l2.get(i)))) {
                    return false;
                }
            }
            return true;
        }
    }

}