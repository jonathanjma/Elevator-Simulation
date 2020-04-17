import java.util.ArrayList;

/** Represents an elevator in a building, contains people inside elevator and elevator control code */

public class Elevator {

    private int currentFloor;
    private int targetFloor;

    private boolean goingUp;
    //private boolean switchDir;
    //private boolean inIdle;

    private final int elevatorMaxSize = 10;
    boolean firstCycle = true;

    /*// speed- 10 m/s = 32.8 ft/s = 22.4 mph
    // floor height- 3 m = 9.8 ft
    // average up 1 floor time- 37 seconds???
    // https://www.popularmechanics.com/technology/infrastructure/a20986/the-hidden-science-of-elevators/
    private int travelTimePerFloor = 15;
    private int stopTime = 10;*/

    /*
    Simple Elevator Algorithm
    1. As long as there's someone inside or ahead of the elevator who wants to go in the current direction,
       keep heading in that direction.
    2. Once the elevator has exhausted the requests in its current direction,
       switch directions if there's a request in the other direction.
       Otherwise, stop and wait for a call.
    */

    public static int timesFull = 0;
    public static int peopleTransported = 0;
    //private int switches = 0;
    //private int idles = 0;

    private ArrayList<Person> elevatorPersonArray;

    private Building building;
    /** Creates an elevator starting at the provided floor and direction */
    public Elevator(int startingFloor, boolean goingUp, Building building) {
        elevatorPersonArray = new ArrayList<>();

        currentFloor = startingFloor;
        targetFloor = startingFloor;
        this.goingUp = goingUp;
        this.building = building;
    }

    /** Runs the elevator */
    public void runElevator() {

        //int lastStop = currentFloor;
        //boolean secondTimeAtFloor;
        /*if (inIdle) {
            findClosestCall(currentFloor);
        }*/

        if (currentFloor < targetFloor && !firstCycle) {
            currentFloor++;
        } else if (currentFloor > targetFloor) {
            currentFloor--;
        }

        System.out.println("\n---------------------------------------------");

        /*if (lastStop != currentFloor) {
            lastStop = currentFloor;
            secondTimeAtFloor = false;
        } else {
            secondTimeAtFloor = true;
        }*/

        if (currentFloor == targetFloor) {
            System.out.println("Floor " + currentFloor);
        }

        if (currentFloor == 0 || (firstCycle && goingUp)) { //&& !secondTimeAtFloor) {
            goingUp = true;
            System.out.println("Going Up");
        } else if (currentFloor == Building.numberOfFloors - 1) { //&& !secondTimeAtFloor) {
            goingUp = false;
            System.out.println("Going Down");
        }

        if (currentFloor == targetFloor) {
            System.out.println("\nDoors Opening");
            loadUnload(currentFloor);
            System.out.println("Doors Closing");
            System.out.println("\nLeaving Floor " + currentFloor);
        } else {
            System.out.println("Floor " + currentFloor + " (skipped)");
        }

        System.out.println("---------------------------------------------");

        building.spawnPeople();
        findTargetFloor(currentFloor, goingUp);

        System.out.println(toString());
        System.out.println(building);

        firstCycle = false;
        //if (curCycle == numCycles) {System.out.println("\nend- " + switches + ", " + idles);}
        //
    }

