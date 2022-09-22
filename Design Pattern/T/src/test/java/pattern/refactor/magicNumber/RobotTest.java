package pattern.refactor.magicNumber;

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

        robot.order2(Robot.Command.WALK);
        robot.order2(Robot.Command.STOP);
        robot.order2(Robot.Command.JUMP);

        // robot.order2(100); 컴파일 에러
    }
}