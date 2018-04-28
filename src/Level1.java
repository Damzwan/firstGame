import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Level1 extends BasicGameState {

    private World world;
    private Image map;
    private TrueTypeFont font;

    @Override
    public int getID() {
        return 5;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map = new Image("images/mainMenu.png");
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.BOLD, 40), false);
        List<List<Zombie>> obstacles = new ArrayList<>();
        List<Shape> walls = new ArrayList<>();

        //Create the amount of obstacles
        for (int i = 0; i < 4; i++) {
            obstacles.add(new ArrayList<>());
        }
        walls.add(new Rectangle(0, 130, Application.WIDTH - 150, 180));
        walls.add(new Rectangle(150, 720, Application.WIDTH, 180));

        try {
            world = new World(walls, new Player(new Point(10, 10), 2, 5, 2, world), obstacles, new Rectangle(Application.WIDTH - 64, Application.HEIGHT - 64, 64, 64));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //first obstacle initialization
        addZombie(obstacles.get(0), new Zombie(world, new Point(Application.WIDTH - 150, 0), 5));
        addZombie(obstacles.get(0), new Zombie(world, new Point(100, 60), 5));

        //second obstacle initialization
        int xPos1 = 150;
        int yPos1 = 300;

        //first wall
        for (int i = 0; i < 3; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(xPos1, yPos1), 5));
            yPos1 += 60;
        }
        yPos1 += 120;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(xPos1, yPos1), 5));
            yPos1 += 60;
        }

        //second wall
        yPos1 = 300;
        for (int i = 0; i < 1; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(xPos1, yPos1), 5));
            yPos1 += 60;
        }
        yPos1 += 120;
        for (int i = 0; i < 4; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(xPos1, yPos1), 5));
            yPos1 += 60;
        }

        //third wall
        yPos1 = 420;
        for (int i = 0; i < 5; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(xPos1, yPos1), 5));
            yPos1 += 60;
        }

        //fourth wall
        yPos1 = 300;
        for (int i = 0; i < 5; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(xPos1, yPos1), 5));
            yPos1 += 60;
        }

        //third obstacle initialization

        //zombie blockade
        int yPos2 = 900;
        int xPos2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addZombie(obstacles.get(2), new Zombie(world, new Point(xPos2, yPos2), 5));
                yPos2 += 60;
            }
            yPos2 = 900;
            xPos2 += 60;
        }

        //standing zombies
        yPos2 = 900;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(300, yPos2), 5));
            yPos2 += 60;
        }

        yPos2 = 960;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(500, yPos2), 5));
            yPos2 += 60;
        }

        addZombie(obstacles.get(2), new Zombie(world, new Point(700, 900), 5));
        addZombie(obstacles.get(2), new Zombie(world, new Point(700, 1020), 5));

        addZombie(obstacles.get(2), new Zombie(world, new Point(760, 900), 5));
        addZombie(obstacles.get(2), new Zombie(world, new Point(760, 1020), 5));

        addZombie(obstacles.get(2), new Zombie(world, new Point(820, 900), 5));
        addZombie(obstacles.get(2), new Zombie(world, new Point(820, 1020), 5));

        yPos2 = 960;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(1020, yPos2), 5));
            yPos2 += 60;
        }

        yPos2 = 960;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(1080, yPos2), 5));
            yPos2 += 60;
        }

        yPos2 = 900;
        for (int i = 0; i < 3; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(1280, yPos2), 5));
            yPos2 += 60;
        }

        yPos2 = 900;
        for (int i = 0; i < 3; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(1480, yPos2), 5));
            yPos2 += 60;
        }

        yPos2 = 900;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(1680, yPos2), 5));
            yPos2 += 60;
        }

        addZombie(obstacles.get(2), new Zombie(world, new Point(1740, 900), 5));
        addZombie(obstacles.get(2), new Zombie(world, new Point(1740, 1020), 5));

        addZombie(obstacles.get(2), new Zombie(world, new Point(1800, 900), 5));
        addZombie(obstacles.get(2), new Zombie(world, new Point(1800, 1020), 5));


    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        world.basicRenderSetup(map, font, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        world.basicInputSetup(gc, sbg, getID());

        //first obstacle
        world.getObstacles().get(0).get(0).updateLinearMove( world.getObstacles().get(0).get(0).getOriginalPosition(),new Point(100, 10), 250, 0, Direction.horizontal, StartDirection.right);
        world.getObstacles().get(0).get(1).updateLinearMove( world.getObstacles().get(0).get(1).getOriginalPosition(), new Point(Application.WIDTH-150, 70), 250, 0, Direction.horizontal, StartDirection.left);

        //second obstacle
        int offset = 0;
        List<Zombie> get = world.getObstacles().get(1);
        for (int i = 0; i < 5; i++) {
            Zombie zombie = get.get(i);
            zombie.updateLinearMove(new Point(150, zombie.getOriginalY()), new Point(Application.WIDTH - 150, zombie.getOriginalY()), 500, offset, Direction.horizontal, StartDirection.left);
        }

        for (int i = 5; i < 10; i++) {
            Zombie zombie = get.get(i);
            zombie.updateLinearMove(new Point(150, zombie.getOriginalY()), new Point(Application.WIDTH - 150, zombie.getOriginalY()), 500, -90f, Direction.horizontal, StartDirection.left);
        }

        for (int i = 10; i < 15; i++) {
            Zombie zombie = get.get(i);
            zombie.updateLinearMove(new Point(150, zombie.getOriginalY()), new Point(Application.WIDTH - 150, zombie.getOriginalY()), 500, -180f, Direction.horizontal, StartDirection.left);
        }

        for (int i = 15; i < 20; i++) {
            Zombie zombie = get.get(i);
            zombie.updateLinearMove(new Point(150, zombie.getOriginalY()), new Point(Application.WIDTH - 150, zombie.getOriginalY()), 500, -270f, Direction.horizontal, StartDirection.left);
        }

        //third obstacle
        get = world.getObstacles().get(2);
        for (int i = 0; i < 3; i++) {
            Zombie zombie = get.get(i);
            zombie.updateLinearMove(new Point(0, zombie.getOriginalY()), new Point(Application.WIDTH - 150, zombie.getOriginalY()), 800, 0, Direction.horizontal, StartDirection.left);
        }

        for (int i = 3; i < 6; i++) {
            Zombie zombie = get.get(i);
            zombie.updateLinearMove(new Point(0, zombie.getOriginalY()), new Point(Application.WIDTH - 150, zombie.getOriginalY()), 800, -20, Direction.horizontal, StartDirection.left);
        }

        for (int i = 6; i < 9; i++) {
            Zombie zombie = get.get(i);
            zombie.updateLinearMove(new Point(0, zombie.getOriginalY()), new Point(Application.WIDTH - 150, zombie.getOriginalY()), 800, -40, Direction.horizontal, StartDirection.left);
        }

        //chilling zombies
        for (int i = 9; i < 35; i++) {
            Zombie zombie = get.get(i);
            zombie.stand(Zombie.zombieMoveLeft);
        }

    }

    public void addZombie(List<Zombie> lst, Zombie... zombies) {
        lst.addAll(Arrays.asList(zombies));
    }
}