    /** Finds next floor elevator will stop at */
    public void findTargetFloor(int curFloor, boolean isGoingUp) {
        /*boolean requestsInElevatorDir = false;
        switchDir = false;
        inIdle = false;*/

        if (isGoingUp) {
            for (int floor = curFloor + 1; floor < Building.numberOfFloors; floor++) {
                if (arePeopleGoingInElevatorDir(floor, true) || doPeopleNeedToGetOff(floor)) {
                    //requestsInElevatorDir = true;
                    targetFloor = floor;
                    break;
                }
            }
        } else {
            for (int floor = curFloor - 1; floor >= 0; floor--) {
                if (arePeopleGoingInElevatorDir(floor, false) || doPeopleNeedToGetOff(floor)) {
                    //requestsInElevatorDir = true;
                    targetFloor = floor;
                    break;
                }
            }
        }

        System.out.println("\n-->Target Floor: " + targetFloor);

        /*
        If there are no people waiting in the elevator's direction, switch directions if there are requests in the
        opposite direction. If there are none, wait at the current floor until there is a call. Then go to the call.
        */

        /*if (!requestsInElevatorDir) {
            System.out.println("\n!rTarget Floor- " + targetFloor);
            if (currentFloor != 0 && currentFloor != Building.numberOfFloors - 1
                    && switchCheck(!goingUp, currentFloor)) {
                switchDir = true;
                goingUp = !goingUp;
                System.out.println("\nNo Requests In Elevator Direction, Switching");
                switches++;
                if (goingUp) {System.out.println("\nGoing Up");}
                else {System.out.println("\nGoing Down");}
            } else {
                inIdle = true;
                targetFloor = currentFloor;
                System.out.println("\nNo Requests, Waiting For Request"); idles++;
            }
        }*/

        /*
        if (!requestsInElevatorDir && currentFloor != 0 && currentFloor != Building.numberOfFloors - 1) {
            System.out.println("\nsp1Target Floor- " + targetFloor);
            if (switchCheck(!goingUp, currentFloor)) {
                switchDir = true;
                goingUp = !goingUp;
                System.out.println("\nNo Requests In Elevator Direction, Switching Directions"); switches++;
                if (goingUp) {System.out.println("\nGoing Up"); direction = "Up";}
                else {System.out.println("\nGoing Down"); direction = "Down";}
            } else {
                targetFloor = currentFloor;
                System.out.print("\nNo Requests, Waiting For Request");
            }
        } else if (!requestsInElevatorDir && (currentFloor == 0 || currentFloor == Building.numberOfFloors - 1)
                && targetFloor == currentFloor) {
            System.out.println("\nsp2Target Floor- " + targetFloor);
            System.out.println("\nNo Requests, Waiting For Request"); idles++;
        }
        */
    }

    /** Loads and unloads people at a floor */
    public void loadUnload (int floor) {

        for (int p = 0; p < elevatorPersonArray.size();) {
            Person person = elevatorPersonArray.get(p);
            //System.out.println(p.getIdNumber() + " - From " + p.getOrigin() + ", To " + p.getDestination());
            if (person.getDestination() == currentFloor && floor == currentFloor) {
                elevatorPersonArray.remove(person);
                System.out.println(person.getIdNumber() + " left the elevator ");
                peopleTransported++;
            } else {
                p++;
            }
        }

        if (elevatorPersonArray.size() == elevatorMaxSize && arePeopleGoingInElevatorDir(floor, goingUp)) {
            System.out.println("Elevator Full - Cannot board anyone at Floor " + floor);
            timesFull++;

        } else if (elevatorPersonArray.size() < elevatorMaxSize && arePeopleGoingInElevatorDir(floor, goingUp)) {

            for (int p = 0; p < building.getPeopleWaiting(floor);) {
                Person person = building.getPerson(floor, p);
                if (elevatorPersonArray.size() < elevatorMaxSize && arePeopleGoingInElevatorDir(floor, goingUp)) {
                    if (goingUp && person.isGoingUp() || !goingUp && !person.isGoingUp()) {

                        elevatorPersonArray.add(person);
                        System.out.println(person.getIdNumber() + " boarded the elevator");
                        building.removePersonFromFloor(floor, person);
                    } else {
                        p++;
                    }
                } else {
                    if (elevatorPersonArray.size() == elevatorMaxSize &&
                            arePeopleGoingInElevatorDir(floor, goingUp)) {
                        System.out.println("Elevator Full- Cannot board everyone at Floor " + floor);
                        timesFull++;
                    }
                    break;
                }
            }
        }
    }

    /** Checks if people in the elevator need to get off at a floor */
    public boolean doPeopleNeedToGetOff(int floor) {
        boolean peopleNeedToGetOff = false;
        for (Person person : elevatorPersonArray) {
            if (person.getDestination() == floor) {
                peopleNeedToGetOff = true;
                break;
            }
        }
        return peopleNeedToGetOff;
    }

