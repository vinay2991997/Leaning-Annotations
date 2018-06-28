package math;

import docgen.Doc;

// @Doc(desc = "MathUtils Class for some basic maths function.")
@Doc
public class MathUtils {

    public static double PI = 3.14;

    @Doc(
            desc = "calculates the area of the rectangle",
            params = {"lenght of the rectangle", "Breadth of the rectangle"}
    )
    public static int area(int lenght, int breadth) {
        return 0;
    }

    @Doc
    public static double distance(Point a, Point b) {

        return 0.0;
    }

    @Doc(desc = "Print the value of PI")
    public void printPI() {
        System.out.println(PI);
    }

    private static int privateMethod() {
        return 0;
    }
}
