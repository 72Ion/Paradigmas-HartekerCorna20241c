package dron;

public abstract class Brujula {
    public abstract Brujula turnLeft() ;
    public abstract Brujula turnRight() ;
    public abstract String heading();

}

class Norte extends Brujula {
    public Brujula turnLeft() {
        return new Oeste();
    }

    public Brujula turnRight() {
        return new Este();
    }

    public String heading() {
        return "N";
    }
}

class Sur extends Brujula {
    public Brujula turnLeft() {
        return new Este();
    }

    public Brujula turnRight() {
        return new Oeste();
    }

    public String heading() {
        return "S";
    }
}

class Este extends Brujula {
    public Brujula turnLeft() {
        return new Norte();
    }

    public Brujula turnRight() {
        return new Sur();
    }

    public String heading() {
        return "E";
    }
}

class Oeste extends Brujula {
    public Brujula turnLeft() {
        return new Sur();
    }

    public Brujula turnRight() {
        return new Norte();
    }

    public String heading() {
        return "W";
    }
}