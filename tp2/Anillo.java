package anillo;

public abstract class Anillo {

    protected Anillo next;

    public abstract Anillo next(Ring ring);

    public abstract Anillo current(); // Might be better to use the refactor method.

    public abstract Anillo add(Ring ring, Object cargo);

    public abstract Anillo remove(Ring ring);

}
