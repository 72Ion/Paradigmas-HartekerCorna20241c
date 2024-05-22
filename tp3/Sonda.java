package dron;

public abstract class Sonda {

    public abstract void turnRight(Dron dron);
    public abstract void turnLeft(Dron dron);

    public abstract void checkSpeed(Sonda sonda);

    public abstract Sonda deployed();
    public abstract Sonda retracted();
    public abstract String getState();

}

class Retracted extends Sonda {

    public void turnRight(Dron dron) {
        dron.turnRight();
    }

    public void turnLeft(Dron dron) {
        dron.turnLeft();
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

    public void checkSpeed(Sonda sonda) {};


}

class Deployed extends Sonda {

    public void turnRight(Dron dron) {
        throw new RuntimeException("Can't change direction with deployed probe");
    }
    public void turnLeft(Dron dron) {
        throw new RuntimeException("Can't change direction with deployed probe");
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


    public void checkSpeed(Sonda sonda) {
        throw new RuntimeException("Can't be stopped with deployed probe");
    }
}
