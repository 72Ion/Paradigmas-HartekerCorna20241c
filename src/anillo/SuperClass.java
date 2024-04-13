package anillo;

public abstract class SuperClass {
    public abstract Ring next(Ring ring);

    public abstract Object current(); // Might be better to use the refactor method.

    public abstract Ring add(Ring ring, Object cargo);

    public abstract Ring remove(Ring ring);

}
