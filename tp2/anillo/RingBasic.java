package anillo;

class RingBasic extends SuperClass {
    Object value;
    RingBasic next;

    public RingBasic(Object cargo){
        value = cargo;
        next = this;
    }

    public Object current(){
        return this.value; // original line was: return current.cargo !
    }

    public SuperClass add(Object cargo){
        RingBasic nuevo = new RingBasic(cargo);
        Object actual = this.value;
        this.value = cargo;
        nuevo.value = actual;
        nuevo.next = this.next;
        this.next = nuevo;
        return this;

//        Ring.Node newNode = new Ring.Node(cargo);
//
//        Ring.Node previous = current;
//        while (previous.next != current) {
//            previous = previous.next;
//        }
//
//
//        newNode.next = current;
//        previous.next = newNode; // PREGUNTARLE al chat porque esta bien esto...
//        current = newNode;
//
//        return ring;
    }

    public SuperClass remove(){
         return (this.next == this) ? new RingEmpty() :  this.next;
    }
    public SuperClass next(){
        return this.next;
    }

}
