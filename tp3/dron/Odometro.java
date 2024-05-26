package dron;

public abstract class Odometro {
    protected int speed;
    protected Odometro anterior;

    public abstract int getSpeed();
    public abstract Odometro increaseSpeed();
    public abstract Odometro decreaseSpeed();

    public abstract void checkSpeed(Dron dron);

    public abstract void checkForDeploy(Dron dron);

}
