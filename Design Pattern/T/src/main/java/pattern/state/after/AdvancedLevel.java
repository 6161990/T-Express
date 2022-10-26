package pattern.state.after;

import java.util.Objects;

public class AdvancedLevel extends PlayerLevel2 {

    public static AdvancedLevel advancedLevel = new AdvancedLevel();

    private AdvancedLevel() {
    }

    public static AdvancedLevel getAdvancedLevelInstance() {
        if(Objects.isNull(advancedLevel)){
            advancedLevel = new AdvancedLevel();
        }
        return advancedLevel;
    }

    @Override
    public void run() {
        System.out.println("빨리 달립니다");
    }

    @Override
    public void jump() {
        System.out.println("높이 jump 합니다");
    }

    @Override
    public void turn() {
        System.out.println("Turn 할 줄 모르지롱.");
    }

    @Override
    public String showLevelMessage() {
        return "***** 중급자 레벨 입니다. *****";
    }
}
