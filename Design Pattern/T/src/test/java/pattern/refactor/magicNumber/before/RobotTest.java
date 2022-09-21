package pattern.refactor.magicNumber.before;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RobotTest {

    @Test
    void beforeTest() {
        Robot robot = new Robot("HAKER");

        robot.order(0);
        robot.order(1);
        robot.order(2);

        robot.order(100);
    }
}