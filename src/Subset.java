import edu.princeton.cs.algs4.StdIn;

public class Subset {
    public static void main(String[] args) {
        int nStrings = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
        String[] strings = StdIn.readAllStrings();

        for (int i = 0; i < strings.length; i++)
            randomQueue.enqueue(strings[i]);

        for (int i = 0; i < nStrings && !randomQueue.isEmpty(); i++)
            System.out.println(randomQueue.dequeue());
    }
}
