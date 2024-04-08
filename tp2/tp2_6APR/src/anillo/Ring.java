package anillo;

public class Ring {

    public Ring(){
        this.current = null;
    }
    public Ring(Object cargo) {
        this.current = (Node) cargo;
    }

    public Ring next() {
        if (current == null) {
            throw new RuntimeException("el siguiente es Vacio");
        }
        current = current.next;
        return this;
    }
    
    public Object current() {
        if (current == null) {
            throw new RuntimeException("Empty ring");
        }

        return current.cargo;
    }

    public Ring add(Object cargo) {
        Node newNode = new Node(cargo);
        if (current == null) {
            newNode.next = newNode;
            current = newNode;
        } else {
            Node previous = current;
            while (previous.next != current) {
                previous = previous.next;
            }
            newNode.next = current;
            previous.next = newNode; // PREGUNTARLE al chat porque esta bien esto...
            current = newNode;
        }
        return this;
    }
    public Ring remove() {
        if (current == null) {
            return this;
        }
        if (current.next == current) {
            current = null;
        } else {
            Node temp = current;
            while (temp.next != current) {
                temp = temp.next;
            }
            temp.next = current.next;
            current = current.next;
        }
        return this;
    }

    private static class Node {
        Object cargo;
        Node next;
        Node(Object cargo) {
            this.cargo = cargo;
            this.next = this;
        }
    }

    private Node current = null;
}


