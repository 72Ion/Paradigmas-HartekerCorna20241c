package anillo;

public abstract class Anillo {
    public abstract Anillo next();

    public abstract Object current();

    public abstract Anillo add(Object cargo);

    public abstract Anillo remove();

}
