package uno;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class unoTest {
    LinkedList<List<Carta>> listaVacia = new LinkedList<>();
    List<Carta> emptyDeck = new LinkedList<>();

    @Test void getFirstState() {
        Partida partida = new Partida(listaVacia,emptyDeck);
        assertEquals("Game has not started yet.", partida.getState().getMessage());
    }

    @Test void checkPlayers2StartError() {
        Partida partida = new Partida(listaVacia, emptyDeck);
        assertThrowsLike(() -> partida.getState().startGame(), "Not enough players to start the game.");
    }


    private void assertThrowsLike(Executable runnable, String errorMessage){
        assertEquals( assertThrows( Exception.class, runnable) .getMessage(), errorMessage);
    }


}
