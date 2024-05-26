package dron;

class Norte extends Brujula {

    public Brujula turnLeft() {
        return new Oeste();
    }

    public Brujula turnRight() {
        return new Este();
    }

    public String heading() {
        return "North";
    }

}
