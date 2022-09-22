package pattern.refactor.magicNumber;

import static pattern.refactor.magicNumber.Robot.Command.STOP;
import static pattern.refactor.magicNumber.Robot.Command.WALK;

public class Robot {

    public static final int COMMAND_WALK = 0;
    public static final int COMMAND_STOP = 1;
    public static final int COMMAND_JUMP = 2;

    public static final int ZERO = 0;

    public enum Command {
        WALK,
        STOP,
        JUMP
    }

    public String name;

    public Robot(String name) {
        this.name = name;
    }

    public void order(int command){
        if(command == COMMAND_WALK){
            System.out.println(name + " walk");
        }else if(command == COMMAND_STOP){
            System.out.println(name + " stop");
        }else if(command == COMMAND_JUMP){
            System.out.println(name + " jump");
        }else {
            System.out.println("ERROR");
        }
    }

    public void order2(Command command){
        if(command == WALK){
            System.out.println(name + " walk");
        }else if(command == STOP){
            System.out.println(name + " stop");
        }else if(command == Command.JUMP){
            System.out.println(name + " jump");
        }else {
            System.out.println("ERROR");
        }
    }
}
