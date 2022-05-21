package Wireframe.src;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ShapeLoader {

    public static Triangle[] getCube(int width, int height, int depth) {
        Point3D[] vertices = null;
        Triangle[] result = null;
        try (Scanner s = new Scanner(
                new FileReader("C:\\Users\\Joseph\\Documents\\GitHub\\WireFrame\\Wireframe\\data\\Cube.dat"))) {
            // convience crutch
            vertices = new Point3D[s.nextInt()];
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = new Point3D(s.nextInt() * width, s.nextInt() * height, s.nextInt() * depth);
            }
            result = new Triangle[s.nextInt()];
            for (int i = 0; i < result.length; i++) {
                result[i] = new Triangle(vertices[s.nextInt()], vertices[s.nextInt()], vertices[s.nextInt()]);
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
