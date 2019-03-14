import be.ac.ua.ansymo.adbc.annotations.requires;

import java.util.Random;

public class Driver {

    @requires("args.length >= 0")
    public static void main (String[] args)
    {
        System.out.println("Hello, World!");

        int test_length = 10;

        Random rand = new Random();

        PriorityQueue<Integer, Integer> pq = new PriorityQueue<>(Integer.class, Integer.class, 42);

        for (int i = 0; i < test_length; i++)
        {
            int key = rand.nextInt(100)+100;
            int value = rand.nextInt();
            System.out.println("Inserting => ("+key+",\t"+key+")");
            pq.insert(key, key);
        }
        System.out.println();
        printHeap(pq);

        System.out.println("\n\nLets remove everything now:\n");
        for (int i = 0; i < test_length; i ++)
        {
            System.out.println("Removing => "+pq.min());
            pq.remove();
            //printHeap(pq);
        }
    }

    private static void printHeap(PriorityQueue pq)
    {
        for(int i=0; i < pq.nbElements; i++){
            System.out.print(pq.heap[i].getValue()+" ");
        }
        System.out.println();
    }
}
