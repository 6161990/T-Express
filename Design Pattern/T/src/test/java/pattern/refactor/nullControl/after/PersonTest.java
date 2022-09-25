package pattern.refactor.nullControl.after;

import org.junit.jupiter.api.Test;

class PersonTest {

    @Test
    void after() {
        Person people[] = {
                new Person(new Label("Nana"), new Label("nana@gamil.com")),
                new Person(new Label("Soo"), new Label("soo@gamil.com")),
                new Person(new Label("Jin"))
        };

        for (Person person : people) {
            System.out.println(person);
            person.display();
            System.out.println();
        }
    }
}