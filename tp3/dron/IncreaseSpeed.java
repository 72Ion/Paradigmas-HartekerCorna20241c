package dron;


class IncreaseSpeed extends Commands {

    public char key(){
        return 'i';
    }
    public void execute(Dron dron) {
        dron.higherSpeed();
    }
}
