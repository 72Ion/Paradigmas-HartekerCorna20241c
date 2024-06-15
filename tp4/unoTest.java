package uno;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class unoTest {
    List<List<Carta>> listaVacia = new ArrayList<>();
    List<Carta> emptyDeck = new ArrayList<>();


    List<Carta> list1 = new ArrayList<>(Arrays.asList(new CartaNumerica(1, "Red"), new CartaNumerica(2, "Blue"), new CartaSalto("Green"), new CartaMasDos("Red")));
    List<Carta> list2 = new ArrayList<>(Arrays.asList(new CartaMasDos("Blue"), new CartaNumerica(4, "Yellow"), new CartaCambioColor(""), new CartaNumerica(6, "Red")));

    List<List<Carta>> listaConCartas = new ArrayList<>(Arrays.asList(list1, list2));

    List<Carta> list3 = new ArrayList<>(Arrays.asList(new CartaNumerica(1, "Red"), new CartaNumerica(2, "Blue")));
    List<Carta> list4 = new ArrayList<>(Arrays.asList(new CartaNumerica(1, "Red"), new CartaNumerica(2, "Blue"), new CartaNumerica(3, "Green")));

    List<List<Carta>> listUnoCase = new ArrayList<>(Arrays.asList(list3, list4));

    List<Carta> deck = new ArrayList<>(Arrays.asList(new CartaNumerica(7, "Yellow"), new CartaNumerica(8, "Blue"), new CartaNumerica(5, "Green"), new CartaNumerica(9, "Red"), new CartaNumerica(0, "Yellow"), new CartaNumerica(3, "Blue"), new CartaNumerica(4, "Green"), new CartaNumerica(2, "Red"), new CartaNumerica(1, "Yellow"), new CartaNumerica(6, "Blue")));

    @Test void getFirstState() {
        Partida partida = new Partida(listaVacia,emptyDeck);
        assertEquals("Game has not started yet.", partida.getState().getMessage());
    }

    @Test void checkPlayers2StartError() {
        Partida partida = new Partida(listaVacia, emptyDeck);
        assertThrowsLike(() -> partida.getState().startGame(), "Not enough players to start the game.");
    }

    @Test void checkPlayers2Start() {
        assertEquals("Game is being played.", new Partida(listaConCartas, emptyDeck).getState().startGame().getState().getMessage());
    }

    @Test void headInsertion(){
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        assertEquals(partida.getState().startGame().getState().setHead(head).head, head);
    }

    @Test void startWhenPutting() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        assertThrowsLike(() -> partida.getState().startGame().getState().setHead(head).getState().startGame(), "Game is already being played.");
    }

    @Test void setHeadWhenPutting(){
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        assertThrowsLike(() -> partida.getState().startGame().getState().setHead(head).getState().setHead(head), "Can't randomly set head. Let player play.");
    }



    @Test void improperTurnPutting() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(2, "Blue");
        assertThrowsLike(()-> partida.getState().startGame().getState().setHead(head).getState().playCard(aJugar, 1), "Wrong player turn.");
    }

    @Test void playerDoesNotHaveCard() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(5, "Red");
        assertThrowsLike(()-> partida.getState().startGame().getState().setHead(head).getState().playCard(aJugar, 0), "Player does not have that card.");
    }

    @Test void playerWrongCardPlaced() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(2, "Blue");
        assertThrowsLike(()-> partida.getState().startGame().getState().setHead(head).getState().playCard(aJugar, 0), "Invalid card");
    }

    @Test void correctPuttingColor(){
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(1, "Red");
        partida = partida.getState().startGame().getState().setHead(head).getState().playCard(aJugar, 0);
        assertEquals(partida.head.getColor(),"Red");
        assertEquals(partida.head.getValor(), 1);
    }

    @Test void correctPuttingValue(){
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(1, "Yellow");
        Carta aJugar = new CartaNumerica(1, "Red");
        partida = partida.getState().startGame().getState().setHead(head).getState().playCard(aJugar, 0);
        assertEquals(partida.head.getColor(),"Red");
        assertEquals(partida.head.getValor(), 1);
    }

    @Test void swapTurn4Numeric(){
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(1, "Red");
        partida = partida.getState().startGame().getState().setHead(head).getState().playCard(aJugar, 0);

        assertEquals(partida.currentPlayer, 1);
    }

    @Test void swapTurn4SkipCard() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Green");
        Carta aJugar = new CartaSalto("Green");

        partida = partida.getState().startGame().getState().setHead(head).getState().playCard(aJugar, 0);

        assertEquals(partida.currentPlayer, 0);
        assertEquals(partida.head.getValor(), -2);
    }

    @Test void swapTurn4ColorChange() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(1, "Red");
        partida = partida.getState().startGame().getState().setHead(head).getState().playCard(aJugar, 0);
        Carta head2 = new CartaCambioColor("");
        partida = partida.getState().playCard(head2, 1, "Blue");
        assertEquals(partida.head.getColor(), "Blue");
    }


    @Test void drawCard(){
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        partida = partida.getState().startGame().getState().setHead(head).getState().drawCard(0);
        assertEquals(partida.playerCards.get(0).size(), 5);
        assertEquals("Yellow", partida.checkCardContention(0, new CartaNumerica(7, "Yellow")).getColor());
    }



    @Test void swapTurn4Plus2NoContinuation() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(6, "Red");
        Carta aJugar = new CartaMasDos("Red");

        partida = partida.getState().startGame().getState().setHead(head).getState().playCard(aJugar, 0).getState().playCard(head, 1);
        assertEquals(partida.playerCards.get(1).size(), 5);

        assertEquals("Blue", partida.checkCardContention(1, new CartaNumerica(8, "Blue")).getColor());
        assertEquals(8, partida.checkCardContention(1, new CartaNumerica(8, "Blue")).getValor());

        assertEquals("Yellow", partida.checkCardContention(1, new CartaNumerica(7, "Yellow")).getColor());
        assertEquals(7, partida.checkCardContention(1, new CartaNumerica(7, "Yellow")).getValor());

        assertEquals(partida.getCurrentPlayer(), 0);
    }

    @Test void swapTurn4Plus2Continuation() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(6, "Red");
        Carta aJugar = new CartaMasDos("Red");
        Carta aJugar1 = new CartaMasDos("Blue");
        Carta aJugar2 = new CartaNumerica(2, "Blue");
        partida = partida.getState().startGame().getState().setHead(head).getState().playCard(aJugar, 0).getState().playCard(aJugar1, 1).getState().playCard(aJugar2, 0);
        assertEquals(partida.playerCards.get(0).size(), 6);
        assertEquals(partida.getCurrentPlayer(), 1);
    }

    @Test void getUnoCardFalse() {
        Partida partida = new Partida(listUnoCase, deck);
        Carta head = new CartaNumerica(6, "Red");
        Carta aJugar = new CartaNumerica(1, "Red");
        assertFalse(partida.playerCards.get(0).get(0).unoState);

    }

    @Test void getUnoCardTrue() {
        Partida partida = new Partida(listUnoCase, deck);
        Carta head = new CartaNumerica(6, "Red");
        Carta aJugar = new CartaNumerica(1, "Red");
        partida = partida.getState().startGame().getState().setHead(head).getState().playCard(aJugar, 0);

        assertEquals(partida.playerCards.get(0).get(0).unoState, true);

    }













    private void assertThrowsLike(Executable runnable, String errorMessage){
        assertEquals( assertThrows( Exception.class, runnable) .getMessage(), errorMessage);
    }

}
