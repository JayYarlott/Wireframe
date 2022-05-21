package Wireframe.src;

public class Polygon {
    Point2D[] points;

    public int[] getXPoints() {
        int[] ret = new int[points.length];
        for (int i = 0; i < points.length; i++)
            ret[i] = points[i].getIntX();
        return ret;
    }

    public int[] getYPoints() {
        int[] ret = new int[points.length];
        for (int i = 0; i < points.length; i++)
            ret[i] = points[i].getIntY();
        return ret;
    }
}
