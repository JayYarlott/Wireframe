package Wireframe.src;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static boolean gameOver = false;
    public static int answeredRight = 0;
    static Random rand = new Random();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (gameOver == false) {

            int firstNumber = rand.nextInt((100 - 50) + 1) + 1;
            int secondNumber = rand.nextInt((100 - 50) + 1) + 1;
            int correctAnswer = firstNumber + secondNumber;

            try {
                System.out.println("What is " + firstNumber + " + " + secondNumber + "?");

                while (!input.hasNext())
                    ;
                int userAnswer = input.nextInt();

                if (userAnswer == correctAnswer) {
                    System.out.println("Correct!");
                }

                else {
                    System.out.print("Sorry, that is the incorrect answer. The correct answer is " + correctAnswer);
                    gameOver = true;
                }

            } catch (Exception e) {

            }
        }
        input.close();
    }
}
/*
 * import java.awt.event.*;
 * 
 * import javax.swing.Timer;
 * 
 * public class Main {
 * public static void main(String[] args) {
 * Renderer r = new Renderer();
 * r.addKeyListener(new KeyAdapter() {
 * public void keyPressed(KeyEvent e) {
 * switch (e.getKeyChar()) {
 * case 'w':
 * r.camera.add(0, .2, 0);
 * break;
 * case 'a':
 * r.camera.add(-.2, 0, 0);
 * break;
 * case 's':
 * r.camera.add(0, -.2, 0);
 * break;
 * case 'd':
 * r.camera.add(.2, 0, 0);
 * break;
 * case KeyEvent.VK_ESCAPE:
 * System.exit(0);
 * }
 * }
 * 
 * });
 * 
 * Frame f = new Frame(
 * ShapeLoader.getShape(
 * "C:/Users/Joseph/Documents/GitHub/WireFrame/Wireframe/resources/teapot.obj"))
 * ;
 * 
 * // for (Triangle pi : f.tris) {
 * // pi.mul(300);
 * // }
 * 
 * for (Triangle pi : f.tris)
 * pi.translate(new Point3D(-1, -1, 10));
 * 
 * ActionListener game = e -> {
 * for (Triangle pi : f.tris) {
 * pi.rotateY(new Point3D(0, 0, 11), 0.007);
 * pi.rotateX(new Point3D(0, 0, 11), 0.005);
 * }
 * // for (Triangle pi : f.tris)
 * // pi.rotateX(new Point3D(150, 150, 150), 0.020);
 * r.render(f);
 * };
 * new Timer(12, game).start();
 * }
 * }
 */