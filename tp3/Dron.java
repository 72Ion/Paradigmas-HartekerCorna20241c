package dron;

import java.util.Objects;

public class Dron {


    private Odometro speed = new VelCero();
    private Brujula heading = new Norte(); // Cambiar para que vaya desde Sonda no brujula
    private Sonda probe = new Retracted();

    public int speed() {
        return speed.getSpeed();
    }
    public String heading() {
        return this.heading.heading();
    }

    public String probe() {
        return probe.getState();
    }


    public Dron turnRight() {

        heading = heading.turnRight();
        return this;
    }
    public Dron turnLeft() {
        heading = heading.turnLeft();
        return this;
    }

    public Dron decreaseSpeed() {
        speed = speed.decreaseSpeed();
        speed.checkSpeed(probe);
        return this;
    }

    public Dron higherSpeed() {
        speed = speed.increaseSpeed();
        return this;
    }



}

