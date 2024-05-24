package dron;

public class Dron {

    public static String InvalidCommand = "Invalid command";
    private Odometro odometro = new VelCero();
    private Brujula heading = new Norte(); // Cambiar para que vaya desde Sonda no brujula
    private Sonda probe = new Retracted();


    public int speed() {
        return odometro.getSpeed();
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

    public void checkTurnLeft() {
        probe.checkTurnLeft(this);
    }

    public void checkTurnRight() {
        probe.checkTurnRight(this);
    }



    public Dron decreaseSpeed() {
        odometro = odometro.decreaseSpeed();

        return this;
    }


    public void decreaseSpeedDeploy() {
        odometro.checkSpeed(this);
    }

    public Dron decreaseSpeedCheck() {
        probe.checkSpeed(this);
        return this;
    }

    public Dron higherSpeed() {
        odometro = odometro.increaseSpeed();
        return this;
    }

    public void deployProbe() {
        probe = probe.deployed();
    }

    public void retractProbe() {
        probe = probe.retracted();
    }


    public void checkDeployement() {
        odometro.check4Deploy(this);
    }


    public Dron process(char command) {
        Commands.findCommand(command).execute(this);
        return this;
    }


}

// Clase de julio --> 24-05-2024

/*
* Axiom Heading Speed Sonda
* Oficial Speed, Oficial Sonda
* Oficial Speed/ Level Speed
* Speed/ movil e inmovil.
*
* */
