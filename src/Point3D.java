package Wireframe.src;

public class Point3D {
    private double x, y, z;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Point3D(Point3D p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public void set(Point3D p) {
        this.x = p.x;
        this.y = p.y;
        this.z = p.z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public int getIntX() {
        return (int) x;
    }

    public int getIntY() {
        return (int) y;
    }

    public int getIntZ() {
        return (int) z;
    }

    public void add(Point3D point) {
        this.x += point.x;
        this.y += point.y;
        this.z += point.z;
    }

    public void add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void mul(double a) {
        this.x *= a;
        this.y *= a;
        this.z *= a;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + ", " + z + "]";
    }

    public void setY(double d) {
        y = d;
    }

    public void setX(double d) {
        x = d;
    }

    public void setZ(double d) {
        z = d;
    }

    public void rotateZ(Point3D p, double angle) {
        x -= p.x;
        y -= p.y;
        double temp = x;
        x = (Math.cos(angle) * x - Math.sin(angle) * y + p.x);
        y = (Math.sin(angle) * temp + Math.cos(angle) * y + p.y);
    }

    public void rotateX(Point3D p, double angle) {
        y -= p.y;
        z -= p.z;
        double temp = y;
        y = (Math.cos(angle) * y - Math.sin(angle) * z + p.y);
        z = (Math.sin(angle) * temp + Math.cos(angle) * z + p.z);
    }

    public void rotateY(Point3D p, double angle) {
        z -= p.z;
        x -= p.x;
        double temp = z;
        z = (Math.cos(angle) * z - Math.sin(angle) * x + p.z);
        x = (Math.sin(angle) * temp + Math.cos(angle) * x + p.x);
    }

    public static double sin(double a) {
        return Math.sin(a);
    }

    public static double cos(double a) {
        return Math.cos(a);
    }
}