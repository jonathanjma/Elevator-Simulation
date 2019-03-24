import java.util.Random;

public class person {

    private Random random = new Random();

    private int destination;
    private int origin;

    private int idNumber;
    private String name;

    private boolean success;

    person(int inputOrigin) {
        origin = inputOrigin;
        destination = random.nextInt(building.numberOfFloors);
        while (origin == destination) {
            destination = random.nextInt(building.numberOfFloors);
        }
        idNumber = random.nextInt(1000);

        name = personNames.getNames();

        success = false;
    }

    public int getDestination() {
        return destination;
    }

    public int getOrigin() {
        return origin;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public boolean isSuccessful() {
        return success;
    }

    public void setSuccessful(boolean successful) {
        success = successful;
    }
}
