package uno;
import java.util.*;


public class Partida {

    List<List<Carta>> playerCards;

    List<Carta> deck = new ArrayList<>();

    Carta head;

    int currentPlayer = 0;
    int plus2Counter = 0;

    State state = new EmptyState(this);


    public Partida(List<List<Carta>> playerCards, List<Carta> deck){
        this.playerCards = playerCards;
        this.deck = deck;
    }

    public State getState(){
        return state;
    }

    public int getCurrentPlayer(){
        return currentPlayer;
    }

    public boolean checkMinPlayers(){
        return playerCards.size() >= 2;
    }


    public void comparePlayer(int desiredPlayer) {
        if (!(desiredPlayer == currentPlayer)) {
            throw new RuntimeException("Wrong player turn.");
        }
    }

    public Carta checkCardContention(int desiredPlayer, Carta card){
        return playerCards.get(desiredPlayer).stream()
                .filter(playerCard -> playerCard.getColor().equals(card.getColor()) && playerCard.getValor() == card.getValor())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player does not have that card."));
    }

    public void removePlayerCard(int desiredPlayer, Carta card){
        playerCards.get(desiredPlayer).remove(card);
    }

    public void removeDeckCard(){
        deck.remove(0);
    }

    public Partida changeTurn(String possibleColor) {
        head.executeAction(this, possibleColor);
        return this;
    }

    public Partida checkMultipleDraw(Partida partida, Carta card) {
        return card.comparePlus2(partida);
    }

    public void checkUNOstate(int desiredPlayer) {
        if (playerCards.get(desiredPlayer).size() == 1) {
            playerCards.get(desiredPlayer).get(0).unoState = true;
        }
    }
}

//
//public class playCurrentPlayer(){
//
//
//}
//
