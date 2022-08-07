package pattern.specification;

public interface Specification<FACTOR> {

    boolean isSatisfy(FACTOR factor);

    default Specification<FACTOR> and(Specification<FACTOR> right){
        return new And<>(this, right);
    }
}
