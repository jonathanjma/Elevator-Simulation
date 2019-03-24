public class elevatorSimulation {

    public static void main(String[] args) {
        building theBuilding = new building();
        theBuilding.addFloors();
        theBuilding.addElevator();
        theBuilding.details();
        theBuilding.runElevator();

        System.out.println();
        System.out.println("Total People: " + building.people);
        System.out.println("Times Elevator Full: " + elevator.timesFull);

        /*building details- initial start, end of load/unload
         elevator details- end of load/unload
         people spawn- initial start, end of each load/unload
         */
    }
}

/*
purpose- to find out why israeli elevators are so efficient through simulation
         (they require you to input your destination floor before you board)

Currently Working On-

Current Error
problem-
solution-

todo don't go all the way up/down if nobody is waiting
todo don't pick up people going down when going up
todo give floors/building names
todo people spawn rate for each floor- more realistic (ex- more people at lobby going up)
todo more realistic/informative telemetry when not debugging
todo don't delete people when they get off elevator unless they are on ground floor, add them to floorPersonArray
todo support for more than 1 elevator- use event loop
todo slow down elevator (more realistic)- wait and travel time
todo what is the most efficient way of operating elevator so that people have the least wait/travel time?
todo testing - calculate how long for person from spawn arrive at destination floor, automate test- new classes for experiment- building with more floors (10-100): elevator size, write out values into a csv file (
todo signs something is wrong- wait time decrease when floors increase, wait time increases when elevator capacity increases
*/