package anillo;

public class Ring {

    SuperClass current;

    public Ring(){
        current = new RingEmpty();
    }

    public Ring next() {
        current = current.next();
        return this;
    }
    
    public Object current() {
        return current.current();
    }

    public Ring add(Object cargo) {
        current = current.add(cargo);
        return this;
    }
    public Ring remove() {
        current = current.remove();
        return this;
    }
}


