import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


import java.io.IOException;
import java.util.List;

public class World {
    private List<Shape> walls, fastGoo, slowGoo, stopGoo;
    private Player player;
    private static Audio damage;
    private List<List<Zombie>> obstacles;
    private Shape end;
    private long tick = 0;

    static {
        try {
            damage = SoundStore.get().getOgg("music/damage.ogg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public World(List<Shape> walls, Player player, List<List<Zombie>> obstacles, Shape end, List<Shape> fastGoo, List<Shape> slowGoo, List<Shape> stopGoo) {
        this.walls = walls;
        this.player = player;
        player.setWorld(this);
        this.obstacles = obstacles;
        this.end = end;
        walls.add(new Rectangle(Application.WIDTH, 0, 200, Application.HEIGHT));
        walls.add(new Rectangle(0, -200, Application.WIDTH, 200));
        walls.add(new Rectangle(0, Application.HEIGHT, Application.WIDTH, 200));
        walls.add(new Rectangle(-200, 0, 200, Application.HEIGHT));
        this.fastGoo = fastGoo;
        this.slowGoo = slowGoo;
        this.stopGoo = stopGoo;
    }

    public boolean collidesWithWall() {
        for (Shape wall : walls) {
            if (wall.intersects(player.getHitBox())) return true;
        }
        return false;
    }

    public boolean isInWall(Point point) {
        for (Shape wall : walls) {
            if (wall.contains(point.getX(), point.getY())) return true;
        }
        return false;
    }

    public List<Shape> getWalls() {
        return walls;
    }

    public Player getPlayer() {
        return player;
    }

    public List<List<Zombie>> getObstacles() {
        return obstacles;
    }

    public Shape getEnd() {
        return end;
    }

    public void endGame(StateBasedGame sbg, int id) throws SlickException {
        //Tutorial.music.stop();
        MainMenu.backgroundMusic.loop(1f, 0.4f);
        reset(sbg, id);
        sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
    }

    public void reset(StateBasedGame sbg, int id) {
        sbg.enterState(id, new FadeOutTransition(), new FadeInTransition());
        getPlayer().setHealth(getPlayer().getOriginalHealth());
        getPlayer().setTeleports(getPlayer().getOriginalTeleport());
        getPlayer().setLocation(getPlayer().getOriginalPosition());
        getPlayer().setRunEnergy(100);
    }

    public void getDamage() {
        for (List<Zombie> zombieLst : getObstacles()) {
            for (Zombie zombie : zombieLst) {
                if (zombie.getHitBox().intersects(player.getHitBox())) {
                    if (player.getInvincibility() < 0) {
                        player.setInvincibility(120);
                        player.setHealth(player.getHealth() - 1);
                        damage.playAsSoundEffect(1, 1, false);
                    }
                }
            }
        }

    }

    // A basic render setup for a world
    public void basicRenderSetup(Image map, TrueTypeFont font, Graphics g) {
        tick();

        //draw map and set font
        map.draw();
        g.setFont(font);

        //draw the goo
        if (fastGoo != null) {
            g.setColor(Color.green);
            for (Shape goo : fastGoo) {
                g.draw(goo);
            }
        }


        if (slowGoo != null) {
            g.setColor(Color.yellow);
            for (Shape goo : slowGoo) {
                g.draw(goo);
            }
        }

        if (stopGoo != null){
            g.setColor(Color.black);
            for (Shape goo: stopGoo){
                g.draw(goo);
            }
        }

        //Draw end destination
        g.setColor(Color.blue);
        g.draw(getEnd());
        g.fill(getEnd());

        //Health and energy
        String health = "Health: " + String.valueOf(getPlayer().getHealth());
        g.setColor(Color.green);
        g.drawString(health, 20, Application.HEIGHT - 165);
        g.setColor(Color.red);
        String energy = "Teleports: " + String.valueOf(getPlayer().getTeleports());
        g.drawString(energy, 20, Application.HEIGHT - 115);
        g.setColor(Color.orange);
        String runEnergy = "Energy: " + String.valueOf(getPlayer().getRunEnergy());
        g.drawString(runEnergy, 20, Application.HEIGHT - 65);

        //render the player
        getPlayer().getPlayer().draw(getPlayer().getX(), getPlayer().getY());
        this.getPlayer().getDot().draw(getPlayer().getDotX(), getPlayer().getDotY());

        //render the zombies
        for (List<Zombie> currList : getObstacles()) {
            for (Zombie currZombie : currList) {
                currZombie.getCurrZombieAnimation().draw(currZombie.getX(), currZombie.getY());
                //g.draw(currZombie.getHitBox());
            }
        }

        //draw the walls
        g.setColor(Color.red);
        for (Shape wall : getWalls()) {
            g.draw(wall);
        }
    }

    public void gooSetup(){

        if (slowGoo != null){
            for (Shape goo: slowGoo){
                if (goo.contains(player.getLocation().getX() + 96/2, player.getLocation().getY() + 75/2)) player.setSpeed(player.getSpeed() / 2);
            }
        }
        if (fastGoo != null){
            for (Shape goo: fastGoo) {
                if (goo.contains(player.getLocation().getX() + 96/2, player.getLocation().getY() + 75/2))
                    player.setSpeed(player.getSpeed() * 2);
            }
        }
        if (stopGoo != null){
            for (Shape goo: stopGoo){
                if (goo.contains(player.getLocation().getX() + 96/2, player.getLocation().getY() + 75/2)) player.setSpeed(0.10f);
            }
        }
    }

    public void basicInputSetup(GameContainer gc, StateBasedGame sbg, int id) throws SlickException {
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) endGame(sbg, id);
        if (gc.getInput().isKeyPressed(Input.KEY_R)) reset(sbg, id);
        if (getPlayer().getHealth() <= 0) reset(sbg, id);

        //end the game if target at blue location
        if (getPlayer().getHitBox().intersects(getEnd())) endGame(sbg, id);
        getPlayer().setup(gc);
        getDamage();
        gooSetup();
        System.out.println(player.getSpeed());
    }


    public long getTick() {
        return tick;
    }

    public void tick() {
        tick++;
    }
}
