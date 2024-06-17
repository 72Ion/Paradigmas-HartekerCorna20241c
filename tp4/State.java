package uno;
import java.util.AbstractMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.AbstractMap.SimpleEntry;


public abstract class State {

    protected Partida partida;
    protected String message;


    public abstract String getMessage();
    public abstract State startGame();
    public abstract State setHead(Carta head);
    public abstract Partida playCard(Carta card, String desiredPlayer);
    public abstract void nextTurn();
    public abstract void checkTurn(String desiredPlayer);
    public abstract State swapper();

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

    public Partida playCard(Carta card, String desiredPlayer) {
        throw new RuntimeException("Game has not started yet.");
    }

    public void nextTurn() {
        throw new RuntimeException("Game has not started yet.");
    }

    public void checkTurn(String desiredPlayer) {}

    public State swapper() {
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
        return new PuttingForward(partida);
    }

    public Partida playCard(Carta card, String desiredPlayer) {
        throw new RuntimeException("Can't play card without head.");
    }

    public void nextTurn() {
        throw new RuntimeException("Game has not started yet.");
    }

    public void checkTurn(String desiredPlayer) {
        partida.checkTurnForward(desiredPlayer);
    }

    public State swapper() {
        throw new RuntimeException("Game has not started yet.");
    }

}



class PuttingForward extends State {
    String message = "Game is ready for putting.";

    Partida partida;
    public PuttingForward(Partida partida) {
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

    public Partida playCard(Carta card, String desiredPlayer) {
        partida = partida.beginPut(card, desiredPlayer);
        partida.setUnoState(desiredPlayer);
        partida.checkEnd();
        return partida;
    }

    public void nextTurn() {
        try {
            partida.currentPlayer.next();
        } catch (NoSuchElementException e) {
            partida.currentPlayer = partida.playerCards.listIterator();
        }
    }

    public void checkTurn(String desiredPlayer) {
        partida.checkTurnForward(desiredPlayer);
    }

    public State swapper() {
        return new PuttingReverse(partida);
    }


}


class PuttingReverse extends State {
    String message = "Game is ready for putting.";

    Partida partida;
    public PuttingReverse(Partida partida) {
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

    public Partida playCard(Carta card, String desiredPlayer) {
        partida = partida.beginPut(card, desiredPlayer);
        partida.setUnoState(desiredPlayer);
        partida.checkEnd();
        return partida;
    }

    public void nextTurn() {
        try {
            partida.currentPlayer.previous();
        } catch (NoSuchElementException e) {
            partida.currentPlayer = partida.playerCards.listIterator(partida.playerCards.size());
        }
    }

    public void checkTurn(String desiredPlayer) {
        partida.checkTurnBackward(desiredPlayer);
    }

    public State swapper() {
        return new PuttingForward(partida);
    }

}

class finishedGame extends State {
    String message = "Game is over.";

    Partida partida;
    public finishedGame(Partida partida) {
        this.partida = partida;
    }

    public String getMessage() {
        return message;
    }

    public State startGame(){
        throw new RuntimeException("Game is over.");
    }

    public State setHead(Carta head) {
        throw new RuntimeException("Game is over.");
    }

    public Partida playCard(Carta card, String desiredPlayer) {
        throw new RuntimeException("Game is over.");
    }

    public void nextTurn() {
        throw new RuntimeException("Game is over.");
    }

    public void checkTurn(String desiredPlayer) {
        throw new RuntimeException("Game is over.");
    }

    public State swapper() {
        throw new RuntimeException("Game is over.");
    }

}

