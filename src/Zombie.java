import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Zombie extends Living {
    private Animation zombieMoveUp, zombieMoveDown, zombieMoveLeft, zombieMoveRight, currZombieAnimation;
    private boolean condition = true;
    private Point originalPosition;


    public Zombie(Point location, float speed) throws SlickException {
        this.setLocation(location);
        setOriginalPosition(location);
        setSpeed(speed);
        setHitBox(new Circle(0, 0, 30));
        zombieMoveUp = new Animation(new SpriteSheet("images/zombieUp.png", 64, 64), 500);
        zombieMoveLeft = new Animation(new SpriteSheet("images/zombieLeft.png", 64, 64), 500);
        zombieMoveRight = new Animation(new SpriteSheet("images/zombieRight.png", 64, 64), 500);
        zombieMoveDown = new Animation(new SpriteSheet("images/zombieDown.png", 64, 64), 500);
        setCurrZombieAnimation(zombieMoveRight);
    }
    
    public void moveHorizontalLeftToRight(Point loc1, Point loc2, int delta){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() < loc2.getX() && condition) moveRight(delta);
        else {
            moveLeft(delta);
            condition = getX() < loc1.getX();
        }
    }

    public void moveHorizontalRightToLeft(Point loc1, Point loc2, int delta){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() > loc2.getX() && condition) moveLeft(delta);
        else {
            moveRight(delta);
            condition = getX() > loc1.getX();
        }
    }

    public void moveVerticalUpToDown(Point loc1, Point loc2, int delta){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getY() < loc2.getY() && condition) moveDown(delta);
        else{
            moveUp(delta);
            condition = getY() < loc1.getY();
        }
    }

    public void moveVerticalDownToUp(Point loc1, Point loc2, int delta){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getY() > loc2.getY() && condition) moveUp(delta);
        else {
            moveDown(delta);
            condition = getY() > loc1.getY();
        }
    }

    public void moveSquaredStartLeftRight(Point loc1, Point loc2, Point loc3, Point loc4, int delta){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() <= loc2.getX() && getY() <= loc1.getY()) moveRight(delta);
        if (getY() <= loc3.getY() && getX() >= loc2.getX()) moveDown(delta);
        if (getX() >= loc4.getX() && getY() >= loc3.getY()) moveLeft(delta);
        if (getY() >= loc1.getY() && getX() <= loc4.getX()) moveUp(delta);
    }

    public void moveSquaredStartRightLeft(Point loc1, Point loc2, Point loc3, Point loc4, int delta){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() >= loc2.getX() && getY() <= loc1.getY()) moveLeft(delta);
        if (getY() <= loc3.getY() && getX() <= loc2.getX()) moveDown(delta);
        if (getX() <= loc4.getX() && getY() >= loc3.getY()) moveRight(delta);
        if (getY() >= loc1.getY() && getX() >= loc4.getX()) moveUp(delta);
    }

    public void moveDiagonalLeftRight(Point loc1, Point loc2, int delta){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() <= loc2.getX() && getY() <= loc2.getY() && condition){
            moveDown(delta);
            moveRight(delta);
        }
        else {
            moveUp(delta);
            moveLeft(delta);
            condition = getX() <= loc1.getX() && getY() <= loc1.getY();
        }
    }

    public void moveDiagonalRightLeft(Point loc1, Point loc2, int delta){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() >= loc2.getX() && getY() <= loc2.getY() && condition){
            moveDown(delta);
            moveLeft(delta);
        }
        else{
            moveUp(delta);
            moveRight(delta);
            condition = getX() >= loc1.getX() && getY() <= loc1.getY();
        }
    }

    public void stand(){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        setCurrZombieAnimation(zombieMoveDown);
        //getCurrZombieAnimation().stop();
        setLocation(getOriginalPosition());
    }

    public void moveRight(int delta){
        setCurrZombieAnimation(zombieMoveRight);
        setLocation(new Point(getX() + delta*getSpeed(), getY()));
    }

    public void moveLeft(int delta){
        setCurrZombieAnimation(zombieMoveLeft);
        setLocation(new Point(getX() - delta*getSpeed(), getY()));
    }

    public void moveUp(int delta){
        setCurrZombieAnimation(zombieMoveUp);
        setLocation(new Point(getX(), getY() - delta*getSpeed()));
    }

    public void moveDown(int delta){
        setCurrZombieAnimation(zombieMoveDown);
        setLocation(new Point(getX(), getY() + delta*getSpeed()));
    }

    public Animation getCurrZombieAnimation() {
        return currZombieAnimation;
    }

    public void setCurrZombieAnimation(Animation currZombieAnimation) {
        this.currZombieAnimation = currZombieAnimation;
    }

    public Point getOriginalPosition() {
        return originalPosition;
    }

    public float getOriginalX(){
        return getOriginalPosition().getX();
    }

    public float getOriginalY(){
        return getOriginalPosition().getY();
    }

    public void setOriginalPosition(Point originalPosition) {
        this.originalPosition = originalPosition;
    }
}
