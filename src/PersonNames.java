import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PersonNames {
    private final static String[] namesArray = {
            /*A*/ "Ansh", "Aditya", "Alex", "Alice", "Andrew", "Ashley", "Abigail", "Anton", "Andre",
            /*B*/ "Benjamin", "Betty", "Bryce", "Boxer",
            /*C*/ "Charles", "Chris", "Charlotte", "Colin", "Chuck", "Corey", "Catherine",
            /*D*/ "Donald", "Daisy", "Dill", "Dylan",
            /*E*/ "Ethan", "Eric", "Elizabeth", "Edison", "Edward",
            /*F*/ "Franklin", "Felipe",
            /*G*/ "George", "Grace", "Grant", "Gregory",
            /*H*/ "Henry", "Helen",
            /*I*/ "Isabella",
            /*J*/ "John", "Jeffrey", "Joseph", "Jacob", "Julian", "Jessica", "Juliet", "Jennifer",
            /*K*/ "Krishna", "Kelly", "Kyle",
            /*L*/ "Lennie", "Lisa", "Leon", "Lily",
            /*M*/ "Mukunth", "Mike", "Matthew", "Margot", "Mildred", "Marie", "Martin", "Michelle", "Maxim",
            /*N*/ "Nickolas", "Nancy",
            /*O*/ "Olivia",
            /*P*/ "Patrick", "Peter", "Phillip", "Patricia",
            /*Q*/ "Quincy",
            /*R*/ "Robert", "Ronald", "Romeo", "Ruth", "Ryan",
            /*S*/ "Sam", "Sally", "Sarah", "Serena", "Samantha", "Sophia", "Scout", "Samara",
            /*T*/ "Theo", "Travis", "Thomas",
            /*U*/ "Ulysses",
            /*V*/ "Vivian", "Violet", "Victor",
            /*W*/ "William",
            /*X*/ "Xavier",
            /*Y*/ "Yolanda", "Yoda",
            /*Z*/ "Zack",
            /*Other*/ "Housekeeping", "Housekeeping", "Housekeeping", "Housekeeping", "Housekeeping",
            "Housekeeping", "Housekeeping", "Housekeeping", "Housekeeping", "Housekeeping",
            "Maintenance", "Maintenance", "Maintenance", "Maintenance", "Maintenance"

    };

    private static ArrayList<String> names = new ArrayList<>();

    public static String getRandomName() {
        if (names.size() == 0) {
            names.addAll(Arrays.asList(namesArray));
        }
        int position = new Random().nextInt(names.size());
        String personName = names.get(position);
        names.remove(position);
        return personName;
    }
}
