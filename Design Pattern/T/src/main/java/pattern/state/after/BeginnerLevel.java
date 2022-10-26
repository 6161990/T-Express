package pattern.state.after;

import java.util.Objects;

public class BeginnerLevel extends PlayerLevel2 {

    private static BeginnerLevel beginnerLevel = new BeginnerLevel();

    private BeginnerLevel() {
    }

    public static BeginnerLevel getBeginnerLevelInstance() {
        if(Objects.isNull(beginnerLevel)){
            beginnerLevel = new BeginnerLevel();
        }
        return beginnerLevel;
    }

    @Override
    public void run() {
        System.out.println("천천히 달립니다..");
    }

    @Override
    public void jump() {
        System.out.println("Jump 할 줄 모르지롱.");
    }

    @Override
    public void turn() {
        System.out.println("Turn 할 줄 모르지롱.");
    }

    @Override
    public String showLevelMessage() {
        return "***** 초보자 레벨 입니다. *****";
    }
}
