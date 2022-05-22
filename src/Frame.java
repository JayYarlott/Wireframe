package Wireframe.src;

import java.util.Arrays;

public class Frame {
    Triangle[] tris;

    public Frame(Triangle[] wires) {
        this.tris = wires;
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
