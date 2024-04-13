package anillo;

public class RingEmpty extends Anillo {
    private Anillo.Node current;

    public Anillo next(Anillo ring) {
        throw new RuntimeException("el siguiente es Vacio");
    }

    public Anillo current(){
        throw new RuntimeException("Empty ring");
    }

    public Anillo add(Anillo ring, Object cargo){
        ring.Node newNode = new ring.Node(cargo);

        newNode.next = newNode;
        current = newNode;

        return this;
    }

    public Anillo remove(Ring ring){
        return this;
    }


}
