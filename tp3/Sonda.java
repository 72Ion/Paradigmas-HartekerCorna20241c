package dron;

public abstract class Sonda {

    public abstract void checkTurnLeft(Dron dron);
    public abstract void checkTurnRight(Dron dron);

    public abstract void checkSpeed(Dron dron);

    public abstract Sonda deployed();
    public abstract Sonda retracted();
    public abstract String getState();

}

class Retracted extends Sonda {

    public void checkTurnLeft(Dron dron) {
        dron.turnLeft();
    }

    public void checkTurnRight(Dron dron) {
        dron.turnRight();
    }

    public Sonda deployed() {
        return new Deployed();
    }
    public Sonda retracted() {
        return this;
    }
    public String getState() {
        return "Retracted";
    }

    public void checkSpeed(Dron dron) {
        dron.decreaseSpeed();
    }


}

class Deployed extends Sonda {

    public void checkTurnLeft(Dron dron) {
        throw new RuntimeException("Can't turn with deployed probe");
    }

    public void checkTurnRight(Dron dron) {
        throw new RuntimeException("Can't turn with deployed probe");
    }


    public Sonda deployed() {
        return this;
    }
    public Sonda retracted() {
        return new Retracted();
    }
    public String getState() {
        return "Deployed";
    }


    public void checkSpeed(Dron dron) {
        dron.decreaseSpeedDeploy();
    }
}
