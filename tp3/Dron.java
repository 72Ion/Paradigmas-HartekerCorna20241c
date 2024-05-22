package dron;

import java.util.Objects;

public class Dron {
    public static String InvalidCommand = "Invalid command";
    public static String RetractedProbe = "Retracted";
    public static String DeployedProbe = "Deployed";
    public static String CantChangeDirection = "Can't change direction with deployed probe";
    private Odometro speed = new VelCero();
    private Brujula heading = new Norte(); // Cambiar para que vaya desde Sonda no brujula
    private Sonda probe = new Retracted();

    public int speed() {
        return speed.getSpeed();
    }

    public char heading() {
        return this.heading.heading();
    }

    public String probe() {
        return probe.getState();
    }

    public Dron deployProbe() {
        probe = probe.deployed();
        return this;
    }

    public Dron retractProbe() {
        probe = probe.retracted();
        return this;
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

    public Dron process(char command) {
        Commands.findCommand(command).execute(this);
        return this;
    }
}

