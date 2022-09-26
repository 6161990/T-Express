package pattern.refactor.classificationCode.after;

import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    void after() {
        Item book = new Item(ItemType.BOOK, "Demian", 20000);
        Item dvd = new Item(ItemType.DVD, "King", 50000);
        Item software = new Item(ItemType.SOFTWARE, "Window", 30000);

        // Item newItem = new Item(200, "Window", 30000); 불가능! ItemType에 아이템을 추가해야가능하다

        System.out.println(book);
        System.out.println(dvd);
        System.out.println(software);
    }

}