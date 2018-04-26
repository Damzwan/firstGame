import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import sun.applet.Main;

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
        walls.add(new Rectangle(Application.WIDTH, 0, 200, Application.HEIGHT));


        //Create the amount of obstacles
        for (int i = 0; i < 4; i++) {
            obstacles.add(new ArrayList<>());
        }

        //first obstacle initialization
        int x_pos1 = 10;
        int y_pos1 = 350;
        for (int i = 0; i < 12; i++) {
            addZombie(obstacles.get(0), new Zombie(new Point(x_pos1, y_pos1), 2));
            x_pos1 += 50;
        }

        //second obstacle initialization
        int x_pos2 = 10;
        int y_pos2 = 700;
        for (int i = 0; i < 12; i++) {
            addZombie(obstacles.get(1), new Zombie(new Point(x_pos2, y_pos2), 2));
            x_pos2 += 50;
        }

        //third obstacle initialization
        Zombie zombie25 = new Zombie(new Point(560, 750), 4);
        Zombie zombie26 = new Zombie(new Point(510, 800), 3);
        Zombie zombie27 = new Zombie(new Point(460, 850), 2);

        Zombie zombie28 = new Zombie(new Point(10, 750), 4);
        Zombie zombie29 = new Zombie(new Point(60, 800), 3);
        Zombie zombie30 = new Zombie(new Point(110, 850), 2);

        Zombie zombie31 = new Zombie(new Point(610, 750), 4);
        addZombie(obstacles.get(2), zombie25, zombie26, zombie27, zombie28, zombie29, zombie30, zombie31);

        //fourth obstacle initialization
        int x_pos = 860;
        int y_pos = 750;
        float speed = 3;
        for (int i = 0; i < 11; i++) {
            addZombie(obstacles.get(3), new Zombie(new Point(x_pos, y_pos), speed));
            x_pos += 60;
            y_pos += 25;
        }

        try {
            world = new World(walls, new Player(new Point(10, 10), 2, world), obstacles, new Rectangle(Application.WIDTH - 64, Application.HEIGHT - 64, 64, 64));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //draw map and set font
        map.draw();
        g.setFont(font);
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
        g.drawString("and to reach your end destination(blue mark).", 620, 100);
        g.drawString("You can move by using the movement buttons(W, S, A, D)", 620, 200);
        g.drawString("By holding down 'E' you can move a little bit faster!", 620, 250);
        g.drawString("The other way you can move around is by using spooky teleportation!", 620, 300);
        g.drawString("You can teleport by using the left mouse button.", 620, 350);
        g.drawString("You can see your", 620, 400);
        g.setColor(Color.green);
        g.drawString("Health", 915, 400);
        g.setColor(Color.white);
        g.drawString("in the left corner.", 1040, 400);
        g.drawString("You can also see your", 620, 450);
        g.setColor(Color.orange);
        g.drawString("Energy", 990, 450);
        g.setColor(Color.white);
        g.drawString("at the same place", 1120, 450);
        g.drawString("Energy means the amount of times you can teleport around.", 620, 550);
        g.drawString("Teleportation has a maximum distance that is being shown by the red dot.", 620, 600);
        g.drawString("Try to clear this level. You only get to see the hitboxes for this tutorial!", 620, 650);
        g.drawString("Good Luck! Also do not touch the Red rectangle and press 'R' to reset", 620, 700);

        //Health and energy
        String health = "Health: " + String.valueOf(world.getPlayer().getHealth());
        g.setColor(Color.green);
        g.drawString(health, 20, Application.HEIGHT - 125);
        g.setColor(Color.orange);
        String energy = "Energy: " + String.valueOf(world.getPlayer().getEnergyPoints());
        g.drawString(energy, 20, Application.HEIGHT - 75);
        String runEnergy = "Run Energy: " + String.valueOf(world.getPlayer().getRunEnergy());
        g.drawString(runEnergy, 20, Application.HEIGHT - 200);


    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int _delta) throws SlickException {
        //end the game if target at blue location
        if (world.getPlayer().getHitBox().intersects(world.getEnd())) world.endGame(sbg);

        //inputs
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) world.endGame(sbg);
        if (gc.getInput().isKeyPressed(Input.KEY_R)) world.reset(sbg);

        //player setup
