package pattern.refactor.magicNumber.before;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pattern.refactor.magicNumber.before.Robot.Command.*;

class RobotTest {

    @Test
    void beforeTest() {
        Robot robot = new Robot("HAKER");

        robot.order(0);
        robot.order(1);
        robot.order(2);

        robot.order(100);
    }

    @Test
    void afterTest() {
        Robot robot = new Robot("HAKER");

        robot.order(Robot.COMMAND_WALK);
        robot.order(Robot.COMMAND_STOP);
        robot.order(Robot.COMMAND_JUMP);

        robot.order(100);
    }

    @Test
    void afterTest2() {
        Robot robot = new Robot("HAKER");

        robot.order2(WALK);
        robot.order2(STOP);
        robot.order2(JUMP);

        // robot.order2(100); 컴파일 에러
    }
}