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
        assertEquals('N', newDron().heading());
    }
    @Test
    void increaseSpeed(){
        assertEquals(1,newDron().process('i').speed());
    }

    @Test
    void decreaseNullSpeed(){
        assertThrowsLike("Too Slow", () -> newDron().process('s'));
    }

    @Test void decreaseSpedDrone(){assertEquals(0,newDron().process('i').process('s').speed());}


    @Test public void rotateDroneRightEastDirection() {
        assertEquals('E', newDron().process('r').heading());
    }

    @Test public void rotateDroneRightSouthDirection() {
        assertEquals('S', newDron().process('r').process('r').heading());
    }

    @Test public void rotateDroneRightWestDirection() {
        assertEquals('W', newDron().process('r').process('r').process('r').heading());
    }

    @Test public void rotateDroneRightNorthDirection() {
        assertEquals('N', newDron().process('r').process('r').process('r').process('r').heading());
    }


    @Test public void rotateDroneLeftWestDirection() {
        assertEquals('W', newDron().process('l').heading());
    }

    @Test public void rotateDroneLeftSouthDirection() {
        assertEquals('S', newDron().process('l').process('l').heading());
    }

    @Test public void rotateDroneLeftEastDirection() {
        assertEquals('E', newDron().process('l').process('l').process('l').heading());
    }

    @Test public void rotateDroneLeftNorthDirection() {
        assertEquals('N', newDron().process('l').process('l').process('l').process('l').heading());
    }

    @Test void checkProbe(){
        assertEquals("recovered", newDron().probe());
    }

    @Test void deployDroneProbe(){
        assertEquals("deployed", newDron().process('i').process('d').probe());
    }

    @Test void recoverDroneProbe(){
        assertEquals("recovered", newDron().process('i').process('d').process('f').probe());
    }

    @Test void destroyDroneProbe(){ // IMPORTANTE Hacer otro test casi igual pero con un 3er input distinto.
        assertThrowsLike("Probe Destroyed", () -> newDron().process('i').process('d').process('i'));
    }

    @Test void destroyDroneProbe2(){
        assertThrowsLike("Probe Destroyed", () -> newDron().process('i').process('d').process('r'));
    }


    //

    private Dron newDron() {return new Dron();}

    private static void assertThrowsLike(String message, Executable executable) {
        assertEquals(assertThrows(Exception.class, executable).getMessage(), message);
    }
}
