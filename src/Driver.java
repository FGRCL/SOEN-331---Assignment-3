import be.ac.ua.ansymo.adbc.annotations.requires;

public class Driver {

    @requires("args.length >= 0")
    public static void main (String[] args)
    {
        System.out.println("Hello, World!");
    }
}
