import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Zombie extends Living {
    private SpriteSheet zombieDown, zombieUp, zombieLeft, zombieRight;
    private Animation zombieMoveUp, zombieMoveDown, zombieMoveLeft, zombieMoveRight, currZombieAnimation;
    private Shape hitbox;
    private boolean condition = true;


    public Zombie(Point location, float speed) throws SlickException {
        this.setLocation(location);
        setSpeed(speed);
        hitbox = new Circle(0, 0, 40);
        setZombieDown();
        setZombieLeft();
        setZombieRight();
        setZombieUp();
        setZombieMoveUp();
        setZombieMoveLeft();
        setZombieMoveRight();
        setZombieMoveDown();
        setCurrZombieAnimation(getZombieMoveRight());
    }

    public Shape getHitbox() {
        return hitbox;
    }

    public void setHitbox(Point location) {
        hitbox.setLocation(location.getX(), location.getY());
    }
    
    public void leftToRight(Point loc1, Point loc2, int delta){
        setCurrZombieAnimation(getZombieMoveRight());

        if (getX() < loc2.getX() && condition) moveRight(delta);
        else {
            moveLeft(delta);
            condition = getX() < loc1.getX();
        }
    }
    public void moveRight(int delta){
        setCurrZombieAnimation(getZombieMoveRight());
        setLocation(new Point(getX() + delta*getSpeed(), getY()));
    }

    public void moveLeft(int delta){
        setCurrZombieAnimation(getZombieMoveLeft());
        setLocation(new Point(getX() - delta*getSpeed(), getY()));
    }

    public void moveUp(int delta){
        setCurrZombieAnimation(getZombieMoveUp());
        setLocation(new Point(getX(), getY() - delta*getSpeed()));
    }

    public void moveDown(int delta){
        setCurrZombieAnimation(getZombieMoveDown());
        setLocation(new Point(getX(), getY() + delta*getSpeed()));
    }

    public void setZombieDown() throws SlickException {
        zombieDown = new SpriteSheet("items/images/zombieDown.png", 64, 64);
    }

    public void setZombieUp() throws SlickException {
        zombieUp = new SpriteSheet("items/images/zombieUp.png", 64, 64);
    }

    public void setZombieLeft() throws SlickException {
        zombieLeft = new SpriteSheet("items/images/zombieLeft.png", 64, 64);
    }

    public void setZombieRight() throws SlickException {
        zombieRight = new SpriteSheet("items/images/zombieRight.png", 64, 64);
    }

    public void setZombieMoveUp() {
        zombieMoveUp = new Animation(getZombieUp(), 500);
    }

    public void setZombieMoveDown() {
        zombieMoveDown = new Animation(getZombieDown(), 500);
    }

    public void setZombieMoveLeft() {
        zombieMoveLeft = new Animation(getZombieLeft(), 500);
    }

    public void setZombieMoveRight() {
        zombieMoveRight = new Animation(getZombieRight(), 500);
    }

    public SpriteSheet getZombieDown() {
        return zombieDown;
    }

    public SpriteSheet getZombieUp() {
        return zombieUp;
    }

    public SpriteSheet getZombieLeft() {
        return zombieLeft;
    }

    public SpriteSheet getZombieRight() {
        return zombieRight;
    }

    public Animation getZombieMoveUp() {
        return zombieMoveUp;
    }

    public Animation getZombieMoveDown() {
        return zombieMoveDown;
    }

    public Animation getZombieMoveLeft() {
        return zombieMoveLeft;
    }

    public Animation getZombieMoveRight() {
        return zombieMoveRight;
    }

    public Animation getCurrZombieAnimation() {
        return currZombieAnimation;
    }

    public void setCurrZombieAnimation(Animation currZombieAnimation) {
        this.currZombieAnimation = currZombieAnimation;
    }


}
