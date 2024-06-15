package uno;

public abstract class State {

    protected Partida partida;
    protected String message;

    public abstract String getMessage();
    public abstract Partida startGame();
    public abstract Partida setHead(Carta head);
    public abstract Partida playCard(Carta card, int desiredPlayer);
    public abstract Partida playCard(Carta card, int desiredPlayer, String possibleColor);
    public abstract Partida drawCard(int desiredPlayer);



}

class EmptyState extends State {
    String message = "Game has not started yet.";

    Partida partida;
    public EmptyState(Partida partida) {
        this.partida = partida;
    }

    public String getMessage() {
        return message;
    }

    public Partida startGame(){
        if (partida.checkMinPlayers()) {
            partida.state = new PlayState(partida);
            return partida;
        } else {
            throw new RuntimeException("Not enough players to start the game.");
        }
    }

    public Partida setHead(Carta head) {
        throw new RuntimeException("Define Players Before"); // Falta Test!!
    }


    public Partida playCard(Carta card, int desiredPlayer) { // Falta Test!!
        throw new RuntimeException("Game has not started yet.");
    }


    public Partida playCard(Carta card, int desiredPlayer, String possibleColor) { // Falta Test!!
        throw new RuntimeException("Game has not started yet.");
    }

    public Partida drawCard(int desiredPlayer){ // Falta Test!!
        throw new RuntimeException("Game has not started yet.");
    }


}

class PlayState extends State {
    String message = "Game is being played.";

    Partida partida;
    public PlayState(Partida partida) {
        this.partida = partida;
    }

    public String getMessage() {
        return message;
    }

    public Partida startGame(){throw new RuntimeException("Game is already being played.");} // Falta test!!

    public Partida setHead(Carta head){
        partida.head = head;
        partida.state = new CurrentPlayerPut(partida);
        return partida;
    }

    public Partida playCard(Carta card, int desiredPlayer) { // Falta Test!!
        throw new RuntimeException("Can't play card without player playing.");
    }

    public Partida playCard(Carta card, int desiredPlayer, String possibeColor) { // Falta Test!!
        throw new RuntimeException("Can't play card without player playing.");
    }

    public Partida drawCard(int desiredPlayer){ // Falta Test!!
        throw new RuntimeException("Can't draw card without player playing.");
    }


}


class CurrentPlayerPut extends State {
    String message = "Player is playing.";

    Partida partida;

    public CurrentPlayerPut(Partida partida) {
        this.partida = partida;
    }

    public String getMessage() {
        return message;
    }

    public Partida startGame() {
        throw new RuntimeException("Game is already being played.");
    }

    public Partida setHead(Carta head) {
        throw new RuntimeException("Can't randomly set head. Let player play.");
    }


    public Partida playCard(Carta card, int desiredPlayer) {

        partida.comparePlayer(desiredPlayer); // Compara turno
        card = partida.checkCardContention(desiredPlayer, card); // Compara si el jugador tiene la carta
        partida = partida.checkMultipleDraw(partida, card);

        partida.head = partida.head.getComparison(card); // Compara si la carta se puede agregar
        partida.removePlayerCard(desiredPlayer, card);


        partida = partida.changeTurn("");
        return partida;
    }

    public Partida playCard(Carta card, int desiredPlayer, String possibleColor) {

        partida.comparePlayer(desiredPlayer); // Compara turno

        card = partida.checkCardContention(desiredPlayer, card); // Compara si el jugador tiene la carta

        partida = partida.checkMultipleDraw(partida, card);

        partida.head = partida.head.getComparison(card); // Compara si la carta se puede agregar

        partida.removePlayerCard(desiredPlayer, card);

        partida.checkUNOstate(desiredPlayer);

        partida = partida.changeTurn(possibleColor);
        return partida;
    }

    public Partida drawCard(int desiredPlayer){
        partida.comparePlayer(desiredPlayer);
        partida.state = new DrawingCard(partida);
        partida = partida.state.drawCard(desiredPlayer);
        return partida;
    }

}

class DrawingCard extends State {
    String message = "Player is drawing.";

    Partida partida;

    public DrawingCard(Partida partida) {
        this.partida = partida;
    }

    public String getMessage() {
        return message;
    }

    public Partida startGame() {
        throw new RuntimeException("Game is already being played.");
    }

    public Partida setHead(Carta head) {
        throw new RuntimeException("Can't randomly set head. Let player play.");
    }

    public Partida playCard(Carta card, int desiredPlayer) {
        throw new RuntimeException("Player is drawing.");
    }

    public Partida playCard(Carta card, int desiredPlayer, String possibleColor) {
        throw new RuntimeException("Player is drawing.");
    }

    public Partida drawCard(int desiredPlayer){
        partida.playerCards.get(desiredPlayer).add(partida.deck.get(0));
        partida.removeDeckCard();
        partida.state = new CurrentPlayerPut(partida);
        return partida;
    }

    public Partida changeTurn(String possibleColor) { // Falta test!!
        throw new RuntimeException("Player is drawing.");
    }

}




