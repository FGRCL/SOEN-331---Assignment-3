import be.ac.ua.ansymo.adbc.annotations.requires;

import java.util.Random;

public class Driver {

    @requires("args.length >= 0")
    public static void main (String[] args)
    {
        System.out.println("Hello, World!");

        int test_length = 10;

        Random rand = new Random(322);

        PriorityQueue<Integer, Integer> pq = new PriorityQueue<Integer, Integer>(Integer.class, Integer.class);

        for (int i = 0; i < test_length; i++)
        {
            int key = rand.nextInt(100)+100;
            int value = rand.nextInt();
            System.out.println("Inserting => ("+key+",\t"+key+")");
            pq.insert(key, key);
        }
        System.out.println("\nThe min is now: "+pq.min());

        System.out.println("\n\nLets remove everything now:");
        for (int i = 0; i < test_length; i ++)
        {
            System.out.println("Removing => "+pq.min());
            pq.remove();
        }
    }
}
