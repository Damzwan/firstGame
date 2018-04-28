import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tutorial extends BasicGameState {

    private World world;
    private Image map;
    private TrueTypeFont font;
    public static Music music;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        map = new Image("images/mainMenu.png");
        music = new Music("music/World1.ogg");
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF, java.awt.Font.BOLD, 40), false);
        List<List<Zombie>> obstacles = new ArrayList<>();

        List<Shape> walls = new ArrayList<>();
        walls.add(new Rectangle(610, 0, Application.WIDTH - 610, 750));
        walls.add(new Rectangle(110, 750, 800, 200));

        try {
            world = new World(
                    walls,
                    new Player(new Point(10, 10), 2, 8, 2, world), obstacles,
                    new Rectangle(Application.WIDTH - 64, Application.HEIGHT - 64, 64, 64)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Create the amount of obstacles
        for (int i = 0; i < 4; i++) {
            obstacles.add(new ArrayList<>());
        }

        //first obstacle initialization
        int x_pos1 = 10;
        int y_pos1 = 350;
        for (int i = 0; i < 12; i++) {
            addZombie(obstacles.get(0), new Zombie(world, new Point(x_pos1, y_pos1), 2));
            x_pos1 += 50;
        }

        //second obstacle initialization
        int x_pos2 = 10;
        int y_pos2 = 700;
        for (int i = 0; i < 12; i++) {
            addZombie(obstacles.get(1), new Zombie(world, new Point(x_pos2, y_pos2), 2));
            x_pos2 += 50;
        }

        //third obstacle initialization
        int y_pos3 = 950;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(650, y_pos3), 2));
            y_pos3 += 60;
        }
        y_pos3 = 950;
        for (int i = 0; i < 2; i++) {
            addZombie(obstacles.get(2), new Zombie(world, new Point(850, y_pos3), 2));
            y_pos3 += 60;
        }

        //fourth obstacle initialization
        int x_pos = 980;
        int y_pos = 750;
        float speed = 3;
        for (int i = 0; i < 12; i++) {
            addZombie(obstacles.get(3), new Zombie(world, new Point(x_pos, y_pos), speed));
            x_pos += 60;
            y_pos += 25;
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        world.tick();
        //draw map and set font
        map.draw();
        g.setFont(font);

        //draw the walls
        g.setColor(Color.red);
        for (Shape wall : world.getWalls()) {
            g.draw(wall);
        }

        //render the player
        g.setColor(Color.red);
        world.getPlayer().getPlayer().draw(world.getPlayer().getX(), world.getPlayer().getY());
        g.draw(world.getPlayer().getHitBox());
        world.getPlayer().getDot().draw(world.getPlayer().getDotX(), world.getPlayer().getDotY());

        //render the zombies
        for (List<Zombie> currList : world.getObstacles()) {
            for (Zombie currZombie : currList) {
                currZombie.getCurrZombieAnimation().draw(currZombie.getX(), currZombie.getY());
                g.draw(currZombie.getHitBox());
            }
        }

        //Draw end destination
        g.setColor(Color.blue);
        g.draw(world.getEnd());
        g.fill(world.getEnd());

        //Text
        g.setColor(Color.white);
        g.drawString("Welcome to zombie escape!", 620, 0);
        g.drawString("The objective of this game is to avoid getting hit by the zombies", 620, 50);
        g.drawString("and to reach your end destination(blue mark)", 620, 100);
        g.drawString("You can move by using the movement buttons(W, S, A, D)", 620, 200);
        g.drawString("By holding down 'E' you can move a little bit faster!", 620, 250);
        g.drawString("The other way you can move around is by using teleportation!", 620, 300);
        g.drawString("You can teleport by using the left mouse button", 620, 350);
        g.drawString("You can see your", 620, 400);
        g.setColor(Color.green);
        g.drawString("Health", 915, 400);
        g.setColor(Color.white);
        g.drawString("in the left corner.", 1040, 400);
        g.drawString("You can also see your", 620, 450);
        g.setColor(Color.orange);
        g.drawString("Energy", 990, 450);
        g.setColor(Color.white);
        g.drawString("and amount of", 1120, 450);
        g.setColor(Color.red);
        g.drawString("Teleports", 1370, 450);
        g.setColor(Color.white);
        g.drawString("at the same place", 1540, 450);
        g.drawString("Energy means the amount of time you can sprint", 620, 550);
        g.drawString("Teleportation has a maximum distance that is being shown by the red dot", 620, 600);
        g.drawString("Try to clear this level. You only get to see the hitboxes for this tutorial!", 620, 650);
        g.drawString("Good Luck! Press R if you would like to restart!", 620, 700);

        //Health and energy
        String health = "Health: " + String.valueOf(world.getPlayer().getHealth());
        g.setColor(Color.green);
        g.drawString(health, 20, Application.HEIGHT - 165);
        g.setColor(Color.red);
        String energy = "Teleports: " + String.valueOf(world.getPlayer().getTeleports());
        g.drawString(energy, 20, Application.HEIGHT - 115);
        g.setColor(Color.orange);
        String runEnergy = "Energy: " + String.valueOf(world.getPlayer().getRunEnergy());
        g.drawString(runEnergy, 20, Application.HEIGHT - 65);


    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int _delta) throws SlickException {
        world.basicInputSetup(gc, sbg, getID());
        //zombie setup

        //first obstacle
        for (Zombie currZombie : world.getObstacles().get(0)) {
            currZombie.stand(Zombie.zombieMoveDown);
        }

        //second obstacle
        for (Zombie currZombie : world.getObstacles().get(1)) {
            currZombie.stand(Zombie.zombieMoveDown);
        }

        //third obstacle
        for (int i = 0; i < 2; i++) {
            Zombie currZombie = world.getObstacles().get(2).get(i);
            currZombie.moveHorizontal(new Point(100, currZombie.getOriginalY()), currZombie.getOriginalPosition());
        }

        for (int i = 2; i < 4; i++) {
            Zombie currZombie = world.getObstacles().get(2).get(i);
            currZombie.moveHorizontal(new Point(300, currZombie.getOriginalY()), currZombie.getOriginalPosition());
        }

        //fourth obstacle
        float offset = 0;
        for (Zombie currZombie : world.getObstacles().get(3)) {
            currZombie.updateLinearMove(new Point(currZombie.getX(), 750),
                    new Point(currZombie.getX(), Application.HEIGHT - 65), 100, offset += 90f / world.getObstacles().get(3).size(), Direction.vertical, StartDirection.up);
        }
    }

    int i = 0;

    public void addZombie(List<Zombie> lst, Zombie... zombies) {
        lst.addAll(Arrays.asList(zombies));
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getPosX() {
        return Mouse.getX();
    }

    public int getPosY(GameContainer gc) {
        return gc.getInput().getMouseY();
    }
}

