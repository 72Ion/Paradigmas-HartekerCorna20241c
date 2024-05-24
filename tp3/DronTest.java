package dron;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DronTest {



    @Test
    void initialSpeed(){
        assertEquals(0,newDron().speed());
    }
    @Test
    void initialDirection(){
        assertEquals("North", newDron().heading());
    }
    @Test
    void increaseSpeed(){
        assertEquals(1,newDron().process('i').speed());
    }
    @Test void decreaseSpeedDrone(){
        assertEquals(0,newDron().process('i').process('s').speed());
    }


    @Test public void rotateDroneRightEastDirection() {
        assertEquals("East", newDron().process('r').heading());
    }

    @Test public void rotateDroneRightSouthDirection() {
        assertEquals("South", newDron().process('r').process('r').heading());
    }

    @Test public void rotateDroneRightWestDirection() {
        assertEquals("West", newDron().process('r').process('r').process('r').heading());
    }

    @Test public void rotateDroneRightNorthDirection() {
        assertEquals("North", newDron().process('r').process('r').process('r').process('r').heading());
    }


    @Test public void rotateDroneLeftWestDirection() {
        assertEquals("West", newDron().process('l').heading());
    }

    @Test public void rotateDroneLeftSouthDirection() {
        assertEquals("South", newDron().process('l').process('l').heading());
    }

    @Test public void rotateDroneLeftEastDirection() {
        assertEquals("East", newDron().process('l').process('l').process('l').heading());
    }

    @Test public void rotateDroneLeftNorthDirection() {
        assertEquals("North", newDron().process('l').process('l').process('l').process('l').heading());
    }

    @Test void checkProbe(){
        assertEquals("Retracted", newDron().probe());
    }



    @Test void deployDroneProbe(){
        assertEquals("Deployed", newDron().process('i').process('d').probe());
    }

    @Test void recoverDroneProbe(){
        assertEquals("Retracted", newDron().process('i').process('d').process('f').probe());
    }

    @Test void destroyDroneProbeRTurn(){
        assertThrowsLike("Can't turn with deployed probe", () -> newDron().process('i').process('d').process('r'));
    }

    @Test void destroyDroneProbeLTurn(){
        assertThrowsLike("Can't turn with deployed probe", () -> newDron().process('i').process('d').process('l'));
    }

    @Test void deployWithSpeedZero(){
        assertThrowsLike("Can't deploy probe with speed 0", () -> newDron().process('d'));
    }

    @Test void destroyDroneProbeDecreaseSpeed(){
        assertThrowsLike("Cant reach Stop with deployed probe", () -> newDron().process('i').process('d').process('s'));
    }


    @Test void deployDroneProbeIncreaseSpeed(){
        assertEquals(2, newDron().process('i').process('d').process('i').speed());
    }

    @Test void deployDroneProbeIncreaseSpeedDecreaseSpeed(){
        assertEquals(1, newDron().process('i').process('d').process('i').process('s').speed());
    }





    private Dron newDron() {return new Dron();}

    private static void assertThrowsLike(String message, Executable executable) {
        assertEquals(assertThrows(Exception.class, executable).getMessage(), message);
    }
}
