package Wireframe.src;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Renderer extends JFrame {

    Point3D camera = new Point3D();

    double aspectRatio;
    double fov = Math.PI / 2; // 90 degrees
    double F = 1 / (Math.tan(fov / 2));

    public Renderer() {

        setDefaultCloseOperation(3);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setSize(this.getMaximumSize());
        rpanel.setSize(rpanel.getMaximumSize());
        add(rpanel);
        rpanel.setVisible(true);
        setVisible(true);
        setIgnoreRepaint(true);
        createBufferStrategy(2);
        rpanel.repaint();
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

    JPanel rpanel = new JPanel();

    double dcpx = 0, dcpy = 0;

    public void render(Frame f) {

        var g = (Graphics2D) getBufferStrategy().getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Ortheographic projection

        g.setColor(Color.white);
        g.setStroke(new BasicStroke(3));

        f.sort();

        var tris = f.getTris();

        for (int i = 0; i < tris.length; i++) {
            g.setColor(Color.white);
            var t = tris[i];
            Point2D p1 = new Point2D((aspectRatio * F * t.points[0].getX()) / t.points[0].getZ(),
                    (F * t.points[0].getY()) / t.points[0].getZ());
            Point2D p2 = new Point2D((aspectRatio * F * t.points[1].getX()) / t.points[1].getZ(),
                    (F * t.points[1].getY()) / t.points[1].getZ());
            Point2D p3 = new Point2D((aspectRatio * F * t.points[2].getX()) / t.points[2].getZ(),
                    (F * t.points[2].getY()) / t.points[2].getZ());
            Point2D[] col = { p1, p2, p3 };
            // p1.add(100, 100);
            // p2.add(100, 100);
            // p3.add(100, 100);
            for (Point2D p : col)
                p.mul(getWidth() / 2);

            for (Point2D p : col)
                p.add(camera.getX(), camera.getY());

            var normal = t.cross();
            // if (normal.getX() * (t.points[0].getX() - camera.getX()) +
            // normal.getY() * (t.points[0].getY() - camera.getY()) +
            // normal.getZ() * (t.points[0].getZ() - camera.getZ()) < 0.0) {
            Point3D rel = new Point3D((t.points[0].getX()),
                    (t.points[0].getY()),
                    (t.points[0].getZ()));

            if (normal.dot(rel) < 0.0) {
                Point3D light = new Point3D(0, 0, -1);
                light.mul(1 / light.length());

                g.setColor(Color.getHSBColor(0, 0, (float) light.dot(normal)));
                g.fillPolygon(Triangle.toPolygon(p1, p2, p3));
                // g.drawPolygon(Triangle.toPolygon(p1, p2, p3));
                // g.drawLine(p1.getIntX(), p1.getIntY(), p2.getIntX(), p2.getIntY());
                // g.drawLine(p2.getIntX(), p2.getIntY(), p3.getIntX(), p3.getIntY());
                // g.drawLine(p3.getIntX(), p3.getIntY(), p1.getIntX(), p1.getIntY());

            }
        }

        g.dispose();
        getBufferStrategy().show();
        setVisible(true);

    }

}
