package uno;
import java.util.*;

public class Partida {

    LinkedList<List<Carta>> playerCards;
    List<Carta> deck;
    Carta head;
    ListIterator<List<Carta>> currentPlayer;
    int plus2Counter = 0;
    State state;

    public Partida(LinkedList<List<Carta>> playerCards, List<Carta> deck){
        this.playerCards = playerCards;
        this.deck = deck;
        this.currentPlayer = playerCards.listIterator();
        this.state = new NoPlayers(this);
    }

    public State getState(){
        return state;
    }

    public void startGame(){
        this.state = this.state.startGame();
    }



    // Other methods...

    public void changeTurn() {
        if (!currentPlayer.hasNext()) {
            currentPlayer = playerCards.listIterator();
        }
        currentPlayer.next();
    }

    public boolean checkMinPlayers() {
        return playerCards.size() >= 2;
    }
}