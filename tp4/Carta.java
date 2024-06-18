package uno;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class Carta {
    protected int valor;
    protected String color;
    protected boolean unoState;
    protected String accion;

    public abstract int getValor();
    public abstract String getColor();
    public abstract Carta getComparison(Carta incoming);
    public abstract String getAccion();
    public abstract Partida comparePlus2(Partida partida);

    public abstract void executeAction(Partida partida, Carta card);

}



class CartaNumerica extends Carta {
    public CartaNumerica(int valor, String color) {
        this.valor = valor;
        this.color = color;
        this.unoState = false;
        this.accion = "None/Numeric";
    }

    public int getValor() {
        return valor;
    }

    public String getColor() {
        return color;
    }

    public String getAccion() {
        return accion;
    }

    // Para los terminos del parentesis de if puedo armar unos booleans para cada OR...
    public Carta getComparison(Carta incoming) {
        return Optional.of(incoming)
                .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                .orElseThrow(() -> new RuntimeException("Invalid card"));
    }

    public void executeAction(Partida partida, Carta card) {
        partida.nextTurn();
    }

    public Partida comparePlus2(Partida partida) {
        while (partida.plus2Counter > 0) {
            partida.drawCard(partida.getCurrentPlayerName());
            partida.plus2Counter -= 1;
        }
        return partida;
    }

}

class CartaSalto extends Carta {
    public CartaSalto(String color) {
        this.color = color;
        this.valor = -2;
        this.unoState = false;
        this.accion = "Skip";
    }

    public int getValor() {
        return valor;
    }


    public String getColor() {
        return color;
    }

    public String getAccion() {
        return accion;
    }

    public void executeAction(Partida partida, Carta card) {
        partida.nextTurn();
        partida.nextTurn();
    }

    public Carta getComparison(Carta incoming) {
        return Optional.of(incoming)
                .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                .orElseThrow(() -> new RuntimeException("Invalid card"));
    }

    public Partida comparePlus2(Partida partida) {
        while (partida.plus2Counter > 0) {
            partida.drawCard(partida.getCurrentPlayerName());
            partida.plus2Counter -= 1;
        }
        return partida;
    }
}

class CartaCambioColor extends Carta {
    public CartaCambioColor(String color) {
        this.color = color;
        this.valor = -4;
        this.unoState = false;
        this.accion = "Color Change";
    }

    public int getValor() {
        return valor;
    }

    public String getColor() {
        return color;
    }

    public String getAccion() {
        return accion;
    }

    public void executeAction(Partida partida, Carta card) {
        partida.nextTurn();
    }

    public Carta getComparison(Carta incoming) {
        return Optional.of(incoming)
                .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor())
                .orElseThrow(() -> new RuntimeException("Invalid card"));
    }

    public Partida comparePlus2(Partida partida) {
        while (partida.plus2Counter > 0) {
            partida.drawCard(partida.getCurrentPlayerName());
            partida.plus2Counter -= 1;
        }
        return partida;
    }
}



class CartaMasDos extends Carta {
    public CartaMasDos(String color) {
        this.color = color;
        this.valor = -3;
        this.unoState = false;
        this.accion = "Plus 2";
    }

    public int getValor() {
        return valor;
    }

    public String getColor() {
        return color;
    }

    public String getAccion() {
        return accion;
    }

    public void executeAction(Partida partida, Carta card) {
        partida.plus2Counter += 2;
        partida.nextTurn();
    }

    public Partida comparePlus2(Partida partida) {
        return partida;
    }


    public Carta getComparison(Carta incoming) {
        return Optional.of(incoming)
                .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                .orElseThrow(() -> new RuntimeException("Invalid card"));
    }

}


class CartaReversa extends Carta {
    public CartaReversa(String color) {
        this.color = color;
        this.valor = -1;
        this.unoState = false;
        this.accion = "Reverse";
    }


    public int getValor() {
        return valor;
    }

    public String getColor() {
        return color;
    }

    public String getAccion() {
        return accion;
    }

    public void executeAction(Partida partida, Carta card) {
        partida.state = partida.state.swapper();

        partida.nextTurn();
    }

    public Partida comparePlus2(Partida partida) {
        while (partida.plus2Counter > 0) {
            partida.drawCard(partida.getCurrentPlayerName());
            partida.plus2Counter -= 1;
        }
        return partida;
    }

    public Carta getComparison(Carta incoming) {
        return Optional.of(incoming)
                .filter(card -> card.getColor().equals(this.getColor()) || card.getValor() == this.getValor() || card.getColor().isEmpty())
                .orElseThrow(() -> new RuntimeException("Invalid card"));
    }

}

