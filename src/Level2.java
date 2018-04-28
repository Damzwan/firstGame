import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level2 extends BasicGameState {

    private World world;
    private Image map;
    private TrueTypeFont font;
    private List<Shape> correctLocations = new ArrayList<>();

    @Override
    public int getID() {
        return 6;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map = new Image("images/mainMenu.png");
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.BOLD, 40), false);
        List<List<Zombie>> obstacles = new ArrayList<>();
        List<Shape> walls = new ArrayList<>();

        //Create the amount of obstacles
        for (int i = 0; i < 3; i++) {
            obstacles.add(new ArrayList<>());
        }

        try {
            world = new World(walls, new Player(new Point(10, 10), 2, 8, 2, world), obstacles, new Rectangle(Application.WIDTH - 64, Application.HEIGHT - 64, 64, 64));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        world.basicRenderSetup(map, font, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        world.basicInputSetup(gc, sbg, getID());
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Image getMap() {
        return map;
    }

    public void setMap(Image map) {
        this.map = map;
    }

    public TrueTypeFont getFont() {
        return font;
    }

    public void setFont(TrueTypeFont font) {
        this.font = font;
    }
}
