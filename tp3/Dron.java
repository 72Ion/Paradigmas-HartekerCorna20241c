package dron;

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
        this.heading = heading.turnRight();
        return this;
    }

    public Dron turnLeft() {
        this.heading = heading.turnLeft();
        return this;
    }

    public void checkTurnLeft(Dron dron) {
        probe.checkTurnLeft(dron);
    }

    public void checkTurnRight(Dron dron) {
        probe.checkTurnRight(dron);
    }



    public void decreaseSpeed() {
        speed = speed.decreaseSpeed();
    }


    public void decreaseSpeedDeploy() {
        speed.checkSpeed(this);
    }

    public Dron decreaseSpeedCheck() {
        probe.checkSpeed(this);
        return this;
    }

    public Dron higherSpeed() {
        speed = speed.increaseSpeed();
        return this;
    }

    public void deployProbe() {
        probe = probe.deployed();
    }

    public void retractProbe() {
        probe = probe.retracted();
    }


    public void checkDeployement() {
        speed.checkSpeed(this);
    }


}

