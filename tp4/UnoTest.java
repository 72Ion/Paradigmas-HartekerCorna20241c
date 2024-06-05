package UnoTp4;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnoTest {

    @Test public void partidaVacia() {
        Partida uno = new Partida();
        assertThrowsLike("At least two players are required to start the game", ()-> uno.checkPlayers());
    }

    @Test public void partidaJugadorUnico() {
        Partida uno = new Partida();
        Jugador playerOne = new Jugador("Alice");
        uno.addPlayer(playerOne);
        assertThrowsLike("At least two players are required to start the game", ()->uno.checkPlayers());
    }

    @Test public void cambioDeTurno() {
        Partida uno = new Partida();
        Jugador playerOne = new Jugador("Alice");
        Jugador playerTwo = new Jugador("Bob");
        uno.addPlayer(playerOne);
        uno.addPlayer(playerTwo);
        uno.checkPlayers();
        assertEquals("Alice", uno.getCurrentPlayer());
    }

    @Test public void cambioDeTurnoDosJugadores() {
            Partida uno = new Partida();
            Jugador playerOne = new Jugador("Alice");
            Jugador playerTwo = new Jugador("Bob");
            uno.addPlayer(playerOne);
            uno.addPlayer(playerTwo);
            uno.checkPlayers();
            uno.nextTurn();
            assertEquals("Bob", uno.getCurrentPlayer());
            uno.nextTurn();
            assertEquals("Alice", uno.getCurrentPlayer());
        }


    @Test public void cartaFinal(){
            Partida uno = new Partida();
            Jugador playerOne = new Jugador("Alice");
            Jugador playerTwo = new Jugador("Bob");
            uno.addPlayer(playerOne);
            uno.addPlayer(playerTwo);
            uno.checkPlayers();

            Card cartaUno = new numericCard("Red", "1");

            uno.setUpperCard(cartaUno); // Esta hardcodeado, deberia ser una restante del mazo
            assertEquals("Red", uno.getUpperCard().getColor());
            assertEquals("1", uno.getUpperCard().getNumber());
    }



    @Test public void cartasParaDosJugadores() {
            Partida uno = new Partida();
            Jugador playerOne = new Jugador("Alice");
            Jugador playerTwo = new Jugador("Bob");
            uno.addPlayer(playerOne);
            uno.addPlayer(playerTwo);
            uno.checkPlayers();

            Card cartaUno = new numericCard("Red", "1");
            Card cartaDos = new numericCard("Blue", "2");

            playerOne.addCard(cartaUno);
            playerTwo.addCard(cartaDos);

            assertEquals("Red", playerOne.showCurrentCard().getColor());
            assertEquals("1", playerOne.showCurrentCard().getNumber());

            assertEquals("Blue", playerTwo.showCurrentCard().getColor());
            assertEquals("2", playerTwo.showCurrentCard().getNumber());

    }

    @Test public void apoyarCartaClonada() {
        Partida uno = new Partida();
        Jugador playerOne = new Jugador("Alice");
        Jugador playerTwo = new Jugador("Bob");
        uno.addPlayer(playerOne);
        uno.addPlayer(playerTwo);
        uno.checkPlayers();

        Card cartaUno = new numericCard("Red", "1");
        uno.setUpperCard(cartaUno);

        Card cartaDos = new numericCard("Red", "1");
        playerOne.addCard(cartaDos);

        uno.partidaReceive(playerOne);

        assertEquals("Red",uno.getUpperCard().getColor());
    }


    @Test public void apoyarAsimetriaNumeroYcolor() {
        Partida uno = new Partida();
        Jugador playerOne = new Jugador("Alice");
        Jugador playerTwo = new Jugador("Bob");
        uno.addPlayer(playerOne);
        uno.addPlayer(playerTwo);
        uno.checkPlayers();

        Card cartaUno = new numericCard("Red", "1");
        uno.setUpperCard(cartaUno);

        Card cartaDos = new numericCard("Blue", "2");
        playerOne.addCard(cartaDos);

        assertThrowsLike("Can't insert mismatched card", () -> uno.partidaReceive(playerOne));

    }

    @Test public void apoyarAsimetriaNumero() {
        Partida uno = new Partida();
        Jugador playerOne = new Jugador("Alice");
        Jugador playerTwo = new Jugador("Bob");
        uno.addPlayer(playerOne);
        uno.addPlayer(playerTwo);
        uno.checkPlayers();

        Card cartaUno = new numericCard("Red", "1");
        uno.setUpperCard(cartaUno);

        Card cartaDos = new numericCard("Red", "2");
        playerOne.addCard(cartaDos);

        uno.partidaReceive(playerOne);

        assertEquals("2",uno.getUpperCard().getNumber());

    }

    @Test public void apoyarAsimetriaColor() {
        Partida uno = new Partida();
        Jugador playerOne = new Jugador("Alice");
        Jugador playerTwo = new Jugador("Bob");
        uno.addPlayer(playerOne);
        uno.addPlayer(playerTwo);
        uno.checkPlayers();

        Card cartaUno = new numericCard("Red", "1");
        uno.setUpperCard(cartaUno);

        Card cartaDos = new numericCard("Blue", "1");
        playerOne.addCard(cartaDos);

        uno.partidaReceive(playerOne);

        assertEquals("Blue",uno.getUpperCard().getColor());

    }
















    private static void assertThrowsLike(String message, Executable executable) {
        assertEquals(assertThrows(Exception.class, executable).getMessage(), message);
    }

}


/*
Hola, abajo se transcribe el enunciado del 4to TP.
La fecha de entrega es Domingo 16 de junio, 23:59 hs
La entrega debe hacerse en el repositorio informado por cada grupo incluyendo los fuentes que implementan el TP y sus correspondientes tests.

Saludos

Emilio

Enunciado
Se nos pide desarrollar la lógica de una partida del juego del Uno.
(https://ruibalgames.com/wp-content/uploads/2017/06/UNO-reglas.pdf)
(https://www.minijuegos.com/juego/4-colors-multiplayer-monument-edition)
Las cartas a considerar en este ejercicio son:
- All numbered cards.
- Draw 2 card.
- Reverse card.
- Skip card.
-.Wild card.
La implementación y sus tests deben ser determinísticos.
La partida debe poder iniciar con 2 o más jugadores hasta que alguno se descarte de todas su cartas siguiendo las reglas del juego.
Una vez terminado no se puede seguir jugando.

Es requisito cumplir con todos los criterios vistos a lo largo de la cursada.
 */