package dron;

class Sur extends Brujula {
    public Brujula turnLeft() {
        return new Este();
    }

    public Brujula turnRight() {
        return new Oeste();
    }

    public String heading() {
        return "South";
    }
}
