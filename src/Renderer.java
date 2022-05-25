package Wireframe.src;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.awt.*;

public class Renderer extends JFrame {

    Point3D camera = new Point3D();

    double aspectRatio;
    double fov = Math.PI / 2; // 90 degrees
    double F = 1 / (Math.tan(fov / 2));

    JPanel rpanel = new JPanel();

    public Renderer() {

        setDefaultCloseOperation(3);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setSize(this.getMaximumSize());
        rpanel.setSize(rpanel.getMaximumSize());
        add(rpanel);
        // rpanel.setVisible(true);
        setVisible(true);
        // setIgnoreRepaint(true);
        createBufferStrategy(2);
        // rpanel.repaint();
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                rpanel.repaint();
            }

        });
        camera = new Point3D(getWidth() / 2, getHeight() / 2, 0);
        aspectRatio = (double) getWidth() / (double) getHeight();
    }

    public void setCamera(Point3D camera) {
        this.camera = camera;
    }

    double dcpx = 0, dcpy = 0;
    Point3D light = new Point3D(0, 0, -1);

    public void render(Frame f) {
        light.rotateY(f.center, 0.01);
        long time = System.currentTimeMillis();
        var g = (Graphics2D) getBufferStrategy().getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Ortheographic projection

        g.setColor(Color.white);
        g.setStroke(new BasicStroke(3));

        f.sort();

        var tris = f.getTris();

        for (int i = 0; i < tris.length; i++) {
            var t = tris[i];
            Point2D p1 = new Point2D((aspectRatio * F * t.points[0].getX()) / t.points[0].getZ(),
                    (F * t.points[0].getY()) / t.points[0].getZ());
            Point2D p2 = new Point2D((aspectRatio * F * t.points[1].getX()) / t.points[1].getZ(),
                    (F * t.points[1].getY()) / t.points[1].getZ());
            Point2D p3 = new Point2D((aspectRatio * F * t.points[2].getX()) / t.points[2].getZ(),
                    (F * t.points[2].getY()) / t.points[2].getZ());

            Point2D[] col = { p1, p2, p3 };

            for (Point2D p : col)
                p.mul(getWidth() / 2);

            for (Point2D p : col)
                p.add(camera.getX(), camera.getY());

            var normal = t.cross();
            Point3D rel = new Point3D((t.points[0].getX()),
                    (t.points[0].getY()),
                    (t.points[0].getZ()));

            // if (normal.dot(rel) < 0.0) {

            light.mul(1 / light.length());

            g.setColor(Color.getHSBColor(0, 0, (float) Math.abs(light.dot(normal))));

            g.fillPolygon(Triangle.toPolygon(p1, p2, p3));

            // g.drawPolygon(Triangle.toPolygon(p1, p2, p3));

            // drawTriangle(col, g);
            // g.drawLine(p1.getIntX(), p1.getIntY(), p2.getIntX(), p2.getIntY());
            // g.drawLine(p2.getIntX(), p2.getIntY(), p3.getIntX(), p3.getIntY());
            // g.drawLine(p3.getIntX(), p3.getIntY(), p1.getIntX(), p1.getIntY());

            // }
        }

        g.dispose();
        getBufferStrategy().show();
        setVisible(true);
        console.log(System.currentTimeMillis() - time);
    }

    private void fillBottomFlatTriangle(Point2D v1, Point2D v2, Point2D v3, Graphics2D g) {
        double invslope1 = (v2.getX() - v1.getX()) / (v2.getY() - v1.getY());
        double invslope2 = (v3.getX() - v1.getX()) / (v3.getY() - v1.getY());

        double curx1 = v1.getX();
        double curx2 = v1.getX();

        for (int scanlineY = v1.getIntY(); scanlineY <= v2.getY(); scanlineY++) {
            g.drawLine((int) curx1, scanlineY, (int) curx2, scanlineY);
            curx1 += invslope1;
            curx2 += invslope2;
        }
    }

    public void fillTopFlatTriangle(Point2D v1, Point2D v2, Point2D v3, Graphics2D g) {
        double invslope1 = (v3.getX() - v1.getX()) / (v3.getY() - v1.getY());
        double invslope2 = (v3.getX() - v2.getX()) / (v3.getY() - v2.getY());

        double curx1 = v3.getX();
        double curx2 = v3.getX();

        for (int scanlineY = v3.getIntY(); scanlineY > v1.getY(); scanlineY--) {
            g.drawLine((int) curx1, scanlineY, (int) curx2, scanlineY);
            curx1 -= invslope1;
            curx2 -= invslope2;
        }
    }

    public void drawTriangle(Point2D[] points, Graphics2D g) {
        /*
         * at first sort the three vertices by y-coordinate ascending so v1 is the
         * topmost vertice
         */
        points = Arrays.stream(points).sorted((t1, t2) -> {
            return (t1.getY() < t2.getY()) ? -1 : (t1.getY() > t2.getY()) ? 1 : 0;
        }).toArray(Point2D[]::new);
        /* here we know that v1.y <= v2.y <= v3.y */
        /* check for trivial case of bottom-flat triangle */
        {
            /* general case - split the triangle in a topflat and bottom-flat one */
            Point2D v4 = new Point2D(
                    (points[0].getX() + ((points[1].getY() - points[0].getY())
                            / (points[2].getY() - points[0].getY()))
                            * (points[2].getX() - points[0].getX())),
                    points[1].getY());
            fillBottomFlatTriangle(points[0], points[1], v4, g);
            fillTopFlatTriangle(points[1], v4, points[2], g);
        }
    }

}
