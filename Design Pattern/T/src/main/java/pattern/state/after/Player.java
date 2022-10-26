package pattern.state.after;


public class Player {

    private PlayerLevel2 level;

    public Player() {
        level = new BeginnerLevel();
        this.level.showLevelMessage();
    }

    public PlayerLevel2 getLevel() {
        return level;
    }

    public void upgradeLevel(PlayerLevel2 level) {
        this.level = level;
        level.showLevelMessage();
    }

    public void play(int count){
        level.go(count);
    }
}