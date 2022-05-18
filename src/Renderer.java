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
        aspectRatio = (double) getWidth() / (double) getHeight();
    }

    public void setCamera(Point3D camera) {
        this.camera = camera;
    }

    JPanel rpanel = new JPanel();

    double dcpx = 0, dcpy = 0;

    public void render(Frame f) {

        var g = getBufferStrategy().getDrawGraphics();
        g.clearRect(0, 0, getWidth(), getHeight());

        // Ortheographic projection

        g.setColor(Color.black);

        for (Line3D l : f.getLines()) {

            Point2D p1 = new Point2D((aspectRatio * F * l.p1.getX()) / l.p1.getZ(), (F * l.p1.getY()) / l.p1.getZ());
            Point2D p2 = new Point2D((aspectRatio * F * l.p2.getX()) / l.p2.getZ(), (F * l.p2.getY()) / l.p2.getZ());
            p1.mul(500);
            p2.mul(500);
            g.drawLine(p1.getIntX(), p1.getIntY(), p2.getIntX(), p2.getIntY());
        }

        g.dispose();
        getBufferStrategy().show();
        setVisible(true);

    }

}
