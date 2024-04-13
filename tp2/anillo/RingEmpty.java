package anillo;

class RingEmpty extends SuperClass{
    private Object current;
    public SuperClass next() {
        throw new RuntimeException("el siguiente es Vacio");
    }

    public Object current(){
        throw new RuntimeException("Empty ring");
    }

    public SuperClass add(Object cargo){
        return new RingBasic(cargo);
    }

    public SuperClass remove(){
        throw new RuntimeException();
    }


}
