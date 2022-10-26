package pattern.state.before;

public class Player {

    public static final int BEGINNER_LEVEL = 1;
    public static final int ADVANCED_LEVEL = 2;
    public static final int SUPER_LEVEL = 3;
    private int level;

    public Player()
    {
        level = BEGINNER_LEVEL;
        showLevelMessage();
    }

    public int getLevel() {
        return level;
    }

    public void upgradeLevel() {
        if (level == BEGINNER_LEVEL) {
            level = ADVANCED_LEVEL;
        }
        else if (level == ADVANCED_LEVEL) {
            level = SUPER_LEVEL;
        }
        else {
            System.out.println("not support level");
        }
        showLevelMessage();
    }

    public void play(int count)
    {
        run();
        for(int i=0; i<count; i++){
            jump();
        }
        turn();
    }

    public void run() {
        if (level == BEGINNER_LEVEL) {
            System.out.println("천천히 달립니다..");
        }
        else if (level == ADVANCED_LEVEL) {
            System.out.println("빨리 달립니다");
        }
        else if(level == SUPER_LEVEL) {
            System.out.println("엄청 빨리 달립니다.");
        }
        else {
            System.out.println("not support level");
        }

    }

    public void jump() {
        if (level == BEGINNER_LEVEL) {
            System.out.println("Jump 할 줄 모르지롱.");
        }
        else if (level == ADVANCED_LEVEL) {
            System.out.println("높이 jump 합니다");
        }
        else if(level == SUPER_LEVEL) {
            System.out.println("엄청 높게 jump합니다.");
        }
        else {
            System.out.println("not support level");
        }

    }

    public void turn() {

        if (level == BEGINNER_LEVEL) {
            System.out.println("Turn 할 줄 모르지롱.");
        }
        else if (level == ADVANCED_LEVEL) {
            System.out.println("Turn 할 줄 모르지롱.");
        }
        else if(level == SUPER_LEVEL) {
            System.out.println("한 바퀴 돕니다.");
        }
        else {
            System.out.println("not support level");
        }
    }


    public void showLevelMessage() {

        if (level == BEGINNER_LEVEL) {
            System.out.println("***** 초보자 레벨 입니다. *****");
        }
        else if (level == ADVANCED_LEVEL) {
            System.out.println("***** 중급자 레벨 입니다. *****");
        }
        else if(level == SUPER_LEVEL) {
            System.out.println("***** 고급자 레벨 입니다. *****");
        }
        else {
            System.out.println("not support level");
        }
    }
}
