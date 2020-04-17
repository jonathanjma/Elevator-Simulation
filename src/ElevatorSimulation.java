public class ElevatorSimulation {

    public static void main(String[] args) {
        Building building = new Building(11);
        System.out.println(building);
        building.runElevator();

        System.out.println();
        System.out.println("People Spawned: " + Building.people);
        System.out.println("People Transported: " + Elevator.peopleTransported);
        System.out.println("Times People Could Not Board: " + Elevator.timesFull);
    }
}

/*
purpose- to find out why israeli elevators are so efficient through simulation
         (they require you to input your destination floor before you board)

Current Task- smarter elevator
calculate how long for a person from spawn arrive at destination floor, automate test- new class for experiment- building with more floors (10-100): elevator size, write out values into a csv file
signs something is wrong- wait time decrease when floors increase, wait time increases when elevator capacity increases

Issues
issue-

todo give floors/building names
todo people spawn rate for each floor- more realistic (ex- more people at lobby going up)
todo more realistic/informative telemetry when not debugging
todo support for more than 1 elevator- use event loop
todo what is the most efficient way of operating elevator so that people have the least wait/travel time?
*/