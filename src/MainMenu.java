import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends BasicGameState {

    private Image map;
    private Music music;
    private TrueTypeFont font;
    private String play = "Play Game(P)";
    private String tutorial = "Tutorial(T)";
    private String options = "Options(O)";
    private String exit = "Exit(esc)";
    private List<Zombie> zombies = new ArrayList<>();
    private Zombie henk;
    private Zombie billy;
    private Zombie george;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //music = new Music("items/music/zelda.ogg");
        //music.loop();
        map = new Image("items/images/mainMenu.png");
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 50), false);
        henk = new Zombie(new Point(100, 100), .2f);
        billy = new Zombie(new Point(105, 105), .2f);
        george = new Zombie(new Point(110, 110), .2f);
        addZombie(henk, billy, george);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.draw();
        g.setFont(font);
        g.setColor(Color.white);
        g.drawString(play, Application.WIDTH/2 - (font.getWidth(play)/2), Application.HEIGHT/2 - font.getHeight(play) - 200);
        g.drawString(tutorial, Application.WIDTH/2 - (font.getWidth(play)/2), Application.HEIGHT/2 - font.getHeight(play) - 100);
        g.drawString(options, Application.WIDTH/2 - (font.getWidth(play)/2), Application.HEIGHT/2 - font.getHeight(play));
        g.drawString(exit, Application.WIDTH/2 - (font.getWidth(play)/2), Application.HEIGHT/2 - font.getHeight(play) + 100);
        g.setColor(Color.red);
        for (Zombie currZombie: zombies){
            currZombie.getCurrZombieAnimation().draw(currZombie.getX(), currZombie.getY());
            g.draw(currZombie.getHitbox());
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        int posX = Mouse.getX();
        int posY = Mouse.getY();
        henk.leftToRight(new Point(100, 100), new Point(1000, 100), delta);
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) gc.exit();
        for (Zombie currZombie: zombies){
            Point loc1 = new Point(100, 100);
            Point loc2 = new Point(1000, 100);
            currZombie.leftToRight(loc1, loc2, delta);
            currZombie.setHitbox(new Point(currZombie.getX() - 5, currZombie.getY() - 5));
            loc1.setY(loc1.getY() - 20);
            loc2.setY(loc2.getY() - 20);
        }
    }

    public List getZombies() {
        return zombies;
    }

    public void addZombie(Zombie... zombis){
        for (Zombie currZombie: zombis){
            zombies.add(currZombie);
        }
    }


}
