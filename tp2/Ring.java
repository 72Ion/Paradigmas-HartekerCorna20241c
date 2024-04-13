package anillo;

public class Ring {


    //public AbstractConcurrentProcessor.Node current;
    Anillo current;


        public Ring() {
            this.current = new RingEmpty();  // Initialize to an empty state
        }

        public Ring(Object cargo) {
            this.current = new RingBasic();  // Transition to a basic state
            this.add(cargo);  // Properly add cargo using the add method
        }

        public Anillo next() {
            return current.next(this);

        }

        public Object current() {
            return current.current();
        }

        public Anillo add(Object cargo) {
            return current.add(this, cargo);  // Delegate to the current state
        }

        public Anillo remove() {
            return current.remove(this);
        }


    public static class Node {
        Object cargo;
        Node next;
        Node(Object cargo) {
            this.cargo = cargo;
            this.next = this;
        }
    } // ACA originalmente private.

}


