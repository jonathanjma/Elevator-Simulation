import java.util.Random;

/** Represents a person */

public class Person {

    private int origin;
    private int destination;
    private boolean goingUp;

    private final int idNumber;
    private final String name;

    //private int waitTime = 0;
    //private int travelTime = 0;

    /** Creates a person with a specified origin */
    public Person(int origin) {
        this(origin, new Random().nextInt(Building.numberOfFloors));
    }

    /** Creates a person with a specified origin and destination */
    public Person(int origin, int destination) {
        while (origin == destination) {
            destination = new Random().nextInt(Building.numberOfFloors);
        }

        this.origin = origin;
        this.destination = destination;

        goingUp = destination > this.origin;

        idNumber = new Random().nextInt(1000);
        name = PersonNames.getRandomName();
    }

    /** Gets the origin of a person */
    public int getOrigin() {
        return origin;
    }

    /** Gets the destination of a person */
    public int getDestination() {
        return destination;
    }

    /** Gets whether a person is going up */
    public boolean isGoingUp() {
        return goingUp;
    }

    /** Gets the id# of a person */
    public int getIdNumber() {
        return idNumber;
    }

    /** Gets the name of a person */
    public String getName() {
        return name;
    }
}