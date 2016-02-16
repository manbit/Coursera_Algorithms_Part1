import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    // this private Item array is used to represent the randomized queue data structure
    private Item[] randomQueue;

    // this private field is used to store the size of the randomized queue
    private int randomQueueSize;

    // this class helps us to implement the iterator for the RandomizedQueue
    private class RandomizedQueueIterator<Item> implements Iterator<Item> {
        private int[] randomList;
        private int index;

        public RandomizedQueueIterator() {
            index = 0;
            createRandomList();
        }

        private void createRandomList() {
            randomList = new int[randomQueueSize];

            for (int j = 0; j < randomQueueSize; j++) {
                randomList[j] = j;
            }

            int random, temp;
            for (int j = 1; j < randomQueueSize; j++) {
                random = StdRandom.uniform(j);
                temp = randomList[random];
                randomList[random] = randomList[j];
                randomList[j] = temp;
            }
        }

        public boolean hasNext() {
            return index < randomQueueSize;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return (Item) randomQueue[randomList[index++]];
        }
    }

    // construct an empty randomized queue, allocates an array with initial capacity to hold one element
    public RandomizedQueue() {
        this.randomQueue = (Item[])(new Object[1]);
        this.randomQueueSize = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return this.randomQueueSize <= 0;
    }

    // return the number of items on the queue
    public int size() {
        return this.randomQueueSize;
    }

    // this private method helps us to resize the randomized queue in accordance with the principle of doubling or halving the size of the array
    private void resize(int newSize) {
        Item[] resizedRandomizedQueue = (Item[]) new Object[newSize];
        for (int i = 0; i < this.randomQueueSize; i++) {
            resizedRandomizedQueue[i] = this.randomQueue[i];
        }

        this.randomQueue = resizedRandomizedQueue;
    }

    // add the item to the randomized queue
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }

        this.randomQueue[this.randomQueueSize++] = item;

        // if the number of items in the randomized queue is equal to the capacity of the queue we expand the array representing the data structure
        if (this.randomQueueSize == this.randomQueue.length) {
            resize(this.randomQueue.length * 2);
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if(this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        // as in the randomized queue the element to be removed should be chosen at random, we select the index of the item to be deleted at random
        int randomIndex = StdRandom.uniform(this.randomQueueSize);
        Item item = this.randomQueue[randomIndex];

        // fill the void position at the random index with the element in the last position
        this.randomQueue[randomIndex] = this.randomQueue[--this.randomQueueSize];
        this.randomQueue[this.randomQueueSize] = null;

        // resize the array if the number of elements in the array is a quarter of the total capacity
        if(this.randomQueueSize == this.randomQueue.length/4) {
            resize(this.randomQueue.length/2);
        }

        return item;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if(this.isEmpty()) {
            throw new java.util.NoSuchElementException();
        }

        // as we just need to return the element at a random index and not remove it
        int randomIndex = StdRandom.uniform(this.randomQueueSize);
        Item item = this.randomQueue[randomIndex];

        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<Item>();
    }
    /*public static void main(String[] args)   // unit testing */
}