//        if (player1.getHitBox().intersects(outOfBound)) reset(sbg);
        world.getPlayer().setup(gc);
        if (world.getPlayer().getHealth() <= 0) world.reset(sbg);

        //If out of map
        //for (Shape wall : walls) {
            //if (wall.intersects(player1.getHitBox())) {
                //reset(sbg);
            //}
        //}


        //zombie setup

        //set hitbox damage for all zombies
        for (List<Zombie> currList : world.getObstacles()) {
            for (Zombie currZombie : currList) {
                world.getPlayer().getDamage(currZombie.getHitBox());
            }
        }

        //first obstacle
        for (Zombie currZombie : world.getObstacles().get(0)) {
            currZombie.stand();
        }

        //second obstacle
        for (Zombie currZombie : world.getObstacles().get(1)) {
            currZombie.stand();
        }

        //third obstacle
        int x_3 = 10;
        int y_3 = Application.HEIGHT - 65;

        for (int i = 0; i < 3; i++) {
            Point originalPosition = new Point(560, 750);
            Zombie currZombie = world.getObstacles().get(2).get(i);
            Point currZombieLoc1 = currZombie.getOriginalPosition();
            Point currZombieLoc2 = new Point(x_3, originalPosition.getY());
            Point currZombieLoc3 = new Point(x_3, y_3);
            Point currZombieLoc4 = new Point(originalPosition.getX(), y_3);
            currZombie.moveSquaredStartRightLeft(currZombieLoc1, currZombieLoc2, currZombieLoc3, currZombieLoc4);
            x_3 += 50;
            y_3 -= 50;
        }

        int x_4 = 10;
        int y_4 = Application.HEIGHT - 65;
        for (int i = 3; i < 6; i++) {
            Zombie currZombie = world.getObstacles().get(2).get(i);
            Point currZombieLoc1 = new Point(x_4, world.getObstacles().get(2).get(i - 3).getOriginalY());
            Point currZombieLoc2 = world.getObstacles().get(2).get(i - 3).getOriginalPosition();
            Point currZombieLoc3 = new Point(world.getObstacles().get(0).get(i - 3).getOriginalX(), y_4);
            Point currZombieLoc4 = new Point(x_4, y_4);
            currZombie.moveSquaredStartLeftRight(currZombieLoc1, currZombieLoc2, currZombieLoc3, currZombieLoc4);
            x_4 += 50;
            y_4 -= 50;
        }

        Zombie _3zombie7 = world.getObstacles().get(2).get(6);
        Point _3zombie7Loc1 = _3zombie7.getOriginalPosition();
        Point _3zombie7Loc2 = new Point(_3zombie7Loc1.getX(), Application.HEIGHT - 65);
        _3zombie7.moveVertical(_3zombie7Loc1, _3zombie7Loc2);

        //fourth obstacle
        for (Zombie currZombie : world.getObstacles().get(3)) {
            currZombie.moveVertical(new Point(860, 750),
                    new Point(860, Application.HEIGHT - 65));
        }

        Zombie last = world.getObstacles().get(3).get(world.getObstacles().get(3).size() - 1);
        Zombie secondLast = world.getObstacles().get(3).get(world.getObstacles().get(3).size() - 2);
        if (last.condition == secondLast.condition){
            System.out.println(Math.abs(last.getY() - secondLast.getY()));
            System.out.println("Speed Last:" +  last.getSpeed());
            System.out.println("speed secondLast: " + secondLast.getSpeed());
        }

    }

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

