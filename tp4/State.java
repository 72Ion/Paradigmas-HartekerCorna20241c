package uno;
import java.util.Optional;

public abstract class State {

    protected Partida partida;
    protected String message;


    public abstract String getMessage();
    public abstract State startGame();
    public abstract State setHead(Carta head);
    public abstract State playCard(Carta card, String desiredPlayer);
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

    public State setHead(Carta head) {
        throw new RuntimeException("Define Players Before");
    }

    public State playCard(Carta card, String desiredPlayer) {
        throw new RuntimeException("Game has not started yet.");
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

    public State setHead(Carta head) {
        partida.head = head;
        return new readyForPutting(partida);
    }

    public State playCard(Carta card, String desiredPlayer) {
        throw new RuntimeException("Can't play card without head.");
    }
}

class readyForPutting extends State {
    String message = "Game is ready for putting.";

    Partida partida;
    public readyForPutting(Partida partida) {
        this.partida = partida;
    }

    public String getMessage() {
        return message;
    }

    public State startGame(){
        throw new RuntimeException("Game is already being played.");
    }

    public State setHead(Carta head) {
        throw new RuntimeException("Can't randomly set head. Let player play.");
    }

    public State playCard(Carta card, String desiredPlayer) {
       partida.checkTurn(desiredPlayer);
       partida.beginPut(card, desiredPlayer);
       return this;
    }
}
