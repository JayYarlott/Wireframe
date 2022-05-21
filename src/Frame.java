package Wireframe.src;

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
}
