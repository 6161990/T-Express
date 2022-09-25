package pattern.refactor.nullControl.after;

public class NullLabel extends Label{

    public NullLabel() {
        super("(none)");
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
