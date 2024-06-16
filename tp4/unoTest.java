package uno;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.util.AbstractMap.SimpleEntry;




public class unoTest {
    LinkedList<SimpleEntry<String, List<Carta>>> listaVacia = new LinkedList<>();

    List<Carta> emptyDeck = new LinkedList<>();

    List<Carta> list1 = new ArrayList<>(Arrays.asList(new CartaNumerica(1, "Red"), new CartaNumerica(3, "Blue")));
    List<Carta> list2 = new ArrayList<>(Arrays.asList(new CartaNumerica(3, "Red"), new CartaNumerica(4, "Blue"), new CartaNumerica(3, "Green")));

    LinkedList<SimpleEntry<String, List<Carta>>> listaConCartas = new LinkedList<>(
            Arrays.asList(
                    new SimpleEntry<>("A", list1),
                    new SimpleEntry<>("B", list2)
            )
    );




    @Test void getFirstState() {
        Partida partida = new Partida(listaVacia,emptyDeck);
        assertEquals("Game has not started yet.", partida.getState().getMessage());
    }

    @Test void checkPlayers2StartError() {
        Partida partida = new Partida(listaVacia, emptyDeck);
        assertThrowsLike(() -> partida.startGame(), "Not enough players to start the game.");
    }

    @Test void checkPlayers2Start() {
        assertEquals("Game is being played.", new Partida(listaConCartas, emptyDeck).startGame().getState().getMessage());
    }

    // Hacer un Test para wrong head insertion: Osea seteo la head sin antes definir los players

    @Test void headInsertion(){
        Partida partida = new Partida(listaConCartas, emptyDeck);
        Carta head = new CartaNumerica(5, "Red");
        assertEquals(partida.startGame().setHead(head).head, head);
    }


    @Test void startWhenPutting() {
        Partida partida = new Partida(listaConCartas, emptyDeck);
        Carta head = new CartaNumerica(5, "Red");
        assertThrowsLike(() -> partida.startGame().setHead(head).startGame(), "Game is already being played.");
    }

    @Test void setHeadWhenPutting(){
        Partida partida = new Partida(listaConCartas, emptyDeck);
        Carta head = new CartaNumerica(5, "Red");
        assertThrowsLike(() -> partida.getState().startGame().setHead(head).setHead(head), "Can't randomly set head. Let player play.");
    }

    @Test void improperTurnPutting() {
        Partida partida = new Partida(listaConCartas, emptyDeck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(2, "Blue");
        assertThrowsLike(()-> partida.startGame().setHead(head).playCard(aJugar, "B"), "Wrong player turn.");
    }

    @Test void playerDoesNotHaveCard() {
        Partida partida = new Partida(listaConCartas, emptyDeck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(5, "Red");
        assertThrowsLike(()-> partida.startGame().setHead(head).playCard(aJugar, "A"), "Player does not have that card.");
    }

    





    private void assertThrowsLike(Executable runnable, String errorMessage){
        assertEquals( assertThrows( Exception.class, runnable) .getMessage(), errorMessage);
    }


}
