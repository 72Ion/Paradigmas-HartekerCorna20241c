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



