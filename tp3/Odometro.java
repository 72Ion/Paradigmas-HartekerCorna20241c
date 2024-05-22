package dron;

public abstract class Odometro {
    protected int speed;
    protected Odometro anterior;

    public abstract int getSpeed();
    public abstract Odometro increaseSpeed();
    public abstract Odometro decreaseSpeed();

    public abstract void checkSpeed(Dron dron);

    public abstract void check4Deploy(Dron dron);

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

    public void checkSpeed(Dron dron) {
        throw new RuntimeException("Can't decrease speed with speed 0");
    }

    public void check4Deploy(Dron dron) {
        throw new RuntimeException("Can't decrease speed with speed 0");
    }


    public Odometro decreaseSpeed() {
        return this;
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

    public void checkSpeed(Dron dron) {
        dron.decreaseSpeed();
    }

    public Odometro decreaseSpeed() {
        return anterior;
    }

    public void check4Deploy(Dron dron) {
        dron.deployProbe();
    }



}