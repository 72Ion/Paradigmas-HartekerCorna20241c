package dron;

class TurnLeft extends Commands {

    public char key(){
        return 'l';
    }
    public void execute(Dron dron) {
        dron.checkTurnLeft();
    }
}
