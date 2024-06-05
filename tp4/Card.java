package UnoTp4;

public abstract class Card {
    protected String color;
    protected String number;

    public abstract String getColor();

    public abstract String getNumber();

    public abstract void checkInsertion(Card mazoCard);


}

class numericCard extends Card {
    public numericCard(String color, String number) {
        this.color = color;
        this.number = number;
    }
    public String getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public void checkInsertion(Card mazoCard) {
        if (mazoCard.getNumber() != this.getNumber() && mazoCard.getColor() != this.getColor()) {
            throw new RuntimeException("Can't insert mismatched card");
        } else return;
    }
}
