package pattern.specification;

public interface Event {
    @Deprecated
    String getEventType();

    @Deprecated
    String getEventVersion();

    default EventType getType(){
        return EventType.of(getEventType());
    }

    default EventVersion getVersion(){
        return EventVersion.of(getEventVersion());
    }
}
