package dron;


class RetractProbe extends Commands {

    public char key(){
        return 'f';
    }
    public void execute(Dron dron) {
        dron.retractProbe();
    }
}