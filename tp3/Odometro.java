package dron;

public abstract class Odometro {
    protected int speed;
    protected Odometro anterior;

    public abstract int getSpeed();
    public abstract Odometro increaseSpeed();
    public abstract Odometro decreaseSpeed();

    public abstract void checkSpeed(Sonda probe);

}

class VelCero extends Odometro {
    Odometro anterior = null;

    public int speed = 0;
    public int getSpeed() {
        return speed;
    }
    public Odometro increaseSpeed() {
        Odometro nuevoOdometro = new VelMayor();
        nuevoOdometro.anterior = this;
        return nuevoOdometro;
    }
    public Odometro decreaseSpeed() {
        return this;
    }

    public void checkSpeed(Sonda probe) {
        probe.checkSpeed(probe);
    }

}

class VelMayor extends Odometro {
    Odometro anterior = null;
    public int speed = 1;
    public int getSpeed() {
        return speed;
    }

public Odometro increaseSpeed() {
        Odometro nuevoOdometro = new VelMayor();
        nuevoOdometro.anterior = this;
        nuevoOdometro.speed= this.speed+1;
        return nuevoOdometro;
    }

    public Odometro decreaseSpeed() {
        return anterior;
    }

    public void checkSpeed(Sonda probe) {
        return;
    }

}