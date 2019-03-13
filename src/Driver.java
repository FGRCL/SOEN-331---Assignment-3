import be.ac.ua.ansymo.adbc.annotations.requires;

public class Driver {

    @requires("args.length >= 0")
    public static void main (String[] args)
    {
        System.out.println("Hello, World!");
        PriorityQueue<Integer, Integer> pq = new PriorityQueue<Integer, Integer>(Integer.class, Integer.class);
        pq.insert(10, 10);
        pq.min();
        pq.remove();
    }
}
