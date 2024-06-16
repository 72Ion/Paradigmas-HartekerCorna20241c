package uno;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Partida {

    LinkedList<SimpleEntry<String, List<Carta>>> playerCards;
    List<Carta> deck;
    ListIterator<SimpleEntry<String, List<Carta>>> currentPlayer;

    Carta head;


    int plus2Counter = 0;
    State state;

    public Partida(LinkedList<SimpleEntry<String, List<Carta>>> playerCards, List<Carta> deck){
        this.playerCards = playerCards;
        this.deck = deck;
        this.currentPlayer = playerCards.listIterator();
        this.state = new NoPlayers(this);
    }

    public State getState(){
        return state;
    }

    public Partida startGame(){
        this.state = this.state.startGame();
        return this;
    }

    public Partida setHead(Carta head) {
        this.state = this.state.setHead(head);
        return this;
    }

    public Partida playCard(Carta card, String desiredPlayer) {
        this.state = this.state.playCard(card, desiredPlayer);
        return this;
    }

//    public void changeTurn() {
//        if (!currentPlayer.hasNext()) {
//            currentPlayer = playerCards.listIterator();
//        }
//        currentPlayer.next();
//    }

    public boolean checkMinPlayers() {
        return playerCards.size() >= 2;
    }

//    public void checkTurn(String desiredPlayer) {
//        Optional.of(currentPlayer)
//                .filter(player -> Objects.equals(desiredPlayer, player))
//                .orElseThrow(() -> new RuntimeException("Wrong player turn."));
//    }

    public void checkTurn(String desiredPlayer) {
        SimpleEntry<String, List<Carta>> currentEntry = currentPlayer.next();
        currentPlayer.previous(); // Move the iterator back to the original position

        String currentPlayerName = currentEntry.getKey();

        Optional.of(currentPlayerName)
                .filter(playerName -> Objects.equals(desiredPlayer, playerName))
                .orElseThrow(() -> new RuntimeException("Wrong player turn."));
    }

    public void beginPut(Carta card, String desiredPlayer) {
        card = this.checkCardContention(card, desiredPlayer);
//      this = this.checkMultipleDraw(card);

        // Esperar mas tests.


    }

    public Carta checkCardContention(Carta card, String desiredPlayer){
        // Get the desired player's hand
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        // Check if the card is in the player's hand
        return playerHand.stream()
                .filter(playerCard -> playerCard.getColor().equals(card.getColor()) && playerCard.getValor() == card.getValor())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player does not have that card."));
    }

}