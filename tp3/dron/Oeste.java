package dron;

class Oeste extends Brujula {
    public Brujula turnLeft() {
        return new Sur();
    }

    public Brujula turnRight() {
        return new Norte();
    }

    public String heading() {
        return "West";
    }
}