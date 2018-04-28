import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

public class Zombie extends Living {
    public static Animation zombieMoveUp, zombieMoveDown, zombieMoveLeft, zombieMoveRight;
    private Animation currZombieAnimation;
    private World world;


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

    private boolean condition = true; //TODO private
    private Point originalPosition;


    public Zombie(World world, Point location, float speed) throws SlickException {
        this.setLocation(location);
        setOriginalPosition(location);
        this.world = world;
        setSpeed(speed);
        setHitBox(new Circle(0, 0, 22));
        setCurrZombieAnimation(zombieMoveRight);
    }

    public void update(){

    }

    public void moveHorizontal(Point loc1, Point loc2) {
        if (getX() < loc2.getX() && condition) moveRight();
        else {
            moveLeft();
            condition = getX() < loc1.getX();
        }
        setHitBoxLocation(new Point(getX() + 32, getY() + 32));
    }

    public void updateLinearMove(Point loc1, Point loc2, float timeLength, float offset, Direction direction, StartDirection startDirection) {
        float unitTime = ((world.getTick() + offset) % (2 * timeLength)) / timeLength - 1;
        float t = Math.abs(unitTime);

        if (direction == Direction.vertical && startDirection == StartDirection.up) {
            if (unitTime < 0) setCurrZombieAnimation(zombieMoveDown);
            else setCurrZombieAnimation(zombieMoveUp);
        } else if (direction == Direction.vertical && startDirection == StartDirection.down) {
            if (unitTime < 0) setCurrZombieAnimation(zombieMoveUp);
            else setCurrZombieAnimation(zombieMoveDown);
        } else if (direction == Direction.horizontal && startDirection == StartDirection.left) {
            if (unitTime < 0) setCurrZombieAnimation(zombieMoveRight);
            else setCurrZombieAnimation(zombieMoveLeft);
        } else if (direction == Direction.horizontal && startDirection == StartDirection.right) {
            if (unitTime < 0) setCurrZombieAnimation(zombieMoveLeft);
            else setCurrZombieAnimation(zombieMoveRight);
        }


        setLocation(loc1.interpolate(loc2, t));
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
    }

    public void moveSquaredStartLeftRight(Point loc1, Point loc2, Point loc3, Point loc4, int timeLength) {
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() <= loc2.getX() && getY() <= loc1.getY())
            updateLinearMove(loc1, loc2, timeLength, 0, Direction.horizontal, StartDirection.left);
        if (getY() <= loc3.getY() && getX() >= loc2.getX())
            updateLinearMove(loc2, loc3, timeLength, 0, Direction.vertical, StartDirection.up);
        if (getX() >= loc4.getX() && getY() >= loc3.getY())
            updateLinearMove(loc3, loc4, timeLength, 0, Direction.horizontal, StartDirection.right);
        if (getY() >= loc1.getY() && getX() <= loc4.getX())
            updateLinearMove(loc4, loc1, timeLength, 0, Direction.vertical, StartDirection.down);
    }

    public void moveSquaredStartRightLeft(Point loc1, Point loc2, Point loc3, Point loc4) {
        if (getX() >= loc2.getX() && getY() <= loc1.getY()) moveLeft();
        if (getY() <= loc3.getY() && getX() <= loc2.getX()) moveDown();
        if (getX() <= loc4.getX() && getY() >= loc3.getY()) moveRight();
        if (getY() >= loc1.getY() && getX() >= loc4.getX()) moveUp();
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
    }

    public void moveDiagonalLeftRight(Point loc1, Point loc2) {
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() <= loc2.getX() && getY() <= loc2.getY() && condition) {
            moveDown();
            moveRight();
        } else {
            moveUp();
            moveLeft();
            condition = getX() <= loc1.getX() && getY() <= loc1.getY();
        }
    }

    public void moveDiagonalRightLeft(Point loc1, Point loc2) {
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
        if (getX() >= loc2.getX() && getY() <= loc2.getY() && condition) {
            moveDown();
            moveLeft();
        } else {
            moveUp();
            moveRight();
            condition = getX() >= loc1.getX() && getY() <= loc1.getY();
        }
    }

    public void stand(Animation animation) {
        setCurrZombieAnimation(animation);
        setLocation(getOriginalPosition());
        setHitBoxLocation(new Point(getX() + 30, getY() + 30));
    }

    private void moveRight() {
        setCurrZombieAnimation(zombieMoveRight);
        setLocation(new Point(getX() + getSpeed(), getY()));
    }

    private void moveLeft() {
        setCurrZombieAnimation(zombieMoveLeft);
        setLocation(new Point(getX() - getSpeed(), getY()));
    }

    private void moveUp() {
        setCurrZombieAnimation(zombieMoveUp);
        setLocation(new Point(getX(), getY() - getSpeed()));
    }

    private void moveDown() {
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

    public float getOriginalX() {
        return getOriginalPosition().getX();
    }

    public float getOriginalY() {
        return getOriginalPosition().getY();
    }

    public void setOriginalPosition(Point originalPosition) {
        this.originalPosition = originalPosition;
    }
}
