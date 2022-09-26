package pattern.refactor.classificationCode.after;

import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    void after() {
        Item book = new Item(Item.TYPECODE_BOOK, "Demian", 20000);
        Item dvd = new Item(Item.TYPECODE_DVD, "King", 50000);
        Item software = new Item(Item.TYPECODE_SOFTWARE, "Window", 30000);

        System.out.println(book);
        System.out.println(dvd);
        System.out.println(software);
    }

}