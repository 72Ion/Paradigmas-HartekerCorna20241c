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

    //Agrego aqui cartas para poder hacer un verdadero contains:

//    List<Carta> list1 = Arrays.asList(new CartaNumerica(1, "Red"), new CartaNumerica(2, "Blue"));
//    List<Carta> list2 = Arrays.asList(new CartaNumerica(3, "Green"), new CartaNumerica(4, "Yellow"));
//    List<List<Carta>> listaConCartas = Arrays.asList(list1, list2);

    List<Carta> list1 = new ArrayList<>(Arrays.asList(new CartaNumerica(1, "Red"), new CartaNumerica(2, "Blue")));
    List<Carta> list2 = new ArrayList<>(Arrays.asList(new CartaNumerica(3, "Green"), new CartaNumerica(4, "Yellow")));

    List<List<Carta>> listaConCartas = new ArrayList<>(Arrays.asList(list1, list2));

    List<Carta> deck = Arrays.asList(new CartaNumerica(1, "Red"), new CartaNumerica(2, "Blue"), new CartaNumerica(3, "Green"), new CartaNumerica(4, "Yellow"));


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
        assertThrowsLike(() -> partida.getState().startGame().getState().setHead(head).getState().playCurrentPlayer().getState().startGame(), "Game is already being played.");
    }

    @Test void setHeadWhenPutting(){
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        assertThrowsLike(() -> partida.getState().startGame().getState().setHead(head).getState().playCurrentPlayer().getState().setHead(head), "Can't randomly set head. Let player play.");
    }

    @Test void playerPutTwice() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        assertThrowsLike(() -> partida.getState().startGame().getState().setHead(head).getState().playCurrentPlayer().getState().playCurrentPlayer(), "Player is already playing.");

    }

    @Test void improperTurnPutting() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(2, "Blue");
        assertThrowsLike(()-> partida.getState().startGame().getState().setHead(head).getState().playCurrentPlayer().getState().playCard(aJugar, 1), "Wrong player turn.");
    }

    @Test void playerDoesNotHaveCard() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(5, "Red");
        assertThrowsLike(()-> partida.getState().startGame().getState().setHead(head).getState().playCurrentPlayer().getState().playCard(aJugar, 0), "Player does not have that card.");
    }

    @Test void playerWrongCardPlaced() {
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(2, "Blue");
        assertThrowsLike(()-> partida.getState().startGame().getState().setHead(head).getState().playCurrentPlayer().getState().playCard(aJugar, 0), "Invalid card");
    }

    @Test void correctPutting(){
        Partida partida = new Partida(listaConCartas, deck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(1, "Red");
        partida = partida.getState().startGame().getState().setHead(head).getState().playCurrentPlayer().getState().playCard(aJugar, 0);
        assertEquals(partida.head.getColor(),"Red");
        assertEquals(partida.head.getValor(), 1);
    }

    @Test void correctTurnSwap(){

    }

    private void assertThrowsLike(Executable runnable, String errorMessage){
        assertEquals( assertThrows( Exception.class, runnable) .getMessage(), errorMessage);
    }

}
