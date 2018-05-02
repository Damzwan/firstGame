import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level2 extends BasicGameState {

    private World world;
    private Image map;
    private TrueTypeFont font;
    private Shape start;
    private Shape firstObstacle;
    private Shape secondObstacle;
    private Shape thirdObstacle;
    private Shape fourthObstacle;


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
        for (int i = 0; i < 5; i++) {
            obstacles.add(new ArrayList<>());
        }

        try {
            world = new World(walls, new Player(new Point(10, 10), 2, 8, 2, world), obstacles,
                    new Rectangle(Application.WIDTH - 64, Application.HEIGHT - 64, 64, 64), null, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //first obstacle initialisation
        int yPos1 = 0;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(0), new Zombie(world, new Point(100, yPos1), 5));
            yPos1 += 60;
        }
        yPos1 = 0;
        for (int i = 0; i < 5; i++) {
            addZombie(obstacles.get(0), new Zombie(world, new Point(340, yPos1), 5));
            yPos1 += 60;
        }
        int xPos1 = 100;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(0), new Zombie(world, new Point(xPos1, 0), 5));
            xPos1 += 60;
        }
        xPos1 = 100;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(0), new Zombie(world, new Point(xPos1, 240), 5));
            xPos1 += 60;
        }

        //second obstacle initialisation
        int yPos2 = 300;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(100, yPos2), 5));
            yPos2 += 60;
        }
        yPos2 = 300;
        for (int i = 0; i < 5; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(340, yPos2), 5));
            yPos2 += 60;
        }
        int xPos2 = 100;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(xPos2, 300), 5));
            xPos2 += 60;
        }
        xPos2 = 100;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(xPos2, 540), 5));
            xPos2 += 60;
        }
        //third obstacle initialisation
        int yPos3 = 600;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(100, yPos3), 5));
            yPos3 += 60;
        }
        yPos3 = 600;
        for (int i = 0; i < 5; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(340, yPos3), 5));
            yPos3 += 60;
        }
        int xPos3 = 100;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(xPos3, 600), 5));
            xPos3 += 60;
        }
        xPos3 = 100;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(xPos3, 840), 5));
            xPos3 += 60;
        }

        //fourth obstacle initialisation
        int yPos4 = 780;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(3), new Zombie(world, new Point(400, yPos4), 5));
            yPos4 += 60;
        }
        yPos4 = 780;
        for (int i = 0; i < 5; i++) {
            addZombie(obstacles.get(3), new Zombie(world, new Point(640, yPos4), 5));
            yPos4 += 60;
        }
        int xPos4 = 400;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(3), new Zombie(world, new Point(xPos4, 780), 5));
            xPos4 += 60;
        }
        xPos4 = 400;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(3), new Zombie(world, new Point(xPos4, 1020), 5));
            xPos4 += 60;
        }

        //fifth obstacle initialisation
        //first
        int yPos5 = 840;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(4), new Zombie(world, new Point(740, yPos5), 5));
            yPos5 += 60;
        }

        //second
        yPos5 = 900;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(4), new Zombie(world, new Point(1140, yPos5), 5));
            yPos5 += 60;
        }

        //third
        addZombie(obstacles.get(4), new Zombie(world, new Point(1540, 840), 5));
        addZombie(obstacles.get(4), new Zombie(world, new Point(1540, 960), 5));

        //fourth
        addZombie(obstacles.get(4), new Zombie(world, new Point(1600, 840), 5));
        addZombie(obstacles.get(4), new Zombie(world, new Point(1600, 960), 5));

        start = new Rectangle(0, 0, 100, 100);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        world.basicRenderSetup(map, font, g);
        firstObstacle = new Rectangle(world.getObstacles().get(0).get(0).getX() + 60, world.getObstacles().get(0).get(0).getY() + 60, 180, 180);
        secondObstacle = new Rectangle(world.getObstacles().get(1).get(0).getX() + 60, world.getObstacles().get(1).get(0).getY() + 60, 180, 180);
        thirdObstacle = new Rectangle(world.getObstacles().get(2).get(0).getX() + 60, world.getObstacles().get(2).get(0).getY() + 60, 180, 180);
        fourthObstacle = new Rectangle(world.getObstacles().get(3).get(0).getX() + 60, world.getObstacles().get(3).get(0).getY() + 60, 180, 180);
        g.draw(firstObstacle);
        g.draw(secondObstacle);
        g.draw(thirdObstacle);
        g.draw(fourthObstacle);
        g.draw(start);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        world.basicInputSetup(gc, sbg, getID());
        Shape playerShape = getWorld().getPlayer().getHitBox();

        //first obstacle
        for (Zombie zombie : world.getObstacles().get(0)) {
            zombie.updateLinearMove(new Point(zombie.getOriginalX() + 1520, zombie.getOriginalY()), zombie.getOriginalPosition(), 800, 0, Direction.horizontal, StartDirection.right);
        }
        //second obstacle
        for (Zombie zombie : world.getObstacles().get(1)) {
            zombie.updateLinearMove(zombie.getOriginalPosition(), new Point(zombie.getOriginalX() + 1520, zombie.getOriginalY()), 800, 0, Direction.horizontal, StartDirection.left);
        }
        //third obstacle
        for (Zombie zombie : world.getObstacles().get(2)) {
            zombie.updateLinearMove(zombie.getOriginalPosition(), new Point(zombie.getOriginalX(), zombie.getOriginalY() + 180), 400, 0, Direction.vertical, StartDirection.up);
        }

        //fourth obstacle
        for (Zombie zombie : world.getObstacles().get(3)) {
            zombie.updateLinearMove( new Point(zombie.getOriginalX() + (1220 - 64), zombie.getOriginalY()), zombie.getOriginalPosition(), 1200, 0, Direction.horizontal, StartDirection.right);
        }

        //fifth obstacle
        for (Zombie zombie : world.getObstacles().get(4)) {
            zombie.stand(Zombie.zombieMoveLeft);
        }

        if(!playerShape.intersects(start)&&!playerShape.intersects(firstObstacle)&&!playerShape.intersects(secondObstacle)
            &&!playerShape.intersects(thirdObstacle)&&!playerShape.intersects(fourthObstacle) && !playerShape.intersects(world.getEnd()))world.reset(sbg,

    getID());
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
