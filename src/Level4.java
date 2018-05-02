import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level4 extends BasicGameState {
    private World world;
    private Image map;
    private TrueTypeFont font;

    @Override
    public int getID() {
        return 8;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        map = new Image("images/mainMenu.png");
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.BOLD, 40), false);
        List<List<Zombie>> obstacles = new ArrayList<>();
        List<Shape> walls = new ArrayList<>();
        List<Shape> slowGoo = new ArrayList<>();
        List<Shape> fastGoo = new ArrayList<>();
        List<Shape> stopGoo = new ArrayList<>();


        //Create the amount of obstacles
        for (int i = 0; i < 3; i++) {
            obstacles.add(new ArrayList<>());
        }
        walls.add(new Rectangle(300, 100, 200, Application.HEIGHT - 100));
//        walls.add(new Rectangle(150, 720, Application.WIDTH, 180));

        fastGoo.add(new Rectangle(1, Application.HEIGHT - 500, 299, 200));
        slowGoo.add(new Rectangle(1, 430, 299, 120));
        stopGoo.add(new Rectangle(1, 300, 299, 50));
        stopGoo.add(new Rectangle(1, 100, 299, 50));

        try {
            world = new World(walls, new Player(new Point(100, Application.HEIGHT - 220), 2, 8, 2, world),
                    obstacles, new Rectangle(Application.WIDTH - 64, Application.HEIGHT - 64, 64, 64), fastGoo, slowGoo, stopGoo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //first obstacle initialisation
        int xPos1 = 0;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(0), new Zombie(world, new Point(xPos1, Application.HEIGHT - 360), 5));
            xPos1 += 60;
        }
        xPos1 += 60;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(0), new Zombie(world, new Point(xPos1, Application.HEIGHT - 360), 5));
            xPos1 += 60;
        }
        xPos1 = 60;
        for (int i = 0; i < 3; i++) {
            addZombie(obstacles.get(0), new Zombie(world, new Point(xPos1, Application.HEIGHT - 500), 5));
            xPos1 += 60;
        }
        //second obstacle initialisation
        int yPos2 = 430;
        addZombie(obstacles.get(1), new Zombie(world, new Point(0, yPos2), 5));
        yPos2 += 60;
        addZombie(obstacles.get(1), new Zombie(world, new Point(240, yPos2), 5));
        
        //third obstacle initialisation
        int xPos3 = 0;
        for (int i = 0; i < 5; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(xPos3, 100), 5));
            xPos3 += 60;
        }

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        getWorld().basicRenderSetup(map, font, g);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        getWorld().basicInputSetup(gc, sbg, 8);

        //first obstacle
        for (Zombie zombie: world.getObstacles().get(0)){
            zombie.stand(Zombie.zombieMoveDown);
        }

        //second obstacle
        Zombie zombie1 = world.getObstacles().get(1).get(0);
        zombie1.updateLinearMove(zombie1.getOriginalPosition(), new Point(240, zombie1.getOriginalY()), 200, 0, Direction.horizontal, StartDirection.left);
        zombie1 = world.getObstacles().get(1).get(1);
        zombie1.updateLinearMove(zombie1.getOriginalPosition(), new Point(0, zombie1.getOriginalY()), 200, 0, Direction.horizontal, StartDirection.right);

        //third obstacle
        for (Zombie zombie: world.getObstacles().get(2)){
            zombie.updateLinearMove(zombie.getOriginalPosition(), new Point(zombie.getOriginalX(), 310), 150, 0, Direction.vertical, StartDirection.up);

        }
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

    public void addZombie(List<Zombie> lst, Zombie... zombies) {
        lst.addAll(Arrays.asList(zombies));
    }
}
