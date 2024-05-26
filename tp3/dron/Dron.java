package dron;

public class Dron {

    public static String InvalidCommand = "Invalid command";
    private Odometro rapidez = new VelCero();
    private Brujula direccion = new Norte();
    private Sonda probe = new Retracted();


    public int speed() {
        return rapidez.getSpeed();
    }


    public String heading() {
        return this.direccion.heading();
    }

    public String probe() {
        return probe.getState();
    }


    public Dron turnRight() {
        this.direccion = direccion.turnRight();
        return this;
    }

    public Dron turnLeft() {
        this.direccion = direccion.turnLeft();
        return this;
    }

    public void checkTurnLeft() {
        probe.checkTurnLeft(this);
    }

    public void checkTurnRight() {
        probe.checkTurnRight(this);
    }



    public Dron decreaseSpeed() {
        rapidez = rapidez.decreaseSpeed();

        return this;
    }


    public void decreaseSpeedWhenDeployed() {
        rapidez.checkSpeed(this);
    }

    public Dron decreaseSpeedCheck() {
        probe.checkSpeedProbe(this);
        return this;
    }

    public Dron higherSpeed() {
        rapidez = rapidez.increaseSpeed();
        return this;
    }

    public void deployProbe() {
        probe = probe.deployed();
    }

    public void retractProbe() {
        probe = probe.retracted();
    }


    public void checkDeployement() {
        rapidez.checkForDeploy(this);
    }


    public Dron process(char command) {
        Commands.findCommand(command).execute(this);
        return this;
    }


}
