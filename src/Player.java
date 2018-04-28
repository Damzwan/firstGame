import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;

import java.io.IOException;

public class Player extends Living {
    private World world;
    private int health, teleports, originalHealth, originalTeleport;
    private static Image player, dot;
    private Point dotLocation;
    private float angle, originalSpeed;
    private static Audio blink;
    private int invincibility = -1;
    private int runEnergy = 100;
    float runEnergyCounter = 0;

    static {
        try {
            player = new Image("images/realAssassin.png");
            dot = new Image("images/dotty.png");
            blink = SoundStore.get().getOgg("music/blink.ogg");
        } catch (SlickException | IOException e) {
            e.printStackTrace();
        }
    }

    public Player(Point location, int health, int teleports, float speed, World world) throws SlickException, IOException {
        this.world = world;
        originalHealth = health;
        originalTeleport = teleports;
        setOriginalSpeed(speed);
        setHealth(health);
        setTeleports(teleports);
        setLocation(location);
        setSpeed(speed);
        setHitBox(new Circle(0,
                0, 25));
        setDotLocation();
    }

    public void setup(GameContainer gc) {
        if (gc.getInput().isMousePressed(Input.MOUSE_RIGHT_BUTTON)) System.out.println(getAngle());
        setDotLocation();
        move(gc);
        teleport(gc);
        rotateChar(gc);
        sprint(gc);
        countInvincibility();
    }

    /**
     * Move the player in a given direction
     */
    public void move(GameContainer gc) {

        setHitBoxLocation(new Point(getX() + 96 / 2, getY() + 75 / 2));

        int dx = 0;
        int dy = 0;

        if (gc.getInput().isKeyDown(Input.KEY_W)) {
            if (!world.collidesWithWall() || !world.isInWall(new Point(getX() + 96 / 2, (getY() - getHitBox().getBoundingCircleRadius() + 75 / 2))))
                dy = -1;
        }
        if (gc.getInput().isKeyDown(Input.KEY_S)) {
            if (!world.collidesWithWall() || !world.isInWall(new Point(getX() + 96 / 2, getY() + getHitBox().getBoundingCircleRadius() + 75 / 2)))
                dy = 1;

        }
        if (gc.getInput().isKeyDown(Input.KEY_A)) {
            if (!world.collidesWithWall() || !world.isInWall(new Point(getX() - getHitBox().getBoundingCircleRadius() + 96 / 2, getY() + 75 / 2)))
                dx = -1;
        }
        if (gc.getInput().isKeyDown(Input.KEY_D)) {
            if (!world.collidesWithWall() || !world.isInWall(new Point(getX() + getHitBox().getBoundingCircleRadius() + 96 / 2, getY() + 75 / 2)))
                dx = 1;
        }

        Point next = new Point(getX() + dx * getSpeed(), getY() + dy * getSpeed());
        setLocation(next);
    }

    /**
     * Increase the speed when E is pressed
     */
    public void sprint(GameContainer gc) {
        if (gc.getInput().isKeyDown(Input.KEY_LSHIFT) && runEnergy > 0) {
            setSpeed(getOriginalSpeed() + 2);
            runEnergy--;
        } else {
            setSpeed(getOriginalSpeed());
            if (runEnergy < 100) runEnergyCounter += 0.1;
            if (runEnergyCounter >= 1) {
                runEnergyCounter = 0;
                runEnergy++;
            }
        }
    }

    /**
     * Rotate the main player in a given direction
     */
    public void rotateChar(GameContainer gc) {
        float mouseX = gc.getInput().getMouseX();
        float mouseY = gc.getInput().getMouseY();
        float xDistance = mouseX - (getX() + 96 / 2);
        float yDistance = mouseY - (getY() + 75 / 2);
        double angle = Math.toDegrees(Math.atan2(yDistance, xDistance));
        setAngle((float) angle);
        player.setRotation((float) angle);
    }

    /**
     * Teleport the player to a given location
     * if the distance is too far, the player will be teleported to the maximum distance in the given direction
     */
    //TODO fix teleport
    public void teleport(GameContainer gc) {
        if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
            float x;
            float y;
            if (getTeleports() <= 0) return;
            double distance = getDistance(getX(), getMousePosX(), getY(), getMousePosY(gc));
            if (distance >= 200) {
                x = getX() + (float) (200 * Math.cos(Math.toRadians(getAngle())));
                y = getY() + (float) (200 * Math.sin(Math.toRadians(getAngle())));
            } else {
                x = getMousePosX() - 106 / 3;
                y = getMousePosY(gc) - 112 / 3;
            }
            if (world.isInWall(new Point(x + 96 / 2 + getHitBox().getBoundingCircleRadius(), y + 75 / 2 + getHitBox().getBoundingCircleRadius()))
                    || world.isInWall(new Point(x + 96 / 2 - getHitBox().getBoundingCircleRadius(), y + 75 / 2 - getHitBox().getBoundingCircleRadius()))
                    || world.isInWall(new Point(x + 96 / 2 + getHitBox().getBoundingCircleRadius(), y + 75 / 2 - getHitBox().getBoundingCircleRadius()))
                    || world.isInWall(new Point(x + 96 / 2 - getHitBox().getBoundingCircleRadius(), y + 75 / 2 + getHitBox().getBoundingCircleRadius())))
                return;
            setLocation(new Point(x, y));
            setTeleports(getTeleports() - 1);
            blink.playAsSoundEffect(1, 0.50f, false);
        }
    }

    public void countInvincibility() {
        if (invincibility >= 0) invincibility -= 1;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getTeleports() {
        return teleports;
    }

    public void setTeleports(int teleports) {
        this.teleports = teleports;
    }

    public Image getPlayer() {
        return player;
    }

    public Image getDot() {
        return dot;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Point getDotLocation() {
        return dotLocation;
    }

    public float getDotX() {
        return getDotLocation().getX();
    }

    public float getDotY() {
        return getDotLocation().getY();
    }

    public void setDotLocation() {

        this.dotLocation = new Point(getX() + 96 / 2 - 5 + (float) (200 * Math.cos(Math.toRadians(getAngle()))),
                getY() + 75 / 2 - 3.5f + (float) (200 * Math.sin(Math.toRadians(getAngle()))));
    }

    public double getDistance(float X1, float X2, float Y1, float Y2) {
        return Math.sqrt(Math.pow(X2 - X1, 2) + Math.pow(Y2 - Y1, 2));
    }

    public int getMousePosX() {
        return Mouse.getX();
    }

    public int getMousePosY(GameContainer gc) {
        return gc.getInput().getMouseY();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public int getRunEnergy() {
        return runEnergy;
    }

    public void setRunEnergy(int runEnergy) {
        this.runEnergy = runEnergy;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public int getOriginalTeleport() {
        return originalTeleport;
    }

    public int getInvincibility() {
        return invincibility;
    }

    public void setInvincibility(int invincibility) {
        this.invincibility = invincibility;
    }

    public float getOriginalSpeed() {
        return originalSpeed;
    }

    public void setOriginalSpeed(float originalSpeed) {
        this.originalSpeed = originalSpeed;
    }
}
