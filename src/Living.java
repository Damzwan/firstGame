public abstract class Living {
    private Point location;
    private float speed;

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
