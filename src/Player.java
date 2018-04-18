import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Living{
    private int health, energyPoints;
    private Image player, dot;
    private Point dotLocation;
    private float angle;
    private Music blink;
    int invincibility = -1;


    public Player(Point location, float speed) throws SlickException {
        setHealth(2);
        setEnergyPoints(5);
        setLocation(location);
        setSpeed(speed);
        setHitBox(new Circle(0,
                0, 30));
        player = new Image("images/realAssassin.png");
        dot = new Image("images/dot.png");
        blink = new Music("music/blink.ogg");
        setDotLocation();
    }

    public void setup(GameContainer gc, int delta){
        setDotLocation();
        move(gc, delta);
        teleport(gc);
        rotateChar(gc);
        sprint(gc);
        countInvincibility();
    }

    /**
     * Move the player in given direction
     * If E is pressed the player moves faster
     */
    public void move(GameContainer gc, int delta){

        setHitBoxLocation(new Point(getX() + 96/2, getY() + 75/2));

        if (gc.getInput().isKeyDown(Input.KEY_W)){
            setLocation(new Point(getX(), getY() - delta*getSpeed()));
        }

        if (gc.getInput().isKeyDown(Input.KEY_S)){
            setLocation(new Point(getX(), getY() + delta*getSpeed()));
        }

        if (gc.getInput().isKeyDown(Input.KEY_A)){
            setLocation(new Point(getX() - delta*getSpeed(), getY()));
        }

        if (gc.getInput().isKeyDown(Input.KEY_D)){
            setLocation(new Point(getX() + delta*getSpeed(), getY()));
        }
    }

    public void sprint(GameContainer gc){
        if (gc.getInput().isKeyDown(Input.KEY_E)){
            setSpeed(.3f);
        }
        else setSpeed(.2f);
    }

    /**
     * Rotate the main player in a given direction
     */
    public void rotateChar(GameContainer gc){
        float mouseX = gc.getInput().getMouseX();
        float mouseY = gc.getInput().getMouseY();
        float xDistance = mouseX - getX();
        float yDistance = mouseY - getY();
        double angle = Math.toDegrees(Math.atan2(yDistance, xDistance));
        setAngle((float) angle);
        player.setRotation((float) angle);
    }

    /**
     * Teleport the player to a given location
     * if the distance is too far, the player will be teleported to the maximum distance in the given direction
     */
    public void teleport(GameContainer gc){
        if (gc.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            if (getEnergyPoints() <= 0) return;
            double distance = getDistance(getX(), getMousePosX(), getY(), getMousePosY(gc));
            if (distance >= 200){
                blink.play();
                float x = getX() + (float) (200*Math.cos(Math.toRadians(getAngle()))) - 106/3;
                float y = getY() + (float) (200*Math.sin(Math.toRadians(getAngle()))) - 112/3;
                setLocation(new Point(x, y));
                setEnergyPoints(getEnergyPoints() - 1);
            }
            else {
                blink.play();
                float x = getMousePosX() - 106/3;
                float y = getMousePosY(gc) - 112/3;
                setLocation(new Point(x, y));
                setEnergyPoints(getEnergyPoints() - 1);
            }
        }
    }

    public void getDamage(Shape enemyHitBox){
        if (getHitBox().intersects(enemyHitBox)){
            if(invincibility < 0){
                //damage.play();
                invincibility = 100;
                health -= 1;
            }
        }
    }

    public void countInvincibility(){
        if (invincibility >= 0) invincibility -= 1;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getEnergyPoints() {
        return energyPoints;
    }

    public void setEnergyPoints(int energyPoints) {
        this.energyPoints = energyPoints;
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

    public float getDotX(){
        return getDotLocation().getX();
    }

    public float getDotY(){
        return getDotLocation().getY();
    }

    public void setDotLocation() {
        this.dotLocation = new Point(getX() + (float) (200*Math.cos(Math.toRadians(getAngle()))),
                getY() + (float) (200*Math.sin(Math.toRadians(getAngle()))));
    }

    public double getDistance(float X1, float X2, float Y1, float Y2){
        return Math.sqrt(Math.pow(X2 - X1, 2) + Math.pow(Y2 - Y1, 2));
    }

    public int getMousePosX(){
        return Mouse.getX();
    }

    public int getMousePosY(GameContainer gc){
        return gc.getInput().getMouseY();
    }
}
