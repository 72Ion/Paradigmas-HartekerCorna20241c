
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

    List<Carta> list1 = new ArrayList<>(Arrays.asList(getNumericCard(1, "Red"), getNumericCard(3, "Blue"), getSkipCard("Green"), getPlusTwoCard("Red"), getReverseCard("Red")));

    private static CartaReversa getReverseCard(String color) {
        return new CartaReversa(color);
    }

    private static CartaSalto getSkipCard(String color) {
        return new CartaSalto(color);
    }

    List<Carta> list2 = new ArrayList<>(Arrays.asList(getNumericCard(3, "Red"), getNumericCard(4, "Blue"), getNumericCard(3, "Green"), getChangeColorCard(), getPlusTwoCard("Blue")));

    private static CartaCambioColor getChangeColorCard() {
        return new CartaCambioColor("");
    }

    List<Carta> list3 = new ArrayList<>(Arrays.asList(getNumericCard(9, "Green"), getNumericCard(8, "Blue"), getNumericCard(7, "Red"), getChangeColorCard(), getPlusTwoCard("Yellow"), getReverseCard("Blue")));



    LinkedList<SimpleEntry<String, List<Carta>>> listaConCartas = new LinkedList<>(
            Arrays.asList(
                    new SimpleEntry<>("A", list1),
                    new SimpleEntry<>("B", list2)
            )
    );

    List<Carta> deck1 = new ArrayList<>(Arrays.asList(getNumericCard(7, "Yellow"), getNumericCard(8, "Blue"), getNumericCard(5, "Green"), getNumericCard(9, "Red"), getNumericCard(0, "Yellow"), getNumericCard(3, "Blue"), getNumericCard(4, "Green"), getNumericCard(2, "Red"), getNumericCard(1, "Yellow"), getNumericCard(6, "Blue")));

    LinkedList<SimpleEntry<String, List<Carta>>> cartas3Jugadores = new LinkedList<>(
            Arrays.asList(
                    new SimpleEntry<>("A", list1),
                    new SimpleEntry<>("B", list2),
                    new SimpleEntry<>("C", list3)
            )
    );

    List<Carta> listUNO1 = new ArrayList<>(Arrays.asList(getNumericCard(1, "Red"), getNumericCard(3, "Blue")));
    List<Carta> listUNO2 = new ArrayList<>(Arrays.asList(getNumericCard(3, "Red"), getNumericCard(4, "Blue")));

    LinkedList<SimpleEntry<String, List<Carta>>> listUnoCase = new LinkedList<>(
            Arrays.asList(
                    new SimpleEntry<>("A", listUNO1),
                    new SimpleEntry<>("B", listUNO2)
            )
    );


    @Test void getFirstState() {
        Partida partida = PartidaConListaVaciaYDeckVacio();
        assertEquals("Game has not started yet.", getMessageFromPartida(partida));
    }

    @Test void checkPlayers2StartError() {
        Partida partida = new Partida(listaVacia, emptyDeck);
        assertThrowsLike(() -> partida.startGame(), "Not enough players to start the game.");
    }

    @Test void checkPlayers2Start() {
        assertEquals("Game is being played.", getMessageFromPartida(new Partida(listaConCartas, emptyDeck).startGame()));
    }

    @Test void headInsertion(){
        Partida partida = PartidaConListaVaciaYDeckVacio();
        Carta head = getNumericCard(5, "Red");
        assertEquals(partida.startGame().setHead(head).head, head);
    }

    @Test void headInsertionErrorPlayersUndefined(){
        Partida partida = PartidaConListaVaciaYDeckVacio();
        Carta head = getNumericCard(5, "Red");
        assertThrowsLike(() -> partida.setHead(head), "Define Players Before");
    }

    @Test void startWhenPutting() {
        Partida partida = PartidaConListaVaciaYDeckVacio();
        Carta head = getNumericCard(5, "Red");
        assertThrowsLike(() -> partida.startGame().setHead(head).startGame(), "Game is already being played.");
    }

    @Test void setHeadWhenPutting(){
        Partida partida = PartidaConListaVaciaYDeckVacio();
        Carta head = getNumericCard(5, "Red");
        assertThrowsLike(() -> partida.startGame().setHead(head).setHead(head), "Can't randomly set head. Let player play.");
    }

    @Test void playerDoesntExist(){
        assertDifferentGameErrors(5, "Red", 3, "Blue", "C", "Player not found.");
    }

    @Test void improperTurnPutting() {
        assertDifferentGameErrors(5, "Red", 4, "Blue", "B", "Wrong player turn.");
    }

    @Test void playerDoesNotHaveCard() {
        assertDifferentGameErrors(5, "Red", 5, "Red", "A", "Player does not have that card.");
    }

    @Test void playerWrongCardPlaced() {
        assertDifferentGameErrors(5, "Red", 3, "Blue", "A", "Invalid card");
    }

    @Test void testPlayerCanPlayCardColor() {
        initializeGameAndPlayCardWithDifferentHead(5, "Red");
    }

    @Test void testPlayerCanPlayCardNumero() {
        initializeGameAndPlayCardWithDifferentHead(1, "Green");
    }

    @Test void testNumericCorrectTurnSwap() {
        Partida partida = PartidaConListaVaciaYDeckVacio();

        startGameWithGreen1HeadAndPlayRed1Card(partida);

        assertEquals("B", partida.getCurrentPlayerName());
        partida.playCard(getNumericCard(3, "Red"), "B");
        assertEquals("A", partida.getCurrentPlayerName());
    }

    @Test void swapTurn4SkipCard() {
        Partida partida = PartidaConListaVaciaYDeckVacio();
        Carta head = getNumericCard(5, "Green");
        Carta aJugar = getSkipCard("Green");

        partida.startGame().setHead(head).playCard(aJugar, "A");

        assertEquals(partida.getCurrentPlayerName(), "A");
        assertEquals(partida.head.getAccion(), "Skip");
    }


    @Test void swapTurn4ColorChange() {
        Partida partida = PartidaConListaVaciaYDeckVacio();
        Carta head = getNumericCard(5, "Red");
        Carta aJugar = getNumericCard(1, "Red");
        partida.startGame().setHead(head).playCard(aJugar, "A");
        Carta head2 = getChangeColorCard();

        partida.playCard(head2, "B", "Blue");
        assertEquals(partida.head.getColor(), "Blue");
    }

    @Test void drawCard(){
        Partida partida = new Partida(listaConCartas, deck1);
        Carta head = getNumericCard(5, "Red");
        Carta drawn = getNumericCard(7, "Yellow");
        partida.startGame().setHead(head).drawCard("A");

        // Get the desired player's hand
        Carta cardToCompare = checkContentionOnly(partida, drawn, "A");

        assertEquals(cardToCompare.getValor(),7);
        assertEquals(cardToCompare.getColor(),"Yellow");
    }


    @Test void testPlusTwoCard() {
        Partida partida = new Partida(listaConCartas, deck1);
        Carta head = getNumericCard(6, "Red");
        Carta plusTwoCard = getPlusTwoCard("Red");
        Carta jugarB = getNumericCard(3, "Red");

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
        Carta head = getNumericCard(6, "Red");
        Carta plusTwoCard = getPlusTwoCard("Red");
        Carta jugarB = getPlusTwoCard("Blue");
        Carta jugarA = getNumericCard(3, "Blue");

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
        Carta head = getNumericCard(6, "Red");
        Carta reverseCard = getReverseCard("Red");
        Carta jugarC = getNumericCard(7, "Red");

        partida.startGame().setHead(head);

        partida.playCard(reverseCard, "A");

        partida.playCard(jugarC, "C");
        assertEquals(partida.head.getValor(), 7);

    }

    @Test void testDoubleReverseCard() {
        Partida partida = new Partida(cartas3Jugadores, deck1);
        Carta head = getNumericCard(6, "Red");
        Carta reverseCardA = getReverseCard("Red");
        Carta reverseCardC = getReverseCard("Blue");
        Carta jugarA = getNumericCard(3, "Blue");

        partida.startGame().setHead(head);

        partida.playCard(reverseCardA, "A");
        partida.playCard(reverseCardC, "C");

        partida.playCard(jugarA, "A");
        assertEquals(partida.head.getValor(), 3);

    }

    @Test void getUNOState() {
        Partida partida = new Partida(listUnoCase, emptyDeck);

        startGameWithGreen1HeadAndPlayRed1Card(partida);

        List<Carta> playerHandA = getPlayerHand(partida, "A");

        partida.playCard(getNumericCard(3, "Red"), "B");

        List<Carta> playerHandB = getPlayerHand(partida, "B");


        assertTrue(playerHandA.get(0).unoState);
        assertTrue(playerHandB.get(0).unoState);


    }

    @Test void returnUNOIfDraw() {
        Partida partida = new Partida(listUnoCase, deck1); // Podria hacer un test de deck...

        startGameWithGreen1HeadAndPlayRed1Card(partida);

        partida.playCard(getNumericCard(3, "Red"), "B");

        partida.drawCard("A");

        List<Carta> playerHandA = getPlayerHand(partida, "A");

        assertFalse(playerHandA.get(0).unoState);

    }

    @Test void shoutUNOToMe() {
        Partida partida = new Partida(listUnoCase, deck1); // Podria hacer un test de deck...

        startGameWithGreen1HeadAndPlayRed1Card(partida);

        partida.personalUNO("A");

        List<Carta> playerHandA = getPlayerHand(partida, "A");

        assertFalse(playerHandA.get(0).unoState);
    }

    @Test void shoutUNOToOthers() {
        Partida partida = new Partida(listUnoCase, deck1); // Podria hacer un test de deck...

        startGameWithGreen1HeadAndPlayRed1Card(partida);

        partida.shoutUNOTo("A");

        int actualCardCount = getActualCardCountForPlayer(partida, "A");

        assertEquals(3, actualCardCount);

    }

    @Test void multipleUNOOperations() {
        Partida partida = new Partida(listUnoCase, deck1); // Podria hacer un test de deck...

        startGameWithGreen1HeadAndPlayRed1Card(partida);

        partida.personalUNO("A").shoutUNOTo("B").shoutUNOTo("A");

        partida.playCard(getNumericCard(3, "Red"), "B");
        partida.shoutUNOTo("B");

        int actualCardCount = getActualCardCountForPlayer(partida, "B");

        assertEquals(3, actualCardCount);
    }

    @Test void finishGame() {
        Partida partida = new Partida(listUnoCase, deck1); // Podria hacer un test de deck...

        Carta head = getNumericCard(1, "Green");
        partida.startGame().setHead(head);

        Carta cardToPlay = getNumericCard(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A").playCard(getNumericCard(3, "Red"), "B");

        assertThrowsLike(()->partida.playCard(getNumericCard(3,"Blue"), "A").drawCard("B"), "Game is over.");
    }

    private static Carta getNumericCard(int valor, String color) {
        Carta head = new CartaNumerica(valor, color);
        return head;
    }

    private static CartaMasDos getPlusTwoCard(String color) {
        return new CartaMasDos(color);
    }

    private Partida PartidaConListaVaciaYDeckVacio() {
        Partida partida = new Partida(listaConCartas, emptyDeck);
        return partida;
    }

    private void assertDifferentGameErrors(int valorH, String colorH, int valorJ, String colorJ, String desiredPlayer, String errorMessage) {
        Partida partida = PartidaConListaVaciaYDeckVacio();
        Carta head = getNumericCard(valorH, colorH);
        Carta aJugar = getNumericCard(valorJ, colorJ);
        AssertDiferentPLayersAndCards(partida, head, aJugar, desiredPlayer, errorMessage);
    }

    private void AssertDiferentPLayersAndCards(Partida partida, Carta head, Carta aJugar, String desiredPlayer, String errorMessage) {
        assertThrowsLike(()-> partida.startGame().setHead(head).playCard(aJugar, desiredPlayer), errorMessage);
    }

    private static String getMessageFromPartida(Partida partida) {
        return partida.getState().getMessage();
    }

    private static void AssertCardPLayedAndHeadValue(Partida partida, Carta cardToPlay,String desiredPlayer, int headValor, String headColor, String errorMessage) {
        assertEquals(headValor,1);

        assertFalse(partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals(desiredPlayer))
                .findFirst()
                .get()
                .getValue()
                .contains(cardToPlay), errorMessage);
    }

    private void initializeGameAndPlayCardWithDifferentHead(int valorH, String colorH) {
        Partida partida = PartidaConListaVaciaYDeckVacio();

        Carta head = getNumericCard(valorH, colorH);
        partida.startGame().setHead(head);

        Carta cardToPlay = getNumericCard(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A");

        AssertCardPLayedAndHeadValue(partida, cardToPlay,"A", 1, "Red", "Player A was not able to play the card.");
    }

    private static List<Carta> getPlayerHand(Partida partida, String player) {
        List<Carta> playerHandA = partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals(player))
                .findFirst()
                .map(SimpleEntry::getValue)
                .orElseThrow(() -> new RuntimeException("Player not found."));
        return playerHandA;
    }

    private static int getActualCardCountForPlayer(Partida partida, String player) {
        int actualCardCount = partida.playerCards.stream()
                .filter(entry -> entry.getKey().equals(player))
                .findFirst()
                .map(entry -> entry.getValue().size())
                .orElse(0);
        return actualCardCount;
    }

    private static void startGameWithGreen1HeadAndPlayRed1Card(Partida partida) {
        Carta head = getNumericCard(1, "Green");
        partida.startGame().setHead(head);

        Carta cardToPlay = getNumericCard(1, "Red"); // Assuming player A has this card
        partida.playCard(cardToPlay, "A");
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
