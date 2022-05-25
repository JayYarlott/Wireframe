package Wireframe.src;

import java.awt.event.*;

import javax.swing.Timer;

public class Main {
    public static void main(String[] args) {
        Renderer r = new Renderer();
        r.addKeyListener(new KeyAdapter() {
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
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                    case KeyEvent.VK_TAB:
                        r.pack();
                }
            }

        });

        Frame f = new Frame(
                ShapeLoader.getShape(
                        "C:/Users/Joseph/Documents/GitHub/WireFrame/Wireframe/resources/horse.obj"));

        // for (Triangle pi : f.tris) {
        // pi.mul(300);
        // }

        for (Triangle pi : f.tris)
            pi.translate(new Point3D(-1, -1, 10));

        ActionListener game = e -> {
            // for (Triangle pi : f.tris) {
            // pi.rotateY(new Point3D(0, 0, 11), 0.007);
            // pi.rotateX(new Point3D(0, 0, 11), 0.005);
            // }
            // for (Triangle pi : f.tris)
            // pi.rotateX(new Point3D(150, 150, 150), 0.020);
            r.render(f);
        };
        new Timer(12, game).start();
    }
}
