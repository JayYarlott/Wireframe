package Wireframe.src;

public class Line3D {
    Point3D p1, p2;

    public Line3D(Point3D p1, Point3D p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void rotateZ(Point3D p, double angle) {
        p1.rotateZ(p, angle);
        p2.rotateZ(p, angle);
    }

    public void rotateX(Point3D p, double angle) {
        p1.rotateX(p, angle);
        p2.rotateX(p, angle);
    }

}
