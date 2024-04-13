package anillo;

public class RingBasic extends Anillo {
    private Anillo.Node current;

    public Anillo next(Ring ring) {
        return next;
    }

    public Anillo current(){
        return this; // original line was: return current.cargo !
    }

    public Anillo add(Anillo ring, Object cargo){
        ring.Node newNode = new Ring.Node(cargo);

        ring.Node previous = current;
        while (previous.next != current) {
            previous = previous.next;
        }


        newNode.next = current;
        previous.next = newNode; // PREGUNTARLE al chat porque esta bien esto...
        current = newNode;

        return this;
    }

    public Anillo remove(Anillo ring){
            if (current.next == current) {
                current = null;
            } else {
                ring.Node temp = current;
                while (temp.next != current) {
                    temp = temp.next;
                }
                temp.next = current.next;
                current = current.next;
            }
            return ring;
    }


}
