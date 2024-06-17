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

    List<Carta> list1 = new ArrayList<>(Arrays.asList(new CartaNumerica(1, "Red"), new CartaNumerica(3, "Blue"), new CartaSalto("Green"), new CartaMasDos("Red"), new CartaReversa("Red")));
    List<Carta> list2 = new ArrayList<>(Arrays.asList(new CartaNumerica(3, "Red"), new CartaNumerica(4, "Blue"), new CartaNumerica(3, "Green"), new CartaCambioColor(""), new CartaMasDos("Blue")));
    List<Carta> list3 = new ArrayList<>(Arrays.asList(new CartaNumerica(9, "Green"), new CartaNumerica(8, "Blue"), new CartaNumerica(7, "Red"), new CartaCambioColor(""), new CartaMasDos("Yellow"), new CartaReversa("Blue")));



    LinkedList<SimpleEntry<String, List<Carta>>> listaConCartas = new LinkedList<>(
            Arrays.asList(
                    new SimpleEntry<>("A", list1),
                    new SimpleEntry<>("B", list2)
            )
    );

    List<Carta> deck1 = new ArrayList<>(Arrays.asList(new CartaNumerica(7, "Yellow"), new CartaNumerica(8, "Blue"), new CartaNumerica(5, "Green"), new CartaNumerica(9, "Red"), new CartaNumerica(0, "Yellow"), new CartaNumerica(3, "Blue"), new CartaNumerica(4, "Green"), new CartaNumerica(2, "Red"), new CartaNumerica(1, "Yellow"), new CartaNumerica(6, "Blue")));

    LinkedList<SimpleEntry<String, List<Carta>>> cartas3Jugadores = new LinkedList<>(
            Arrays.asList(
                    new SimpleEntry<>("A", list1),
                    new SimpleEntry<>("B", list2),
                    new SimpleEntry<>("C", list3)
            )
    );

    List<Carta> listUNO1 = new ArrayList<>(Arrays.asList(new CartaNumerica(1, "Red"), new CartaNumerica(3, "Blue")));
    List<Carta> listUNO2 = new ArrayList<>(Arrays.asList(new CartaNumerica(3, "Red"), new CartaNumerica(4, "Blue")));

    LinkedList<SimpleEntry<String, List<Carta>>> listUnoCase = new LinkedList<>(
            Arrays.asList(
                    new SimpleEntry<>("A", listUNO1),
                    new SimpleEntry<>("B", listUNO2)
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

    @Test void playerDoesntExist(){
        Partida partida = new Partida(listaConCartas, emptyDeck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(3, "Blue");
        assertThrowsLike(()-> partida.startGame().setHead(head).playCard(aJugar, "C"), "Player not found.");
    }

    @Test void improperTurnPutting() {
        Partida partida = new Partida(listaConCartas, emptyDeck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(4, "Blue");
        assertThrowsLike(()-> partida.startGame().setHead(head).playCard(aJugar, "B"), "Wrong player turn.");
    }

    @Test void playerDoesNotHaveCard() {
        Partida partida = new Partida(listaConCartas, emptyDeck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(5, "Red");
        assertThrowsLike(()-> partida.startGame().setHead(head).playCard(aJugar, "A"), "Player does not have that card.");
    }


    @Test void playerWrongCardPlaced() {
        Partida partida = new Partida(listaConCartas, emptyDeck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(3, "Blue");
        assertThrowsLike(()-> partida.getState().startGame().setHead(head).playCard(aJugar, "A"), "Invalid card");

    }


    @Test void testPlayerCanPlayCardColor() {
        Partida partida = new Partida(listaConCartas, emptyDeck);

        Carta head = new CartaNumerica(5, "Red");
        partida.startGame().setHead(head);

        Carta cardToPlay = new CartaNumerica(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A");

        assertEquals(partida.head.getValor(),1);

        assertFalse(partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals("A"))
                .findFirst()
                .get()
                .getValue()
                .contains(cardToPlay), "Player A was not able to play the card.");
    }

    @Test void testPlayerCanPlayCardNumero() {
        Partida partida = new Partida(listaConCartas, emptyDeck);

        Carta head = new CartaNumerica(1, "Green");
        partida.startGame().setHead(head);

        Carta cardToPlay = new CartaNumerica(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A");

        assertEquals(partida.head.getColor(),"Red");

        assertFalse(partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals("A"))
                .findFirst()
                .get()
                .getValue()
                .contains(cardToPlay), "Player A was not able to play the card.");
    }

    @Test void testNumericCorrectTurnSwap() {
        Partida partida = new Partida(listaConCartas, emptyDeck);

        Carta head = new CartaNumerica(1, "Green");
        partida.startGame().setHead(head);

        Carta cardToPlay = new CartaNumerica(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A");

        assertEquals("B", partida.getCurrentPlayerName());
        partida.playCard(new CartaNumerica(3, "Red"), "B");
        assertEquals("A", partida.getCurrentPlayerName());
    }

    @Test void swapTurn4SkipCard() {
        Partida partida = new Partida(listaConCartas, emptyDeck);
        Carta head = new CartaNumerica(5, "Green");
        Carta aJugar = new CartaSalto("Green");

        partida.startGame().setHead(head).playCard(aJugar, "A");

        assertEquals(partida.getCurrentPlayerName(), "A");
        assertEquals(partida.head.getAccion(), "Skip");
    }


    @Test void swapTurn4ColorChange() {
        Partida partida = new Partida(listaConCartas, emptyDeck);
        Carta head = new CartaNumerica(5, "Red");
        Carta aJugar = new CartaNumerica(1, "Red");
        partida.startGame().setHead(head).playCard(aJugar, "A");
        Carta head2 = new CartaCambioColor("");

        partida.playCard(head2, "B", "Blue");
        assertEquals(partida.head.getColor(), "Blue");
    }

    @Test void drawCard(){
        Partida partida = new Partida(listaConCartas, deck1);
        Carta head = new CartaNumerica(5, "Red");
        Carta drawn = new CartaNumerica(7, "Yellow");
        partida.startGame().setHead(head).drawCard("A");

        // Get the desired player's hand
        Carta cardToCompare = checkContentionOnly(partida, drawn, "A");

        assertEquals(cardToCompare.getValor(),7);
        assertEquals(cardToCompare.getColor(),"Yellow");
    }


    @Test void testPlusTwoCard() {
        Partida partida = new Partida(listaConCartas, deck1);
        Carta head = new CartaNumerica(6, "Red");
        Carta plusTwoCard = new CartaMasDos("Red");
        Carta jugarB = new CartaNumerica(3, "Red");

        // Start the game and set the head
        partida.startGame().setHead(head);

        // Player A plays the "Plus 2" card
        partida.playCard(plusTwoCard, "A");

        Carta drawn1 = deck1.get(0);
        Carta drawn2 = deck1.get(1);

        partida.playCard(jugarB, "B");

        Carta cardToCompare1 = checkContentionOnly(partida, drawn1, "B");
        Carta cardToCompare2 = checkContentionOnly(partida, drawn2, "B");

        assertEquals(cardToCompare1.getValor(), 7);
        assertEquals(cardToCompare1.getColor(), "Yellow");

        assertEquals(cardToCompare2.getValor(), 8);
        assertEquals(cardToCompare2.getColor(), "Blue");

    }


    @Test void testPlusTwoCardContinued() {
        Partida partida = new Partida(listaConCartas, deck1);
        Carta head = new CartaNumerica(6, "Red");
        Carta plusTwoCard = new CartaMasDos("Red");
        Carta jugarB = new CartaMasDos("Blue");
        Carta jugarA = new CartaNumerica(3, "Blue");

        // Start the game and set the head
        partida.startGame().setHead(head);

        // Player A plays the "Plus 2" card
        partida.playCard(plusTwoCard, "A");

        Carta drawn1 = deck1.get(0);
        Carta drawn4 = deck1.get(3);

        partida.playCard(jugarB, "B");

        partida.playCard(jugarA, "A");

        Carta cardToCompare1 = checkContentionOnly(partida, drawn1, "A");
        Carta cardToCompare2 = checkContentionOnly(partida, drawn4, "A");

        assertEquals(cardToCompare1.getValor(), 7);
        assertEquals(cardToCompare1.getColor(), "Yellow");

        assertEquals(cardToCompare2.getValor(), 9);
        assertEquals(cardToCompare2.getColor(), "Red");

    }


    @Test void testReverseCard() {
        Partida partida = new Partida(cartas3Jugadores, deck1);
        Carta head = new CartaNumerica(6, "Red");
        Carta reverseCard = new CartaReversa("Red");
        Carta jugarC = new CartaNumerica(7, "Red");

        partida.startGame().setHead(head);

        partida.playCard(reverseCard, "A");

        partida.playCard(jugarC, "C");
        assertEquals(partida.head.getValor(), 7);

    }

    @Test void testDoubleReverseCard() {
        Partida partida = new Partida(cartas3Jugadores, deck1);
        Carta head = new CartaNumerica(6, "Red");
        Carta reverseCardA = new CartaReversa("Red");
        Carta reverseCardC = new CartaReversa("Blue");
        Carta jugarA = new CartaNumerica(3, "Blue");

        partida.startGame().setHead(head);

        partida.playCard(reverseCardA, "A");
        partida.playCard(reverseCardC, "C");

        partida.playCard(jugarA, "A");
        assertEquals(partida.head.getValor(), 3);

    }

    @Test void getUNOState() {
        Partida partida = new Partida(listUnoCase, emptyDeck);

        Carta head = new CartaNumerica(1, "Green");
        partida.startGame().setHead(head);

        Carta cardToPlay = new CartaNumerica(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A");

        List<Carta> playerHandA = partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals("A"))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        partida.playCard(new CartaNumerica(3, "Red"), "B");

        List<Carta> playerHandB = partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals("B"))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));


        assertTrue(playerHandA.get(0).unoState);
        assertTrue(playerHandB.get(0).unoState);


    }

    @Test void returnUNOIfDraw() {
        Partida partida = new Partida(listUnoCase, deck1); // Podria hacer un test de deck...

        Carta head = new CartaNumerica(1, "Green");
        partida.startGame().setHead(head);

        Carta cardToPlay = new CartaNumerica(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A");

        partida.playCard(new CartaNumerica(3, "Red"), "B");

        partida.drawCard("A");

        List<Carta> playerHandA = partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals("A"))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        assertFalse(playerHandA.get(0).unoState);

    }

    @Test void shoutUNOToMe() {
        Partida partida = new Partida(listUnoCase, deck1); // Podria hacer un test de deck...

        Carta head = new CartaNumerica(1, "Green");
        partida.startGame().setHead(head);

        Carta cardToPlay = new CartaNumerica(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A");

        partida.personalUNO("A");

        List<Carta> playerHandA = partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals("A"))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        assertFalse(playerHandA.get(0).unoState);
    }

    @Test void shoutUNOToOthers() {
        Partida partida = new Partida(listUnoCase, deck1); // Podria hacer un test de deck...

        Carta head = new CartaNumerica(1, "Green");
        partida.startGame().setHead(head);

        Carta cardToPlay = new CartaNumerica(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A");

        partida.shoutUNOTo("A");

        int actualCardCount = partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals("A"))
                .findFirst()
                .map(entry -> entry.getValue().size())
                .orElse(0);

        assertEquals(3, actualCardCount);

    }

    @Test void multipleUNOOperations() {
        Partida partida = new Partida(listUnoCase, deck1); // Podria hacer un test de deck...

        Carta head = new CartaNumerica(1, "Green");
        partida.startGame().setHead(head);

        Carta cardToPlay = new CartaNumerica(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A");

        partida.personalUNO("A").shoutUNOTo("B").shoutUNOTo("A");

        partida.playCard(new CartaNumerica(3, "Red"), "B");
        partida.shoutUNOTo("B");

        int actualCardCount = partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals("B"))
                .findFirst()
                .map(entry -> entry.getValue().size())
                .orElse(0);

        assertEquals(3, actualCardCount);
    }

    @Test void finishGame() {
        Partida partida = new Partida(listUnoCase, deck1); // Podria hacer un test de deck...

        Carta head = new CartaNumerica(1, "Green");
        partida.startGame().setHead(head);

        Carta cardToPlay = new CartaNumerica(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A").playCard(new CartaNumerica(3, "Red"), "B");

        assertThrowsLike(()->partida.playCard(new CartaNumerica(3,"Blue"), "A").drawCard("B"), "Game is over.");
    }




    private static Carta checkContentionOnly(Partida partida, Carta drawn, String desiredPlayer) {
        List<Carta> playerHand = partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));

        Carta cardToCompare = playerHand.stream()
                .filter(playerCard -> playerCard.getColor().equals(drawn.getColor()) && playerCard.getValor() == drawn.getValor())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Player does not have that card."));
        return cardToCompare;
    }

        private void assertThrowsLike(Executable runnable, String errorMessage){
        assertEquals( assertThrows( Exception.class, runnable) .getMessage(), errorMessage);
    }


}
