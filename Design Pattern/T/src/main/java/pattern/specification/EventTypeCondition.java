package pattern.specification;

public class EventTypeCondition implements Specification<Event> {

    EventType expected;

    public static EventTypeCondition of(EventType eventType){
        return new EventTypeCondition(eventType);
    }

    public EventTypeCondition(EventType expected) {
        this.expected = expected;
    }

    @Override
    public boolean isSatisfy(Event factor) {
        return expected.equals(factor.getType());
    }


}
