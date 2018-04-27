import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


import java.util.List;

public class World {
    private List<Shape> walls;
    private Player player;
    private List<List<Zombie>> obstacles;
    private Shape end;

    public World(List<Shape> walls, Player player, List<List<Zombie>> obstacles, Shape end) {
        this.walls = walls;
        this.player = player;
        player.setWorld(this);
        this.obstacles = obstacles;
        this.end = end;
        walls.add(new Rectangle(Application.WIDTH, 0, 200, Application.HEIGHT));
        walls.add(new Rectangle(0, -200, Application.WIDTH, 200));
        walls.add(new Rectangle(0, Application.HEIGHT, Application.WIDTH, 200));
        walls.add(new Rectangle(-200, 0, 200, Application.HEIGHT));
    }

    public void endGame(StateBasedGame sbg) throws SlickException {
        MainMenu.backgroundMusic.loop(1f, 0.5f);
        reset(sbg);
        sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
    }

    public void reset(StateBasedGame sbg) {
        sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        getPlayer().setHealth(getPlayer().getOriginalHealth());
        getPlayer().setTeleports(getPlayer().getOriginalTeleport());
        getPlayer().setLocation(new Point(10, 10));
        getPlayer().setRunEnergy(100);
    }

    public boolean collidesWithWall(){
        for (Shape wall: walls){
            if (wall.intersects(player.getHitBox())) return true;
        }
        return false;
    }

    public boolean isInWall(Point point){
        for(Shape wall: walls){
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
}
