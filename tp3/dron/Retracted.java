package dron;


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

    public void checkSpeedProbe(Dron dron) {
        dron.decreaseSpeed();

    }


}
