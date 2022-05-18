package Wireframe.src;

import java.awt.event.*;

import javax.swing.Timer;

public class Main {
    public static void main(String[] args) {
        Renderer r = new Renderer();
        r.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    System.exit(0);
            }

            public void keyPressed(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }
        });
        Point3D[] p = { new Point3D(0, 0, 0), new Point3D(0, 0, 100), new Point3D(100, 0, 100),
                new Point3D(100, 0, 0),
                new Point3D(0, 100, 0), new Point3D(0, 100, 100), new Point3D(100, 100, 100),
                new Point3D(100, 100, 0) };
        for (Point3D pi : p)
            pi.add(new Point3D(200, 200, 200));
        Frame f = new Frame(p[0], p[1], p[2], p[3], p[0], p[4], p[5], p[6], p[7], p[4], p[7], p[3], p[2], p[6],
                p[5], p[1]);
        ActionListener game = e -> {
            for (Point3D pi : p)
                pi.rotateZ(new Point3D(250, 250, 250), 0.010);
            for (Point3D pi : p)
                pi.rotateX(new Point3D(250, 250, 250), 0.020);
            r.render(f);
        };
        new Timer(14, game).start();
    }
}
