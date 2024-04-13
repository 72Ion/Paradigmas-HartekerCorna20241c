package anillo;

public abstract class SuperClass {
    public abstract SuperClass next();

    public abstract Object current(); // Might be better to use the refactor method.

    public abstract SuperClass add(Object cargo);

    public abstract SuperClass remove();

}
