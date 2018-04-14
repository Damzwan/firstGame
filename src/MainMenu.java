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
    private Music music;
    private TrueTypeFont font;
    private List<Zombie> zombies = new ArrayList<>();
    private Player player1;
    private Shape end;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //music = new Music("items/music/zelda.ogg");
        //music.loop();
        map = new Image("images/mainMenu.png");
        end = new Rectangle(0, 0, 64, 64);
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 50), false);
        player1 = new Player(new Point(550, 510), .2f);
        Zombie henky = new Zombie(new Point(10, 200), .2f);
        Zombie billy = new Zombie(new Point(10, 250), .2f);
        Zombie george = new Zombie(new Point(10, 300), .2f);
        addZombie(henky, billy, george);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        //draw map and set font
        map.draw();
        g.setFont(font);

        //Draw end destination
        g.setColor(Color.blue);
        g.draw(end);
        g.fill(end);

        //render the player
        g.setColor(Color.red);
        player1.getPlayer().draw(player1.getX(), player1.getY());
        g.draw(player1.getHitBox());
        player1.getDot().draw(player1.getDotX(), player1.getDotY());

        //render the zombies
        for (Zombie currZombie: zombies){
            currZombie.getCurrZombieAnimation().draw(currZombie.getX(), currZombie.getY());
            g.draw(currZombie.getHitBox());
        }

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
        String health = "Health: " + String.valueOf(player1.getHealth());
        g.setColor(Color.green);
        g.drawString(health, 20, Application.HEIGHT - 125);
        g.setColor(Color.orange);
        String energy = "Energy" + String.valueOf(player1.getEnergyPoints());
        g.drawString(energy, 20, Application.HEIGHT - 75);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

        if (gc.getInput().isKeyPressed(Input.KEY_T)) sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());

        player1.setup(gc, delta);

        if (player1.getHealth() <= 0) reset(sbg);

        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) gc.exit();

        for(Zombie currZombie: zombies){
            player1.getDamage(currZombie.getHitBox());
        }

        for (Zombie currZombie: zombies){
            float y = currZombie.getOriginalY();
            Point loc1 = new Point(10, y);
            Point loc2 = new Point(300, y);
            currZombie.moveHorizontalLeftToRight(loc1, loc2, delta);
        }

        endGame(sbg);
    }

    public List getZombies() {
        return zombies;
    }

    public void addZombie(Zombie... zombis){
        for (Zombie currZombie: zombis){
            zombies.add(currZombie);
        }
    }

    public int getPosX(){
        return Mouse.getX();
    }

    public int getPosY(GameContainer gc){
        return gc.getInput().getMouseY();
    }

    public void reset(StateBasedGame sbg){
        sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
        player1.setHealth(5);
        player1.setEnergyPoints(5);
        player1.setLocation(new Point(550, 510));
    }

    public void endGame(StateBasedGame sbg){
        if (player1.getHitBox().intersects(end)){
            reset(sbg);
        }
    }


}
