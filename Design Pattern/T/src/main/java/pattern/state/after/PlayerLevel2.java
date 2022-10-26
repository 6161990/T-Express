package pattern.state.after;

public abstract class PlayerLevel2 {
    public abstract void run();
    public abstract void jump();
    public abstract void turn();
    public abstract String showLevelMessage();

    final public void go(int count){ // template method pattern !
        run();

        for(int i=0; i<count; i++){
            jump();
        }

        turn();
    }
}
