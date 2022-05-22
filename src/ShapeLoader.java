package Wireframe.src;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ShapeLoader {

    public static Triangle[] getCube() {
        Point3D[] vertices = null;
        Triangle[] result = null;
        try (Scanner s = new Scanner(
                new FileReader("C:\\Users\\Joseph\\Documents\\GitHub\\WireFrame\\Wireframe\\data\\Cube.dat"))) {
            // convience crutch
            vertices = new Point3D[s.nextInt()];
            for (int i = 0; i < vertices.length; i++) {
                vertices[i] = new Point3D(s.nextInt(), s.nextInt(), s.nextInt());
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

    public static Triangle[] getShape(String fileName) {
        ArrayList<Point3D> points = new ArrayList<Point3D>(3644);
        ArrayList<Triangle> result = new ArrayList<>();
        try (var f = new FileInputStream(fileName)) {
            // convience crutch
            String data = new String(f.readAllBytes());

            Scanner s = new Scanner(new StringReader(data));

            while (s.hasNext()) {
                switch (s.next()) {
                    case "v":
                        points.add(new Point3D(s.nextFloat(), s.nextFloat(), s.nextFloat()));
                        break;
                    case "f":
                        result.add(new Triangle(points.get(s.nextInt() - 1), points.get(s.nextInt() - 1),
                                points.get(s.nextInt() - 1)));
                        break;
                    case "s":
                        s.nextLine();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.stream().toArray(Triangle[]::new);
    }

    public static void main(String[] args) {
        getShape("C:/Users/Joseph/Documents/GitHub/WireFrame/Wireframe/data/teapot.obj");
    }
}
