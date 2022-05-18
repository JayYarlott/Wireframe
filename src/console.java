package Wireframe.src;

import java.lang.reflect.Array;
import java.util.AbstractCollection;

/*
*  convience wrapper for console i/o
*/
public class console {

    public static final String RESET = "";// "\u001B[0m";
    public static final String BLACK = "";// ""\u001B[30m";
    public static final String RED = "";// ""\u001B[31m";
    public static final String GREEN = "";// ""\u001B[32m";
    public static final String YELLOW = "";// ""\u001B[33m";
    public static final String BLUE = "";// ""\u001B[34m";
    public static final String PURPLE = "";// ""\u001B[35m";
    public static final String CYAN = "";// ""\u001B[36m";
    public static final String WHITE = "";// ""\u001B[37m";

    public static void log(Object... objects) {
        int i = 0, length = objects.length;

        for (Object object : objects) {
            ++i;
            if (!object.getClass().isPrimitive() && object instanceof AbstractCollection) {
                log("works");
                for (Object o : ((AbstractCollection<?>) object).toArray())
                    log(o);
            }
            if (object.getClass().isArray()) {
                printArray(object);
            } else
                System.out.print(YELLOW + object + ((i != length) ? ", " : "") + RESET + ((i != length) ? "" : "\n"));
        }
    }

    public static void printArray(Object object) {
        System.out.print(GREEN + "[ ");
        for (int i = 0; i < Array.getLength(object); i++) {
            var temp = Array.get(object, i);
            if (temp.getClass().isArray()) {
                printArray(temp, 1);
                System.out.print(GREEN + ((i + 1 != Array.getLength(object)) ? ", \n" : "]") + RESET);
            }

            else {
                if (i != 0)
                    System.out.print(GREEN + ", ");
                else
                    System.out.print(GREEN);
                System.out.print(temp);
                if (i + 1 == Array.getLength(object))
                    System.out.print("]" + RESET);
            }
        }

    }

    public static void printArray(Object object, int num) {
        System.out.print(GREEN + "[ ");
        for (int i = 0; i < Array.getLength(object); i++) {
            var temp = Array.get(object, i);
            if (temp.getClass().isArray()) {
                printArray(temp, num + 1);
                System.out.print(GREEN + ((i + 1 != Array.getLength(object)) ? ", \n" : "]") + RESET);
            } else {
                if (i != 0)
                    System.out.print(GREEN + ", ");
                else
                    System.out.print(GREEN);
                System.out.print(temp);
                if (i + 1 == Array.getLength(object))
                    System.out.print("]" + RESET);
            }
        }

    }

    public static void error(String s) {
        log(RED + "ERROR: " + s + RESET);
    }
}