package Wireframe.src;

import java.awt.event.*;

import javax.swing.Timer;

public class Main {
    public static void main(String[] args) {
        Renderer r = new Renderer();
        r.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyChar()) {
                    case 'w':
                        r.camera.add(0, .2, 0);
                        break;
                    case 'a':
                        r.camera.add(-.2, 0, 0);
                        break;
                    case 's':
                        r.camera.add(0, -.2, 0);
                        break;
                    case 'd':
                        r.camera.add(.2, 0, 0);
                        break;
                }
            }

            public void keyReleased(KeyEvent e) {

            }
        });

        Frame f = new Frame(ShapeLoader.getCube(2, 2, 2));

        // for (Triangle pi : f.tris) {
        // pi.mul(300);
        // }

        for (Triangle pi : f.tris)
            pi.translate(new Point3D(-1, -1, 10));

        ActionListener game = e -> {
            for (Triangle pi : f.tris) {
                pi.rotateY(new Point3D(0, 0, 11), 0.010);
                pi.rotateX(new Point3D(0, 0, 11), 0.050);
            }
            // for (Triangle pi : f.tris)
            // pi.rotateX(new Point3D(150, 150, 150), 0.020);
            r.render(f);
        };
        new Timer(14, game).start();
    }
}
