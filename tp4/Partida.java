package uno;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Partida {

    LinkedList<SimpleEntry<String, List<Carta>>> playerCards;
    ListIterator<SimpleEntry<String, List<Carta>>> currentPlayer;

    List<Carta> deck;

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

    public String getCurrentPlayerName() {
        try{
            SimpleEntry<String, List<Carta>> currentEntry = currentPlayer.next();
            currentPlayer.previous(); // Move the iterator back to the original position
            return currentEntry.getKey();
        } catch (NoSuchElementException e) {
            currentPlayer = playerCards.listIterator();
            return getCurrentPlayerName();
        }

    }


    public Partida startGame(){
        this.state = this.state.startGame();
        return this;
    }

    public Partida setHead(Carta head) {
        this.state = this.state.setHead(head);
        return this;
    }

    public Partida playCard(Carta card, String desiredPlayer, String possibleColor) {
        Partida partida = this.state.playCard(card, desiredPlayer);
        partida.head.color = possibleColor;
        return partida;
    }

    public Partida playCard(Carta card, String desiredPlayer) {
        Partida partida = this.state.playCard(card, desiredPlayer);
        return partida;
    }


    public boolean checkMinPlayers() {
        return playerCards.size() >= 2;
    }

    public void checkTurn(String desiredPlayer) {
        this.state.checkTurn(desiredPlayer);
    }

    public void checkTurnForward(String desiredPlayer) {
        SimpleEntry<String, List<Carta>> currentEntry;
        try {
            currentEntry = currentPlayer.next();
        } catch (NoSuchElementException e) {
            // Reset the iterator to the beginning of the playerCards list
            currentPlayer = playerCards.listIterator();
            currentEntry = currentPlayer.next();
        }

        currentPlayer.previous(); // Move the iterator back to the original position

        String currentPlayerName = currentEntry.getKey();

        Optional.of(currentPlayerName)
                .filter(name -> name.equals(desiredPlayer))
                .orElseThrow(() -> new RuntimeException("Wrong player turn."));
    }

    public void checkTurnBackward(String desiredPlayer) {
        SimpleEntry<String, List<Carta>> currentEntry;
        try {
            currentEntry = currentPlayer.previous();
        } catch (NoSuchElementException e) {
            // Reset the iterator to the beginning of the playerCards list
            currentPlayer = playerCards.listIterator(playerCards.size());
            currentEntry = currentPlayer.previous();
        }

        currentPlayer.next(); // Move the iterator back to the original position

        String currentPlayerName = currentEntry.getKey();

        Optional.of(currentPlayerName)
                .filter(name -> name.equals(desiredPlayer))
                .orElseThrow(() -> new RuntimeException("Wrong player turn."));
    }



    public Partida beginPut(Carta card, String desiredPlayer) {
        card = this.checkCardContentionAndPop(card, desiredPlayer);
        this.checkTurn(desiredPlayer);
        this.head = this.head.getComparison(card); // Compara si la carta se puede agregar
        head.comparePlus2(this);
        head.executeAction(this, card);
        return this;
    }

    public Carta checkCardContentionAndPop(Carta card, String desiredPlayer){
        // Get the desired player's hand
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        Carta cardToRemove = playerHand.stream()
                .filter(playerCard -> playerCard.getColor().equals(card.getColor()) && playerCard.getValor() == card.getValor())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player does not have that card."));

        return pretendPop(playerHand, cardToRemove);

    }

    private Carta pretendPop(List<Carta> playerHand, Carta cardToRemove) {
        playerHand.remove(cardToRemove);
        return cardToRemove;
    }


    public void nextTurn() {
        this.state.nextTurn();
    }



    public Partida drawCard(String desiredPlayer) {
        this.checkEnd();
        this.checkTurn(desiredPlayer);
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        playerHand.get(playerHand.size()-1).unoState = false;
        playerHand.add(deck.get(0));
        deck.remove(0);
        return this;
    }


    public void drawCardUNOCase(String desiredPlayer) {
        List<Carta> playerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        playerHand.get(playerHand.size()-1).unoState = false;
        playerHand.add(deck.get(0));
        deck.remove(0);
    }
    public void setUnoState(String desiredPlayer) {
        playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer) && entry.getValue().size() == 1)
                .forEach(entry -> entry.getValue().get(0).unoState = true);
    }

    public Partida personalUNO(String desiredPlayer) {
        this.checkEnd();
        playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .ifPresent(entry -> entry.getValue().get(0).unoState = false);
        return this;
    }

    public Partida shoutUNOTo(String targetPlayer) {
        this.checkEnd();
        // Find the target player in the playerCards list
        List<Carta> targetPlayerHand = playerCards.stream()
                .filter(entry -> entry.getKey().equals(targetPlayer))
                .findFirst()
                .map(AbstractMap.SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        // Check if the unoState of the first card is true
        if (!targetPlayerHand.isEmpty() && targetPlayerHand.get(0).unoState) {
            // If it is, make the player draw two cards
            drawCardUNOCase(targetPlayer);
            drawCardUNOCase(targetPlayer);
        }
        return this;
    }

    public Partida checkEnd() {
        // Use Stream API to check if any player has an empty hand
        playerCards.stream()
                .filter(entry -> entry.getValue().isEmpty())
                .findFirst()
                .map(entry -> {
                    this.state = new finishedGame(this);
                    return this;
                })
                .orElse(this);

        return this;
    }
}