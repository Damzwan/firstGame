import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WorldMenu extends BasicGameState {
    private Image map;
    private Font font;
    private List<String> worlds = new ArrayList<>();

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map = new Image("images/mainMenu.png");
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 50), false);
        String world1 = "World 1(1)";
        String world2 = "World 2(2)";
        String world3 = "World 3(3)";
        String world4 = "World 4(4)";
        String world5 = "World 5(5)";
        String world6 = "World 6(6)";
        String world7 = "World 7(7)";
        String world8 = "World 8(8)";
        String world9 = "World 9(9)";
        addWorld(world1, world2, world3, world4, world5, world6, world7, world8, world9);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.draw();
        g.setFont(font);
        int y = 100;
        for (String currString: worlds){
            g.drawString(currString, Application.WIDTH/2 - (font.getWidth(currString)/2), y);
            y += 100;
        }


    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
    }

    public void addWorld(String... strings){
        worlds.addAll(Arrays.asList(strings));
    }
}
