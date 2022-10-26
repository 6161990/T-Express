package pattern.state.after;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void player_test() {
        Player player = new Player();
        player.play(1);
        AdvancedLevel aLevel = AdvancedLevel.getAdvancedLevelInstance();
        player.upgradeLevel(aLevel);
        player.play(2);
        SuperLevel sLevel = SuperLevel.getSuperLevelInstance();
        player.upgradeLevel(sLevel);
        player.play(3);
    }
}
