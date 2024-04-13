package anillo;




public class RingEmpty{
    private Ring.Node current;
    public Ring next(Ring ring) {
        throw new RuntimeException("el siguiente es Vacio");
    }

    public Object current(){
        throw new RuntimeException("Empty ring");
    }

    public Ring add(Ring ring, Object cargo){
        Ring.Node newNode = new Ring.Node(cargo);

        newNode.next = newNode;
        current = newNode;

        return ring;
    }

    public Ring remove(Ring ring){
        return ring;
    }


}
