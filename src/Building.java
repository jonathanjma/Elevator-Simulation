/** Represents a building, contains floors and elevators */

public class Building {

    public static int numberOfFloors;
    public static int people = 0;

    private Floor[] floorArray;
    private Elevator[] elevatorArray;

    /** Creates a building with specified amount of floors and an elevator */
    public Building(int floors) {
        numberOfFloors = floors;
        floorArray = new Floor[numberOfFloors];
        for (int f = 0; f < numberOfFloors; f++) {
            floorArray[f] = new Floor(f);
        }

        elevatorArray = new Elevator[1];
        elevatorArray[0] = new Elevator(0, true, this);
    }

    /** Starts the elevator */
    public void runElevator() {
        int curCycle = 0;
        int numCycles = 30;
        while (curCycle < numCycles) {
            elevatorArray[0].runElevator();
            curCycle++;
        }
    }

    /** Spawn people at each floor */
    public void spawnPeople() {
        for (Floor floor : floorArray) {
            floor.generateWillSpawnNumber();
        }
    }

    /** Removes a person from a floor */
    public void removePersonFromFloor(int floor, Person p) {
        floorArray[floor].removePerson(p);
    }

    /** Gets a person at the given floor and index */
    public Person getPerson(int floor, int index) {
        return floorArray[floor].getPerson(index);
    }

    /** Gets if people are waiting on a floor */
    public boolean arePeopleWaiting(int floor) {
        return floorArray[floor].arePeopleWaiting();
    }

    /** Gets how many people are waiting on a floor */
    public int getPeopleWaiting(int floor) {
        return floorArray[floor].getPeopleWaiting();
    }

    /** Prints number of people waiting on each floor and their destinations */
    @Override @SuppressWarnings("StringConcatenationInLoop")
    public String toString() {
        String buildingDetails = "\nBuilding Status:" +
                "\nFloor  People Waiting  Destination(s)";

        for (int i = 0; i < floorArray.length; i++) {
            buildingDetails += "\n" + floorArray[i].getFloorNumber();

            buildingDetails += i <= 9? "             ":"            "; //10+ 1 less space
            buildingDetails += floorArray[i].getPeopleWaiting();

            if (floorArray[i].arePeopleWaiting() && floorArray[i].getPeopleWaiting() != 0) {
                for (int p = 0; p < floorArray[i].getPeopleWaiting(); p++) {

                    Person person = floorArray[i].getPerson(p);
                    if (floorArray[i].getPersonIndex(person) == 0) {
                        buildingDetails += floorArray[i].getPeopleWaiting() <= 9?
                                "          ":"         "; //10+ 1 less space
                    }
                    buildingDetails += person.getDestination();

                    if (floorArray[i].getPersonIndex(person) != floorArray[i].getPeopleWaiting() - 1) {
                        buildingDetails += ", ";
                    }
                }

            } else {
                buildingDetails += "          n/a";
            }
        }
        return buildingDetails;
    }
}
