package dron;

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

    public void checkForDeploy(Dron dron) {
        dron.deployProbe();
    }
}