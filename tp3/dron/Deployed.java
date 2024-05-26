package dron;


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


    public void checkSpeedProbe(Dron dron) {
        dron.decreaseSpeed();
        dron.decreaseSpeedWhenDeployed();
    }
}
