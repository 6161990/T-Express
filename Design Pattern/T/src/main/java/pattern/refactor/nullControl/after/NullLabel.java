package pattern.refactor.nullControl.after;

public class NullLabel extends Label{

    public NullLabel() {
        super("(none)");
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public void display() {
        /** NullLabel 에서는 아무것도 안함. */
    }
}