    /** Checks if people on a floor want to go the direction as the elevator */
    public boolean arePeopleGoingInElevatorDir(int floor, boolean isGoingUp) {
        boolean peopleGoingInElevatorDir = false;
        if (building.arePeopleWaiting(floor)) {
            for (int p = 0; p < building.getPeopleWaiting(floor); p++) {
                Person person = building.getPerson(floor, p);
                if ((isGoingUp && person.isGoingUp()
                        || isGoingUp && !person.isGoingUp() && floor == Building.numberOfFloors - 1)
                    || (!isGoingUp && !person.isGoingUp()
                        || !isGoingUp && person.isGoingUp() && floor == 0)) {
                    peopleGoingInElevatorDir = true;
                    break;
                }
            }
        }
        return peopleGoingInElevatorDir;
    }

    /*public boolean switchCheck(boolean isGoingUp, int floor) { // not fully tested
        boolean requestsInSwitchDir = false;
        if (isGoingUp) {
            for (int f = floor + 1; f < Building.numberOfFloors; f++) {
                if ((building.arePeopleWaiting(f) && arePeopleGoingInElevatorDir(true, f))) {
                    requestsInSwitchDir = true;
                    targetFloor = f;
                    System.out.println("sTarget Floor- " + targetFloor);
                    break;
                }
            }
        } //removing "+ 1" or "- 1" will result in double stop at floor if people are waiting
        else {
            for (int f = floor - 1; f >= 0; f--) {
                if ((building.arePeopleWaiting(f) && arePeopleGoingInElevatorDir(false, f))) {
                    requestsInSwitchDir = true;
                    targetFloor = f;
                    System.out.println("sTarget Floor- " + targetFloor);
                    break;
                }
            }
        }
        return requestsInSwitchDir;
    }*/

    /*public void findClosestCall(int floor) { // not fully tested
        int foundFloorBottom = 0;
        int diffBottom = 0;
        int foundFloorTop = Building.numberOfFloors - 1;
        int diffTop = 0;
        if (currentFloor != Building.numberOfFloors - 1) {
            for (int f = floor; f < Building.numberOfFloors; f++) {
                if ((building.arePeopleWaiting(f))) {
                    foundFloorTop = f;
                    //diffTop = foundFloorTop - currentFloor;
                    diffTop = currentFloor - foundFloorTop;
                    break;
                }
            }
        }
        if (currentFloor != 0) {
            for (int f = floor; f >= 0; f--) {
                if ((building.arePeopleWaiting(f))) {
                    foundFloorBottom = f;
                    //diffBottom = currentFloor - foundFloorBottom;
                    diffBottom = foundFloorBottom - currentFloor;
                    break;
                }
            }
        }
        System.out.println("\n" + foundFloorTop + ", " + diffTop);
        System.out.println(foundFloorBottom + ", " + diffBottom);
        if (diffBottom < diffTop) {
            targetFloor = foundFloorBottom;
            System.out.println("\nfTarget Floor- " + targetFloor);
        }
        else if (diffTop < diffBottom) {
            targetFloor = foundFloorTop;
            System.out.println("\nfTarget Floor- " + targetFloor);
        }
    }*/

    /** Prints the elevator's direction, # of people inside,
     * and name, id, origin, and destination of each person */
    @Override @SuppressWarnings("StringConcatenationInLoop")
    public String toString() {
        String elevatorDetails = "\nElevator Status:" +
                "\nGoing ";

        elevatorDetails += goingUp? "Up":"Down";

        if (elevatorPersonArray.size() == elevatorMaxSize) {
            elevatorDetails += ", Full";
        }

        elevatorDetails += "\nPeople in elevator: " + elevatorPersonArray.size();
        elevatorDetails += "\nPerson Name (ID)   Origin   Destination";

        for (Person person : elevatorPersonArray) {
            elevatorDetails += "\n" + person.getName() + " (" + person.getIdNumber() + ")";

            int length = person.getName().length() + String.valueOf(person.getIdNumber()).length() + 3;
            elevatorDetails += calculateSpacing(length) + person.getOrigin();
            elevatorDetails += person.getOrigin() <= 9? "         ":"        "; //10+ 1 less space
            elevatorDetails += person.getDestination();
        }
        return elevatorDetails;
    }

    /** Makes sure spacing between person name/id and origin is consistent */
    public String calculateSpacing (int nameLength) {
        int spacing = 21 - nameLength;
        String spacingStr = "";
        for (int i = 0; i <= spacing; i++) {
            //noinspection StringConcatenationInLoop
            spacingStr += " ";
        }
        return spacingStr;
    }
}
