import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class Zombie extends Living {
    private static Animation zombieMoveUp, zombieMoveDown, zombieMoveLeft, zombieMoveRight;
    private Animation currZombieAnimation;

    static {
        try {
            zombieMoveDown = new Animation(new SpriteSheet("images/zombieDown.png", 64, 64), 500);
            zombieMoveLeft = new Animation(new SpriteSheet("images/zombieLeft.png", 64, 64), 500);
            zombieMoveRight = new Animation(new SpriteSheet("images/zombieRight.png", 64, 64), 500);
            zombieMoveUp = new Animation(new SpriteSheet("images/zombieUp.png", 64, 64), 500);

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public boolean condition = true; //TODO private
    private Point originalPosition;


    public Zombie(Point location, float speed) throws SlickException {
        this.setLocation(location);
        setOriginalPosition(location);
        setSpeed(speed);
        setHitBox(new Circle(0, 0, 25));
        setCurrZombieAnimation(zombieMoveRight);
    }

    public void moveHorizontal(Point loc1, Point loc2){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() < loc2.getX() && condition) moveRight();
        else {
            moveLeft();
            condition = getX() < loc1.getX();
        }
    }

    public void moveVertical(Point loc1, Point loc2){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getY() < loc2.getY() && condition)
            moveDown();
        else{
            moveUp();
            condition = getY() < loc1.getY();
        }
    }

    public void moveSquaredStartLeftRight(Point loc1, Point loc2, Point loc3, Point loc4){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() <= loc2.getX() && getY() <= loc1.getY()) moveRight();
        if (getY() <= loc3.getY() && getX() >= loc2.getX()) moveDown();
        if (getX() >= loc4.getX() && getY() >= loc3.getY()) moveLeft();
        if (getY() >= loc1.getY() && getX() <= loc4.getX()) moveUp();
    }

    public void moveSquaredStartRightLeft(Point loc1, Point loc2, Point loc3, Point loc4){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() >= loc2.getX() && getY() <= loc1.getY()) moveLeft();
        if (getY() <= loc3.getY() && getX() <= loc2.getX()) moveDown();
        if (getX() <= loc4.getX() && getY() >= loc3.getY()) moveRight();
        if (getY() >= loc1.getY() && getX() >= loc4.getX()) moveUp();
    }

    public void moveDiagonalLeftRight(Point loc1, Point loc2){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() <= loc2.getX() && getY() <= loc2.getY() && condition){
            moveDown();
            moveRight();
        }
        else {
            moveUp();
            moveLeft();
            condition = getX() <= loc1.getX() && getY() <= loc1.getY();
        }
    }

    public void moveDiagonalRightLeft(Point loc1, Point loc2){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() >= loc2.getX() && getY() <= loc2.getY() && condition){
            moveDown();
            moveLeft();
        }
        else{
            moveUp();
            moveRight();
            condition = getX() >= loc1.getX() && getY() <= loc1.getY();
        }
    }

    public void stand(){
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        setCurrZombieAnimation(zombieMoveDown);
        //getCurrZombieAnimation().stop();
        setLocation(getOriginalPosition());
    }

    private void moveRight(){
        setCurrZombieAnimation(zombieMoveRight);
        setLocation(new Point(getX() + getSpeed(), getY()));
    }

    private void moveLeft(){
        setCurrZombieAnimation(zombieMoveLeft);
        setLocation(new Point(getX() - getSpeed(), getY()));
    }

    private void moveUp(){
        setCurrZombieAnimation(zombieMoveUp);
        setLocation(new Point(getX(), getY() - getSpeed()));
    }

    private void moveDown(){
        setCurrZombieAnimation(zombieMoveDown);
        setLocation(new Point(getX(), getY() + getSpeed()));
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
