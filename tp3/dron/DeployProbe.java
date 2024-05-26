package dron;


class DeployProbe extends Commands {

    public char key(){
        return 'd';
    }
    public void execute(Dron dron) {
        dron.checkDeployement();
    }
}
