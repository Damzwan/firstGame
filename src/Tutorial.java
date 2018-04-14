import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tutorial extends BasicGameState {

    private Image map;
    private Music music;
    private TrueTypeFont font;
    private List<Zombie> firstObstacle = new ArrayList<>();
    private List<Zombie> secondObstacle = new ArrayList<>();
    private List<Zombie> thirdObstacle = new ArrayList<>();
    private Player player1;
    private Shape end;
    private Shape outOfBound;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        map = new Image("images/mainMenu.png");
        end = new Rectangle(Application.WIDTH - 64, Application.HEIGHT - 64, 64, 64);
        outOfBound = new Rectangle(610, 0, Application.WIDTH - 610, 750);
        font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 40), false);
        player1 = new Player(new Point(10, 10), .2f);

        //first obstacle initialization
        Zombie zombie1 = new Zombie(new Point(10, 350), .2f);
        Zombie zombie2 = new Zombie(new Point(60, 350), .2f);
        Zombie zombie3 = new Zombie(new Point(110, 350), .2f);
        Zombie zombie4 = new Zombie(new Point(160, 350), .2f);
        Zombie zombie5 = new Zombie(new Point(210, 350), .2f);
        Zombie zombie6 = new Zombie(new Point(260, 350), .2f);
        Zombie zombie7 = new Zombie(new Point(310, 350), .2f);
        Zombie zombie8 = new Zombie(new Point(360, 350), .2f);
        Zombie zombie9 = new Zombie(new Point(410, 350), .2f);
        Zombie zombie10 = new Zombie(new Point(460, 350), .2f);
        Zombie zombie11 = new Zombie(new Point(510, 350), .2f);
        Zombie zombie12 = new Zombie(new Point(560, 350), .2f);
        addZombie(firstObstacle, zombie1, zombie2, zombie3, zombie4, zombie5, zombie6, zombie7, zombie8, zombie9, zombie10,
                zombie11, zombie12);

        //second obstacle initialization
        Zombie zombie13 = new Zombie(new Point(10, 700), .2f);
        Zombie zombie14 = new Zombie(new Point(60, 700), .2f);
        Zombie zombie15 = new Zombie(new Point(110, 700), .2f);
        Zombie zombie16 = new Zombie(new Point(160, 700), .2f);
        Zombie zombie17 = new Zombie(new Point(210, 700), .2f);
        Zombie zombie18 = new Zombie(new Point(260, 700), .2f);
        Zombie zombie19 = new Zombie(new Point(310, 700), .2f);
        Zombie zombie20 = new Zombie(new Point(360, 700), .2f);
        Zombie zombie21 = new Zombie(new Point(410, 700), .2f);
        Zombie zombie22 = new Zombie(new Point(460, 700), .2f);
        Zombie zombie23 = new Zombie(new Point(510, 700), .2f);
        Zombie zombie24 = new Zombie(new Point(560, 700), .2f);
        addZombie(secondObstacle, zombie13, zombie14, zombie15, zombie16, zombie17, zombie18, zombie19, zombie20, zombie21, zombie22,
                zombie23, zombie24);

        //third obstacle initialization
        Zombie zombie25 = new Zombie(new Point(560, 750), .4f);
        Zombie zombie26 = new Zombie(new Point(510, 800), .3f);
        Zombie zombie27 = new Zombie(new Point(460, 850), .2f);

        Zombie zombie28 = new Zombie(new Point(10, 750), .4f);
        Zombie zombie29 = new Zombie(new Point(60, 800), .3f);
        Zombie zombie30 = new Zombie(new Point(110, 850), .2f);

        Zombie zombie31 = new Zombie(new Point(610, 750), .4f);
        addZombie(thirdObstacle, zombie25, zombie26, zombie27, zombie28, zombie29, zombie30, zombie31);


    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //draw map and set font
        map.draw();
        g.setFont(font);

        g.setColor(Color.red);
        g.draw(outOfBound);

        //render the player
        g.setColor(Color.red);
        player1.getPlayer().draw(player1.getX(), player1.getY());
        g.draw(player1.getHitBox());
        player1.getDot().draw(player1.getDotX(), player1.getDotY());

        //render the zombies

        //first obstacle
        for (Zombie currZombie: firstObstacle){
            currZombie.getCurrZombieAnimation().draw(currZombie.getX(), currZombie.getY());
            g.draw(currZombie.getHitBox());
        }

        //second obstacle
        for (Zombie currZombie: secondObstacle){
            currZombie.getCurrZombieAnimation().draw(currZombie.getX(), currZombie.getY());
            g.draw(currZombie.getHitBox());
        }

        //third obstacle
        for (Zombie currZombie: thirdObstacle){
            currZombie.getCurrZombieAnimation().draw(currZombie.getX(), currZombie.getY());
            g.draw(currZombie.getHitBox());
        }



        //Draw end destination
        g.setColor(Color.blue);
        g.draw(end);
        g.fill(end);

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
        g.drawString("Good Luck! Also do not touch the Red rectangle and 'R' to reset", 620, 700);

        //Health and energy
        String health = "Health: " + String.valueOf(player1.getHealth());
        g.setColor(Color.green);
        g.drawString(health, 20, Application.HEIGHT - 125);
        g.setColor(Color.orange);
        String energy = "Energy" + String.valueOf(player1.getEnergyPoints());
        g.drawString(energy, 20, Application.HEIGHT - 75);


    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //end the game if target at blue location
        endGame(sbg);

        //inputs
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) gc.exit();
        if (gc.getInput().isKeyPressed(Input.KEY_R)) reset(sbg);

        //player setup
        if (player1.getHitBox().intersects(outOfBound)) reset(sbg);
        player1.setup(gc, delta);
        if (player1.getHealth() <= 0) reset(sbg);


        //zombie setup
        for (Zombie currZombie: firstObstacle){ currZombie.stand(); }
        for (Zombie currZombie: secondObstacle) {currZombie.stand();}

        for(Zombie currZombie: firstObstacle){ player1.getDamage(currZombie.getHitBox()); }
        for(Zombie currZombie: secondObstacle){ player1.getDamage(currZombie.getHitBox()); }
        for (Zombie currZombie: thirdObstacle) player1.getDamage(currZombie.getHitBox());

        Zombie _3zombie1 = thirdObstacle.get(0);
        Point _3zombieLoc1 = _3zombie1.getOriginalPosition();
        Point _3zombieLoc2 = new Point(10, _3zombie1.getOriginalY());
        Point _3zombieLoc3 = new Point(10, Application.HEIGHT - 65);
        Point _3zombieLoc4 = new Point(_3zombieLoc1.getX(), Application.HEIGHT - 65);
        _3zombie1.moveSquaredStartRightLeft(_3zombieLoc1, _3zombieLoc2, _3zombieLoc3, _3zombieLoc4, delta);

        Zombie _3zombie2 = thirdObstacle.get(1);
        Point _3zombie2Loc1 = _3zombie2.getOriginalPosition();
        Point _3zombie2Loc2 = new Point(60, _3zombie2.getOriginalY());
        Point _3zombie2Loc3 = new Point(60, Application.HEIGHT - 115);
        Point _3zombie2Loc4 = new Point(_3zombie2Loc1.getX(), Application.HEIGHT - 115);
        _3zombie2.moveSquaredStartRightLeft(_3zombie2Loc1, _3zombie2Loc2, _3zombie2Loc3, _3zombie2Loc4, delta);

        Zombie _3zombie3 = thirdObstacle.get(2);
        Point _3zombie3Loc1 = _3zombie3.getOriginalPosition();
        Point _3zombie3Loc2 = new Point(110, _3zombie2.getOriginalY());
        Point _3zombie3Loc3 = new Point(110, Application.HEIGHT - 165);
        Point _3zombie3Loc4 = new Point(_3zombie3Loc1.getX(), Application.HEIGHT - 165);
        _3zombie3.moveSquaredStartRightLeft(_3zombie3Loc1, _3zombie3Loc2, _3zombie3Loc3, _3zombie3Loc4, delta);

        Zombie _3zombie4 = thirdObstacle.get(3);
        Point _3zombie4Loc1 = new Point(10, _3zombie1.getOriginalY());
        Point _3zombie4Loc2 = _3zombie1.getOriginalPosition();
        Point _3zombie4Loc3 = new Point(_3zombie1.getOriginalX(), Application.HEIGHT - 65);
        Point _3zombie4Loc4 = new Point(10, Application.HEIGHT - 65);
        _3zombie4.moveSquaredStartLeftRight(_3zombie4Loc1, _3zombie4Loc2, _3zombie4Loc3, _3zombie4Loc4, delta );

        Zombie _3zombie5 = thirdObstacle.get(4);
        Point _3zombie5Loc1 = new Point(60, _3zombie2.getOriginalY());
        Point _3zombie5Loc2 = _3zombie2.getOriginalPosition();
        Point _3zombie5Loc3 = new Point(_3zombie2.getOriginalX(), Application.HEIGHT - 115);
        Point _3zombie5Loc4 = new Point(60, Application.HEIGHT - 115);
        _3zombie5.moveSquaredStartLeftRight(_3zombie5Loc1, _3zombie5Loc2, _3zombie5Loc3, _3zombie5Loc4, delta );

        Zombie _3zombie6 = thirdObstacle.get(5);
        Point _3zombie6Loc1 = new Point(110, _3zombie3.getOriginalY());
        Point _3zombie6Loc2 = _3zombie3.getOriginalPosition();
        Point _3zombie6Loc3 = new Point(_3zombie3.getOriginalX(), Application.HEIGHT - 165);
        Point _3zombie6Loc4 = new Point(110, Application.HEIGHT - 165);
        _3zombie6.moveSquaredStartLeftRight(_3zombie6Loc1, _3zombie6Loc2, _3zombie6Loc3, _3zombie6Loc4, delta );

        Zombie _3zombie7 = thirdObstacle.get(6);
        Point _3zombie7Loc1 = _3zombie7.getOriginalPosition();
        Point _3zombie7Loc2 = new Point(_3zombie7Loc1.getX(), Application.HEIGHT - 65);
        _3zombie7.moveVerticalUpToDown(_3zombie7Loc1, _3zombie7Loc2, delta);



    }

    public List<Zombie> getFirstObstacle() {
        return firstObstacle;
    }

    public void addZombie(List<Zombie> lst, Zombie... zombies){
        lst.addAll(Arrays.asList(zombies));
    }

    public int getPosX(){
        return Mouse.getX();
    }

    public int getPosY(GameContainer gc){
        return gc.getInput().getMouseY();
    }

    public void reset(StateBasedGame sbg){
        sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        player1.setHealth(5);
        player1.setEnergyPoints(10);
        player1.setLocation(new Point(10, 10));
    }

    public void endGame(StateBasedGame sbg){
        if (player1.getHitBox().intersects(end)){
            sbg.enterState(0, new FadeOutTransition(), new FadeInTransition());
        }
    }
}