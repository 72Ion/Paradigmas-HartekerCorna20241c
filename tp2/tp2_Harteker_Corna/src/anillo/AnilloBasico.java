package anillo;

class AnilloBasico extends Anillo {
    Object value;
    AnilloBasico next;

    public AnilloBasico(Object cargo){
        value = cargo;
        next = this;
    }

    public Object current(){
        return this.value; // original line was: return current.cargo !
    }

    public Anillo add(Object cargo){
        AnilloBasico nuevo = new AnilloBasico(cargo);
        Object temp = this.value;
        this.value = cargo;
        nuevo.value = temp;
        nuevo.next = this.next;
        this.next = nuevo;
        return this;

    }

    public Anillo remove(){
         return (this.next == this) ? new AnilloVacio() :  this.next;
    }
    public Anillo next(){
        return this.next;
    }

}
