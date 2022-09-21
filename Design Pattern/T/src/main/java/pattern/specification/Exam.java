package pattern.specification;

public class Exam {
    private static final Specification<Event> supportSpec
            = new And<>(EventTypeCondition.of(EventType.of("ORDER")), EventVersionCondition.of(new EventVersion("V0")));


    /** And 연산 편하게 하기 */
    private static final Specification<Event> supportSpec2
            = EventTypeCondition.of(EventType.of("ORDER")).and(EventVersionCondition.of(new EventVersion("V0")));
}
