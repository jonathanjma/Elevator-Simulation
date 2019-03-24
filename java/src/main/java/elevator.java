import java.util.ArrayList;

public class elevator {

    private int currentFloor;

    private boolean goingUp = false;
    private int elevatorMaxSize = 5;
    private int cycles = 1;
    private int travelTimePerFloor = 10;
    private int stopTime = 10;

    public static int timesFull = 0;

    private ArrayList elevatorPersonArray = new ArrayList();

    private person p;

    private building myBuilding;
    elevator(int startingFloor, building building) {
        currentFloor = startingFloor;
        myBuilding = building;
    }

    public void loadUnload (int floor) {
        System.out.println();
        System.out.println("Elevator At Floor " + currentFloor + " ---------------------------------------");
        System.out.println();

        for (int person = elevatorPersonArray.size() - 1; person >= 0; person--) {
            p = (person) elevatorPersonArray.get(person);
            System.out.println(/*"index " + (person + 1) + " out of " + elevatorPersonArray.size() + */
                    "id" + p.getIdNumber() + " ori" + p.getOrigin() + " dest" + p.getDestination());
            if (p.getDestination() == currentFloor && floor == currentFloor) {
                System.out.println(p.getIdNumber() + " left the elevator ");
                elevatorPersonArray.remove(person);
            }
        }

        if (elevatorPersonArray.size() == elevatorMaxSize) {
            System.out.println();
            System.out.println("Elevator Full - Cannot board anyone at Floor " + floor);
            System.out.println();
            timesFull++;
        }
        if (floor == currentFloor && myBuilding.arePeopleWaitingAtFloor(floor)) {
            if (elevatorPersonArray.size() < elevatorMaxSize) {

                for (int k = 0; k <= myBuilding.getPersonArraySize(floor) && elevatorPersonArray.size() < elevatorMaxSize; k++) {

                    elevatorPersonArray.add(myBuilding.getPersonAtFloor(floor));
                    System.out.println(myBuilding.getIdNumberAtFloor(floor) + " boarded the elevator");
                    myBuilding.removePersonFromFloor(floor);
                    myBuilding.refreshFloorInfo(false);

                    if (elevatorPersonArray.size() == elevatorMaxSize) {
                        System.out.println();
                        System.out.println("Elevator Full- Cannot board everyone at Floor " + floor);
                        System.out.println();
                        timesFull++;
                    }
                }
            }
        }
        System.out.println();
        myBuilding.refreshFloorInfo(true);
        details();
        myBuilding.details();
    }

    public void runElevator() {
        int x = 0;
        while (x < cycles) { //elevator goes up and down once
            if (currentFloor == 0) {
                System.out.println();
                System.out.println("Going Up");
                goingUp = true;
                for (int floor = 0; floor < myBuilding.getNumberOfFloors(); floor++) {
                    loadUnload(floor);
                    currentFloor++;
                }
                currentFloor--;
            }

            if (currentFloor == 9) {
                System.out.println();
                System.out.println("Going Down");
                goingUp = false;
                for (int floor = 9; floor >= 0; floor--) {
                    loadUnload(floor);
                    currentFloor--;
                }
                currentFloor++;
            }
            x++;
        }
    }

    public String calculateSpacing (int nameLength) {
        int spacing = 27 - nameLength;

        String finalSpacing;
        finalSpacing =  Integer.toString(spacing);
        return finalSpacing;
    }

    public void details() {
        System.out.println();
        System.out.print("Elevator Status ");
        if (goingUp) {
            System.out.print("(Up)");
        }
        if (!goingUp) {
            System.out.print("(Down)");
        }
        System.out.println("\nPeople in elevator: " + elevatorPersonArray.size());
        System.out.format("%s","Person Name (ID#)");
        System.out.format("%15s", "Origin Floor");
        System.out.format("%20s", "Destination Floor");
        for (int i = 0; i < elevatorPersonArray.size(); i++) {
            p = (person) elevatorPersonArray.get(i);
            System.out.format("%n%s%d%s", p.getName() + " (",  p.getIdNumber(), ")");

            int length = p.getName().length() + String.valueOf(p.getIdNumber()).length() + 3;

            System.out.format("%" + calculateSpacing(length) + "s", p.getOrigin());
            System.out.format("%20s", p.getDestination());
            if (p.getDestination() > p.getOrigin()) {
                p.setSuccessful(true);
                System.out.format("%10s", "up");
            }
            if (p.getDestination() < p.getOrigin()) {
                p.setSuccessful(true);
                System.out.format("%10s", "down");
            }
        }
        System.out.println();
    }
}
