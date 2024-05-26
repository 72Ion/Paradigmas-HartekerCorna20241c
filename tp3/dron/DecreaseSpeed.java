package dron;


class DecreaseSpeed extends Commands {

    public char key(){
        return 's';
    }
    public void execute(Dron dron) {
        dron.decreaseSpeedCheck();
    }
}