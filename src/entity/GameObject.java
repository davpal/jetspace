package entity;

import org.newdawn.slick.GameContainer;
import rendering.Renderer;

import java.awt.*;

public abstract class GameObject {
    protected double x, y, dx, dy, speed, angle;
    protected int width, height, collisionWidth, collisionHeight;
    protected boolean left, right, up, down, hit;    
    protected boolean dead, crashing;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    protected GameObject(double d, double e, double a) {
        this.x = d;
        this.y = e;
        angle = a;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getCenterX() {
        return x + width / 2;
    }

    public double getCenterY() {
        return y + height / 2;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getAngle() {
        return angle;
    }

    public boolean isDead() {
        return dead;
    }

    public void kill() {
        crashing = false;
        dead = true;
    }

    public boolean isCrashing() {
        return crashing;
    }

    public void setCrashing() {
        crashing = true;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean h) {
        hit = h;
    }

    public boolean intersect(GameObject o) {
        Rectangle a = new Rectangle((int) x, (int) y, (int) collisionWidth,
                (int) collisionHeight);
        Rectangle b = new Rectangle((int) o.x, (int) o.y,
                (int) o.collisionWidth, (int) o.collisionHeight);
        return a.intersects(b);
    }

    protected void destroyOutside(GameContainer g){
        if((x - width) > g.getWidth() || (y - height) > g.getHeight()
            || (x + width) < 0 || (y + height) < 0)
            kill();
    }

    public abstract void update(GameContainer g);
    public abstract void render(Renderer r);
}
