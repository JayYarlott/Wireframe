package Wireframe.src;

import java.util.Arrays;

public class Frame {
    Triangle[] tris;
    Point3D center = new Point3D();

    public Frame(Triangle[] wires) {
        this.tris = wires;
        long num = 0;
        for (Triangle pi : tris)
            for (Point3D point : pi.points) {
                center.add(point);
                num++;
            }
        center.mul((double) 1 / num);
    }

    public Triangle[] getTris() {
        return tris;
    }

    public void rotateZ(Point3D ra, double d) {
        for (int i = 0; i < tris.length; i++) {
            tris[i].rotateZ(ra, d);
        }
    }

    public void sort() {
        tris = Arrays.stream(tris).sorted((t1, t2) -> {
            return (t1.getZ() < t2.getZ()) ? 1 : (t1.getZ() > t2.getZ()) ? -1 : 0;
        }).toArray(Triangle[]::new);
    }
}
