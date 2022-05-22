package Wireframe.src;

import java.awt.Polygon;
import java.util.Arrays;

public class Triangle {
    Point3D[] points = new Point3D[3];

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        points[0] = new Point3D(p1);
        points[1] = new Point3D(p2);
        points[2] = new Point3D(p3);
    }

    public Triangle(Point2D p1, Point2D p2, Point2D p3) {
        points[0] = new Point3D(p1);
        points[1] = new Point3D(p2);
        points[2] = new Point3D(p3);
    }

    public void translate(Point3D p) {
        for (Point3D point : points) {
            point.add(p);
        }
    }

    public void rotateZ(Point3D ra, double d) {
        points[0].rotateZ(ra, d);
        points[1].rotateZ(ra, d);
        points[2].rotateZ(ra, d);
    }

    public void rotateX(Point3D ra, double d) {
        points[0].rotateX(ra, d);
        points[1].rotateX(ra, d);
        points[2].rotateX(ra, d);
    }

    public void rotateY(Point3D ra, double d) {
        points[0].rotateY(ra, d);
        points[1].rotateY(ra, d);
        points[2].rotateY(ra, d);
    }

    public Point3D cross() {
        var p1 = new Point3D(points[1].getX() - points[0].getX(), points[1].getY() - points[0].getY(),
                points[1].getZ() - points[0].getZ());
        var p2 = new Point3D(points[2].getX() - points[0].getX(), points[2].getY() - points[0].getY(),
                points[2].getZ() - points[0].getZ());
        var ret = new Point3D(
                p1.getY() * p2.getZ() - p1.getZ() * p2.getY(),
                p1.getZ() * p2.getX() - p1.getX() * p2.getZ(),
                p1.getX() * p2.getY() - p1.getY() * p2.getX());
        ret.mul(1 / ret.length());
        return ret;
    }

    public void mul(double scale) {
        points[0].mul(scale);
        points[1].mul(scale);
        points[2].mul(scale);
    }

    public double getZ() {
        return (points[0].getZ() + points[1].getZ() + points[2].getZ()) / 3;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("[");
        ret.append(points[0].toString() + ", ");
        ret.append(points[1].toString() + ", ");
        ret.append(points[2].toString() + "]");
        return ret.toString();
    }

    public static Polygon toPolygon(Point2D... points) {
        return new Polygon(Arrays.stream(points).mapToInt(a -> a.getIntX()).toArray(),
                Arrays.stream(points).mapToInt(a -> a.getIntY()).toArray(), points.length);
    }
}
