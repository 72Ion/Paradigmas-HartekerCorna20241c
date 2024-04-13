package anillo;

class AnilloVacio extends Anillo {

    public Anillo next() {
        throw new RuntimeException("el siguiente es Vacio");
    }

    public Object current(){
        throw new RuntimeException("Empty ring");
    }

    public Anillo add(Object cargo){
        return new AnilloBasico(cargo);
    }

    public Anillo remove(){
        throw new RuntimeException();
    }


}
