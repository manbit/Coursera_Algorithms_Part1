import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    // these fields are used to store the head and tail of the Deque data structure and help us to add and remove items quickly from the deque
    private Node head;
    private Node tail;

    // this field is used to store the size of the Deque i.e the number of items stored in the Deque data structure
    private int dequeSize;

    // this private class implements the iterator interface and implements all the methods such as hasNext, next and remove
    private class DequeIterator<Item> implements Iterator<Item> {

        // this node holds a reference to the current item which is the head of the linked list representing the deque initially
        Node current = head;

        // checks if the deque has the next element or not
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if(!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            Item item = (Item) current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // this is a private inner class that we use to represent the Deque data structure, we use the doubly linked list to represent the deque data structure
    private class Node {
        Item item;
        Node next;
        Node previous;
    }
    // construct an empty deque and assign the head and tail pointers to null respectively
    public Deque() {
        this.head = null;
        this.tail = null;
        this.dequeSize = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.dequeSize == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.dequeSize;
    }

    // add the item to the front
    public void addFirst(Item item) {

        if(item == null) {
            throw new java.lang.NullPointerException();
        }

        // allocate a new Node to be inserted in the deque
        Node node = new Node();
        node.item = item;
        node.previous = null;

        // check if the deque is empty or not. If it is empty then we just assign both head and tail of the deque data structure to be the new node
        if(this.isEmpty()) {
            node.next = null;
            this.head = node;
            this.tail = node;
        } else {
            Node oldHead = this.head;
            oldHead.previous = node;
            node.next = oldHead;
            this.head = node;
        }

        // increase the count of the number of items stored in the deque by 1 every time this method is called
        ++this.dequeSize;
    }

    // add the item to the end
    public void addLast(Item item) {

        if(item == null) {
            throw new java.lang.NullPointerException();
        }

        // allocate a new Node to be inserted in the deque
        Node node = new Node();
        node.item = item;
        node.next = null;

        // check if the deque is empty or not. If it is empty then we just assign both head and tail of the deque data structure to be the new node
        if(this.isEmpty()) {
            node.previous = null;
            this.head = node;
            this.tail = node;
        } else {
            Node oldTail = this.tail;
            oldTail.next = node;
            node.previous = oldTail;
            this.tail = node;
        }

        // increase the count of the number of items stored in the deque by 1 every time this method is called
        ++this.dequeSize;
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if(this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        // decrease the size of the deque whenever this method is called
        --this.dequeSize;

        Item firstItem = this.head.item;

        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
            return firstItem;
        }

        this.head = this.head.next;
        this.head.previous = null;

        return firstItem;
    }

    // remove and return the item from the end
    public Item removeLast() {

        if(this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        // decrease the size of deque whenever this method is called
        --this.dequeSize;

        Item lastItem = this.tail.item;

        if(this.head == this.tail) {
            this.head = null;
            this.tail = null;
            return lastItem;
        }

        this.tail = this.tail.previous;
        this.tail.next = null;

        return lastItem;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }

    // unit testing
    public static void main(String[] args) {

    }
}
