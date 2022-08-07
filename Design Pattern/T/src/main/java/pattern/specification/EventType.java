package pattern.specification;

public class EventType {

    String eventType;

    public EventType(String eventType) {
        this.eventType = eventType;
    }

    public static EventType of(String eventType) {
        return new EventType(eventType);
    }
}
