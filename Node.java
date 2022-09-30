package sol;

/**
 * A class that implements doubly linked list nodes.
 */

// Task 5-C

/*

 */
public class Node<S> {
    private S item;
    private Node<S> next;

    //Task 6-A
    private Node<S> prev;

    /**
     * A constructor for Node.
     *
     * @param item - the item stored at this node
     */
    public Node(S item) {
        this.item = item;
        this.next = null;
        this.prev = null;
    }

    /**
     * A constructor for Node.
     *
     * @param item - the item stored at this node
     * @param next - the node which comes directly after this node in the list
     */
    public Node(S item, Node<S> next) {
        this.item = item;
        this.next = next;
    }

    /**
     * Returns a boolean indicating whether the Node has a given item.
     *
     * @param checkItem - the item to check if the Node has
     * @return true if this Node has checkItem, false otherwise
     */
    public boolean hasItem(S checkItem) {
        return this.item.equals(checkItem);
    }

    /**
     * Returns the Node's item.
     *
     * @return the Node's item
     */
    public S getItem() {
        return this.item;
    }

    /**
     * Returns a boolean indicating if the Node has a Node as its next field.
     *
     * @return true if the Node has a next Node, false otherwise
     */
    private boolean hasNext() {
        return (this.next != null);
    }

    /**
     * Returns the size of the list starting at this Node until the end of the list.
     *
     * @return the size of the list starting at this Node until the end of the list.
     */
    public int size() {
        if (this.hasNext())
            return 1 + this.next.size();
        else
            return 1;
    }

    /**
     * Returns the Node's representation as a String.
     *
     * @return the Node's representation as a String
     */

   public String toString() {
        if (this.hasNext())
            return this.item.toString() + ", " + this.next.toString();
        else
            return this.item.toString();
    }

    /**
     * A method for get that takes in an integer i and recurses through a tree
     * to find the number item that matches the index input. This method
     * builds a count based on how many times recursion has occurred and if
     * the count is the same as the item, it returns that item. Otherwise,
     * if the next item is null or index is less than 0, a runtime exception
     * is thrown. If count != the index, it moves on to recursing through
     * next.
     *
     * @param i
     * @return
     */

    public S indexed(Integer i) {
        // empty case
        if (this.item == null || i < 0) {
            throw new RuntimeException("Index out of bounds");
            // successful case - returns this item
        } else if (i == 0) {
            return this.item;
        } else {
            // recurse if not found
            return this.next.indexed(i - 1);
        }
    }

    // Runtime = O(n)


    // Task 6-B

    /**
     *
     * If a node is in between other nodes, links external nodes and drops
     * central node. If prev node does not exist, next.prev is null. If next
     * node DNE, next.prev is null.
     *
     */

    public void bypass() {

        if (this.next == null && this.prev == null) {
            return;
        }

        //at end of list

        else if ((this.prev != null) && (this.next == null)) {
            this.prev.next = null; }

        //beginning of list

        else if ((this.next != null) && (this.prev == null)) {
            this.next.prev = null;
        }

        //bypasses this node

        else {
            this.prev.next = this.next;
            this.next.prev = this.prev;
            }
        }

    /**
     *
     * Recurses through a given node searching for an inout item. If it locates
     * that matching item, it bypasses it and returns true. If item is not
     * found, false is returned. Otherwise, recurses through next.
     *
     * @param findItem
     * @return
     */

    public boolean fullBypass(S findItem) {

        //Item found and is bypassed

        if (this.item.equals(findItem)) {
            this.bypass();
            return true;

            //Item is not found and there is no list left

        } else if (!this.item.equals(findItem) && this.next == null) {
            return false;

            //recursion continues

        } else {
            return this.next.fullBypass(findItem);
        }
    }

    /**
     *
     * returns prev
     *
     * @return
     */

    public Node<S> getPrev() {
        return this.prev;
    }

    /**
     *
     * returns next
     *
     * @return
     */

    public Node<S> getNext() {
        return this.next;
    }

    /**
     *
     * sets prev as input node
     *
     * @return
     */

    public void setPrev(Node<S> n) {
        this.prev = n;
    }

    /**
     *
     * sets next as input node
     *
     * @return
     */

    public void setNext(Node<S> n) {
        this.next = n;
    }

    /**
     *
     * attempts to return a node based on whether its item matches the input.
     * If the item does not exist, returns null. Otherwise, recurses through
     * rest.
     *
     * @param argument
     * @return
     */

    public Node<S> findNode(S argument) {

        // returns if items are equal
        if (this.item.equals(argument)) {
            return this;
        }
        // returns null if end reached
        else if (this.next == null) {
            return null;
        } else {
            // recurses if not found
            return this.next.findNode(argument);
        }
    }

}
