package dron;

public abstract class Sonda {

    public abstract void checkTurnLeft(Dron dron);
    public abstract void checkTurnRight(Dron dron);

    public abstract void checkSpeedProbe(Dron dron);

    public abstract Sonda deployed();
    public abstract Sonda retracted();
    public abstract String getState();

}
