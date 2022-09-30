package sol;

import src.IList;

import src.IListWithNode;

/*
Dear grader,

I did my best to use getters and setters, but I removed them for clarity.
Now that my code is working after being broken for so long, I don't want to
replace anything. Apologies.
 */


/**
 * A class that implements singly-linked mutable lists.
 *
 * @param <T> - the type of items in the list
 */
public class MutableList<T> implements IList<T>, IListWithNode<T> {
  private Node<T> start;

  private Node<T> last;
  private int length;

  /**
   * A constructor for Mutable List.
   */
  public MutableList() {
    this.start = null;
    this.last = null;
    this.length = 0;
  }

  /**
   * Returns whether the list is empty.
   *
   * @return true if the list is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return (this.start == null);
  }

  /**
   * Returns the first item in the list.
   *
   * @throws RuntimeException "List is empty" when the list is empty
   * @return the first item in the list
   */

  @Override
  public T getFirst() {
      // empty case
    if (this.isEmpty()) {
      throw new RuntimeException("List is empty");
    }

    // else returns item
    return this.start.getItem();
  }

  /**
   * Adds an item to the start of the list.
   *
   * @param item - the item to be added
   */

  @Override
  public void addFirst(T item) {
      //adds 1 every time
    this.length = this.length + 1;

    Node<T> newNode = new Node<T>(item);

    //empty case
    if (this.start == null) {
        if (this.last == null) { //I wrote an extra if statement for clarity
            this.last = newNode;
        }

      this.start = newNode;
        //other cases
    } else {
        newNode.setNext(this.start);
        this.start.setPrev(newNode);
        this.start = newNode;
    }
  }

   /**
   * Returns the number of elements in the list.
   *
   * @return the number of elements in the list
   */

  // Task 3-B

    /**
     *
     * returns the length of a list built using addFirst and addLast.
     *
     * @return
     */

    @Override
  public int size() {
    return this.length;
  }

  // Worst case runtime O(1) (constant time). It only takes one call

  /**
   * Returns the item at the specified index.
   *
   * @param index - an item index
   * @throws RuntimeException "Index out of bounds" if index is too small or big
   * @return the item at the specified index
   */

  // Task 1-B

  @Override
  public T get(int index) {
      // empty case
      if (this.length == 0) {
          throw new RuntimeException("Index out of bounds");
          //other cases
      } else {
          return (T) this.start.indexed(index);
      }
  }

  // Task 1-E

  /*
  Worst case runtime of indexed is O(n) because runtime of indexed is O(n)
  (Linear)
   */

  /**
   * Removes an item from the list. If the item is not in the list, the list is
   * unchanged. If the item occurs more than once in the list, removes only the
   * first instance.
   *
   * @param item - the item to remove
   *
   * @return whether or not the item was successfully removed from the list
   */

  // Part 5-C

  /**
   *
   * Uses fullBypass to recurse through a list and if a given node is found,
   * bypass that node by linking nodes or null on either side
   *
   * @param item - the item to remove
   *
   * @return
   */

  // Task 6-C
  @Override
  public boolean remove(T item) {
      //empty case
      if (this.length == 0){
          return false;
          //if you can bypass the item, bypass and return true
      } else if (this.start.fullBypass(item)) {
          this.length--;
          return true;
          //otherwise false
      } else {
          return false;
      }
  }

  // Worst case runtime is O(n) - linear time

  /**
   * Adds an item to the end of the list.
   *
   * @param item - the item to be added
   */

  // Task 2-C

  /**
   * I believe that creating a "last" field has a lower O runtime because the
   * field initializes itself with addFirst and addLast, and then you can just
   * get last rather than recursing through the whole list
   *
   * @param item - the item to be added
   */

  // Task 2-B

    /**
     *
     * Increases the length of the list by 1 and uses last to make a new node
     * at the start of the list, linking the old start in the next field.
     *
     * @param item - the item to be added
     */

    @Override
    public void addLast(T item) {
        // increments every time, could be better notation but I prefer clarity
        this.length = this.length + 1;

        Node<T> last = new Node<T>(item, null);

        // empty case (increments first)
        if (this.length == 1) {
            this.start = last;
            this.last = last;

            //all else
        } else {
            last.setPrev(this.last);

            this.last.setNext(last);

            this.last = last;
        }
    }

  /**
   * Returns the first item in the list as a String.
   *
   * @return the first item in the list as a String
   */
  public String toString() {
    if (this.start == null)
      return "[]";
    else
      return "[" + this.start.toString() + "]";
  }

  // Task 7-A

    /**
     *
     * enters a list and uses findNode to search for a node with matching item,
     * subsequently returning the appropriate node.
     *
     * @param item -- an item in the list
     * @return
     */

  public Node<T> getNode(T item) {
      Node<T> n = this.start.findNode(item);
      //empty case
      if (n == null) {
          throw new RuntimeException("Node does not exist");
      }
      //else
      return n;
  }

        // Task 7-B

    /**
     *
     * removes an input node from the list
     *
     * @param node - the node to remove
     */


//    public void removeNode(Node<T> node) {
//        if (this.start == null) {
//            throw new RuntimeException("Empty list");
//        }
//
//        else if (this.start.equals(node)) {
//            this.start.getNext().setPrev(null);
//            this.start = this.start.getNext();
//        }
//
//        else if (this.last.equals(node)) {
//
//    }

    public void removeNode(Node<T> node) {
        //empty case
        if (node == null) {
            throw new RuntimeException("Node not found");
        }

        if (this.start == null) {
            throw new RuntimeException("Node not found");
        }

        // checks for node at start
        else if (this.start == node) {
            this.start = null;
        }

        // or node at last
        else if (node == this.last) {
            this.last = node.getPrev();
            this.last.setNext(null);
        }

        //bypasses, decrements length
        node.bypass();
        this.length--;
    }

//  public void removeNode(Node<T> node) {
//      //empty case
//      if (node == null) {
//          throw new RuntimeException("Node not found");
//      }
//
//      if (this.start == null) {
//          throw new RuntimeException("Node not found");
//      }
//
//      if (this.start.getNext() == null) {
//          this.start = null;
//          this.last = null;
//      }
//
//      if (this.start == null) {
//          this.last = null;
//      }
//
//      // checks for node at start
//      if (this.start == node) {
//          this.start = this.start.getNext();
//          this.start.setPrev(null);
//      }
//
//      // or node at last
//      if (node == this.last) {
//          this.last = this.last.getPrev();
//          this.last.setNext(null);
//      }
//
//      //bypasses, decrements length
//      node.bypass();
//      this.length--;
//    }

    // Task 7-C

    /**
     *
     * Removes a node from a list based on an input parameter item. If a
     * matching node exists, the node is removed and true is returned.
     * Otherwise, returns false.
     *
     * @param item -- an item in the list to be removed
     * @return
     */

    public boolean removeWithNodes(T item) {

        //empty case - possibly unnecessary
        //attempted to use to weed out implementation errors (unsuccessful)
        if (this.start == null) {
            return false;

            // try catch runtime - returns false (invalidates above statement)

       } else {
            Node<T> ourNode;
            try {
                ourNode = this.getNode(item);
            }
            catch (RuntimeException e) { //Has to be illegal argument?
                return false;
            }

            // successful case
            this.removeNode(ourNode);
            return true;
        }
    }

}
