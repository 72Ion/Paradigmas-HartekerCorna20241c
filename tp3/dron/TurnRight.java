package dron;

class TurnRight extends Commands {

    public char key(){
        return 'r';
    }
    public void execute(Dron dron) {
        dron.checkTurnRight();
    }
}
