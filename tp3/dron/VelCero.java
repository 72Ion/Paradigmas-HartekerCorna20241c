package dron;

class VelCero extends Odometro {
    public VelCero() {
        this.speed = 0;
        this.anterior = this;
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

    public void checkForDeploy(Dron dron) {
        throw new RuntimeException("Can't deploy probe with speed 0");
    }

    public Odometro decreaseSpeed() {
        return this;
    }
}

