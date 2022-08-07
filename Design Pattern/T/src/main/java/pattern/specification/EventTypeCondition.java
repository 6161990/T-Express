package pattern.specification;

import lombok.Value;

@Value(staticConstructor = "of")
public class EventTypeCondition implements Specification<Event> {

    EventType expected;

    @Override
    public boolean isSatisfy(Event factor) {
        return expected.equals(factor.getType());
    }
}
