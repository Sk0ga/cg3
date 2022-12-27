package com.company.method;

import com.company.Point;

import java.util.ArrayList;
import java.util.List;

//логика метода Б-сплайн
public class BSpline {

    private final int[] matrix1 = {-1, 3, -3, 1};
    private final int[] matrix2 = {3, -6, 0, 4};
    private final int[] matrix3 = {-3, 3, 3, 1};
    private final int[] matrix4 = {1, 0, 0, 0};

    List<Point> points;
    List<Point> addPoints = new ArrayList<>();

    public BSpline(List<Point> points) {
        this.points = points;
    }

    public void calculation() {
        for (int i = 1; i < points.size() - 2; i++) {
            for (double t = 0; t <= 1; t += 0.01) {
                double mult1 = mult(t, matrix1);
                double mult2 = mult(t, matrix2);
                double mult3 = mult(t, matrix3);
                double mult4 = mult(t, matrix4);

                double x0 = points.get(i - 1).getX() * mult1 + points.get(i).getX() * mult2
                        + points.get(i + 1).getX() * mult3 + points.get(i + 2).getX() * mult4;
                double y0 = points.get(i - 1).getY() * mult1 + points.get(i).getY() * mult2
                        + points.get(i + 1).getY() * mult3 + points.get(i + 2).getY() * mult4;

                addPoints.add(new Point(1/6.0 * x0, 1/6.0 * y0));
            }
        }
    }

    private double mult(double t, int[] matrix){
        return Math.pow(t, 3) * matrix[0] + Math.pow(t, 2) * matrix[1] + t * matrix[2] + matrix[3];
    }

    public List<Point> getAddPoints() {
        return addPoints;
    }
}
