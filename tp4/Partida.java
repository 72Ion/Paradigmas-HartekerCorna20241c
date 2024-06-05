package UnoTp4;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    // Create a variable that's a list with players

    private List<Jugador> players;
    private Card upperCard;


    public Partida() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Jugador player) {
        this.players.add(player);
    }

    public void checkPlayers() {
        if (players.size() < 2) {
            throw new RuntimeException("At least two players are required to start the game");
        }
    }

    public void nextTurn() {
        if (!players.isEmpty()) {
            Jugador currentPlayer = players.remove(0);
            players.add(currentPlayer);
        }
    }

    public void setUpperCard(Card card) {
        this.upperCard = card;
    }



    public String getCurrentPlayer() {
        if (!players.isEmpty()) {
            Jugador playerInQuestion =  players.get(0); // return the first player as the current player
            return playerInQuestion.getName();
        }
        throw new RuntimeException("No players added");
    }

    public Card getUpperCard() {
        return upperCard;
    }

    public void partidaReceive(Jugador player) {
        this.setUpperCard(player.removeAndReturnFirstCard(this.getUpperCard()));
    }

}
