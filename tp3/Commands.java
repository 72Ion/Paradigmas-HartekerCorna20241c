package dron;

import java.util.List;

public abstract class Commands {

    static public Commands findCommand(char command) {
        return List.of(new TurnLeft(), new TurnRight(), new IncreaseSpeed(), new DecreaseSpeed(), new DeployProbe(), new RetractProbe()).stream().filter((any) -> any.canHandle(command)).findAny().orElseThrow(() -> new RuntimeException(Dron.InvalidCommand));
    }

    public abstract char key();

    public abstract void execute(Dron dron);

    private boolean canHandle(char command) {
        return key() == command;
    }
}

class TurnLeft extends Commands {

    public char key(){
        return 'l';
    }
    public void execute(Dron dron) {
        dron.turnLeft();
    }
}

class TurnRight extends Commands {

    public char key(){
        return 'r';
    }
    public void execute(Dron dron) {
        dron.turnRight();
    }
}

class IncreaseSpeed extends Commands {

    public char key(){
        return 'i';
    }
    public void execute(Dron dron) {
        dron.higherSpeed();
    }
}

class DecreaseSpeed extends Commands {

    public char key(){
        return 's';
    }
    public void execute(Dron dron) {
        dron.decreaseSpeed();
    }
}

class DeployProbe extends Commands {

    public char key(){
        return 'd';
    }
    public void execute(Dron dron) {
        dron.deployProbe();
    }
}

class RetractProbe extends Commands {

    public char key(){
        return 'f';
    }
    public void execute(Dron dron) {
        dron.retractProbe();
    }
}

