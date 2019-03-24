import java.util.ArrayList;
import java.util.Random;

public class floor {

    private Random random = new Random();

    private ArrayList floorPersonArray = new ArrayList();

    private int willSpawnNumber;
    private int willSpawnThreshold = 90;
    private int peopleCounter;
    private int floorNumber;
    private boolean peopleWaiting;

    person p;

    floor(int inputFloorNumber) {
        floorNumber = inputFloorNumber;
        peopleCounter = 0;
        peopleWaiting = false;

        generateWillSpawnNumber();
    }

    public void details() {
        if (floorPersonArray.size() > 0) {
            peopleCounter = floorPersonArray.size();
            peopleWaiting = true;
        }

        if (floorPersonArray.size() == 0) {
            peopleCounter = 0;
            peopleWaiting = false;
        }
    }

    public void generateWillSpawnNumber () {
        willSpawnNumber = random.nextInt(100);
        if (willSpawnNumber >= willSpawnThreshold) {
            p = new person(floorNumber);
            floorPersonArray.add(p);
            System.out.println("Floor " + floorNumber + "- New Person (" + p.getIdNumber() + ")");
            building.people++;
        }
        details();
    }

    public void reassignP() {
        if (floorPersonArray.size() != 0) {
            p = (person) floorPersonArray.get(0);
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public boolean arePeopleWaiting() {
        return peopleWaiting;
    }

    public int getPeopleCounter() {
        return peopleCounter;
    }

    public ArrayList getFloorPersonArray() {
        return floorPersonArray;
    }
}
