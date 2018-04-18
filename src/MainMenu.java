import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends BasicGameState {

    private Image map;
    public static Music music;
    private TrueTypeFont font;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        music = new Music("items/music/background.ogg");
        music.setVolume(60);
        music.loop();
        map = new Image("images/mainMenu.png");
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 50), false);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        //draw map and set font
        map.draw();
        g.setFont(font);

        //Draw end destination
        g.setColor(Color.blue);

        //render the strings
        g.setColor(Color.white);
        String play = "Play Game(P)";
        g.drawString(play, Application.WIDTH/2 - (font.getWidth(play)/2), Application.HEIGHT/2 - font.getHeight(play) - 200);
        String tutorial = "Tutorial(T)";
        g.drawString(tutorial, Application.WIDTH/2 - (font.getWidth(play)/2), Application.HEIGHT/2 - font.getHeight(play) - 100);
        String options = "Options(O)";
        g.drawString(options, Application.WIDTH/2 - (font.getWidth(play)/2), Application.HEIGHT/2 - font.getHeight(play));
        String exit = "Exit(esc)";
        g.drawString(exit, Application.WIDTH/2 - (font.getWidth(play)/2), Application.HEIGHT/2 - font.getHeight(play) + 100);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_O)) sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        if (gc.getInput().isKeyPressed(Input.KEY_T)){
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
            music.pause();
        }
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) gc.exit();
    }





}
