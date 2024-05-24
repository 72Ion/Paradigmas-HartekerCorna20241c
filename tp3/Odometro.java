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
    public VelCero() {
        this.speed = 0;
        this.anterior = null;
    }

    public int getSpeed() {
        return speed;
    }

    public Odometro increaseSpeed() {
        Odometro nuevoOdometro = new VelMayor(this, this.speed + 1);
        return nuevoOdometro;
    }

    public void checkSpeed(Dron dron) {
        throw new RuntimeException("Cant reach Stop with deployed probe");
    }

    public void check4Deploy(Dron dron) {
        throw new RuntimeException("Can't deploy probe with speed 0");
    }

    public Odometro decreaseSpeed() {
        return this;
    }
}

class VelMayor extends Odometro {
    public VelMayor(Odometro anterior, int speed) {
        this.anterior = anterior;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public Odometro increaseSpeed() {
        Odometro nuevoOdometro = new VelMayor(this, this.speed + 1);
        return nuevoOdometro;
    }

    public void checkSpeed(Dron dron) {
        return;
    }

    public Odometro decreaseSpeed() {
        if (anterior == null) {
            throw new RuntimeException("No previous speed to decrease to.");
        }
        return anterior;
    }

    public void check4Deploy(Dron dron) {
        dron.deployProbe();
    }
}





















//
//class VelCero extends Odometro {
//    Odometro anterior = this;
//
//    public int speed = 0;
//    public int getSpeed() {
//        return speed;
//    }
//    public Odometro increaseSpeed() {
//        Odometro nuevoOdometro = new VelMayor();
//        nuevoOdometro.anterior = new VelCero();
//        return nuevoOdometro;
//    }
//
//    public void checkSpeed(Dron dron) {
//        throw new RuntimeException("Can't decrease speed to 0 with deployed probe"); /////////  PROBS
//    }
//
//    public void check4Deploy(Dron dron) {
//        throw new RuntimeException("Can't deploy probe with speed 0"); ////////// Probs
//    }
//
//
//    public Odometro decreaseSpeed() {
//        return this;
//    }
//
//}
//
//class VelMayor extends Odometro {
//    Odometro anterior;
//    public int speed = 1;
//    public int getSpeed() {
//        return speed;
//    }
//
//    public Odometro increaseSpeed() {
//        Odometro nuevoOdometro = new VelMayor();
//        nuevoOdometro.anterior = this;
//        nuevoOdometro.speed= this.speed+1;
//        return nuevoOdometro;
//    }
//
//    public void checkSpeed(Dron dron) {
//        dron.decreaseSpeed();
//    }
//
//    public Odometro decreaseSpeed() {
//        if (anterior == null) {
//            throw new RuntimeException("No previous speed to decrease to.");
//        }
//        return anterior;
//    }
//
//    public void check4Deploy(Dron dron) {
//        dron.deployProbe();
//    }
//
//
//
//}