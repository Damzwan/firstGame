import org.newdawn.slick.geom.Shape;

public abstract class Living {
    private Point location;
    private float speed;
    private Shape hitBox;

    public Shape getHitBox() {
        return hitBox;
    }

    public void setHitBox(Shape hitBox){
        this.hitBox = hitBox;
    }

    public void setHitBoxLocation(Point location) {
        hitBox.setCenterX(location.getX());
        hitBox.setCenterY(location.getY());
    }

    public Point getLocation() {
        return location;
    }

    public float getX(){
        return getLocation().getX();
    }

    public float getY(){
        return getLocation().getY();
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
