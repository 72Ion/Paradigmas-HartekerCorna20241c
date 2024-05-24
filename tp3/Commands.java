package dron;

import java.util.List;

public abstract class Commands {

    static public Commands findCommand(char command) {
        return List.of(new checkTurnLeft(), new checkTurnRight(), new higherSpeed(), new decreaseSpeedCheck(), new checkDeployement(), new retractProbe()).stream().filter((any) -> any.canHandle(command)).findAny().orElseThrow(() -> new RuntimeException(Dron.InvalidCommand));
    }

    public abstract char key();

    public abstract void execute(Dron dron);

    private boolean canHandle(char command) {
        return key() == command;
    }
}

class checkTurnLeft extends Commands {

    public char key(){
        return 'l';
    }
    public void execute(Dron dron) {
        dron.checkTurnLeft();
    }
}

class checkTurnRight extends Commands {

    public char key(){
        return 'r';
    }
    public void execute(Dron dron) {
        dron.checkTurnRight();
    }
}

class higherSpeed extends Commands {

    public char key(){
        return 'i';
    }
    public void execute(Dron dron) {
        dron.higherSpeed();
    }
}

class decreaseSpeedCheck extends Commands {

    public char key(){
        return 's';
    }
    public void execute(Dron dron) {
        dron.decreaseSpeedCheck();
    }
}

class checkDeployement extends Commands {

    public char key(){
        return 'd';
    }
    public void execute(Dron dron) {
        dron.checkDeployement();
    }
}

class retractProbe extends Commands {

    public char key(){
        return 'f';
    }
    public void execute(Dron dron) {
        dron.retractProbe();
    }
}