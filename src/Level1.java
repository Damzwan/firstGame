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

        walls.add(new Rectangle(500, 0, 180, Application.HEIGHT - 200));
        walls.add(new Rectangle(880, Application.HEIGHT - 400, Application.WIDTH - 880 - 64, 200));
        walls.add(new Rectangle(680, 0, Application.WIDTH - 680, 600));

        //Create the amount of obstacles
        for (int i = 0; i < 5; i++) {
            obstacles.add(new ArrayList<>());
        }

        try {
            world = new World(walls, new Player(new Point(10, 10), 1, 4, 2, world), obstacles, new Rectangle(Application.WIDTH - 64, Application.HEIGHT - 64, 64, 64),
                    null, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //first obstacle initialisation
        int yPos1 = 205;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(0), new Zombie(world, new Point(0, yPos1), 5));
            yPos1 += 410;
        }

        //second obstacle initialisation
        int yPos2 = 410;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(440, yPos2), 5));
            yPos2 += 410;
        }

        //third obstacle initialisation
        //zombie blockade
        int yPos3 = Application.HEIGHT - 200;
        int xPos3 = Application.WIDTH - 1040;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                addZombie(obstacles.get(2), new Zombie(world, new Point(xPos3, yPos3), 3));
                yPos3 += 60;
            }
            yPos3 = Application.HEIGHT - 200;
            //xPos3 += 60;
        }

        //fourth obstacle initialisation
        int xPos4 = 680;
        for (int i = 0; i < 1; i++) {
            addZombie(obstacles.get(3), new Zombie(world, new Point(xPos4, 610), 4));
            xPos4+= 60;
        }
        xPos4 += 180;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(3), new Zombie(world, new Point(xPos4, 610), 4));
            xPos4+= 60;
        }
        xPos4 += 180;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(3), new Zombie(world, new Point(xPos4, 610), 4));
            xPos4+= 60;
        }
        xPos4 += 180;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(3), new Zombie(world, new Point(xPos4, 610), 4));
            xPos4+= 60;
        }


    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        world.basicRenderSetup(map, font, g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        world.basicInputSetup(gc, sbg, getID());

        //first obstacle
        for (Zombie zombie: world.getObstacles().get(0)){
            zombie.updateLinearMove(zombie.getOriginalPosition(), new Point(440, zombie.getOriginalY()), 300, 0, Direction.horizontal, StartDirection.left);
        }
        //second obstacle
        for (Zombie zombie: world.getObstacles().get(1)){
            zombie.updateLinearMove(zombie.getOriginalPosition(), new Point(0, zombie.getOriginalY()), 300, 0, Direction.horizontal, StartDirection.right);
        }
        //third obstacle
        List<Zombie> get = world.getObstacles().get(2);
        for (int i = 0; i < 3; i++) {
            Zombie zombie = get.get(i);
            zombie.updateLinearMove(new Point(Application.WIDTH - 1040, zombie.getOriginalY()), new Point(Application.WIDTH - 150, zombie.getOriginalY()), 200, 0, Direction.horizontal, StartDirection.left);
        }

        for (int i = 3; i < 6; i++) {
            Zombie zombie = get.get(i);
            zombie.updateLinearMove(new Point(Application.WIDTH - 1040, zombie.getOriginalY()), new Point(Application.WIDTH - 150, zombie.getOriginalY()), 200, -15, Direction.horizontal, StartDirection.left);
        }

        for (int i = 6; i < 9; i++) {
            Zombie zombie = get.get(i);
            zombie.updateLinearMove(new Point(Application.WIDTH - 1040, zombie.getOriginalY()), new Point(Application.WIDTH - 150, zombie.getOriginalY()), 200, -30, Direction.horizontal, StartDirection.left);
        }
        get = world.getObstacles().get(3);
        //fourth obstacle
        for (int i = 0; i < 7; i++) {
            Zombie zombie = get.get(i);
            zombie.updateLinearMove(zombie.getOriginalPosition(), new Point(zombie.getOriginalX() + 280 - 64, zombie.getOriginalY()), 300, 0, Direction.horizontal, StartDirection.left);
        }

    }

    public void addZombie(List<Zombie> lst, Zombie... zombies) {
        lst.addAll(Arrays.asList(zombies));
    }
}
