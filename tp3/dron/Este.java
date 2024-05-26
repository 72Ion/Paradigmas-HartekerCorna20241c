package dron;

class Este extends Brujula {
    public Brujula turnLeft() {
        return new Norte();
    }

    public Brujula turnRight() {
        return new Sur();
    }

    public String heading() {
        return "East";
    }
}
