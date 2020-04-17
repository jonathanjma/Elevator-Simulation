import java.util.ArrayList;
import java.util.Random;

/** Represents a floor in a building, contains people waiting at each floor and people spawning code */

public class Floor {

    private final int floorNumber;
    private ArrayList<Person> floorPersonArray;

    private int willSpawnNumber;
    private final int willSpawnThreshold = 900;

    /** Creates floor with given floor number */
    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        floorPersonArray = new ArrayList<>();

        generateWillSpawnNumber();
    }

    /** Spawns a new person if a random # > spawning threshold */
    public void generateWillSpawnNumber() {
        willSpawnNumber = new Random().nextInt(1000);
        if (willSpawnNumber >= willSpawnThreshold) {
            Person p = new Person(floorNumber);
            floorPersonArray.add(p);
            System.out.println("Floor " + floorNumber + "- New Person (" + p.getIdNumber() + ")");
            Building.people++;
        }
    }

    /** Adds the specified person to this floor */
    public void addPerson(Person p) {
        floorPersonArray.add(p);
        Building.people++;
    }

    /** Gets floor number of floor */
    public int getFloorNumber() {
        return floorNumber;
    }

    /** Gets if people are waiting at this floor */
    public boolean arePeopleWaiting() {
        return floorPersonArray.size() > 0;
    }

    /** Gets how many people are waiting at this floor */
    public int getPeopleWaiting() {
        return floorPersonArray.size();
    }

    /** Removes the given person from the floor */
    public void removePerson(Person p) {
        floorPersonArray.remove(p);
    }

    /** Gets the person on this floor at the given index */
    public Person getPerson(int index) {
        return floorPersonArray.get(index);
    }

    /** Gets index of the given person */
    public int getPersonIndex(Person p) {
        return floorPersonArray.indexOf(p);
    }
}
