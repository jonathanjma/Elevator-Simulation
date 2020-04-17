import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElevatorSimulationTest {

    private final String expectedBuilding = "";

    @Test public void basicTest() {
        Building building = new Building(6);
        building.runElevator();

        assertEquals(expectedBuilding, building.toString());
    }
}