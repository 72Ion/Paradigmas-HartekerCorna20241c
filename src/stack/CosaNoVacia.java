package stack;

public class CosaNoVacia {
    public Object pop(OOStack ooStack){
        return ooStack.stack.remove( ooStack.size() - 1 );
    }
}
