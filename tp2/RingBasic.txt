package anillo;

public class RingBasic {
    private Ring.Node current;
    public Ring next(Ring ring) {
        ring.current = ring.current.next;
        return ring; // no es ring.this? o solo this?
    }

    public Object current(){
        return this; // original line was: return current.cargo !
    }

    public Ring add(Ring ring, Object cargo){
        Ring.Node newNode = new Ring.Node(cargo);

        Ring.Node previous = current;
        while (previous.next != current) {
            previous = previous.next;
        }


        newNode.next = current;
        previous.next = newNode; // PREGUNTARLE al chat porque esta bien esto...
        current = newNode;

        return ring;
    }

    public Ring remove(Ring ring){
            if (current.next == current) {
                current = null;
            } else {
                Ring.Node temp = current;
                while (temp.next != current) {
                    temp = temp.next;
                }
                temp.next = current.next;
                current = current.next;
            }
            return ring;
    }


}
