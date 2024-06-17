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



    /*
    public boolean canHandle(Carta incoming) {
    return incoming.getColor().equals(this.getColor()) || incoming.getValor() == this.getValor() || incoming.getColor().isEmpty();
    }
    public Carta getComparison(Carta incoming) {
    if (canHandle(incoming)) {
        return incoming;
    } else {
        throw new RuntimeException("Invalid card");
    }
}
    */

//    public abstract Partida executeAction(Partida partida, String possibleColor);

//    public abstract Partida comparePlus2(Partida partida);
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
//        partida.state = new PuttingReverse(partida);
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




//class CartaNumerica extends Carta {
//    public CartaNumerica(int valor, String color) {
//        this.valor = valor;
//        this.color = color;
//        this.unoState = false;
//    }
//
//    public int getValor() {
//        return valor;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public Partida executeAction(Partida partida, String possibleColor) {
//        partida.currentPlayer = (partida.currentPlayer + 1) % partida.playerCards.size();
//        return partida;
//    }
//
//    public Partida comparePlus2(Partida partida) {
//        while (partida.plus2Counter > 0) {
//            partida = partida.getState().drawCard(partida.currentPlayer);
//            partida.plus2Counter -= 1;
//        }
//        return partida;
//    }
//
//    // Para los terminos del parentesis de if puedo armar unos booleans para cada OR...
//    public Carta getComparison(Carta incoming) { // Hay que definir si es incoming u ongoing
//        if (incoming.getColor()==this.getColor() || incoming.getValor()==this.getValor()||incoming.getColor()=="") { // HACER LOS EQUALS en vez de ==
//            return incoming;
//        } else {
//            throw new RuntimeException("Invalid card");
//        }
//    }
//}
//
//class CartaReversa extends Carta {
//    public CartaReversa(String color) {
//        this.color = color;
//        this.valor = -1;
//        this.unoState = false;
//    }
//
//
//    public int getValor() {
//        return valor;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public Partida executeAction(Partida partida, String possibleColor) {  // ACA VAMOS A TENER QUE CHEQUEAR QUIEN ES EL JUGADOR QUE TIENE QUE JUGAR.
//        Collections.reverse(partida.playerCards); // Reverse the order of players
//        partida.currentPlayer = (partida.playerCards.size() - partida.currentPlayer + 1) % partida.playerCards.size();
//
//        return partida;
//    }
//
//    public Partida comparePlus2(Partida partida) {
//        while (partida.plus2Counter > 0) {
//            partida = partida.getState().drawCard(partida.currentPlayer);
//            partida.plus2Counter -= 1;
//        }
//        return partida;
//    }
//
//    public Carta getComparison(Carta incoming) {
//        if (incoming.getColor()==this.getColor() || incoming.getValor()==this.getValor()||incoming.getColor()=="") {
//            return incoming;
//        } else {
//            throw new RuntimeException("Invalid card");
//        }
//    }
//
//}
//
//class CartaSalto extends Carta {
//    public CartaSalto(String color) {
//        this.color = color;
//        this.valor = -2;
//        this.unoState = false;
//    }
//
//    public int getValor() {
//        return valor;
//    }
//
//
//    public String getColor() {
//        return color;
//    }
//
//    public Partida executeAction(Partida partida, String possibleColor) {
//        partida.currentPlayer = (partida.currentPlayer + 2) % partida.playerCards.size();
//        return partida;
//    }
//
//    public Partida comparePlus2(Partida partida) {
//        while (partida.plus2Counter > 0) {
//            partida = partida.getState().drawCard(partida.currentPlayer);
//            partida.plus2Counter -= 1;
//        }
//        return partida;
//    }
//
//    public Carta getComparison(Carta incoming) {
//        if (incoming.getColor()==this.getColor() || incoming.getValor()==this.getValor()||incoming.getColor()=="") {
//            return incoming;
//        } else {
//            throw new RuntimeException("Invalid card"); // Quiza hacer desde aca un llamado para que se omita el turno.
//        }
//    }
//
//}
//
//class CartaMasDos extends Carta {
//    public CartaMasDos(String color) {
//        this.color = color;
//        this.valor = -3;
//        this.unoState = false;
//    }
//
//    public int getValor() {
//        return valor;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public Partida executeAction(Partida partida, String possibleColor) {
//        partida.currentPlayer = (partida.currentPlayer + 1) % partida.playerCards.size();
//        partida.plus2Counter += 2;
//
//        return partida;
//    }
//
//    public Partida comparePlus2(Partida partida) {
//        return partida;
//    }
//
//
//    public Carta getComparison(Carta incoming) {
//        if (incoming.getColor()==this.getColor() || incoming.getValor()==this.getValor()||incoming.getColor()=="") {
//            return incoming;
//        } else {
//            throw new RuntimeException("Invalid card");
//        }
//    }
//
//}
//
//
//class CartaCambioColor extends Carta {
//    public CartaCambioColor(String color) {
//        this.color = color;
//        this.valor = -4;
//        this.unoState = false;
//    }
//
//    public int getValor() {
//        return valor;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public Partida executeAction(Partida partida, String possibleColor) {
//        partida.head.color = possibleColor; // Aca hay que hacer un chequeo de que el color sea valido.
//        partida.currentPlayer = (partida.currentPlayer + 1) % partida.playerCards.size();
//        return partida;
//    }
//
//
//    public Carta getComparison(Carta incoming) {
//        if (incoming.getColor()==this.getColor() || incoming.getValor()==this.getValor()) {
//            return incoming;
//        } else {
//            throw new RuntimeException("Invalid card");
//        }
//    }
//
//    public Partida comparePlus2(Partida partida) {
//        while (partida.plus2Counter > 0) {
//            partida = partida.getState().drawCard(partida.currentPlayer);
//            partida.plus2Counter -= 1;
//        }
//        return partida;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//        return;
//    }
//
//}
