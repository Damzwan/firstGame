import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World1Menu extends BasicGameState {
    private Image map;
    private Font font;
    private List<String> levels = new ArrayList<>();

    @Override
    public int getID() {
        return 4;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map = new Image("images/mainMenu.png");
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.BOLD, 50), false);

        String level1 = "level 1(1)";
        String level2 = "level 2(2)";
        String level3 = "level 3(3)";
        String level4 = "level 4(4)";
        String level5 = "level 5(5)";
        String level6 = "level 6(6)";
        String level7 = "level 7(7)";
        String level8 = "level 8(8)";
        String level9 = "level 9(9)";
        addLevel(level1, level2, level3, level4, level5, level6, level7, level8, level9);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.draw();
        g.setFont(font);
        int y = 100;
        for (String currString : levels) {
            g.drawString(currString, Application.WIDTH / 2 - (font.getWidth(currString) / 2), y);
            y += 100;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
            sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());
        if (gc.getInput().isKeyPressed(Input.KEY_1)) {
            sbg.enterState(5, new FadeOutTransition(), new FadeInTransition());
            MainMenu.backgroundMusic.stop();
            //Tutorial.music.loop(1f, 0.1f);
        }
        if (gc.getInput().isKeyPressed(Input.KEY_2)) {
            sbg.enterState(6, new FadeOutTransition(), new FadeInTransition());
            MainMenu.backgroundMusic.stop();
            //Tutorial.music.loop(1f, 0.1f);
        }

        if (gc.getInput().isKeyPressed(Input.KEY_3)) {
            sbg.enterState(7, new FadeOutTransition(), new FadeInTransition());
            MainMenu.backgroundMusic.stop();
            //Tutorial.music.loop(1f, 0.1f);
        }

        if (gc.getInput().isKeyPressed(Input.KEY_4)) {
            sbg.enterState(8, new FadeOutTransition(), new FadeInTransition());
            MainMenu.backgroundMusic.stop();
            //Tutorial.music.loop(1f, 0.1f);
        }

    }

    public void addLevel(String... strings) {
        levels.addAll(Arrays.asList(strings));
    }
}
