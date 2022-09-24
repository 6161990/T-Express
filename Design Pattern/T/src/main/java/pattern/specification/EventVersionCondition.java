package pattern.specification;

public class EventVersionCondition implements Specification<Event> {

    EventVersion expected;

    public static EventVersionCondition of(EventVersion eventType){
        return new EventVersionCondition(eventType);
    }

    public EventVersionCondition(EventVersion expected) {
        this.expected = expected;
    }

    @Override
    public boolean isSatisfy(Event factor) {
        return expected.equals(factor.getVersion());
    }
}
