package pattern.specification;

public class EventVersion {

    String eventVersion;

    public EventVersion(String eventVersion) {
        this.eventVersion = eventVersion;
    }

    public static EventVersion of(String eventVersion){
        return new EventVersion(eventVersion);
    }

}
