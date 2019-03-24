import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class personNames {
    private final static String[] namesArray = {
            /*A (9)*/ "Ansh", "Aditya", "Alex", "Alice", "Andrew", "Ashley", "Abigail", "Anton", "Andre",
            /*B (2)*/ "Benjamin", "Betty",
            /*C (6)*/ "Charles", "Chris", "Charlotte", "Colin", "Chuck", "Corey",
            /*D (4)*/ "Donald", "Daisy", "Dill", "Dylan",
            /*E (5)*/ "Ethan", "Eric", "Elizabeth", "Edison", "Edward",
            /*F (1)*/ "Franklin",
            /*G (3)*/ "George", "Grace", "Grant",
            /*H (2)*/ "Henry", "Helen",
            /*I (1)*/ "Isabella",
            /*J (8)*/ "John", "Jeffrey", "Joseph", "Jacob", "Julian", "Jessica", "Juliet", "Jennifer",
            /*K (3)*/ "Krishna", "Kelly", "Kyle",
            /*L (3)*/ "Lennie", "Lisa", "Leon",
            /*M (9)*/ "Mukunth", "Mike", "Matthew", "Margot", "Mildred", "Marie", "Martin", "Michelle", "Maxim",
            /*N (2)*/ "Nickolas", "Nancy",
            /*O (1)*/ "Olivia",
            /*P (4)*/ "Patrick", "Peter", "Phillip", "Patricia",
            /*Q (1)*/ "Quincy",
            /*R (5)*/ "Robert", "Ronald", "Romeo", "Ruth", "Ryan",
            /*S (8)*/ "Sam", "Sally", "Sarah", "Serena", "Samantha", "Sophia", "Scout", "Samara",
            /*T (2)*/ "Theo", "Travis",
            /*U (1)*/ "Ulysses",
            /*V (2)*/ "Vivian", "Verona",
            /*W (1)*/ "William",
            /*X (1)*/ "Xavier",
            /*Y (1)*/ "Yolanda",
            /*Z (1)*/ "Zack",
            /*Other (15)*/ "Housekeeping 1", "Housekeeping 2", "Housekeeping 3", "Housekeeping 4", "Housekeeping 5",
            "Housekeeping 6", "Housekeeping 7", "Housekeeping 8", "Housekeeping 9", "Housekeeping 10",
            "Maintenance 1", "Maintenance 2", "Maintenance 3", "Maintenance 4", "Maintenance 5"

    };

    private static ArrayList names = new ArrayList();
    private static Random random = new Random();

    public static String getNames() {
        if (names.size() == 0) {
            names.addAll(Arrays.asList(namesArray));
        }
        int position = random.nextInt(names.size());
        String personName = (String) names.get(position);
        names.remove(position);
        return personName;
    }
}
