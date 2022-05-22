package Wireframe.src;

public class Point2D {
    private double x, y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point2D() {
        this.x = 0;
        this.y = 0;
    }

    public Point2D(Point2D p) {
        this.x = p.x;
        this.y = p.y;
    }

    public void set(Point2D p) {
        this.x = p.x;
        this.y = p.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getIntX() {
        return (int) x;
    }

    public int getIntY() {
        return (int) y;
    }

    public void add(Point2D point) {
        this.x += point.x;
        this.y += point.y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void mul(double a) {
        this.x *= a;
        this.y *= a;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }

    public void setY(double d) {
        y = d;
    }

    public void setX(double d) {
        x = d;
    }

}