package uno;

public abstract class State {

    protected Partida partida;
    protected String message;

    public abstract String getMessage();
    public abstract Partida startGame();
    public abstract Partida setHead(Carta head);
    public abstract Partida playCurrentPlayer();
    public abstract Partida playCard(Carta card, int desiredPlayer);



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
        partida.head = head;
        return partida;
    }

    public Partida playCurrentPlayer(){
        throw new RuntimeException("Game has not started yet.");
    }

    public Partida playCard(Carta card, int desiredPlayer) { // Falta Test!!
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

    public Partida startGame(){return partida;}

    public Partida setHead(Carta head){
        partida.head = head;
        return partida;
    }

    public Partida playCurrentPlayer(){
        partida.state = new CurrentPlayerPut(partida);
        return partida;

    }

    public Partida playCard(Carta card, int desiredPlayer) { // Falta Test!!
        throw new RuntimeException("Can't play card without player playing.");
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

    public Partida playCurrentPlayer(){
        throw new RuntimeException("Player is already playing.");
    }

    public Partida playCard(Carta card, int desiredPlayer) {
        partida.comparePlayer(desiredPlayer); // Compara turno
        card = partida.checkCardContention(desiredPlayer, card); // Compara si el jugador tiene la carta
        partida.head = partida.head.getComparison(card); // Compara si la carta se puede agregar
        partida.removePlayerCard(desiredPlayer, card);
        return partida;
    }



}


//
//    public Partida playCurrentPlayer(){
//        partida.state = new StartPlayerOps(partida);
//        return partida;
//
//    }
//
//    public Partida playCard(Carta card){
//        if (partida.playerCards.get(0).contains(card)){
//            partida.playerCards.get(0).remove(card);
//            partida.state = new PlayState(partida);
//            return partida;
//        } else {
//            throw new RuntimeException("Invalid card");
//        }
//    }
//
//    public Partida drawCard(){
//        partida.playerCards.get(0).add(partida.deck.get(0));
//        partida.deck.remove(0);
//        partida.state = new PlayState(partida);
//        return partida;
//    }
//
//    public Partida endTurn(){
//        List<Carta> temp = partida.playerCards.get(0);
//        partida.playerCards.remove(0);
//        partida.playerCards.add(temp);
//        partida.state = new PlayState(partida);
//        return partida;




