package uno;
import java.util.Optional;

public abstract class State {

    protected Partida partida;
    protected String message;


    public abstract String getMessage();
    public abstract State startGame();
}

class NoPlayers extends State {
    String message = "Game has not started yet.";

    Partida partida;
    public NoPlayers(Partida partida) {
        this.partida = partida;
    }

    public String getMessage() {
        return message;
    }

    public State startGame(){
        return Optional.ofNullable(partida.checkMinPlayers() ? new NoHead(partida) : null)
                .orElseThrow(() -> new RuntimeException("Not enough players to start the game."));
    }
}

class NoHead extends State {
    String message = "Game is being played.";

    Partida partida;
    public NoHead(Partida partida) {
        this.partida = partida;
    }

    public String getMessage() {
        return message;
    }

    public State startGame(){
        throw new RuntimeException("Game is already being played.");
    }

//    public State setHead(Carta head) {
//        return null;
////        return new CardPutting(partida, head);
//    }
}
