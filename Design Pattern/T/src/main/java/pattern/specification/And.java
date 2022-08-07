package pattern.specification;

public class And<FACTOR> implements Specification<FACTOR> {

    private final Specification<FACTOR> left;
    private final Specification<FACTOR> right;

    public And(Specification<FACTOR> left, Specification<FACTOR> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isSatisfy(FACTOR factor) {
        return left.isSatisfy(factor) && right.isSatisfy(factor);
    }
}
