package pattern.state.after;

import java.util.Objects;

public class SuperLevel extends PlayerLevel2{

    public static SuperLevel superLevel = new SuperLevel();

    private SuperLevel() {
    }

    public static SuperLevel getSuperLevelInstance() {
        if(Objects.isNull(superLevel)){
            superLevel = new SuperLevel();
        }
        return superLevel;
    }

    @Override
    public void run() {
        System.out.println("엄청 빨리 달립니다.");
    }

    @Override
    public void jump() {
        System.out.println("엄청 높게 jump합니다.");
    }

    @Override
    public void turn() {
        System.out.println("한 바퀴 돕니다.");
    }

    @Override
    public String showLevelMessage() {
        return "***** 고급자 레벨 입니다. *****";
    }
}
