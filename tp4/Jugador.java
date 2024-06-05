package UnoTp4;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
    //Create this class as a player I want the player to have a name as a string and his/her own deck of 'cards', remember we have a Card class.
    private String name;
    private List<Card> cartas;   // Add a card to the deck


    public Jugador(String name) {
        this.name = name;
        this.cartas = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        cartas.add(card);
    }



    public Card showCurrentCard() {
        return cartas.get(0);
    }

    //En una sola funcion quiero ambas cosas, un return card y un remove card

    public Card removeAndReturnFirstCard(Card mazoCard) {
        Card playerCard = cartas.get(0);
        playerCard.checkInsertion(mazoCard);

        cartas.remove(0);
        return playerCard;
    }






}
