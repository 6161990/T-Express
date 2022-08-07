package pattern.specification;

import lombok.Value;

@Value(staticConstructor = "of")
public class EventVersionCondition implements Specification<Event> {

    EventVersion expected;

    @Override
    public boolean isSatisfy(Event factor) {
        return expected.equals(factor.getVersion());
    }
}
