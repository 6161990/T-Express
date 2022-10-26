package pattern.state.before;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    void player_test() {
        Player player = new Player();
        player.play(1);
        player.upgradeLevel();
        player.play(2);
        player.upgradeLevel();
        player.play(3);
    }
}
