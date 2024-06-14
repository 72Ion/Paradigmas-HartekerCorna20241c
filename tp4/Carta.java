package uno;

public abstract class Carta {
    protected int valor;
    protected String color;
    //protected boolean unoState; // FALTA EL TEMA DEL UNO STATE.

    public abstract int getValor();
    public abstract String getColor();
    public abstract Carta getComparison(Carta incoming);

}

class CartaNumerica extends Carta {
    public CartaNumerica(int valor, String color) {
        this.valor = valor;
        this.color = color;
    }

    public int getValor() {
        return valor;
    }

    public String getColor() {
        return color;
    }

    // Para los terminos del parentesis de if puedo armar unos booleans para cada OR...
    public Carta getComparison(Carta incoming) { // Hay que definir si es incoming u ongoing
        if (incoming.getColor()==this.getColor() || incoming.getValor()==this.getValor()||incoming.getColor()=="") { // HACER LOS EQUALS en vez de ==
            return incoming;
        } else {
            throw new RuntimeException("Invalid card");
        }
    }
}

class CartaReversa extends Carta {
    public CartaReversa(String color) {
        this.color = color;
        this.valor = -1;

    }


    public int getValor() {
        return valor;
    }


    public String getColor() {
        return color;
    }

    public Carta getComparison(Carta incoming) {
        if (incoming.getColor()==this.getColor() || incoming.getValor()==this.getValor()||incoming.getColor()=="") {
            return incoming;
        } else {
            throw new RuntimeException("Invalid card");
        }
    }

}

class CartaSalto extends Carta {
    public CartaSalto(String color) {
        this.color = color;
        this.valor = -2;
    }

    public int getValor() {
        return valor;
    }


    public String getColor() {
        return color;
    }

    public Carta getComparison(Carta incoming) {
        if (incoming.getColor()==this.getColor() || incoming.getValor()==this.getValor()||incoming.getColor()=="") {
            return incoming;
        } else {
            throw new RuntimeException("Invalid card"); // Quiza hacer desde aca un llamado para que se omita el turno.
        }
    }

}

class CartaMasDos extends Carta {
    public CartaMasDos(String color) {
        this.color = color;
        this.valor = -3;
    }

    public int getValor() {
        return valor;
    }

    public String getColor() {
        return color;
    }

    public Carta getComparison(Carta incoming) {
        if (incoming.getColor()==this.getColor() || incoming.getValor()==this.getValor()||incoming.getColor()=="") {
            return incoming;
        } else {
            throw new RuntimeException("Invalid card");
        }
    }

}


class CartaCambioColor extends Carta {
    public CartaCambioColor(String color) {
        this.color = color;
        this.valor = -4;
    }

    public int getValor() {
        return valor;
    }

    public String getColor() {
        return color;
    }


    public Carta getComparison(Carta incoming) {
        if (incoming.getColor()==this.getColor() || incoming.getValor()==this.getValor()) {
            return incoming;
        } else {
            throw new RuntimeException("Invalid card");
        }
    }

    public void setColor(String color) {
        this.color = color;
        return;
    }

}
