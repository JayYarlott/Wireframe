package Wireframe.src;

public class Frame {
    Line3D[] wires;

    public Frame(Line3D[] wires) {
        this.wires = wires;
    }

    public Frame(Point3D... points) {
        if ((points.length < 2))
            System.exit(1);
        wires = new Line3D[points.length - 1];
        for (int i = 1; i < points.length; i++) {
            wires[i - 1] = new Line3D(points[i - 1], points[i]);
        }
    }

    public Line3D[] getLines() {
        return wires;
    }

    public void rotateZ(Point3D ra, double d) {
        for (int i = 0; i < wires.length; i++) {
            wires[i].rotateZ(ra, d);
        }
    }
}
