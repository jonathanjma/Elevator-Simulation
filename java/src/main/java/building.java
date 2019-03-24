public class building {

    public static int numberOfFloors = 10;
    public static int people = 0;

    private floor[] floorArray = new floor[numberOfFloors];
    private elevator[] elevatorArray = new elevator[1];

    public void addFloors() {
        floorArray[0] = new floor(0);
        floorArray[1] = new floor(1);
        floorArray[2] = new floor(2);
        floorArray[3] = new floor(3);
        floorArray[4] = new floor(4);
        floorArray[5] = new floor(5);
        floorArray[6] = new floor(6);
        floorArray[7] = new floor(7);
        floorArray[8] = new floor(8);
        floorArray[9] = new floor(9);
    }

    public void details() {
        System.out.println();
        System.out.println("Building Status");
        System.out.format("%s", "Floor #");
        System.out.format("%25s", "# of People Waiting");
        System.out.format("%23s", "Destination Floor(s)");
        for (int i = 0; i < floorArray.length; i++) {
            System.out.format("%n%d", floorArray[i].getFloorNumber());
            System.out.format("%25d", floorArray[i].getPeopleCounter());
            if (floorArray[i].arePeopleWaiting() && floorArray[i].getFloorPersonArray().size() != 0) {

                for (int k = 0; k < floorArray[i].getFloorPersonArray().size(); k++) {
                    person p = (person) floorArray[i].getFloorPersonArray().get(k);
                    if (floorArray[i].getFloorPersonArray().indexOf(p) == 0) {
                        System.out.format("%17d", p.getDestination());
                    } else {
                        System.out.print(p.getDestination());
                    }
                    if (floorArray[i].getFloorPersonArray().indexOf(p) != floorArray[i].getFloorPersonArray().size() - 1) {
                        System.out.print(", ");
                    }
                }

            } else {
                System.out.format("%17s", "n/a");
            }
        }
        System.out.println();
    }

    public void refreshFloorInfo(boolean willSpawn) {
        for (int i = 0; i < floorArray.length; i++) {
            floorArray[i].details();
            if (willSpawn) {floorArray[i].generateWillSpawnNumber();}
        }
    }

    public void removePersonFromFloor(int floor) {
        floorArray[floor].getFloorPersonArray().remove(floorArray[floor].p);
        floorArray[floor].p = null;
        floorArray[floor].reassignP();
    }

    public void addElevator() {
        elevatorArray[0] = new elevator(0, this);
    }

    public void runElevator() {
        elevatorArray[0].runElevator();
    }

    public int getPersonArraySize(int floor) {
        return floorArray[floor].getFloorPersonArray().size();
    }

    public person getPersonAtFloor(int floor) {
        return floorArray[floor].p;
    }

    public int getIdNumberAtFloor(int floor) {
        return floorArray[floor].p.getIdNumber();
    }

    public boolean arePeopleWaitingAtFloor(int floor) {
        return floorArray[floor].arePeopleWaiting();
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
}
