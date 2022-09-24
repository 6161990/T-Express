package pattern.refactor.flag;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindIntTest {

    int[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @Test
    void findBefore() {
        assertTrue(FindInt.findBefore(data, 10));
    }

    @Test
    void findAfter() {
        assertTrue(FindInt.findAfter(data, 10));
    }

    @Test
    void findAfter2() {
        assertTrue(FindInt.findAfter2(data, 10));
    }

    @Test
    void findAfter3() {
        assertTrue(FindInt.findAfter3(data, 10));
    }
}