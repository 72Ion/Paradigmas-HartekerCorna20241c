package anillo;

public abstract class Anillo {
    public abstract Anillo next();

    public abstract Object current(); // Might be better to use the refactor method.

    public abstract Anillo add(Object cargo);

    public abstract Anillo remove();

}
