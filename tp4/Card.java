package UnoTp4;

public abstract class Card {
    protected String color;
    protected String number;

    public abstract String getColor();

    public abstract String getNumber();

    public abstract void checkInsertion(Card mazoCard);

    public abstract void checkR1();
    public abstract void checkA2();

    public
}

class R1 extends Card {
    public R1() {
        this.color = "Red";
        this.number = "1";
    }

    public String getColor() {
        return this.color;
    }

    public String getNumber() {
        return this.number;
    }

    public void checkInsertion(Card mazoCard) {
        mazoCard.checkR1();
    }

    public void checkR1() {
        throw new RuntimeException("Can't be two R1's in game");
    }

    public void checkA2() {
        throw new RuntimeException("Can't insert A2 after R1");
    }


}

class A2 extends Card {
    public A2() {
        this.color = "Blue";
        this.number = "2";
    }

    public String getColor() {
        return this.color;
    }

    public String getNumber() {
        return this.number;
    }


    public void checkInsertion(Card mazoCard) {
        mazoCard.checkA2();
    }

    public void checkA2() {
        throw new RuntimeException("Can't be two A2's in game");
    }

    public void checkR1() {
        throw new RuntimeException("Can't insert R1 after A2");
    }



}

class R2 extends Card {
    public R2() {
        this.color = "Red";
        this.number = "2";
    }

    public String getColor() {
        return this.color;
    }

    public String getNumber() {
        return this.number;
    }

    public void checkInsertion(Card mazoCard) {
        mazoCard.checkR2();
    }

    public void checkR1() {
        return;
    }

    public void checkA2() {
        return;
    }

}